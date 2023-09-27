package kr.or.ddit.memo.dao;

import java.util.List;

import kr.or.ddit.memo.VO.MemoVO;

public interface MemoDAO {

	public int insertMemo(MemoVO memo);
	
	public List<MemoVO> selectList();
	
	public int updateMemo(MemoVO memo);
	
	public int deleteMemo(int code);
}
