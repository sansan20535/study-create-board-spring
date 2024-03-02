package com.study.board.repository;

import com.study.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //Repository 표시
//JpaRepository<Entity, Primary Key Type>상속
public interface BoardRepository extends JpaRepository<Board, Integer> {

    //keyword를 통해서 검색하는 메소드 추가
    Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);
}
