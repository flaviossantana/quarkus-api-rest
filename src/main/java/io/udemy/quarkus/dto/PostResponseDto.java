package io.udemy.quarkus.dto;

import io.udemy.quarkus.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PostResponseDto {

    private String texto;
    private LocalDateTime dataCriacao;

    public static PostResponseDto from(Post post) {
        return new PostResponseDto(post.getTexto(), post.getDataCriacao());
    }


}
