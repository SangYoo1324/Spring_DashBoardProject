package com.example.FirstProject.repository;

import com.example.FirstProject.Entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article,Long> {
    @Override
    ArrayList<Article> findAll();// =iterable인데 arrayList로 반환하도록 변경
}
