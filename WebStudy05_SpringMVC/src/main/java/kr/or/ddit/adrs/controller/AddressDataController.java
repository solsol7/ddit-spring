package kr.or.ddit.adrs.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.adrs.service.AddressService;
import kr.or.ddit.vo.AddressVO;

/**
 *  /adrs/address (GET)
 *  /adrs/address (POST)
 *  /adrs/address/23 (GET)
 *  /adrs/address/23 (PUT)
 *  /adrs/address/23 (DELETE)
 */
@Controller
@RequestMapping("/adrs/address")
public class AddressDataController{
	@Inject
	private AddressService service;
	
	@GetMapping
	public String adrsList(Principal principal, Model model) {

		String memId = principal.getName();
		List<AddressVO> adrsList = service.retriveAddressList(memId);
		
		model.addAttribute("adrsList", adrsList);
		
		return "jsonView";
	}
	
//    private ObjectMapper mapper = new ObjectMapper();

	@PostMapping		
	public String doPost(
			@Valid @RequestBody AddressVO vo // RequestBody -> 핸들러 어뎁터에게 파라미터에서 꺼낼게 아니라 리퀘스트 바디에 들어있는 컨텐츠를 언마셜링해야해
			, Errors errors
			, Model model
			, Principal principal){
//		try(
//			InputStream is = req.getInputStream();	// request안에 있는 원문 데이터 꺼내기 (json)
//		){
//			AddressVO vo = mapper.readValue(is, AddressVO.class);	// 언마셜링
			model.addAttribute("originalData", vo);
			
//			String authId = (String) req.getSession().getAttribute("authId");
			
			vo.setMemId(principal.getName());
			
			boolean valid = !errors.hasErrors();
			
			boolean success = false;
			String message = null;
			if(valid) {
				if(service.createAddress(vo)) {
					success = true;
				}else {
					message = "등록 실패";
				}
			}else {
				message = "필수파라미터 누락";
			}
			
			model.addAttribute("success", success);
			model.addAttribute("message", message);
//		}
		
		return "jsonView";
	}
	
//	/adrs/address/23
	
	@DeleteMapping("{adrsNo}")
	public String doDelete(@PathVariable int adrsNo, Model model) throws ServletException, IOException {
		boolean success = service.removeAddress(adrsNo);
		model.addAttribute("success", success);
		if(!success) {
			model.addAttribute("message", "삭제 실패");
		}
		
		return "jsonView";
	}
}
