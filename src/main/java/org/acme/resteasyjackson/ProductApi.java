package org.acme.resteasyjackson;

import org.acme.entities.Product;
import org.acme.repositories.ProductRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductApi {

    @Inject
    private ProductRepository repository;

    @GET
    public List<Product> list() {
        return repository.list();
    }

    @POST
    public Response add(Product product) {
        repository.create(product);
        return Response.ok().build();
    }

    @DELETE
    public Response delete(Product product) {
        repository.delete(product);
        return Response.noContent().build();
    }
}