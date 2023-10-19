package com.springboard.board.dao;

import org.apache.ibatis.annotations.Mapper;

import com.springboard.board.vo.FreeBoardVO;

@Mapper
public interface BoardDAO {
	public int insertBoard(FreeBoardVO board);
	public FreeBoardVO selectBoard(int boNo);
}
