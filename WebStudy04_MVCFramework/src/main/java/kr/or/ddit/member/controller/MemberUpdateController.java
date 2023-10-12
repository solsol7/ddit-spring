package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
import kr.or.ddit.file.utils.MultipartFile;
import kr.or.ddit.file.utils.StandardMultipartHttpServletRequest;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.TilesViewResolver;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidationUtils;
import kr.or.ddit.validate.grouphint.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberUpdateController{
	private MemberService service = new MemberServiceImpl();

	@RequestMapping("/member/memberUpdate.do")
	public String updateUI(Principal principal, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String memId = principal.getName();
		
		MemberVO member = service.retrieveMember(memId);

		// 정보 request로 전달
		req.setAttribute("member", member);

		return "member/memberForm";
		
		
	}

	@RequestMapping(value = "/member/memberUpdate.do", method = RequestMethod.POST)
	public String memberUpdate(@ModelAttribute("member") MemberVO member, HttpServletRequest req) throws IOException {

		if(req instanceof StandardMultipartHttpServletRequest) {
			MultipartFile memImage = ((StandardMultipartHttpServletRequest) req).getFile("memImage");
			if(memImage != null && !memImage.isEmpty()) {
				member.setMemImg(memImage.getBytes());
			}
		}

		// 검증...? - memberVO, errors 필요
		Map<String, List<String>> errors = new HashMap<>();
		boolean valid = ValidationUtils.validate(member, errors, UpdateGroup.class);
		req.setAttribute("errors", errors);
		// 검증통과
		String viewName = null;
		if(valid) {
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
				// INVALIDPASSWORD
				// message 저장 - 비밀번호 오류
				// memberForm 이동
				req.setAttribute("message", "비밀번호 오류");
				viewName = "member/memberForm";
				break;
			case OK:
				// OK
				// 웰컴페이지 이동
				viewName = "redirect:/mypage";
				break;
			default:
				// FAIL
				// message 저장 - 서버 오류
				// memberForm 이동
				req.setAttribute("message", "서버 오류");
				viewName = "member/memberForm";
				break;
			}
		}else {
			// 불통
			// memberForm 이동
			viewName = "member/memberForm";
		}

		return viewName;
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
