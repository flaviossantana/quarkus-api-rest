package io.udemy.quarkus.resource;

import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.udemy.quarkus.dto.UsuarioDto;
import io.udemy.quarkus.validator.ResponseError;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import java.net.URL;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;


@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsuariosResourceTest {

    @TestHTTPResource("/usuarios")
    URL urlUsuario;

    @Test
    @Order(value = 1)
    @DisplayName("Criar um usuário: STATUS 201")
    public void criarUsuario() {
        given()
                .contentType(ContentType.JSON)
                .body(new UsuarioDto("João Biosco", 30))
                .when()
                .post(urlUsuario)
                .then()
                .statusCode(201)
                .body("nome", is("João Biosco"))
                .body("idade", is(30));

    }

    @Test
    @Order(value = 2)
    @DisplayName("Criar um usuário: STATUS 422")
    public void criarUsuarioComNomeNulo() {
        given()
                .contentType(ContentType.JSON)
                .body(new UsuarioDto(null, null))
                .when()
                .post(urlUsuario)
                .then()
                .statusCode(ResponseError.UNPROCESSABLE_ENTITY)
                .body("message", is("Validation error"))
                .body("errors.get(0).get(\"message\")", notNullValue())
                .body("errors.get(1).get(\"message\")", notNullValue());
    }

    @Test
    @Order(value = 3)
    @DisplayName("Listar todos os usuários: STATUS 200")
    public void listarTodosUsuarios() {
        given()
                .when()
                .get(urlUsuario)
                .then()
                .statusCode(200)
                .body("size()", Matchers.is(3));
    }


}
