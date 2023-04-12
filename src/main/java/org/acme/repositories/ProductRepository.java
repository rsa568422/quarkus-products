package org.acme.repositories;

import org.acme.entities.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@ApplicationScoped
public class ProductRepository {

    @Inject
    EntityManager em;

    @Transactional
    public Optional<Product> findById(Long id) {
        Object product = em.createQuery("select p from Product p where p.id = :id")
                .setParameter("id", id)
                .getSingleResult();
        if (Objects.isNull(product)) return Optional.empty();
        return Optional.of((Product) product);
    }

    @Transactional
    public void create(Product product) {
        em.persist(product);
    }

    @Transactional
    public void delete(Long id) {
        em.createQuery("delete from Product p where p.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Transactional
    public List<Product> list() {
        List<Product> products = new LinkedList<>();
        em.createQuery("select p from Product p")
                .getResultList()
                .listIterator()
                .forEachRemaining(o -> products.add((Product) o));
        return products;
    }

    @Transactional
    public void update(Product product) {
        em.createQuery("update Product p set p.name = :name, p.description = :description where p.id = :id")
                .setParameter("name", product.getName())
                .setParameter("description", product.getDescription())
                .setParameter("id", product.getId())
                .executeUpdate();
    }

}
