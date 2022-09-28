package io.udemy.quarkus.resource;

import io.udemy.quarkus.core.JsonMediaTypeApplications;
import io.udemy.quarkus.dto.SeguidorRequestDto;
import io.udemy.quarkus.dto.SeguidorResponseDto;
import io.udemy.quarkus.dto.SeguidoresPorUsuarioResponseDto;
import io.udemy.quarkus.model.Seguidor;
import io.udemy.quarkus.model.Usuario;
import io.udemy.quarkus.repository.SeguidorRepository;
import io.udemy.quarkus.repository.UsuarioRepository;
import io.udemy.quarkus.validator.FieldError;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Path("/usuarios/{userId}/seguidores")
public class SeguidorResource implements JsonMediaTypeApplications {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    SeguidorRepository seguidorRepository;

    @GET
    public Response todos(@PathParam("userId") Long userId) {

        List<Seguidor> seguidores = this.seguidorRepository.buscarPorUsuario(userId);

        if(seguidores.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        List<SeguidorResponseDto> seguidoresDTO = seguidores
                .stream()
                .map(SeguidorResponseDto::new)
                .collect(Collectors.toList());

        return Response.ok(new SeguidoresPorUsuarioResponseDto(seguidoresDTO)).build();
    }

    @PUT
    @Transactional
    public Response seguirUsuario(@PathParam("userId") Long userId, SeguidorRequestDto dto) {

        if(userId.equals(dto.getIdSeguido())){
            return Response
                    .status(Response.Status.CONFLICT)
                    .entity(new FieldError("idSeguido", "Você não pode seguir a si mesmo"))
                    .build();
        }

        Optional<Usuario> usuarioOptional = this.usuarioRepository
                .findByIdOptional(userId);

        Optional<Usuario> seguidorOptional = this.usuarioRepository
                .findByIdOptional(dto.getIdSeguido());

        if (usuarioOptional.isEmpty() || seguidorOptional.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if(this.seguidorRepository.isNaoESeguidor(seguidorOptional.get(), usuarioOptional.get())){
            this.seguidorRepository
                    .persist(new Seguidor(usuarioOptional.get(), seguidorOptional.get()));
        }

        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
