package kr.or.ddit.adrs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AddressUIController{
	@RequestMapping("/adrs/view")
	public String addressUI(){
		return "adrs/adrsView";
		
	}
}
