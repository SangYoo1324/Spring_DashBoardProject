package com.example.FirstProject.api;

import com.example.FirstProject.Entity.Article;
import com.example.FirstProject.dto.ArticleForm;
import com.example.FirstProject.repository.ArticleRepository;
import com.example.FirstProject.service.ArticleService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {// 웨이터
//    @Autowired// 스프링부트가 미리 생성해놓은 객체를 가져다가 자동으로 sql과(db) 연결
//    private ArticleRepository articleRepository;
//
//    //GET
//
//    @GetMapping("/api/articles")
//    public List<Article> index() {
//        return articleRepository.findAll();
//    }
//
//    @GetMapping("/api/articles/{id}")
//    public Article index(@PathVariable long id) {
//        return articleRepository.findById(id).orElse(null);
//    }
//
//    //POST
//@PostMapping("/api/articles")
//    public Article create(@RequestBody ArticleForm dto) {//RequestBody 부분을 dto에 넣어라 라는 어노테이션
//        Article article = dto.toEntity();
//        return articleRepository.save(article);// article을 Dto로 받아와준다
//    }
//
//    //PATCH
//    @PatchMapping("/api/articles/{id}")
//    public ResponseEntity<Article> patch(@PathVariable long id, @RequestBody ArticleForm dto) {
//        //ResponseEntity에 Article을 담아서 보내줘야 (상태코드)400번 요청 보낼 수 있음
//
//        //1. 수정용 entity를 생성
//        Article article=dto.toEntity();
//        log.info("id: {}, article: {}", id, article.toString());
//        //2. 대상 엔티티를 조회
//        Article target= articleRepository.findById(id).orElse(null);
//        //3. 잘못된 요청 처리
//        if(target==null || id != article.getId()) {
//            //400, 잘못된 요청 응답
//            log.info("wrong request id:{} , article: {}", id, article.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        //4. 업데이트 및 정상 응답
//        target.patch(article);
//        Article updated= articleRepository.save(target);
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//
//
//    }
//    //DELETE
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id){
//        //대상 찾기
//       Article target= articleRepository.findById(id).orElse(null);
//
//       // 잘못된 요청 처리
//        if(target == null){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        //대상 삭제
//        articleRepository.delete(target);
//        //데이터 변환
//
//        return ResponseEntity.status(HttpStatus.OK).body(null);
//    }

    @Autowired
    private ArticleService articleService;

    //Get
    @GetMapping("/api/articles")
    public List<Article> index() {
    return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable long id){
        return articleService.show(id);
    }

    //Post
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){
        Article created = articleService.create(dto);
        return (created != null) ? ResponseEntity.status(HttpStatus.OK).body(created): ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Patch
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable long id, @RequestBody ArticleForm dto){
        Article updated=articleService.update(id, dto);
        return (updated != null) ? ResponseEntity.status(HttpStatus.OK).body(updated) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Delete
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable long id){
        Article deleted= articleService.delete(id);
        return (deleted != null)?  ResponseEntity.status(HttpStatus.NO_CONTENT).build(): ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //transaction  -> fail -> roll back
@PostMapping("/api/articles/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm>dtos){
        List<Article>createdList = articleService.createArticles(dtos);
        return (createdList != null)?
                ResponseEntity.status(HttpStatus.OK).body(createdList) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
}


}
