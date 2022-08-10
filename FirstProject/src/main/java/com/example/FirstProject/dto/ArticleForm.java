package com.example.FirstProject.dto;
//form 데이터를 받아올 그릇

import com.example.FirstProject.Entity.Article;

public class ArticleForm {
    private Long id;
    private String title;
    private String content;

    public ArticleForm(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticleForm{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Article toEntity() {// entity는 article 객체
        return new Article(id, title, content);
    }
}
