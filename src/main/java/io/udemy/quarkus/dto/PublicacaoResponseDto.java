package io.udemy.quarkus.dto;

import io.udemy.quarkus.model.Publicacao;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PublicacaoResponseDto {

    private String texto;
    private LocalDateTime dataCriacao;

    public static PublicacaoResponseDto from(Publicacao publicacao) {
        return new PublicacaoResponseDto(publicacao.getTexto(), publicacao.getDataCriacao());
    }


}
