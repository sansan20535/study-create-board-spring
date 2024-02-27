package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service //service 표시
public class BoardService {

    @Autowired //의존관계 주입
    private BoardRepository boardRepository;

    // 글 작성 처리
    public void write(Board board, MultipartFile file) throws Exception{

        //저장 경로 지정(프로젝트 경로 + 저장 위치)
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        //식별자 생성
        UUID uuid = UUID.randomUUID();

        //식별자를 포함한 파일 이름 생성
        String fileName = uuid + "_" + file.getOriginalFilename();

        //File(경로, 이 파일에 붙일 이름)
        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        //파일경로 저장
        board.setFilename(fileName);

        //static아래의 경로만 설정
        board.setFilepath("/files/" + fileName);

        boardRepository.save(board); // save메소드를 이용하여 Entity저장
    }

    // 게시글 리스트 처리
    public Page<Board> boardList(Pageable pageable){

        //findAll()메소드를 이용하여 List<Board>를 반환
        //단, 매개변수가 존재할 경우 Page 인터페이스를 반환
        return boardRepository.findAll(pageable);
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
