package kr.or.ddit.adrs.service;

import java.util.List;

import kr.or.ddit.vo.AddressVO;

/**
 * 주소록 관리를 위한 Business Logic Layer
 * @author PC-16
 *
 */
public interface AddressService {
	/**
	 * 신규 주소 등록
	 * @param adrsVO
	 * @return 등록 성공시(>=1)
	 */
	public boolean createAddress(AddressVO adrsVO);

	/**
	 * 특정 소유자의 주소록 조회
	 * @param memId 검색 조건에 사용될 소유자 아이디
	 * @return size 에 따라 주소록 존재 여부 확인
	 */
	public List<AddressVO> retriveAddressList(String memId);
	
	/**
	 * 기존 주소록 수정
	 * @param adrsVO 수정할 대상이 되는 정보를 가진 객체
	 * @return 수정 성공시(>=1)
	 */
	public boolean modifyAddress(AddressVO adrsVO);
	
	/**
	 * 주소록 삭제
	 * @param adrsNo 삭제할 주소록 번호
	 * @return 삭제 성공시(>=1)
	 */
	public boolean removeAddress(int adrsNo);
	
}
