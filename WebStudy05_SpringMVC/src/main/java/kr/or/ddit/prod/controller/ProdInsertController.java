package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.ValidationUtils;
import kr.or.ddit.validate.grouphint.InsertGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@WebServlet("/prod/prodInsert.do")
//@MultipartConfig

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProdInsertController{
	@Value("/resources/prodImages")
	private String prodImagesUrl;
				// binary Data가 저장되는 곳 -> Web resource 형태로 저장
				// 파일을 저장할 때 원본파일 이름을 그대로 저장하지 않음, 확장자명 없애는 것이 좋음
	@Value("/resources/prodImages")
	private Resource prodImages;
	
	private File saveFolder;
	
	@PostConstruct // 생성자 이후 모든 주입이 완료된 이후에 실행해줘 - 그냥 init만 쓴다고 되는거 X
	public void init() throws IOException {
	// 모든 주입이 끝난 후 동작
		saveFolder = prodImages.getFile();
		log.info("상품 이미지 저장 위치 : {}", saveFolder.getCanonicalPath());
	}
	
	private final ProdService service;
	@Inject
	private OthersDAO othersDAO;
	

	@ModelAttribute("lprodList")
	public List<LprodVO> lprodList(){
		return othersDAO.selectLprodList();
	}
	
	@ModelAttribute("buyerList")
	public List<BuyerVO> buyerList(){
		return othersDAO.selectBuyerList(null);
	}
	
	@RequestMapping("/prod/prodInsert.do")
	public String prodForm(){
		return "prod/prodForm";
	}
	
	@RequestMapping(value = "/prod/prodInsert.do", method = RequestMethod.POST)
	public String prodInsert(
			MultipartFile prodImage
			, @ModelAttribute("prod") ProdVO prod
			, Model model
	) throws IOException{
			if(!prodImage.isEmpty()) {
				String filename = UUID.randomUUID().toString();
				File saveFile = new File(saveFolder, filename);
				// 상품 이미지의 2진 데이터 저장
				prodImage.transferTo(saveFile);				
				prod.setProdImg(filename);
			}
		
		
		Map<String, List<String>> errors = new HashMap<>();
		model.addAttribute("errors", errors);
		boolean valid = ValidationUtils.validate(prod, errors, InsertGroup.class);
		
		String viewName = null;
		if(valid) {
			ServiceResult result = service.createProd(prod);
			switch (result) {
			case OK:
				viewName = "redirect:/prod/prodView.do?what="+prod.getProdId();
				break;
			default:
				model.addAttribute("message", "서버 오류, 쫌따 다시 해보셈.");
				viewName = "prod/prodForm";
				break;
			}
		}else {
			viewName = "prod/prodForm";
		}
		
		return viewName;
		
	}
}
