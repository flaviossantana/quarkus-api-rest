package io.udemy.quarkus.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import io.udemy.quarkus.model.Seguidor;
import io.udemy.quarkus.model.Usuario;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class SeguidorRepository implements PanacheRepository<Seguidor> {

    public boolean isNaoESeguidor(Usuario seguidor, Usuario usuario) {

        Optional<Seguidor> seguidorOptional = find("seguidor = :seguidor and usuario = :usuario",
                Parameters
                        .with("seguidor", seguidor)
                        .and("usuario", usuario)
                        .map()).firstResultOptional();

        return !seguidorOptional.isPresent();
    }


    public List<Seguidor> buscarPorUsuario(Long userId) {
        return list("usuario.id", userId);
    }

    public void excluirPorUsuarioESeguidor(Long userId, Long seguidorId) {
        delete("usuario.id = :userId and seguidor.id = :seguidorId",
                Parameters
                        .with("userId", userId)
                        .and("seguidorId", seguidorId).map()
        );
    }
}
