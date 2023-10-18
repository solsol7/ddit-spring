package kr.or.ddit.buyer.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.annotation.MultipartConfig;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.validate.grouphint.InsertGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;

@Controller
@RequestMapping("/buyer")
@MultipartConfig
public class BuyerInsertController {

	@Inject
	private BuyerService service;
	
	@Inject
	private OthersDAO dao;
	
	@ModelAttribute("buyer")
	public BuyerVO Buyer() {
		return new BuyerVO();
	}
	
	@ModelAttribute("lprodList")
	public List<LprodVO> lprodList() {
		return dao.selectLprodList();
	}

	@GetMapping("form")
	public String buyerInsertForm() {
		return "buyer/buyerForm";
	}
	
	@PostMapping
	public String buyerInsert(
			@Validated(InsertGroup.class) @ModelAttribute("buyer") BuyerVO buyer
			, Errors errors
			, Model model
	) {
		boolean valid = !errors.hasErrors();
		String viewName = "";
		if(valid) {
			ServiceResult result = service.createBuyer(buyer);
			switch (result) {
			case OK:
				viewName="redirect:/buyer/"+buyer.getBuyerId();
				break;
			default:
				model.addAttribute("message", "서버 오류");
				viewName="buyer/buyerForm";
			}
		}else {
			model.addAttribute("errors", errors);
			viewName="buyer/buyerForm";
		}
		
		return viewName;
	}
}
