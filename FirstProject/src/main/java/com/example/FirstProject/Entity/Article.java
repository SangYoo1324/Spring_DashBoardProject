package com.example.FirstProject.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity  // db에서 인식을 할 수 있게 어노테이션이 필요함
public class Article {
    @Id
    @GeneratedValue // 아이디 자동생성
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

    public Long getId() {
        return id;
    }
}
