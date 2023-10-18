package kr.or.ddit.buyer.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.vo.BuyerVO;
import lombok.extern.slf4j.Slf4j;

// /buyer/P10101/form
@Slf4j
@Controller
@RequestMapping("/buyer")
public class BuyerModifyController {
	@Inject
	private BuyerService service;
	
//	@ModelAttribute("buyer")
//	public BuyerVO buyer() {
//		return new BuyerVO();
//	}
	
	@GetMapping("{buyerId}/form")
	public String buyerUpdateForm(@PathVariable String buyerId, Model model) {
		BuyerVO buyer = service.retrieveBuyer(buyerId);
		model.addAttribute("buyer", buyer);
		return "buyer/buyerEdit";
	}
	
	@PutMapping("{buyerId}")
	public String buyerUpdate(@ModelAttribute("buyer") BuyerVO buyer, Errors errors, Model model) {
		boolean valid = !errors.hasErrors();
		String viewName = "";
		if(valid) {
			ServiceResult result = service.modifyBuyer(buyerId);
			switch (result) {
			case OK:
				viewName="redirect:/buyer/{buyerId}";
				break;
			default:
				model.addAttribute("message", "서버 오류");
				viewName="buyer/buyerForm";
			}
		}else {
			model.addAttribute("errors", errors);
			viewName="buyer/buyerForm";
		}
		
		return ""; // 경로변수 그대로 넣어줄 수 있음
	}
}
