package io.udemy.quarkus.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.udemy.quarkus.dto.UsuarioDto;
import io.udemy.quarkus.validator.ResponseError;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;


@QuarkusTest
class UsuariosResourceTest {

    @Test
    @DisplayName("Criar um usuário: STATUS 201")
    public void criarUsuario() {
        given()
                .contentType(ContentType.JSON)
                .body(new UsuarioDto("João Biosco", 30))
                .when()
                .post("/usuarios")
                .then()
                .statusCode(201)
                .body("nome", is("João Biosco"))
                .body("idade", is(30));

    }

    @Test
    @DisplayName("Criar um usuário: STATUS 422")
    public void criarUsuarioComNomeNulo() {
        given()
                .contentType(ContentType.JSON)
                .body(new UsuarioDto(null, null))
                .when()
                .post("/usuarios")
                .then()
                .statusCode(ResponseError.UNPROCESSABLE_ENTITY)
                .body("message", is("Validation error"))
                .body("errors.get(0).get(\"message\")", is("Nome é obrigatório"))
                .body("errors.get(1).get(\"message\")", is("Idade é obrigatório"));
    }

}
