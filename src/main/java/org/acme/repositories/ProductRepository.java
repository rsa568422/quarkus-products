package org.acme.repositories;

import org.acme.entities.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ProductRepository {

    @Inject
    EntityManager em;

    @Transactional
    public void create(Product product) {
        em.persist(product);
    }

    @Transactional
    public void delete(Product product) {
        em.remove(product);
    }

    @Transactional
    public List<Product> list() {
        return em.createQuery("select p from Product p").getResultList();
    }

}
