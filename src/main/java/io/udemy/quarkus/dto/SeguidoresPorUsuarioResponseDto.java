package io.udemy.quarkus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeguidoresPorUsuarioResponseDto {
    private List<SeguidorResponseDto> seguidores = new ArrayList<>();

    public int getQuantidadeSeguidores() {
        return this.seguidores.size();
    }
}
