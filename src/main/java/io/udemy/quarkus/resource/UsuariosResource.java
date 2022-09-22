package io.udemy.quarkus.resource;

import io.udemy.quarkus.dto.UsuarioDto;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/usuarios")
public class UsuariosResource {

    @POST
    public Response criarUsuario(UsuarioDto usuarioDto) {
        return Response.status(Response.Status.CREATED).build();
    }

}
