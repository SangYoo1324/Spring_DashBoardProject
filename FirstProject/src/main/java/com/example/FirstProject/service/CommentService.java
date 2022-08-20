package com.example.FirstProject.service;

import com.example.FirstProject.Entity.Article;
import com.example.FirstProject.Entity.Comments;
import com.example.FirstProject.dto.CommentsDto;
import com.example.FirstProject.repository.ArticleRepository;
import com.example.FirstProject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;


    //엔티티를 dto로 반환 시켜주는 메서드
    public List<CommentsDto> commentsMethod(Long articleId) {

//        //댓글 목록 조회
//        List<Comments>comments=commentRepository.findByArticleId(articleId);
//
//        //변환: 엔티티->dto
//      List<CommentsDto>dtos =  new ArrayList<CommentsDto>();
//        for(int i= 0; i<comments.size(); i++){
//            Comments c = comments.get(i);
//            CommentsDto dto = CommentsDto.createCommentsDto(c);
//            dtos.add(dto);
//        }
//        //dto 반환
        return commentRepository.findByArticleId(articleId).stream().map(comments ->
                CommentsDto.createCommentsDto(comments)).collect(Collectors.toList());
    }

    //댓글 생성
    @Transactional// create는 db를 건드리고있어, db에 변경이 일어날 수 있어, 만약 예외로 에러가 나면 완전 롤백시키기 위한 어노테이션
    public CommentsDto create(Long articleId, CommentsDto dto) {
        //타겟이 될 게시글 조회 및  문제 시 예외 발생
        Article article =// 코멘트가 들어갈 게시글 조회
                 articleRepository.findById(articleId).orElseThrow(()-> new IllegalArgumentException("Comment create Failed!!!!!!!!!!!"));
        //댓글 엔티티 생성
    Comments comment = Comments.createComments(dto, article);
        // 댓글 엔티티를 DB로 저장
    Comments created = commentRepository.save(comment);
        //DTO로 변경하여 반환
        return CommentsDto.createCommentsDto(created);
    }

    //댓글 수정
    @Transactional
    public CommentsDto update(Long id, CommentsDto dto) {
        //타겟이 될 댓글 조회, 문제가 있다면 예외 발생
        Comments target=// target은 기존에 있던 게시글
        commentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("update Failed"));
        //댓글 수정
        target.patch(dto);
        //db로 갱신
        Comments updated = commentRepository.save(target);
        //댓글 엔티티를 dto로 변환
        return CommentsDto.createCommentsDto(updated);


    }

    @Transactional
    public CommentsDto delete(Long id) {
        //타겟이 될 댓글조회 및 예외 발생
      Comments target=  commentRepository.findById(id).orElseThrow(
              ()->new IllegalArgumentException("delete Failed, no target"));
        //db에서 댓글 삭제
    commentRepository.deleteById(id);
        //삭제된 상태의 덧글을 dto로 반환
        return CommentsDto.createCommentsDto(target);

    }





}
