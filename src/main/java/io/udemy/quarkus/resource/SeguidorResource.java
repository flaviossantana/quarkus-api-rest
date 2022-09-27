package io.udemy.quarkus.resource;

import io.udemy.quarkus.core.JsonMediaTypeApplications;
import io.udemy.quarkus.dto.SeguidorRequestDto;
import io.udemy.quarkus.model.Seguidor;
import io.udemy.quarkus.model.Usuario;
import io.udemy.quarkus.repository.SeguidorRepository;
import io.udemy.quarkus.repository.UsuarioRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.Optional;


@Path("/usuarios/{userId}/seguidores")
public class SeguidorResource implements JsonMediaTypeApplications {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    SeguidorRepository seguidorRepository;

    @PUT
    public Response seguirUsuario(@PathParam("userId") Long userId, SeguidorRequestDto dto) {

        Optional<Usuario> usuarioOptional = this.usuarioRepository.findByIdOptional(userId);

        if (usuarioOptional.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Optional<Usuario> seguidorOptional = this.usuarioRepository
                .findByIdOptional(dto.getIdSeguido());


        if (seguidorOptional.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        this.seguidorRepository
                .persist(new Seguidor(usuarioOptional.get(), seguidorOptional.get()));

        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
