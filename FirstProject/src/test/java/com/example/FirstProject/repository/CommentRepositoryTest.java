package com.example.FirstProject.repository;

import com.example.FirstProject.Entity.Article;
import com.example.FirstProject.Entity.Comments;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest // JPA와 연동한 테스트
class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;

    @Test
    @DisplayName("all comments ")
    void findByArticleId() {
        //////////////Case 1: 4번 게시글의 모든 댓글 조회////////////////
        {
            //입력 데이터 준비
Long articleId = 4L;
            //실제 수행
List<Comments> comments= commentRepository.findByArticleId(articleId);
            //예상하기
            Article article = new Article(4L,"Hello World !", "Hello World" );
            Comments  a= new Comments(1L,article,"Hello World !","Hello World" );
            Comments  b= new Comments(2L,article,"Hello World !","Hello World" );
            Comments  c= new Comments(3L,article,"Hello World !","Hello World" );
List <Comments> expected= Arrays.asList(a,b,c);
            // 검증증
assertEquals(expected.toString(),comments.toString());
        }
////////////case2: 1번 게시글의 모든 덧글 조회 //////////////
        {

        }


    }

    @Test
    @DisplayName("Sort by nickname")
    void findByNickname() {

        //입력 데이터 준비
        String nickname= "Hello World !";
        //실제 수행
       List<Comments>comments= commentRepository.findByNickname(nickname) ;
        //예상하기
        Article article = new Article(4L,"Hello World !", "Hello World" );
        Comments  a= new Comments(1L,article,nickname,"Hello World" );
        Comments  b= new Comments(2L,article,nickname,"Hello World" );
        Comments  c= new Comments(3L,article,nickname,"Hello World" );
        List<Comments> expected = Arrays.asList(a,b,c);
       //검증
        assertEquals(expected.toString(),comments.toString());

    }
}