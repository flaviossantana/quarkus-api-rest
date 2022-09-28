package io.udemy.quarkus.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.udemy.quarkus.dto.UsuarioDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;


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

}
