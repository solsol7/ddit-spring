package kr.or.ddit.adrs.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.adrs.dao.AddressDAO;
import kr.or.ddit.vo.AddressVO;

@Service
public class AddressServiceImpl implements AddressService {

	@Inject
	private AddressDAO dao;
	
	@Override
	public boolean createAddress(AddressVO adrsVO) {
		int rowcnt = dao.insertAddress(adrsVO);
		return rowcnt >=1;
	}

	@Override
	public List<AddressVO> retriveAddressList(String memId) {
		return dao.selectAddressList(memId);
	}

	@Override
	public boolean modifyAddress(AddressVO adrsVO) {
		int cnt = dao.updateAddress(adrsVO);
		return cnt>=1;
	}

	@Override
	public boolean removeAddress(int adrsNo) {
		int cnt = dao.deleteAddress(adrsNo);
		return cnt>=1;
	}

}
