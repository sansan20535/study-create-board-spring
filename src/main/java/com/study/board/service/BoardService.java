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

    public void write(Board board){
        boardRepository.save(board); // save메소드를 이용하여 Entity저장
    }

    public List<Board> boardList(){

        return boardRepository.findAll(); //findAll()메소드를 이용하여 List<Board>를 반환
    }
}
