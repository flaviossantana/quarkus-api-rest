package io.udemy.quarkus.dto;

import io.udemy.quarkus.model.Seguidor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeguidorResponseDto {
    private Long id;
    private String nome;

    public SeguidorResponseDto(Seguidor seguidor) {
        this(seguidor.getId(), seguidor.getSeguidor().getNome());
    }
}
