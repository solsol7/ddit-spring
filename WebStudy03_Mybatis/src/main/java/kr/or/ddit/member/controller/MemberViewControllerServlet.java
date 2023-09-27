package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberView.do")
public class MemberViewControllerServlet extends HttpServlet{
	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		// 파라미터 받아오기
		String memId = (String)req.getParameter("who");
		
		// VO 정보 받아오기
		MemberVO member = service.retrieveMember(memId);
		
		// 정보 request에 저장
		req.setAttribute("member", member);
		
		// ..어디로보내?
		String viewName = "member/ajax/memberView";
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
}
