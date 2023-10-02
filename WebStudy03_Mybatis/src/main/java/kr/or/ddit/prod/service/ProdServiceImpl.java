package kr.or.ddit.prod.service;

import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.prod.dao.ProdDaoImpl;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements ProdService{
	
	private ProdDAO dao = new ProdDaoImpl();
	
	@Override
	public ProdVO retrieveProd(String prodId) {
		return dao.selectProd(prodId);
	}

}
