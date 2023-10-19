package kr.or.ddit.buyer.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;
import lombok.extern.slf4j.Slf4j;

// /buyer/P10101/form
@Slf4j
@Controller
@RequestMapping("/buyer/{buyerId}")
public class BuyerModifyController {
	@Inject
	private BuyerService service;
	
//	@ModelAttribute("buyer")
//	public BuyerVO buyer() {
//		return new BuyerVO();
//	}
	
	@Inject
	private OthersDAO dao;
	
	@ModelAttribute("lprodList")
	public List<LprodVO> lprodList(){
		return dao.selectLprodList();
	}
	
	@GetMapping("form")
	public String buyerUpdateForm(@PathVariable String buyerId, Model model) {
		BuyerVO buyer = service.retrieveBuyer(buyerId);
		if(buyer==null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"해당 제조사 없음.");
		model.addAttribute("buyer", buyer);
		return "buyer/buyerEdit";
	}
	
	@PutMapping
	public String buyerUpdate(@Validated @ModelAttribute("buyer") BuyerVO buyer, Errors errors, Model model) {
		boolean valid = !errors.hasErrors();
		String viewName = "";
		if(valid) {
			ServiceResult result = service.modifyBuyer(buyer);
			switch (result) {
			case OK:
				viewName="redirect:/buyer/{buyerId}";
				break;
			default:
				model.addAttribute("message", "서버 오류");
				viewName="buyer/buyerEdit";
			}
		}else {
			model.addAttribute("errors", errors);
			viewName="buyer/buyerEdit";
		}
		
		return viewName; // 경로변수 그대로 넣어줄 수 있음
	}
}
