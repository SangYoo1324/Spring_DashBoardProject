package com.example.FirstProject.Entity;

import com.example.FirstProject.dto.CommentsDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString

@NoArgsConstructor
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne// 해당 댓글 엔티티 여러개가, 하나의 Article에 연관된다
    @JoinColumn(name = "article_id") // article의 id값이 comment에 article_id 값에 담기면서 연결된다
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;


    public Comments(Long id, Article article, String nickname, String body) {
        this.id = id;
        this.article = article;
        this.nickname = nickname;
        this.body = body;
    }

    public static Comments createComments(CommentsDto dto, Article article) { // JSon data , 커멘트 쓰고자하는 아티클
        //예외 처리
        if(dto.getId() != null){
            throw new IllegalArgumentException("id needs to be blank(autopopulate");
        }
        if(dto.getArticleId() != article.getId()){
            throw new IllegalArgumentException("articleId needs to be same");
        }
        //엔티티 생성 & 반환
        return new Comments(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(CommentsDto dto){
        //예외발생
        //this 는 제이슨 객체
        if(this.id != dto.getId())
            throw new IllegalArgumentException("id needs to be same");
        //객체를 갱신
        //this 는 제이슨 객체
        if(dto.getNickname()!= null)
            this.nickname = dto.getNickname();
        if(dto.getBody() != null)
            this.body = dto.getBody();
    }
}
