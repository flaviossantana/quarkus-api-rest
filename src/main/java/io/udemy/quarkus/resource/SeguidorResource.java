package io.udemy.quarkus.resource;

import io.udemy.quarkus.core.JsonMediaTypeApplications;
import io.udemy.quarkus.repository.SeguidorRepository;
import io.udemy.quarkus.repository.UsuarioRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


@Path("/usuarios/{userId}/seguidores")
public class SeguidorResource implements JsonMediaTypeApplications {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    SeguidorRepository seguidorRepository;

    @GET
    public Response listarTodos(){
        return Response.ok().build();
    }

}
