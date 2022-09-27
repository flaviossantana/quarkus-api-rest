package io.udemy.quarkus.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
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

}
