package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public String boardWritePro(Board board, Model model, @RequestParam(name = "file") MultipartFile file) throws Exception {
        //lombok을 이용하여 Board 클래스로 바인딩

        boardService.write(board, file);

        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");

        return "message";
    }

    @GetMapping("/board/list")
    //Model : 데이터를 담아 페이지로 전송, Pageable(Domain 인터페이스 선택)
    public String boardList(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Board> list = boardService.boardList(pageable);

        //현재 페이지 번호
        //페이지가 0번부터 시작하기 때문에 + 1
        int nowPage = list.getPageable().getPageNumber() + 1;
        //시작페이지 번호 (최소 페이지를 넘어가지 않게 하기 위해 Math클래스의 max함수 사용)
        int startPage = Math.max(nowPage - 4, 1);
        //끝페이지 번호 (최대 페이지를 넘어가지 않게 하기 위해 Math클래스의 min함수 사용)
        //list.getTotalPages() : 총 페이지 수 반환
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        //addAttribute(Name, Value) : Value를 Name이라는 이름으로 받아서 데이터를 return하는 html파일에 전송
        //boardlist에서 list라는 이름의 변수 사용 가능
        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);



        return "boardlist";
    }

    @GetMapping("/board/view") // localhost:8090/board/view?id=1 Get방식을 통해서 id값이 넘어감
    public String boardView(Model model, @RequestParam(name = "id") Integer id) {

        model.addAttribute("board", boardService.boardView(id));
        return "boardview";
    }

    @GetMapping("/board/delete")
    public String boardDelete(@RequestParam(name = "id") Integer id) {

        boardService.boardDelete(id);
        return "redirect:/board/list"; // redirect:/주소 -> 해당 URL요청을 다시 하는 것
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id,
                              Model model) {
        // @PathVariable : 경로변수를 표시하기 위해 사용 ({}중괄호 사용)
        //                 URL 경로에서 변수 값을 추출하여 매개변수에 할당
        //                 상세 조회, 수정, 삭제와 같은 작업에서 사용

        model.addAttribute("board", boardService.boardView(id)); //수정 후 넘기는 데이터가 상세 페이지와 동일
        return "boardmodify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board, @RequestParam(name = "file") MultipartFile file) throws Exception {

        //기존에 있던 글이 담겨짐
        Board boardTemp = boardService.boardView(id);

        //새로운 내용을 기존에 있던 내용에 덮어 씌움
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.write(boardTemp, file);

        return "redirect:/board/list";
    }
}
