package kr.or.ddit.member.controller;

import java.security.Principal;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.validate.grouphint.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberUpdateController{
	@Inject
	private MemberService service;
	
	@GetMapping("/member/memberUpdate.do")
 	public String updateForm(Principal principal, Model model) {
		
		String memId = principal.getName();
		
		MemberVO member = service.retrieveMember(memId);
		
		model.addAttribute("member", member);
		
		return "member/memberForm";
	}
	

	@PostMapping(value = "/member/memberUpdate.do")
	public String updateProcess(
		@Validated(UpdateGroup.class) @ModelAttribute("member") MemberVO member
		, Errors errors
		, Model model
	){
		boolean valid = !errors.hasErrors();
		
		String viewName = null;
		if(valid) {
//			통과
//				4. modifyMember 수정 처리
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
//					1) INVALIDPASSWORD 
//						memberForm 으로 이동 (기존 입력 데이터, 메시지, dispatch)
				model.addAttribute("message", "비밀번호 오류");
				viewName = "member/memberForm";
				break;
			case OK:
//					2) OK 
//						/mypage 로 이동 (redirect)
				viewName = "redirect:/mypage";
				break;
			default:
//					3) FAIL
//						memberForm 으로 이동 (기존 입력 데이터, 메시지, dispatch)
				model.addAttribute("message", "서버 오류, 쫌따 다시 해보셈.");
				viewName = "member/memberForm";
				break;
			}
		}else {
//			불통
//				memberForm 으로 이동 (기존 입력 데이터, 검증 결과 메시지들.., dispatch)
			viewName = "member/memberForm";
		}
		return viewName;
	}

}











