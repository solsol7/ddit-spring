package com.springboard.board.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboard.board.BoardNotFoundException;
import com.springboard.board.InvalidPasswordException;
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
	
	@Inject
	private PasswordEncoder passwordEncoder;
	
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
//					if(1==1) {
//						throw new IOException("강제 발생 예외");
//					}
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			});
		}
	}
	
//	@Transactional
	@Override
	public void createBoard(FreeBoardVO board) {
		
		String plain = board.getBoPass();
		String encoded = passwordEncoder.encode(plain);
		board.setBoPass(encoded);
		
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
	public void retrieveBoardList(PaginationInfo<FreeBoardVO> paging) {
		int totalRecord = boardDAO.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		
		List<FreeBoardVO> dataList = boardDAO.selectBoardList(paging);
		
		paging.setDataList(dataList);
	}

	@Override
	public FreeBoardVO authenticateBoard(FreeBoardVO inputData) {
		FreeBoardVO savedBoard = boardDAO.selectBoard(inputData.getBoNo());
		String encodedPassword = savedBoard.getBoPass();
		String rawPassword = inputData.getBoPass();
		if(passwordEncoder.matches(rawPassword, encodedPassword)) {
			// 인증 성공
			return savedBoard;
		}else {
			// 인증 실패
			throw new InvalidPasswordException();
		}
	}

//	@Transactional
	@Override
	public void modifyBoard(FreeBoardVO board) {
		authenticateBoard(board);
		boardDAO.updateBoard(board);
		processBoFiles(board);
		
	}
	
	private void processDeleteAttatches(FreeBoardVO savedBoard) {
		savedBoard.getAttatchList().forEach((atch)->{
			String saveName = atch.getAttSavename();
			attatchDAO.deleteAttatch(atch.getAttNo());
			try {
				FileUtils.deleteQuietly(new File(boFiles.getFile(), saveName));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			
		});
	}
	
//	@Transactional
	@Override
	public void removeBoard(FreeBoardVO inputData) {
		FreeBoardVO savedBoard = authenticateBoard(inputData);
		processDeleteAttatches(savedBoard);
		boardDAO.deleteBoard(inputData.getBoNo());
		
	}

}
