package io.udemy.quarkus.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UsuarioDto {
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @NotNull(message = "Idade é obrigatório")
    private Integer idade;
}
