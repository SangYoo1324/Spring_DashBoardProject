package com.example.FirstProject.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
   @GetMapping("/hi")
   //hi 라는 url을 받아와라
public String niceToMeetYou(Model model) {
      model.addAttribute("username", "John");
   return  "greetings"; // template 이름
}

@GetMapping("/bye")
    public String seeyouNext(Model model) {
    model.addAttribute("nickname", "Jane");
    return "goodbye"; // 사용할 template 이름
}
}


//사이트로부터 요청은 controller가 받는다. 단, getMapping 이란 어노테이션으로부터 주소 경로(/hi)를 받는다.class
//리턴값은 보여줄 페이지 template 의 이름. 모델은 사용할 변수 등록