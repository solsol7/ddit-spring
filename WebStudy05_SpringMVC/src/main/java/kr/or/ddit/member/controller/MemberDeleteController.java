package kr.or.ddit.member.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.filter.wrapper.MemberVOWrapper;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberDeleteController{
	
	@Inject
	private MemberService service;
	
	@PostMapping(value="/member/memberDelete.do")
	public String doPost(
		MemberVOWrapper principal	// -> interceptor로 자원을 보호하고 있기 때문에 null일 수 없음
		, @RequestParam("password") String password // -> 검증 이미 끝남 .. required 속성 생략 - 없으면 400에러뜸
		, RedirectAttributes redirectAttributes // 플래시 어트리뷰트 사용할 수 있도록 reDirectAttributes 사용
		, HttpSession session
	){
		String memId = principal.getName();

		MemberVO inputData = new MemberVO();
		inputData.setMemId(memId);
		inputData.setMemPass(password);

		String viewName = null;
		ServiceResult result = service.removeMember(inputData);
		switch (result) {
		case INVALIDPASSWORD:
			viewName = "redirect:/mypage";
			redirectAttributes.addFlashAttribute("message", "비밀 번호 오류"); // flash attribute
			break;
		case OK:
			viewName = "redirect:/";
			session.invalidate();
			break;
		default:
			viewName = "redirect:/mypage";
			redirectAttributes.addFlashAttribute("message", "서버 오류"); // flash attribute
			break;
		}
		
//		redirect로 이동 -> request를 사용하고 있지 않기 때문에 모델을 안받음
		
		return viewName;
				
	}
}


