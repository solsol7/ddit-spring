package kr.or.ddit.memo.service;

import java.util.List;

import kr.or.ddit.memo.VO.MemoVO;
import kr.or.ddit.memo.dao.MemoDAO;
import kr.or.ddit.memo.dao.MemoDAOImpl;
import oracle.security.o5logon.d;

public class MemoServiceImpl implements MemoService {
	private MemoDAO dao = new MemoDAOImpl();

	@Override
	public boolean createMemo(MemoVO memo) {
		return dao.insertMemo(memo) > 0;
	}

	@Override
	public List<MemoVO> selectRetrieveMemo() {
		return dao.selectList();
	}

	@Override
	public boolean modifyMemo(MemoVO memo) {
		return dao.updateMemo(memo) > 0;
	}

	@Override
	public boolean removeMemo(int code) {
		return dao.deleteMemo(code) > 0;
	}

	

}
