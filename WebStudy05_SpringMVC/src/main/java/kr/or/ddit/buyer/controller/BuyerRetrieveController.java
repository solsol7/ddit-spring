package kr.or.ddit.buyer.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.paging.BootstrapPaginationRenderer;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.LprodVO;
import kr.or.ddit.vo.PaginationInfo;
import lombok.RequiredArgsConstructor;

/**
 * 	GET/POST
 *	/buyer/buyerView.do
 *	/buyer/buyerList.do
 *	/buyer/buyerInsert.do
 *	/buyer/buyerUpdate.do
 *	/buyer/buyerDelete.do
 *
 *	Restful URI (GET/POST/PUT/DELETE) : 명사(URI), 동사(method)
 *	/buyer/form (GET) : 제조사 등록 양식을 달라
 *	/buyer (POST) : 제조사 등록
 *
 *	/buyer (GET) : 제조사 목록 조회
 *	/buyer/P10101 (GET) : 제조사 상세 조회 - 어떤 제조사 라는 정보도 uri에 담겨있음
 *
 *	/buyer/P10101/form (GET) : 제조사 수정 양식을 달라
 *	/buyer/P10101 (PUT) : 제조사 수정
 *
 *	/buyer/P10101 (DELETE) : 제조사 삭제
 *
 */
@Controller
@RequestMapping("/buyer")	// uri의 /buyer 중복 해결 .. 계층구조(클래스-메소드)있으면 스프링이 / 넣어줌 
public class BuyerRetrieveController {
	@Inject
	private BuyerService service;
	
	@Inject
	private OthersDAO othersDAO;
	
	@ModelAttribute("lprodList")
	public List<LprodVO> lprodList(){
		return othersDAO.selectLprodList();
	}
	
	@GetMapping
	public String buyerListRestful(
			@RequestParam(required = false, defaultValue = "1") int page
			, @ModelAttribute("detailCondition") BuyerVO detailCondition
			, Model model
			) {
		PaginationInfo<BuyerVO> paging = new PaginationInfo<BuyerVO>(4, 2);
		paging.setCurrentPage(page);
		paging.setDetailCondition(detailCondition);
		
		paging.setRenderer(new BootstrapPaginationRenderer());
		
		service.retrieveBuyerList(paging);
		
		model.addAttribute("paging",paging);
		
		return "buyer/buyerList";
	}
	
//	RequestParam -> 필요한 파라미터로 찾음..
//	PathVariable -> 필요한 uri에 포함되어있음 .. -> 경로변수
//	필요한 데이터를 요청 파라미터에서 찾느냐 경로에서 찾느냐
	@GetMapping("{buyerId}")	// Argument처리 - 경로변수
	public String buyerViewRestful(@PathVariable String buyerId, Model model) {
		BuyerVO buyer = service.retrieveBuyer(buyerId);
		model.addAttribute("buyer",buyer);
		return "buyer/buyerView";
	}
	
/*	
	@RequestMapping("/buyer/buyerView.do")
	public String retrieveBuyer(
			@Valid @RequestParam("what") String buyerId
//			RequestParam의 required 속성 기본값 true -> buyerId빠지면 자동으로 404에러
			, Model model) {
//		Model -> callByReference구조 .. 참조주소를 어뎁터도 가지고있음
		// 결과값 받기
		BuyerVO buyer = service.retrieveBuyer(buyerId);
		
		// scope 저장
		model.addAttribute("buyer",buyer);
		
		// view 리턴
		return "buyer/buyerView";
		
	}
*/
	
/*
	@RequestMapping("/buyer/buyerView.do")
		public ModelAndView buyerView(@Valid @RequestParam("what") String buyerId) {
		BuyerVO buyer = service.retrieveBuyer(buyerId);
		ModelAndView mav = new ModelAndView();
		mav.addObject("buyer",buyer);
		mav.setViewName("buyer/buyerView");
		return mav;
	}
}
*/

/*
//	RequestToViewNameTranslator 전략 객체 동작
//	ViewName을 Request에서 찾아버림
	@RequestMapping("/buyer/buyerView.do")
	public void buyerView(
			@Valid @RequestParam("what") String buyerId
			, Model model) {
		BuyerVO buyer = service.retrieveBuyer(buyerId);
		model.addAttribute("buyer",buyer);
//		반환값 없이 나가면 requestUrl에서 컨텍스트패스, / 버리고 확장자 버려서 url 만들어버림
		
//		return "buyer/buyerView";
	}
}
*/


}