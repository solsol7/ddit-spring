package kr.or.ddit.adrs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.adrs.service.AddressService;
import kr.or.ddit.adrs.service.AddressServiceImpl;
import kr.or.ddit.vo.AddressVO;

@WebServlet("/adrs/address")
public class AddressDataControllerServlet extends HttpServlet{
	private AddressService service = new AddressServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String memId = (String)session.getAttribute("authId");
		
		List<AddressVO> adrsList = service.retriveAddressList(memId);
		req.setAttribute("adrsList", adrsList);
		
		String goPage = "/jsonView.view";
		
		if(goPage.startsWith("redirect:")) {
			
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);
		}else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
}
