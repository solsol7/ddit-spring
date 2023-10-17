package kr.or.ddit.buyer.service;

import org.springframework.stereotype.Service;

import kr.or.ddit.buyer.dao.BuyerDAO;
import kr.or.ddit.vo.BuyerVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuyerServiceImpl implements BuyerService{
	
	private final BuyerDAO dao;
	
	@Override
	public BuyerVO retrieveBuyer(String buyerId) {
		return dao.selectBuyer(buyerId);
	}

}
