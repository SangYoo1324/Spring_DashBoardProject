package com.example.FirstProject.service;

import com.example.FirstProject.Entity.Article;
import com.example.FirstProject.dto.ArticleForm;
import com.example.FirstProject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service// 서비스 관련 클래스라고 선언( 서비스 객체를 스프링부트에 생성)
public class ArticleService {// 주방장
    @Autowired
    private ArticleRepository articleRepository;// ArticleRepository 와 협업할 수 있게 생성

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article=dto.toEntity();
        if(article.getId()!=null){
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(long id, ArticleForm dto) {
        //1. 수정용 entity 생성
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());
        //2.대상 entity 찾기
        Article target = articleRepository.findById(id).orElse(null);
        //3. 잘못된 요청 처리(대상이 없거나, id가 다른 경우)
        if (target == null || id != article.getId()) {
            log.info("Wrong Request! id: {}, article: {}", id, article.toString());
            return null;
        }
        //4. 업데이트
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }

    public Article delete(long id){
//        1:대상 엔티티 찾기
        Article target = articleRepository.findById(id).orElse(null);
        //2: 잘못된 요청 처리
        if(target== null){
            return null;// null 반환되면 article API controller 에서 처리

        }
        //3: 대상 삭제 후 응답 반환
        articleRepository.delete(target);
        return target;// empty after delete
    }

    @Transactional//해당 메소드를 transaction 으로 묶음
    public List<Article> createArticles(List<ArticleForm> dtos){
        //dto 묶음을 entity 묶음으로 변환
        List<Article> articleList = dtos.stream().map(dto -> dto.toEntity()).collect(Collectors.toList());
        //Entity 묶음을 dB로 저장
articleList.stream().forEach(article -> articleRepository.save(article));
        //강제 예외 발생
articleRepository.findById(-1L).orElseThrow(
        () -> new IllegalArgumentException("transaction failed")
);
        //결과값 반환
        return articleList;
    }
}

// 404 에러는 주소 잘못된거임
