package com.springboard.board.service;

import java.util.List;

import com.springboard.board.BoardNotFoundException;
import com.springboard.board.vo.AttatchVO;
import com.springboard.board.vo.FreeBoardVO;
import com.springboard.paging.vo.PaginationInfo;

public interface BoardService {
	/**
	 * 게시글 상세조회
	 * @param board
	 * @return 존재하지 않으면, {@link BoardNotFoundException} 발생, 동시에 404 응답 전송
	 */
	public void createBoard(FreeBoardVO board);
	public FreeBoardVO retrieveBoard(int boNo);
	
	public AttatchVO retrieveAttatch(int attNo);
	
	public void retrieveBoardList(PaginationInfo<FreeBoardVO> paging);
	
	/**
	 * 게시글 수정과 삭제에서 사용될 인증
	 * @param inputData
	 * @return 인증에 성공했을 경우, 해당 게시글 반환, 실패한 경우, null 반환
	 */
	public FreeBoardVO authenticateBoard(FreeBoardVO inputData);
	
	public void modifyBoard(FreeBoardVO board);
	public void removeBoard(FreeBoardVO inputData);
}
