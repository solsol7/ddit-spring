package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.prod.dao.ProdDaoImpl;
import kr.or.ddit.vo.PaginationInfo;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements ProdService{
	
	private ProdDAO dao = new ProdDaoImpl();
	
	@Override
	public ProdVO retrieveProd(String prodId) {
		return dao.selectProd(prodId);
	}

	@Override
	public void retreiveProdList(PaginationInfo<ProdVO> paging) {
		int totalRecord = dao.selectTotalRecord();
		paging.setTotalRecord(totalRecord);
		
		List<ProdVO> dataList = dao.selectProdList(paging);
		paging.setDataList(dataList);
	}

}
