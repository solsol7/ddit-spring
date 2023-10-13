package kr.or.ddit.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
