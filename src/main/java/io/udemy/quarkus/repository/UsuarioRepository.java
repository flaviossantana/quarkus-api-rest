package io.udemy.quarkus.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.udemy.quarkus.model.Usuario;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {

}
