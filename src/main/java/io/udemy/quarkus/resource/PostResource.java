package io.udemy.quarkus.resource;


import io.udemy.quarkus.core.JsonMediaTypeApplications;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/usuarios/{userId}/posts")
public class PostResource implements JsonMediaTypeApplications {

    @POST
    public Response salvarPost(@PathParam("userId") Long userId) {
        return Response.ok().build();
    }

    @GET
    public Response listarTodos() {
        return Response.ok().build();
    }

}
