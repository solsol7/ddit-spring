package kr.or.ddit.buyer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.vo.BuyerVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BuyerRetrieveController {
	private final BuyerService service;
	
	@RequestMapping("/buyer/buyerView.do")
	public String retrieveBuyer(
			@Valid @RequestParam("what") String buyerId
			, Model model) {
		// 결과값 받기
		BuyerVO buyer = service.retrieveBuyer(buyerId);
		
		// scope 저장
		model.addAttribute(buyer);
		
		// view 리턴
		return "buyer/buyerView";
		
	}
}
