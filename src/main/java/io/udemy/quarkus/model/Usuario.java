package io.udemy.quarkus.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "usuarios")
public class Usuario extends PanacheEntityBase {

    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "desc_nome")
    private String nome;
    @Column(name = "num_idade")
    private Integer idade;

    public Usuario(String nome, Integer idade) {
        this.nome = nome;
        this.idade = idade;
    }
}
