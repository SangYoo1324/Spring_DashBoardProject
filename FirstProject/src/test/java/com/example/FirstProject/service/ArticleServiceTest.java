package com.example.FirstProject.service;

import com.example.FirstProject.Entity.Article;
import com.example.FirstProject.dto.ArticleForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {
@Autowired ArticleService articleService;// 스프링부트에 이미 생성된 객체에 오토와이어드 되기때문에 초기화 필요 x

    @Test
    void index() {
        //예상
    Article a= new Article(1l,"Hello World !", "Hello World");
        Article b= new Article(2l,"Hello World !", "Hello World");
        Article c= new Article(3l,"Hello World !", "Hello World");
        List<Article> expected =new ArrayList<Article>(Arrays.asList(a,b,c));
        //실제
        List<Article> articles = articleService.index();

        //비교
        assertEquals(expected.toString(),articles.toString());
    }

    @Test
    void show_Success_IDInput() {
        //예상
        Long id= 1L;
        Article expected = new Article(1l,"Hello World !", "Hello World");
        //실제
        Article article= articleService.show(id);
        //비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_Fail_non_exist_IDInput() {
        //예상
        Long id= -1L;
        Article expected = null;
        //실제
        Article article= articleService.show(id);
        //비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void create_Success_DTO_Only_title_() {
        //예상
        String title= "adfad";
        String content= "asdfadf";

        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4l, title, content);

        //실제
    Article article =  articleService.create(dto);

        //비교
assertEquals(expected.toString(), article.toString());
    }


}