package com.springboard.board.service;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboard.board.BoardNotFoundException;
import com.springboard.board.dao.AttatchDAO;
import com.springboard.board.dao.BoardDAO;
import com.springboard.board.vo.AttatchVO;
import com.springboard.board.vo.FreeBoardVO;
import com.springboard.paging.vo.PaginationInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	private final BoardDAO boardDAO;
	private final AttatchDAO attatchDAO;
	
	@Value("#{appInfo.boFiles}")
	private Resource boFiles;
	
	private void processBoFiles(FreeBoardVO board) {
		List<AttatchVO> attatchList = board.getAttatchList();
		if(attatchList!=null) {
			attatchList.forEach((atch)->{
				try {
					atch.setBoNo(board.getBoNo());
					attatchDAO.insertAttatch(atch);
					atch.saveTo(boFiles.getFile());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			});
		}
	}
	
	@Override
	public void createBoard(FreeBoardVO board) {
		boardDAO.insertBoard(board);
		processBoFiles(board);
	}

	@Override
	public FreeBoardVO retrieveBoard(int boNo) {
		
		FreeBoardVO board = boardDAO.selectBoard(boNo);
		if(board==null)
			throw new BoardNotFoundException(
					HttpStatus.NOT_FOUND, String.format("%d 해당 게시글이 없음.", boNo));
		
		boardDAO.incrementHit(boNo);
		return boardDAO.selectBoard(boNo);
	}

	@Override
	public AttatchVO retrieveAttatch(int attNo) {
		AttatchVO atch = attatchDAO.selectAttatch(attNo);
		if(atch==null)
			throw new BoardNotFoundException(
					HttpStatus.NOT_FOUND, String.format("%d 해당 파일이 없음.", attNo));
		
		attatchDAO.incrementDowncount(attNo);
		return atch;
	}
	
	@Override
	public List<FreeBoardVO> retrieveBoardList(PaginationInfo<FreeBoardVO> paging) {
		int totalRecord = boardDAO.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		
		return boardDAO.selectBoardList(paging);
	}

}
