package kr.or.ddit.common.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.ViewResolverComposite;

@WebServlet("/index.do")
public class IndexControllerServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = "컨트롤러에서 만든 Model 타이틀";
		
		req.setAttribute("title", title);
		
		String viewName = "index";
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
	/*
    String goPage = "redirect:/";
    
    if(goPage.startsWith("redirect")) { //redirection
       String location = req.getContextPath() + goPage.substring("redirect:".length());
       resp.sendRedirect(location); //goPage 에서 redirect 떼고 contextpath 붙여줘야함
    }else { //dispatch
       req.getRequestDispatcher(goPage).forward(req, resp);
    }
    */
}
