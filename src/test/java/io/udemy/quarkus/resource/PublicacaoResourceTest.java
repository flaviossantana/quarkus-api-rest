package io.udemy.quarkus.resource;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.udemy.quarkus.dto.PublicacaoRequestDto;
import io.udemy.quarkus.model.Seguidor;
import io.udemy.quarkus.model.Usuario;
import io.udemy.quarkus.repository.SeguidorRepository;
import io.udemy.quarkus.repository.UsuarioRepository;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestHTTPEndpoint(PublicacaoResource.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PublicacaoResourceTest {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    SeguidorRepository seguidorRepository;

    Long joaoBioscoID;
    Long idUsuarioInexistente = 999999999L;

    @BeforeEach
    @Transactional
    public void antesDeCadaTesteExecute() {
        vincularSeguidorEUsuario(criarUsuarioJoaoBiosco());
    }

    private void vincularSeguidorEUsuario(Usuario joaoBiosco) {
        Usuario flavioSantana = this.usuarioRepository.findById(100l);
        this.seguidorRepository.persist(new Seguidor(joaoBiosco, flavioSantana));
    }

    private Usuario criarUsuarioJoaoBiosco() {
        Usuario joaoBiosco = new Usuario("João Biosco", 30);
        this.usuarioRepository.persist(joaoBiosco);
        this.joaoBioscoID = joaoBiosco.getId();
        return joaoBiosco;
    }

    @Test
    @Order(value = 1)
    @DisplayName("Criar publicação: STATUS 200")
    public void criarPublicacao() {
        given()
                .contentType(ContentType.JSON)
                .body(new PublicacaoRequestDto("Criando uma publicação de teste!"))
                .pathParams("userId", joaoBioscoID)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.SC_CREATED);

    }

    @Test
    @Order(value = 2)
    @DisplayName("Criar publicação para usuário inexistente: STATUS 404")
    public void criarPublicacaoSemUsuario() {
        given().contentType(ContentType.JSON)
                .body(new PublicacaoRequestDto("Criando uma publicação de teste!"))
                .pathParams("userId", idUsuarioInexistente)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @Order(value = 3)
    @DisplayName("Não lista post por não seguir o usuário: SATUS 403")
    public void listarPublicacoesNaoPermitida() {
        given()
                .contentType(ContentType.JSON)
                .pathParams("userId", idUsuarioInexistente)
                .header("seguidorId", 100)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .body("message", Matchers.is("Você não pode ver essa publicação"));
    }

    @Test
    @Order(value = 4)
    @DisplayName("Não encontrar postagem para o usuario informado")
    public void usuarioSemPublicacoesPostada() {
        given()
                .contentType(ContentType.JSON)
                .pathParams("userId", joaoBioscoID)
                .header("seguidorId", 100)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @Order(value = 5)
    @DisplayName("Listar publicações: STATUS 200")
    public void listarPublicacoes() {
        given()
                .contentType(ContentType.JSON)
                .pathParams("userId", 200)
                .header("seguidorId", 100)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", Matchers.is(2));
    }

}
