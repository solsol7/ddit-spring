package kr.or.ddit.member.controller;

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
import kr.or.ddit.validate.grouphint.InsertGroup;
import kr.or.ddit.vo.MemberVO;



@Controller
public class MemberInsertController {
	@Inject
	private MemberService service;
	
	@GetMapping("/member/memberInsert.do")
	public String memberForm(){
		return "member/memberForm";
	}
	
	@ModelAttribute("member")
	public MemberVO member() {
		return new MemberVO();
	}
	
	@PostMapping(value="/member/memberInsert.do")
	public String insertProcess(
		@Validated(InsertGroup.class) @ModelAttribute("member") MemberVO member
		, Errors errors
		, Model model
	){
//		Map<String, List<String>> errors = new HashMap<>();
//		model.addAttribute("errors", errors);
//		3. 검증 (대상 : MemberVO)
//		boolean valid = ValidationUtils.validate(member, errors, InsertGroup.class);
		
		boolean valid = !errors.hasErrors();
		
		String viewName = null;
		if(valid) {
//			통과
//				4. createMember 등록 처리
			ServiceResult result = service.createMember(member);
			switch (result) {
			case PKDUPLICATED:
//					1) PKDUPLICATED 
//						memberForm 으로 이동 (기존 입력 데이터, 메시지, dispatch)
				model.addAttribute("message", "아이디 중복");
				viewName = "member/memberForm";
				break;
			case OK:
//					2) OK 
//						welcome page로 이동 (redirect)
				viewName = "redirect:/";
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
