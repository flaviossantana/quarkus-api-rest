package io.udemy.quarkus.resource;

import io.udemy.quarkus.core.JsonMediaTypeApplications;
import io.udemy.quarkus.dto.UsuarioDto;
import io.udemy.quarkus.model.Usuario;
import io.udemy.quarkus.repository.UsuarioRepository;
import io.udemy.quarkus.validator.ResponseError;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Optional;
import java.util.Set;

@Path("/usuarios")
public class UsuariosResource implements JsonMediaTypeApplications {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    Validator validator;

    @GET
    public Response listarTodosUsuarios() {
        return Response.ok(this.usuarioRepository.listAll()).build();
    }

    @POST
    @Transactional
    public Response criarUsuario(UsuarioDto dto) {

        Set<ConstraintViolation<UsuarioDto>> validate = this.validator.validate(dto);

        if (!validate.isEmpty()) {
            return ResponseError
                    .of(validate)
                    .status(ResponseError.UNPROCESSABLE_ENTITY);
        }


        Usuario usuario = new Usuario(dto.getNome(), dto.getIdade());

        // PERSISTINDO USANDO PANACHE
        //usuario.persist();

        this.usuarioRepository.persist(usuario);

        return Response
                .status(Response.Status.CREATED)
                .entity(usuario)
                .build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response excluirUsuario(@PathParam("id") Long id) {

        Optional<Usuario> usuarioOptional = Usuario.findByIdOptional(id);

        if (usuarioOptional.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        usuarioOptional.get().delete();
        return Response.noContent().build();

    }

    @PUT
    @Transactional
    @Path("{id}")
    public Response atualizarUsuario(@PathParam("id") Long id, UsuarioDto dto) {
        Optional<Usuario> usuarioOptional = Usuario.findByIdOptional(id);

        if (usuarioOptional.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        Usuario usuario = usuarioOptional.get();
        usuario.setNome(dto.getNome());
        usuario.setIdade(dto.getIdade());

        return Response.noContent().build();
    }

}
