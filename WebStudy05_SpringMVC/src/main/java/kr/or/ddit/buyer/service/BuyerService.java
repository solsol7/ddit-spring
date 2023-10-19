package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PaginationInfo;

public interface BuyerService {
	/**
	 * 제조사 상세 조회
	 * @param buyerId
	 * @return 존재하지 않으면, null 반환
	 */
	public BuyerVO retrieveBuyer(String buyerId);
	
	/**
	 * 제조사 등록
	 * @param buyer
	 * @return
	 */
	public ServiceResult createBuyer(BuyerVO buyer);
	
	/**
	 * 수정
	 * @param buyer
	 * @return
	 */
	public ServiceResult modifyBuyer(BuyerVO buyer);
	
	public void retrieveBuyerList(PaginationInfo<BuyerVO> paging);
}
