package io.udemy.quarkus.resource;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.udemy.quarkus.dto.PublicacaoRequestDto;
import io.udemy.quarkus.model.Usuario;
import io.udemy.quarkus.repository.UsuarioRepository;
import org.apache.http.HttpStatus;
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

    Long usuarioId;

    @BeforeEach
    @Transactional
    public void antesDeCadaTesteExecute() {
        Usuario usuario = new Usuario("João Biosco", 30);
        this.usuarioRepository.persist(usuario);
        this.usuarioId = usuario.getId();
    }

    @Test
    @DisplayName("Criar publicação: STATUS 200")
    public void criarPublicacao() {
        given()
                .contentType(ContentType.JSON)
                .body(new PublicacaoRequestDto("Criando uma publicação de teste!"))
                .pathParams("userId", usuarioId)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.SC_CREATED);

    }

}
