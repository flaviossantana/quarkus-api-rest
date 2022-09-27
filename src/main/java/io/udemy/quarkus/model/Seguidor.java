package io.udemy.quarkus.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "seguidores")
public class Seguidor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_usuario_seguido", nullable = false)
    private Usuario seguidor;


    public Seguidor(Usuario usuario, Usuario seguidor) {
        this.usuario = usuario;
        this.seguidor = seguidor;
    }
}
