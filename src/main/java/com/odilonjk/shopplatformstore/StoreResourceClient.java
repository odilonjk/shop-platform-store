package com.odilonjk.shopplatformstore;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/store")
@RegisterRestClient(configKey = "store-api")
public interface StoreResourceClient {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void createStore(Store store);

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    List<Store> findByName(@PathParam("name") String name);

}
