package com.example.FirstProject.repository;

import com.example.FirstProject.Entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository <Comments, Long>{// <Entity,ID>
    //특정 게시글의 모든 댓글 조회
    @Query(value = "SELECT * From comments Where article_id = :articleId", nativeQuery = true)
    List<Comments> findByArticleId(Long articleId);  // Query 어노테이션의 article_id의 값하고 같아야함
    //특정 닉네임임의 모든 덧글 조회
@Query(value = "SELECT * FROM comments WHERE nickname= :nickname", nativeQuery = true)
   List <Comments>findByNickname(String nickname);


}
