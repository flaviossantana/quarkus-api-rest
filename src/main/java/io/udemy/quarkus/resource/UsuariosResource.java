package io.udemy.quarkus.resource;

import io.udemy.quarkus.dto.UsuarioDto;
import io.udemy.quarkus.model.Usuario;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuariosResource {

    @POST
    @Transactional
    public Response criarUsuario(UsuarioDto dto) {

        Usuario usuario = new Usuario(dto.getNome(), dto.getIdade());
        usuario.persist();

        return Response.ok(usuario).build();
    }

    @GET
    public Response listarTodosUsuarios() {
        return Response.ok(Usuario.listAll()).build();
    }

}
