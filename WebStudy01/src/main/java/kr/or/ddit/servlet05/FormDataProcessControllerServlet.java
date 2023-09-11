package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/09/model2/formDataProcess")
public class FormDataProcessControllerServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//컨트롤러의 역할 - 요청데이터
		req.setCharacterEncoding("UTF-8");
		String reqContentType = req.getContentType();
			// 데이터가 파라미터로 넘어오는지 json으로 넘어오는지 확인
		Map reqContent = null;
		if(reqContentType.contains("json")) {
			// json payload가 전송됐을 때 -> 역직렬화와 마셜링 필요
			// request의 body에서 읽을 inputStream이 필요
			InputStream is = req.getInputStream();		//역직렬화 할 때 사용할 수 있는 입력 스트림
				// 역직렬화 통해 byte데이터 읽어들임->읽어들인 json데이터를 Java언어로 번역 : 마셜링
			reqContent = new ObjectMapper().readValue(is, HashMap.class);
							// 클라이언트가 서버에게 쓴 편지 - 역직렬화 + 언마셜링까지 해야함 => read
		}else {
			// 파라미터일 수도 있을 때
			reqContent = req.getParameterMap();
			reqContent.forEach((k,v)->{
				System.out.printf("%s : %s\n", k, Arrays.toString((String[])v));
			});
		}
		
		//모델의 역할
//		Map<String, Object> target = new HashMap<String, Object>();
//		target.put("message", "파라미터 처리 완료");
//		target.putAll(reqContent);
		req.setAttribute("message", "파라미터 처리 완료");
		req.setAttribute("reqContent", reqContent);
		// 응답데이터를 어떤 형태로 보내야하는지 확인
		String accept = req.getHeader("Accept");
		
		int sc = 200;
		String viewName = null;
		if(accept.contains("json")) {
			viewName = "/jsonView.view";
		}else if(accept.contains("xml")) {
			sc = HttpServletResponse.SC_NOT_ACCEPTABLE;
		}else {
			 viewName = "/WEB-INF/views/09/formDataView.jsp";
		}
		if(sc==200) {
			req.getRequestDispatcher(viewName).forward(req, resp);			
		}else {
			resp.sendError(sc);
		}
		
	}
}
