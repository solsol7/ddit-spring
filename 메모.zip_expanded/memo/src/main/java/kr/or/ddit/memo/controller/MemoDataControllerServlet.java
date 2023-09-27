package kr.or.ddit.memo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.memo.VO.MemoVO;
import kr.or.ddit.memo.service.MemoService;
import kr.or.ddit.memo.service.MemoServiceImpl;

@WebServlet({ "/memo", "/memo/*" })
public class MemoDataControllerServlet extends HttpServlet {
	private MemoService service = new MemoServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<MemoVO> memoList = service.selectRetrieveMemo();
		req.setAttribute("memoList", memoList);
		System.out.println(memoList);

		String goPage = "/jsonView.view";

		if (goPage.startsWith("redirect:")) {
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);

		} else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}

	private ObjectMapper mapper = new ObjectMapper();

	private boolean validate(MemoVO vo, Map<String, String> errors) {
		boolean valid = true;
		if (StringUtils.isBlank(vo.getWriter())) {
			errors.put("writer", "작성자 누락");
			valid = false;
		}
		if (StringUtils.isBlank(vo.getEmail())) {
			errors.put("email", "이메일 누락");
			valid = false;
		}
		return valid;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		try (
			InputStream is = req.getInputStream();
		) {
			MemoVO vo = mapper.readValue(is, MemoVO.class);
			req.setAttribute("originalData", vo);

			Map<String, String> errors = new HashMap<>();
			req.setAttribute("errors", errors);
			boolean valid = validate(vo, errors);

			boolean success = false;
			String message = null;
			if (valid) {
				if (service.createMemo(vo)) {
					success = true;
				} else {
					message = "실패";
				}
			} else {
				message = "필수파라미터 누락";
			}

			req.setAttribute("success", success);
			req.setAttribute("message", message);
		}

		String goPage = "/jsonView.view";

		if (goPage.startsWith("redirect:")) {
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);

		} else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = StringUtils.substringAfter(req.getRequestURI(), req.getContextPath());
		int lastIdx = uri.lastIndexOf("/");
		int uriLen = uri.length();
		int baseLen = "/memo".length();
		boolean valid = lastIdx >= baseLen && lastIdx < uriLen - 1;
		String codePart = uri.substring(lastIdx + 1);

		int code = -1;
		try {
			code = Integer.parseInt(codePart);
		} catch (NumberFormatException e) {
			valid = false;
		}
		if (!valid) {
			resp.sendError(400, "메모코드가 없음.");
			return;
		}
		boolean success = service.removeMemo(code);
		req.setAttribute("success", success);

		if (!success) {
			req.setAttribute("message", "삭제 실패");
		}

		String goPage = "/jsonView.view";

		if (goPage.startsWith("redirect:")) {
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);

		} else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		try (
				InputStream is = req.getInputStream();
		) {
			MemoVO vo = mapper.readValue(is, MemoVO.class);
			req.setAttribute("modifyData", vo);

			Map<String, String> errors = new HashMap<>();
			req.setAttribute("errors", errors);
			boolean valid = validate(vo, errors);
			boolean success = false;
			String message = null;
			
			if (valid) {
				if (service.modifyMemo(vo)) {
					success = true;
				} else {
					message = "수정 실패";
				}
			} else {
				message = "필수파라미터 누락";
			}

			req.setAttribute("success", success);
			req.setAttribute("message", message);
		}

		String goPage = "/jsonView.view";

		if (goPage.startsWith("redirect:")) {
			String location = req.getContextPath() + goPage.substring("redirect:".length());
			resp.sendRedirect(location);

		} else {
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
	}
}
