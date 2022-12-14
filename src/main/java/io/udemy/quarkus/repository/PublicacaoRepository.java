package io.udemy.quarkus.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.udemy.quarkus.model.Publicacao;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PublicacaoRepository implements PanacheRepository<Publicacao> {

}
