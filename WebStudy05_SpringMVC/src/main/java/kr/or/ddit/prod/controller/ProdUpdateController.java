package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
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
	
	/*
	@Value("#{appInfo.prodImagesUrl}")
	private String prodImagesUrl;
	@Value("#{appInfo.prodImagesUrl}")
	private Resource prodImages;
	
	private File saveFolder;

	@PostConstruct
	private void init() throws IOException {
		saveFolder = prodImages.getFile();
	}
	
	*/
	@ModelAttribute("lprodList")
	public List<LprodVO> lprodList(){
		return othersDAO.selectLprodList();
	}
	
	@ModelAttribute("buyerList")
	public List<BuyerVO> buyerList(){
		return othersDAO.selectBuyerList(null);
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
	
	/**
	 * 스프링을 이용한 객체 검증 (H/V + Spring)
	 * 1. 검증 대상이 되는 command object 에 @Valid / @Validated(group hints) 사용
	 * 2. commend object 바로 다음 파라미터로 검증 결과(Errors / BindingResult) 객체 선언
	 * 3. errors.hasError 로 검증 통과 여부 확인
	 * 4. 검증 결과 메시지 출력시, form:errors 커스텀 태그 활용
	 */
	@RequestMapping(value = "/prod/prodUpdate.do", method = RequestMethod.POST)
	public String doPost(
			@Validated(UpdateGroup.class) @ModelAttribute("prod") ProdVO prod
				//Valid -> 그룹힌트 못넣음, Validated-> 그룹힌트 넣을 수 있음
			, Errors errors // 검증의 결과 어뎁터에게 받아오기
							// 순서 바꾸면 어뎁터는 검증결과 넣어주지 못함 .. errors 사용할때 제약조건
							// 검증의 대상이 되는 커맨드 오브젝트 바로 다음에 선언해야함
			, Model model
			) throws IOException{
//		prod.saveTo(saveFolder);

		// 오류메세지 담을곳
//		Map<String, List<String>> errors = new HashMap<>();
//		model.addAttribute("errors", errors);
		
		String viewName = "";
		// 검증 - 오류메세지
//		boolean valid = ValidationUtils.validate(prod, errors, UpdateGroup.class);
		// 검증 통과
		
		boolean valid = !errors.hasErrors();
		
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
