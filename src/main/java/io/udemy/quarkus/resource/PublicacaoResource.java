package io.udemy.quarkus.resource;


import io.quarkus.panache.common.Sort;
import io.udemy.quarkus.core.JsonMediaTypeApplications;
import io.udemy.quarkus.dto.PublicacaoRequestDto;
import io.udemy.quarkus.dto.PublicacaoResponseDto;
import io.udemy.quarkus.model.Publicacao;
import io.udemy.quarkus.model.Usuario;
import io.udemy.quarkus.repository.PublicacaoRepository;
import io.udemy.quarkus.repository.SeguidorRepository;
import io.udemy.quarkus.repository.UsuarioRepository;
import io.udemy.quarkus.validator.FieldError;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/usuarios/{userId}/publicacoes")
public class PublicacaoResource implements JsonMediaTypeApplications {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    PublicacaoRepository publicacaoRepository;

    @Inject
    SeguidorRepository seguidorRepository;

    @POST
    @Transactional
    public Response salvarPublicacoes(@PathParam("userId") Long userId, PublicacaoRequestDto dto) {

        Optional<Usuario> usuarioOptional = this.usuarioRepository.findByIdOptional(userId);

        if(usuarioOptional.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Publicacao publicacao = new Publicacao(dto.getTexto(), usuarioOptional.get());

        this.publicacaoRepository.persist(publicacao);

        return Response
                .status(Response.Status.CREATED)
                .entity(publicacao)
                .build();
    }

    @GET
    public Response listarTodos(@PathParam("userId") Long userId, @HeaderParam("seguidorId") Long seguidorId) {

        if(this.seguidorRepository.isNaoESeguidor(seguidorId, userId)){
            return Response
                    .status(Response.Status.FORBIDDEN)
                    .entity(new FieldError("seguidorId", "Você não pode ver essa publicação"))
                    .build();
        }


        List<Publicacao> publicacaos = this.publicacaoRepository
                .list("usuario.id", Sort.descending("dataCriacao"), userId);

        if(publicacaos.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        return Response
                .ok(publicacaos
                        .stream()
                        .map(PublicacaoResponseDto::from)
                        .collect(Collectors.toList()))
                .build();
    }

}
