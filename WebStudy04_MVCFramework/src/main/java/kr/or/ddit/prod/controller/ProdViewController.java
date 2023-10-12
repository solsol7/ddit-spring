package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdViewController{
	private ProdService service = new ProdServiceImpl();

	@RequestMapping("/prod/prodView.do") //	어떤 요청을 처리할거다 라는 매핑구조
	public String prodView(@RequestParam("what") String prodId, HttpServletRequest req){
		// DB에서 데이터 받아오기
		ProdVO prod = service.retrieveProd(prodId);
		
		// request에 저장하기
		req.setAttribute("prod", prod);
		
		// view로 이동
		return "prod/prodView";
		
	}
}
