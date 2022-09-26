package io.udemy.quarkus.resource;

import io.udemy.quarkus.dto.UsuarioDto;
import io.udemy.quarkus.model.Usuario;
import io.udemy.quarkus.repository.UsuarioRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuariosResource {

    @Inject
    UsuarioRepository usuarioRepository;

    @GET
    public Response listarTodosUsuarios() {
        return Response.ok(this.usuarioRepository.listAll()).build();
    }

    @POST
    @Transactional
    public Response criarUsuario(UsuarioDto dto) {

        Usuario usuario = new Usuario(dto.getNome(), dto.getIdade());

        // PERSISTINDO USANDO PANACHE
        //usuario.persist();

        this.usuarioRepository.persist(usuario);

        return Response.ok(usuario).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response excluirUsuario(@PathParam("id") Long id) {

        Optional<Usuario> usuarioOptional = Usuario.findByIdOptional(id);

        if(usuarioOptional.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        usuarioOptional.get().delete();
        return Response.ok().build();

    }

    @PUT
    @Transactional
    @Path("{id}")
    public Response atualizarUsuario(@PathParam("id") Long id, UsuarioDto dto) {
        Optional<Usuario> usuarioOptional = Usuario.findByIdOptional(id);

        if(usuarioOptional.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        Usuario usuario = usuarioOptional.get();
        usuario.setNome(dto.getNome());
        usuario.setIdade(dto.getIdade());

        return Response.ok().build();
    }

}
