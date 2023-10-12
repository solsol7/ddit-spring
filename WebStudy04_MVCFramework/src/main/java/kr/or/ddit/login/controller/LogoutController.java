package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;

@Controller
public class LogoutController{
	
	@RequestMapping(value = "/login/logout.do", method = RequestMethod.POST)
	public String logout(HttpSession session, HttpServletResponse resp) throws IOException{
		//세션 받아오기
		if(session ==null || session.isNew()){
			//로그인하지도 않았는데 로그아웃할때/ 세션 만료되고 로그아웃할때 =>만료시킬 세션 아님(이미 만료됨)
			resp.sendError(400, "로그인 하지도 않았는데!!");
			return null;
		}
		
//		// 1. 세션 스코프 안의 속성 지워야함 - authId
//		session.removeAttribute("authId");
		
		// 2. 세션 만료
		session.invalidate();
		
		// 3. 만료된 이후의 결과 클라이언트에 보여주기 - welcome페이지로 보내기(redirect) 
		
		return "redirect:/";
		
	}
}
