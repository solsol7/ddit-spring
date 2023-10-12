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

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.file.utils.MultipartFile;
import kr.or.ddit.file.utils.StandardMultipartHttpServletRequest;
import kr.or.ddit.mvc.ViewResolverComposite;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.utils.ValidationUtils;
import kr.or.ddit.validate.grouphint.InsertGroup;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodInsert.do")
@MultipartConfig
public class ProdInsertControllerServlet extends HttpServlet{
	private String prodImagesUrl = "/resources/prodImages";
				// binary Data가 저장되는 곳 -> Web resource 형태로 저장
				// 파일을 저장할 때 원본파일 이름을 그대로 저장하지 않음, 확장자명 없애는 것이 좋음
	
	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersDAO = new OthersDAOImpl();
	
	private void addRequestAttribute(HttpServletRequest req) {
		req.setAttribute("lprodList", othersDAO.selectLprodList());
		req.setAttribute("buyerList", othersDAO.selectBuyerList(null));
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addRequestAttribute(req);
		
		String viewName = "prod/prodForm";
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		addRequestAttribute(req);
		
		// 파라미터 확보 --> ProdVO
		ProdVO prod = new ProdVO();
		req.setAttribute("prod", prod);
		Map<String, String[]> parameterMap = req.getParameterMap();
		PopulateUtils.populate(prod, parameterMap);
		
		// multipart 처리
		if(req instanceof StandardMultipartHttpServletRequest) {
			MultipartFile prodImage = ((StandardMultipartHttpServletRequest) req).getFile("prodImage");
			if(!prodImage.isEmpty()) {
				String realPath = req.getServletContext().getRealPath(prodImagesUrl);
				File saveFolder = new File(realPath);
				String filename = UUID.randomUUID().toString();
				File saveFile = new File(saveFolder, filename);
				// 상품 이미지의 2진 데이터 저장
				prodImage.transferTo(saveFile);				
				prod.setProdImg(filename);
			}
		}
		
		
		Map<String, List<String>> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = ValidationUtils.validate(prod, errors, InsertGroup.class);
		
		String viewName = null;
		if(valid) {
			ServiceResult result = service.createProd(prod);
			switch (result) {
			case OK:
				viewName = "redirect:/prod/prodView.do?what="+prod.getProdId();
				break;
			default:
				req.setAttribute("message", "서버 오류, 쫌따 다시 해보셈.");
				viewName = "prod/prodForm";
				break;
			}
		}else {
			viewName = "prod/prodForm";
		}


		new ViewResolverComposite().resolveView(viewName, req, resp);
		
	}
}
