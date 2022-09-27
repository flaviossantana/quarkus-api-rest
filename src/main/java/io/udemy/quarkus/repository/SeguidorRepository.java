package io.udemy.quarkus.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.udemy.quarkus.model.Seguidor;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SeguidorRepository implements PanacheRepository<Seguidor> {

}
