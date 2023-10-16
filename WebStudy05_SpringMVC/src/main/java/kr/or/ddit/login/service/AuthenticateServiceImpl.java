package kr.or.ddit.login.service;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.vo.MemberVO;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {
	
	@Inject
	private MemberDAO memberDAO;
	
	@Override
	public ServiceResult authenticate(MemberVO inputData) {
		MemberVO saved = memberDAO.selectMemberForAuth(inputData);
		ServiceResult result = null;
		if(saved!=null) {
			String inputPass = inputData.getMemPass();
			String savePass = saved.getMemPass();
			if(savePass.equals(inputPass)) {
				try {
					BeanUtils.copyProperties(inputData, saved);
					result = ServiceResult.OK;
				} catch (IllegalAccessException | InvocationTargetException e) {
					throw new RuntimeException(e);
				}
			}else {
				result = ServiceResult.INVALIDPASSWORD;
			}
		}else {
			result = ServiceResult.NOTEXIST;
		}
		return result;
	}

}
