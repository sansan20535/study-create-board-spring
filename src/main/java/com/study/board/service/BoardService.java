package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //service 표시
public class BoardService {

    @Autowired //의존관계 주입
    private BoardRepository boardRepository;

    // 글 작성 처리
    public void write(Board board){
        boardRepository.save(board); // save메소드를 이용하여 Entity저장
    }

    // 게시글 리스트 처리
    public List<Board> boardList(){

        return boardRepository.findAll(); //findAll()메소드를 이용하여 List<Board>를 반환
    }

    //특정 게시글 불러오기
    public Board boardView(Integer id){

        return boardRepository.findById(id).get(); //id를 통해 해당 게시글 탐색
    }

    //특정 게시글 삭제
    public void boardDelete(Integer id){

        boardRepository.deleteById(id); //id를 통해 해당 게시글 삭제
    }
}
