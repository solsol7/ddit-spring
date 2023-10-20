package com.springboard.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboard.board.vo.AttatchVO;

@Mapper
public interface AttatchDAO {
	public int insertAttatch(AttatchVO attatch);
	public AttatchVO selectAttatch(int attNo);
	public int deleteAttatch(int attNo);
	
	public int incrementDowncount(int boNo);
}
