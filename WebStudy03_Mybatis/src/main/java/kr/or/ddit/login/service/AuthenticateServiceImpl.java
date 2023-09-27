package kr.or.ddit.login.service;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements AuthenticateService {

	private MemberDAO memberDAO = new MemberDAOImpl();
	
	@Override
	public ServiceResult authenticate(MemberVO inputData) {
		MemberVO saved = memberDAO.selectMemberForAuth(inputData);
		ServiceResult result = null;
		if(saved!=null) {
			String inputPass = inputData.getMemPass();
			String savePass = saved.getMemPass();
			if(savePass.equals(inputPass)) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.INVALIDPASSWORD;
			}
		}else {
			result = ServiceResult.NOTEXIST;
		}
		return result;
	}

}
