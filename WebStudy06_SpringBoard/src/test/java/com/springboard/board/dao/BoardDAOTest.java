package com.springboard.board.dao;

import static org.junit.jupiter.api.Assertions.*;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import com.springboard.board.vo.FreeBoardVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringJUnitWebConfig(locations = "file:src/main/resources/com/springboard/spring/conf/*-context.xml")
class BoardDAOTest {
	
	@Inject
	private BoardDAO dao;

	@Test
	void testInsertBoard() {
		fail("Not yet implemented");
	}

	@Test
	void testSelectBoard() {
		FreeBoardVO board = dao.selectBoard(461);
		log.info("첨부파일 갯수 : {}", board.getAttatchList().size());
	}

}
