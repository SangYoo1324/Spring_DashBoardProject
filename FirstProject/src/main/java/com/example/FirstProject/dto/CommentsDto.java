package com.example.FirstProject.dto;

import com.example.FirstProject.Entity.Comments;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@NoArgsConstructor
@Getter
@Setter
@ToString
public class CommentsDto {
    private Long id;
    @JsonProperty("article_id")
    private Long articleId;
    private String nickname;
    private String body;

    public CommentsDto(Long id, Long articleId, String nickname, String body) {
        this.id = id;

        this.articleId = articleId;
        this.nickname = nickname;
        this.body = body;
    }

    public static CommentsDto createCommentsDto(Comments comment) {
        return new CommentsDto(
                comment.getId(),
                comment.getArticle().getId(),
                comment.getNickname(),
                comment.getBody()
        );
    }
}
