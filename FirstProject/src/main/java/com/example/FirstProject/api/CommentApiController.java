package com.example.FirstProject.api;

import com.example.FirstProject.dto.CommentsDto;
import com.example.FirstProject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired// 서비스와 협업할 수 있도록
    private CommentService commentService;

    //댓글 목록 조회
    @GetMapping("api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentsDto>> comments(@PathVariable("articleId") Long articleId)
    {
        //서비스에게 작업 위임
   List<CommentsDto>  dtos=commentService.commentsMethod(articleId);// 커멘트서비스에서 커멘트메소드를 이용,
        // 엔티티를 dto로 변환해서 반환
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }


    //댓글 생성
    @PostMapping("api/articles/{articleId}/comments")
        public ResponseEntity< CommentsDto> create(@PathVariable Long articleId, @RequestBody CommentsDto dto){
                                                                                                                                    //입력된 body 내용을 Json 데이터를 dto로 받아옴
        // 서비스에게 위임
        CommentsDto createdDto= commentService.create(articleId, dto);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
        }

        //댓글 수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity< CommentsDto> update(@PathVariable Long id, @RequestBody CommentsDto dto) {
        //서비스에게 위임(수정된 제이슨 데이터와 함께)
        CommentsDto updatedDto = commentService.update(id, dto);

        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    //댓글 삭제
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentsDto> delete(@PathVariable Long id){
        CommentsDto updatedDto = commentService.delete(id);

        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);

    }

        }


