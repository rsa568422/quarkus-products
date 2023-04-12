package org.acme.resteasyjackson;

import org.acme.entities.Product;
import org.acme.repositories.ProductRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

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

    @GET
    @Path("/{id}")
    public Optional<Product> findById(@PathParam("id") Long id) {
        return repository.findById(id);
    }

    @POST
    public Response add(Product product) {
        repository.create(product);
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Product product) {
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isEmpty()) return Response.serverError().tag("Product not found").build();
        product.setId(optionalProduct.get().getId());
        repository.update(product);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        repository.delete(id);
        return Response.noContent().build();
    }
}