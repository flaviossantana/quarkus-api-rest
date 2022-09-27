package io.udemy.quarkus.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.udemy.quarkus.model.Post;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PostRepository implements PanacheRepository<Post> {

}
