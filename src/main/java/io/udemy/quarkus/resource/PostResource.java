package io.udemy.quarkus.resource;


import io.quarkus.panache.common.Sort;
import io.udemy.quarkus.core.JsonMediaTypeApplications;
import io.udemy.quarkus.dto.PostRequestDto;
import io.udemy.quarkus.dto.PostResponseDto;
import io.udemy.quarkus.model.Post;
import io.udemy.quarkus.model.Usuario;
import io.udemy.quarkus.repository.PostRepository;
import io.udemy.quarkus.repository.UsuarioRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/usuarios/{userId}/posts")
public class PostResource implements JsonMediaTypeApplications {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    PostRepository  postRepository;

    @POST
    @Transactional
    public Response salvarPost(@PathParam("userId") Long userId, PostRequestDto dto) {

        Optional<Usuario> usuarioOptional = this.usuarioRepository.findByIdOptional(userId);

        if(usuarioOptional.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Post post = new Post(dto.getTexto(), usuarioOptional.get());

        this.postRepository.persist(post);

        return Response
                .status(Response.Status.CREATED)
                .entity(post)
                .build();
    }

    @GET
    public Response listarTodos(@PathParam("userId") Long userId) {

        List<Post> posts = this.postRepository.list("usuario.id", Sort.descending("dataCriacao"), userId);

        if(posts.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        return Response
                .ok(posts
                        .stream()
                        .map(PostResponseDto::from)
                        .collect(Collectors.toList()))
                .build();
    }

}
