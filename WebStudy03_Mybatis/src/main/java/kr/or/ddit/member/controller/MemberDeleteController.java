package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.utils.ValidationUtils;
import kr.or.ddit.validate.grouphint.DeleteGroup;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberDelete.do")
public class MemberDeleteController extends HttpServlet{
	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String memId = (String)session.getAttribute("authId");
		
		req.setCharacterEncoding("UTF-8");
		
		String password = req.getParameter("password");
		
		MemberVO inputData = new MemberVO();
		inputData.setMemId(memId);
		inputData.setMemPass(password);
		
		Map<String, List<String>> errors = new HashMap<>();
		
		boolean valid = ValidationUtils.validate(inputData, errors, DeleteGroup.class);
		String viewName;
		// 쿼리문 실행
		if(valid) {
			ServiceResult result = service.removeMember(inputData);
			switch (result) {
			case INVALIDPASSWORD:
				viewName = "redirect:/mypage";
				session.setAttribute("message", "비밀번호 오류");	// flash attribute
				break;
			case OK:
				// 로그인화면 보내기?
				req.getSession().invalidate();
				viewName = "redirect:/index.do";
				break;
			default:
				viewName = "redirect:/mypage";
				session.setAttribute("message", "서버 오류");
				break;
			}
		}else {
			viewName = "redirect:/mypage";
			session.setAttribute("message", "비밀 번호 누락");
		}
		
		
		new ViewResolverComposite().resolveView(viewName, req, resp);
		
	}
}
