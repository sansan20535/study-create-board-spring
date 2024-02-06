package com.study.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //controller Annotation
public class BoardController {

    @GetMapping("/") //localhost:8090으로 접속했을 때 (기본)
    @ResponseBody //return값을 그대로 띄워주는 Annotation
    public String main(){
        return "Hello World";
    }
}
