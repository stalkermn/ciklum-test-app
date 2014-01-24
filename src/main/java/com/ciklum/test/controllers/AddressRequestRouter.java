package com.ciklum.test.controllers;

import com.ciklum.test.models.Address;
import com.ciklum.test.models.Phone;
import com.ciklum.test.service.AddressManagerService;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Set;

/**
 *
 */
@Path("address/{id}")
public class AddressRequestRouter {
    private AddressManagerService service;

    public AddressRequestRouter(AddressManagerService service){
        this.service = service;
    }

    @GET
    public Response getAddress(@PathParam("id") String id){
        return Response.ok().entity(service.getAddress(id)).build();
    }

    @POST
    public Response addAddress(@Valid Address address){
        return Response.ok().entity(service.addAddress(address)).build();
    }

    @PUT
    public Response updateAddress(@Valid Address address, @PathParam("id") String id){
        return Response.ok().entity(service.updateAddress(id, address)).build();
    }

    @DELETE
    public Response deleteAddress(@PathParam("id") String id){
        return Response.ok().entity(service.deleteAddress(id)).build();
    }

    @GET
    @Path("/phones")
    public Set<Phone> getPhones(@PathParam("id") String id){
        return service.getPhones(id);
    }
    @POST
    @Path("/phone")
    public Address addPhone(@Valid Phone phone, @PathParam("id") String id){
        return service.addPhone(id, phone);
    }

    @PUT
    @Path("/phone")
    public Address addPnoneToAddress(@Valid Phone phone, @PathParam("id") String id){
        return service.addPhone(id, phone);
    }

    @DELETE
    @Path("/phone")
    public Address deleteAddress(@PathParam("id") String id, Phone phone){
        return service.deletePhone(id, phone);
    }
}
