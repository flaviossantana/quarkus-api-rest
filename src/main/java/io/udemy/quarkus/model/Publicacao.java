package io.udemy.quarkus.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "publicacoes")
public class Publicacao {

    @Id
    @Column(name = "id_publicacao")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "desc_texto", length = 150, nullable = false)
    private String texto;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @PrePersist
    public void prePersist(){
        this.dataCriacao = LocalDateTime.now();
    }

    public Publicacao(String texto, Usuario usuario) {
        this.texto = texto;
        this.usuario = usuario;
    }
}
