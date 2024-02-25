package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    private BoardService boardService; //의존관계 주입

    @GetMapping("/board/write") // /board/write로 Get방식을 통해 접속했을 때
    public String boardWriteForm() {

        return "boardwrite"; //return된 이름을 가진 html파일 렌더링
    }

    @PostMapping("/board/writepro") // /board/writepro로 Post방식을 통해 접속했을 때
    public String boardWritePro(Board board) {
        //lombok을 이용하여 Board 클래스로 바인딩

        boardService.write(board);

        return "";
    }

    @GetMapping("/board/list")
    public String boardList(Model model){ //Model : 데이터를 담아 페이지로 전송

        //addAttribute(Name, Value) : Value를 Name이라는 이름으로 받아서 데이터를 전송
        model.addAttribute("list", boardService.boardList());

        return "boardlist";
    }

    @GetMapping("/board/view") // localhost:8090/board/view?id=1 Get방식을 통해서 id값이 넘어감
    public String boardView(Model model, @RequestParam(name = "id") Integer id){

        model.addAttribute("board", boardService.boardView(id));
        return "boardview";
    }

    @GetMapping("/board/delete")
    public String boardDelete(@RequestParam(name = "id") Integer id){

        boardService.boardDelete(id);
        return "redirect:/board/list"; // redirect:/주소 -> 해당 URL요청을 다시 하는 것
    }
}
