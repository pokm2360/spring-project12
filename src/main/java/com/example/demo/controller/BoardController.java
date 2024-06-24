package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BoardDTO;
import com.example.demo.service.BoardService;

@RestController //@Controller + @ResponseBody (api사용할 때)
@RequestMapping("/board") //중간경로
public class BoardController {
	
	@Autowired
	BoardService service;
	
//	게시물 등록 메소드
	@PostMapping("/register")
	public ResponseEntity<Integer> register(@RequestBody BoardDTO dto) { 
		//ResponseEntity이기 때문에 @Controller 써도됨
		int no = service.register(dto);
		return new ResponseEntity<>(no, HttpStatus.CREATED);
//		201성공코드 + 새로운 글번호 반환
	}
	
//	게시물 목록을 반환하는 메소드
	@GetMapping("/list")
	public ResponseEntity<List<BoardDTO>> getList() {
		
		List<BoardDTO> list = service.getList();
		return new ResponseEntity<>(list, HttpStatus.OK);
//		200성공코드 + 게시물 목록
	}
	
//	localhost:8080/board/read?no=1
//	특정 게시물 정보를 반환하는 메소드
	@GetMapping("/read")
	public ResponseEntity<BoardDTO> read(@RequestParam(name = "no") int no) {
		
		BoardDTO dto = service.read(no);
		return new ResponseEntity<>(dto, HttpStatus.OK);
//		200성공코드 + 게시물정보 반환
	}
	
//	게시물 정보를 수정하는 메소드
	@PutMapping("/modify")
	public ResponseEntity modify(@RequestBody BoardDTO dto) {
		service.modify(dto);
		return new ResponseEntity(HttpStatus.NO_CONTENT); //204 성공코드 반환
	}
	
//	localhost:8080/board/remove?no=1
//	특정 게시물을 삭제하는 메소드
	@DeleteMapping("/remove")
	public ResponseEntity remove(@RequestParam(name = "no") int no) {
		service.remove(no);
		return new ResponseEntity(HttpStatus.NO_CONTENT); //204 성공코드 반환
	}
}
