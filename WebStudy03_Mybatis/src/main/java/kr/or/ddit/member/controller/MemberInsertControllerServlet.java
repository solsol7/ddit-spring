package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.TilesViewResolver;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidationUtils;
import kr.or.ddit.validate.grouphint.InsertGroup;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberInsert.do")
public class MemberInsertControllerServlet extends HttpServlet {
	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String viewName = "member/memberForm";
		
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		2. 파라미터 확보 --> MemberVO
		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
		Map<String, String[]> parameterMap = req.getParameterMap();
//		String memId = req.getParameter("memId");
//		member.setMemId(memId);
		
		PopulateUtils.populate(member, parameterMap);

		Map<String, List<String>> errors = new HashMap<>();
		req.setAttribute("errors", errors);
//		3. 검증 (대상 : MemberVO)
		//boolean valid = validate(member, errors);
		boolean valid = ValidationUtils.validate(member, errors, InsertGroup.class);
		String viewName = null;
		if(valid) {
//			통과
//				4. createMember 등록 처리
			ServiceResult result = service.createMember(member);
			switch (result) {
			case PKDUPLICATED:
//					1) PKDUPLICATED
//						memberForm으로 이동 (기존 입력 데이터, 메시지, dispatch)
				req.setAttribute("message", "아이디 중복");
				viewName = "member/memberForm";
				break;
			case OK:
//					2) OK
//						welcome page로 이동 (redirect)
				viewName = "redirect:/";
				break;
			default:
//					3) FAIL
//						memberForm으로 이동 (기존 입력 데이터, 메시지, dispatch)
				req.setAttribute("message", "서버 오류");
				viewName = "member/memberForm";
				break;
			}
			
		}else {
//			불통
//				memberForm으로 이동 (기존 입력 데이터, 검증 결과 메시지들... , dispatch)
			viewName = "member/memberForm";
			
		}


		new ViewResolverComposite().resolveView(viewName, req, resp);

	}
	
/*
	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;
		if (StringUtils.isBlank(member.getMemId())) {
			valid = false;
			errors.put("memId", "회원아이디 누락");
		}
		if (StringUtils.isBlank(member.getMemPass())) {
			valid = false;
			errors.put("memPass", "비밀번호 누락");
		}
		if (StringUtils.isBlank(member.getMemName())) {
			valid = false;
			errors.put("memName", "회원명 누락");
		}
		if (StringUtils.isBlank(member.getMemZip())) {
			valid = false;
			errors.put("memZip", "우편번호 누락");
		}
		if (StringUtils.isBlank(member.getMemAdd1())) {
			valid = false;
			errors.put("memAdd1", "주소1 누락");
		}
		if (StringUtils.isBlank(member.getMemAdd2())) {
			valid = false;
			errors.put("memAdd2", "주소2 누락");
		}
		if (StringUtils.isBlank(member.getMemMail())) {
			valid = false;
			errors.put("memMail", "이메일 누락");
		}
		return valid;
	}
 */
}