package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.file.utils.MultipartFile;
import kr.or.ddit.file.utils.StandardMultipartHttpServletRequest;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidationUtils;
import kr.or.ddit.validate.grouphint.UpdateGroup;
import kr.or.ddit.vo.ProdVO;

//@WebServlet("/prod/prodUpdate.do")
//@MultipartConfig
@Controller
public class ProdUpdateController{
	// 서비스 생성
	ProdService service = new ProdServiceImpl();
	private OthersDAO othersDAO = new OthersDAOImpl();
	
	// 파일 저장 경로
	private String prodImagesUrl = "/resources/prodImages";
	
	private void addRequestAttribute(HttpServletRequest req) {
		req.setAttribute("lprodList", othersDAO.selectLprodList());
		req.setAttribute("buyerList", othersDAO.selectBuyerList(null));
	}
	
	@RequestMapping("/prod/prodUpdate.do")
	public String doGet(HttpServletRequest req, @RequestParam(value = "what", required = true) String prodId){
												// required=true => 필수파라미터
		addRequestAttribute(req);

		// 상품정보 ProdVO 가져오기
		ProdVO prod = service.retrieveProd(prodId);
		
		// ProdVO request에 담기
		req.setAttribute("prod", prod);
		
		// view선택
		return "prod/prodEdit";
		
	}
	
	@RequestMapping(value = "/prod/prodUpdate.do", method = RequestMethod.POST)
	public String doPost(@ModelAttribute("prod") ProdVO prod, HttpServletRequest req) throws IOException{
		addRequestAttribute(req);
		
		// request 확인하기
		if(req instanceof StandardMultipartHttpServletRequest) {
			// 원본 req아니면
			// file 얻기 - partName 필요
			MultipartFile file = ((StandardMultipartHttpServletRequest) req).getFile("prodImage");
			
			// file이 있다면
			if(!file.isEmpty()) {
				// file 이름 임의로 설정
				String saveName = UUID.randomUUID().toString();
				// file 이름 ProdVO에 저장
				prod.setProdImg(saveName);
				// file의 binaryData 정해진 경로에 저장
				// 진짜 저장 경로 구하기
				String realPath = req.getServletContext().getRealPath(prodImagesUrl);
				// 저장 - file을 저장
				File saveFile = new File(realPath, saveName);
				file.transferTo(saveFile);
				
			}
				
		}
		
		// 오류메세지 담을곳
		Map<String, List<String>> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
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
				req.setAttribute("message", "서버 오류");
				viewName = "prod/prodEdit";
				break;
			}
		}else {
			viewName = "prod/prodEdit";
		}
		
		return viewName;
	}
}
