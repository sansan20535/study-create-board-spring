package com.study.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity //DB에 있는 Table을 의미
@Data //lombok : id, title, content를 getId() 등의 메소드로 꺼낼 수 있게 함
public class Board {

    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY : MariaDB, MySQL 
    private Integer id; //DB내 게시글 번호

    private String title; //DB내 게시글 제목

    private String content; //DB내 게시글 내용

    private String filename;

    private String filepath;
}
