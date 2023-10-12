package kr.or.ddit.common.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
// POJO(Plain Old Java Object)
@Controller
public class IndexController{
	
	@RequestMapping("/index.do")
	public String index(HttpServletRequest req){
		String title = "컨트롤러에서 만든 Model 타이틀";
		
		req.setAttribute("title", title);
		
		return "index";
		
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
