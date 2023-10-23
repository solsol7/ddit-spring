package com.springboard.board.controller;

import javax.inject.Inject;
import javax.servlet.annotation.MultipartConfig;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboard.board.service.BoardService;
import com.springboard.board.vo.FreeBoardVO;
import com.springboard.validate.grouphint.InsertGroup;

@Controller
@RequestMapping("/board/new")
@SessionAttributes(names = "newBoard")	// 이 이름에 해당하는 모델만 세션스코프 통해 공유
@MultipartConfig
public class BoardInsertController {
	@Inject
	private BoardService service;
	
	@ModelAttribute("newBoard")	// 이름 일치 -> 세션에 넣어줌
	public FreeBoardVO board(Authentication authentication) {
		FreeBoardVO board = new FreeBoardVO();
		board.setBoWriter(authentication.getName());
		return board;
	}
	
	@GetMapping
	public String boardForm() {
		return "board/boardForm";
	}
	
	@PostMapping
	public String insertBoard(
			@Validated(InsertGroup.class) @ModelAttribute("newBoard") FreeBoardVO board
			// 세션어트리뷰트 이름과 일치하면 request에서 찾는게 아니라 세션에서 찾음
			, BindingResult errors
			, RedirectAttributes redirectAttributes
			, SessionStatus sessionStatus
			
	) {
		
		String viewName = null;
		if(!errors.hasErrors()) {
			service.createBoard(board);
			sessionStatus.setComplete(); // 명령이 완전히 끝났어 -> 세션통해 공유되던 newBoard 삭제됨
			viewName = "redirect:/board/"+board.getBoNo();	// 셀렉트키가 필요한 이유
						// 어디서도 setter가 호출되지 않으면 컨트롤러에서 사용할 수 없음 ..
						// keyProperty 때문에 setBoNo가 호출됨 -> 사용가능
		}else {
			String attrName = BindingResult.MODEL_KEY_PREFIX+"newBoard";
			// 검증 결과 메세지가 전달될 때의 이름
			
			redirectAttributes.addFlashAttribute(attrName, errors);
//			리퀘스트에 담겨있던 녀석을 이쪽으로 옮겨버림 .. redirect이 된 이후에도 살아남아있음
//			 - flash로 넣었으니까 검증 결과 메세지가 한번 출력되고 사라짐
			viewName = "redirect:/board/new";	// redirect 후 get 요청
		}
		
		return viewName;
	}
}
