package com.example.FirstProject.Entity;

import com.fasterxml.jackson.annotation.JsonView;


import javax.persistence.*;
import java.io.Serializable;

@Entity  // db에서 인식을 할 수 있게 entity 어노테이션이 필요함
public class Article {

    @Column
    @Id//대표값을 지정, like 주민번호
    @GeneratedValue(strategy=GenerationType.IDENTITY)// 아이디 자동생성
    private Long id;

    @Column  // db에서 인식을 할 수 있게 어노테이션이 필요함

    private String title;
    @Column  // db에서 인식을 할 수 있게 어노테이션이 필요함

    private String content;

    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    Article(){}//default constructor

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    //Jackson default use getters to create Json Object. so, if no getters, value will not be shown
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void patch(Article article){
        if(article.title != null){
            this.title= article.title;// this는 patch를 쓰는 자신, 즉 타겟
        }
        if(article.content != null){
            this.content= article.content;
        }

    }
}
