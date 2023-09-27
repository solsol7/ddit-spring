package kr.or.ddit.memo.service;

import java.util.List;

import kr.or.ddit.memo.VO.MemoVO;

public interface MemoService {

	public boolean createMemo(MemoVO memo);

	public List<MemoVO> selectRetrieveMemo();

	public boolean modifyMemo(MemoVO memo);

	public boolean removeMemo(int code);
}
