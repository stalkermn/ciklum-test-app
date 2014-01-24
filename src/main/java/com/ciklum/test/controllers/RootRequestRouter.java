package com.ciklum.test.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 */
public class RootRequestRouter {
    @GET
    @Path("/")
    public Response helloWorld(){
        return Response.ok().entity("Hello world message").build();
    }
}
