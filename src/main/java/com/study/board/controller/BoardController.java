package com.study.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //controller Annotation
public class BoardController {

    /*@GetMapping("/") //localhost:8090으로 접속했을 때 (기본)
    @ResponseBody //return값을 그대로 띄워주는 Annotation
    public String main(){
        return "Hello World";
    }*/

    @GetMapping("/board/write") // /board/write로 Get방식을 통해 접속했을 때
    public String boardWriteForm() {

        return "boardwrite"; //return된 이름을 가진 html파일 렌더링
    }

    @PostMapping("/board/writepro") // /board/writepro로 Post방식을 통해 접속했을 때
    public String boardWritePro(@RequestParam(name = "title") String title,@RequestParam(name = "content") String content) {
        // @RequestParam을 통해 title과 content를 인자로 받음
/*
        log를 통해 확인
        System.out.println("title = " + title);
        System.out.println("content = " + content);
*/

        return "";
    }
}
