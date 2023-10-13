package kr.or.ddit.prod.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdViewController{
	@Inject
	private ProdService service;

	@RequestMapping("/prod/prodView.do") //	어떤 요청을 처리할거다 라는 매핑구조
	public String prodView(@RequestParam("what") String prodId, Model model){
		// DB에서 데이터 받아오기
		ProdVO prod = service.retrieveProd(prodId);
		
		// request에 저장하기
		model.addAttribute("prod", prod);
		
		// view로 이동
		return "prod/prodView";
		
	}
}
