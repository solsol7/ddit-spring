package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.utils.ValidationUtils;
import kr.or.ddit.validate.grouphint.UpdateGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;

//@WebServlet("/prod/prodUpdate.do")
//@MultipartConfig
@Controller
@RequiredArgsConstructor
public class ProdUpdateController{
	// 서비스 생성
	private final ProdService service;
	@Inject
	private OthersDAO othersDAO;
	
	@Value("/resources/prodImages")
	private Resource prodImages;
	
	private File saveFolder;
	
	@ModelAttribute("lprodList")
	public List<LprodVO> lprodList(){
		return othersDAO.selectLprodList();
	}
	
	@ModelAttribute("buyerList")
	public List<BuyerVO> buyerList(){
		return othersDAO.selectBuyerList(null);
	}
	
	@PostConstruct
	private void init() throws IOException {
		saveFolder = prodImages.getFile();
	}
	
	
	@RequestMapping("/prod/prodUpdate.do")
	public String doGet(Model model, @RequestParam(value = "what", required = true) String prodId){
												// required=true => 필수파라미터
		// 상품정보 ProdVO 가져오기
		ProdVO prod = service.retrieveProd(prodId);
		
		// ProdVO request에 담기
		model.addAttribute("prod", prod);
		
		// view선택
		return "prod/prodEdit";
		
	}
	
	@RequestMapping(value = "/prod/prodUpdate.do", method = RequestMethod.POST)
	public String doPost(
			@ModelAttribute("prod") ProdVO prod
			, MultipartFile prodImage
			, Model model
			) throws IOException{
		
		if(!prodImage.isEmpty()) {
			
			String saveName = UUID.randomUUID().toString();
			
			File saveFile = new File(saveFolder, saveName);
			prod.setProdImg(saveName);
			prodImage.transferTo(saveFile);
		}
		// 오류메세지 담을곳
		Map<String, List<String>> errors = new HashMap<>();
		model.addAttribute("errors", errors);
		
		String viewName = "";
		// 검증 - 오류메세지
		boolean valid = ValidationUtils.validate(prod, errors, UpdateGroup.class);
		// 검증 통과
		if(valid) {
			// update
			ServiceResult result = service.modifyProd(prod);
			
			// result
			switch (result) {
			case OK:
				// OK -> prodView
				viewName = "redirect:/prod/prodView.do?what="+prod.getProdId();
				break;
			default:
				// FAIL -> prodEdit, prod가지고
				model.addAttribute("message", "서버 오류");
				viewName = "prod/prodEdit";
				break;
			}
		}else {
			viewName = "prod/prodEdit";
		}
		
		return viewName;
	}
}
