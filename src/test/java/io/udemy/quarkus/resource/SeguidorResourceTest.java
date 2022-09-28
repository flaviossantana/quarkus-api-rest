package io.udemy.quarkus.resource;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.udemy.quarkus.dto.SeguidorRequestDto;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestHTTPEndpoint(SeguidorResource.class)
class SeguidorResourceTest {

    Long idUsuarioInexistente = 999999999L;

    @Test
    @DisplayName("Tentar Seguir ele mesmo")
    public void testSeguirEleMesmo() {
                given()
                        .contentType(ContentType.JSON)
                        .pathParams("userId", 100)
                        .body(new SeguidorRequestDto(100l))
                        .put()
                        .then()
                        .statusCode(HttpStatus.SC_CONFLICT);
    }

    @Test
    @DisplayName("Seguidor ou Usuario não encontrado")
    public void naoEncontrouSeguidorOuUSuario() {
        given()
                .contentType(ContentType.JSON)
                .pathParams("userId", 1000)
                .body(new SeguidorRequestDto(2000l))
                .put()
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @DisplayName("Seguir Usuario")
    public void testSeguirUsuario() {
        given()
                .contentType(ContentType.JSON)
                .pathParams("userId", 100)
                .body(new SeguidorRequestDto(200l))
                .put()
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @DisplayName("Seguidor usuário inexistente")
    public void testeDeixarDeSeguirUsuarioInexistente(){
        given()
                .contentType(ContentType.JSON)
                .pathParams("userId", idUsuarioInexistente)
                .body(new SeguidorRequestDto(200l))
                .delete()
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @DisplayName("Deixar de seguir usuário")
    public void testDeixarDeSeguirUsuario(){
        given()
                .contentType(ContentType.JSON)
                .pathParams("userId", 100)
                .body(new SeguidorRequestDto(200l))
                .delete()
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    @DisplayName("Buscar seguidores de um usuário")
    public void testBuscarTodosSeguidores(){
        given()
                .contentType(ContentType.JSON)
                .pathParams("userId", 100)
                .get()
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("Buscar seguidores de um usuário inexistente")
    public void testBuscarSeguidorUsuarioInexistente(){
        given()
                .contentType(ContentType.JSON)
                .pathParams("userId", idUsuarioInexistente)
                .get()
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

}
