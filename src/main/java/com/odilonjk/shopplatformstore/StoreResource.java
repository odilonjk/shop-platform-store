package com.odilonjk.shopplatformstore;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/store")
public class StoreResource {

    @Inject
    @RestClient
    StoreResourceClient storeResourceClient;

    @Inject
    StoreService storeService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createStore(Store store) {
        storeService.createStore(store);
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Store> findByName(@PathParam("name") String name) {
        return storeService.findByName(name);
    }

}