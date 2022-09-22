package io.udemy.quarkus.resource;

import io.udemy.quarkus.dto.UsuarioDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuariosResource {

    @POST
    public Response criarUsuario(UsuarioDto usuarioDto) {
        return Response.ok(usuarioDto).build();
    }

    @GET
    public Response listarTodosUsuarios() {
        return Response.ok().build();
    }


}
