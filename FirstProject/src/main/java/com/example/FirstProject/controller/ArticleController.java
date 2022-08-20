package com.example.FirstProject.controller;

import com.example.FirstProject.Entity.Article;
import com.example.FirstProject.dto.ArticleForm;
import com.example.FirstProject.dto.CommentsDto;
import com.example.FirstProject.repository.ArticleRepository;
import com.example.FirstProject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j //로깅을 위한 어노테이션
public class ArticleController {
    @Autowired
    private CommentService commentService;
    @Autowired// 스프링부트가 미리 생성해놓은 객체를 가져다가 자동 연결
   private ArticleRepository articleRepository;

    @GetMapping("articles/new") // url과 연결
    public String newArticleForm(){
        return "articles/new"; //template 과 연결
    }

@PostMapping("/articles/create") // form 데이터를 받기 위한 메소드(html에 post 메소드로 보낸다고 적었음 // html에 action 에 articles/create로 보낸다고적었음
    public String createArticle(ArticleForm form){
//    System.out.println(form.toString());  Logging 기능으로 대체
log.info(form.toString());

//DTO란? html의 form 데이터를 받는객체(그릇)
    //1. DTO를 Entity로 변환
    Article article = form.toEntity();
    log.info(article.toString());
    //2. repository 에게 entity를 db 안에 저장하게 함
    Article saved= articleRepository.save(article);
    log.info(saved.toString());


        return "redirect:/articles/"+saved.getId();
    }

    //**************************각 아티클 상세 정보 보기*****************************************
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id= "+id);
        //1: id로 데이터를 가져옴!(repository로부터)
        Article articleEntity = articleRepository.findById(id).orElse(null);

        List<CommentsDto>commentDtos = commentService.commentsMethod(id);

        //2: 가져온 데이터를 모델에 등록
        model.addAttribute("article",articleEntity);
        model.addAttribute("commentDtos", commentDtos);
        //3: 보여줄 페이지를 설정
        return "articles/show";
    }
    //**************************각 아티클 상세 정보 보기****************************************************

    //**************************모든 아티클 모아보기**************************************************
    @GetMapping("/articles")//url이름
    public String index(Model model){
        //1. 모든 Article을 가져온다.
List<Article> articleEntityList= articleRepository.findAll();
        //2. 가져온 Article 묶음을 뷰로 전달
model.addAttribute("articleList",articleEntityList);
        //3. 뷰 페이지를 설정정
return "articles/index";//template 이름
   }
    //**************************모든 아티클 모아보기**************************************************

    //****************************edit 페이지 **************************************************
    @GetMapping("/articles/{idvalue}/edit")//url이름
    public String edit(@PathVariable  Long idvalue, Model model){// id 를 가져오는데 mapping url에서 가져온다는 어노테이션

        //수정할 데이터 가져오기
        Article articleEntity= articleRepository.findById(idvalue).orElse(null);

//model에 데이터 등록(attribute속성으로 데이터 등록)
        model.addAttribute("article", articleEntity);

        return"articles/edit";//template 연결;
    }
    //****************************edit 페이지 **************************************************

    //****************************edit 페이지 수정 form 제출 시 db업데이트하고  **************************************
    //****************************수정폼반영된 아티클 상세페이지로 이동   ********************************************
@PostMapping("/articles/update")// edit.mustache에 where값(form버튼) 눌렸을때 이동하는 url에서 동작)
    public String update(ArticleForm form){
    log.info(form.toString());

    //1. DTO를 Entity로   변환
    Article articleEntity= form.toEntity();
    log.info(articleEntity.toString());

    //2. DB에서 기존 데이터를 가져온다
Article target=   articleRepository.findById(articleEntity.getId()).orElse(null);
//2-2. 기존 데이터의 값을 갱신한다
    if(target !=null){
        articleRepository.save(articleEntity);
    }

    //3: 수정 결과 페이지로 리다이렉트

        return "redirect:/articles/"+articleEntity.getId();
    }//****************************edit 페이지 수정 form 제출 시 db업데이트하고  **************************************
//****************************수정폼반영된 아티클 상세페이지로 이동   ******************************************

    @GetMapping ("/articles/{id}/delete")// url로 이동할때 아래 기능들 동작
public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("Delete request");

        //1.삭제 대상을 가져온다
        Article target= articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        //2.대상을 삭제한다
        if(target!=null) {
            articleRepository.delete(target);
    rttr.addFlashAttribute("msg", "Deleted");
        }

        //3. 결과페이지로 리다이렉트
        return "redirect:/articles/";

}
}
