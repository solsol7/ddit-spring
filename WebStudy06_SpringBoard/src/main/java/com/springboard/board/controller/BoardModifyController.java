package com.springboard.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboard.board.InvalidPasswordException;
import com.springboard.board.service.BoardService;
import com.springboard.board.vo.FreeBoardVO;
import com.springboard.validate.grouphint.UpdateGroup;

@Controller
@RequestMapping("/board/{boNo}/edit")
@SessionAttributes("targetBoard")
public class BoardModifyController {
	@Inject
	private BoardService service;
	
	@GetMapping
	public String editForm(@PathVariable int boNo, Model model) {
		if(!model.containsAttribute("targetBoard")) {
			// 처음 editForm으로 접근하는 경우(이미 모델에 targetBoard가 있는 경우) 필요 .. 
			// put요청에서 else 타고 오는 경우에는 필요 없음
			FreeBoardVO targetBoard = service.retrieveBoard(boNo);
			model.addAttribute("targetBoard", targetBoard);
			// 클래스에 어노테이션(SessionAttributes)-> request에 옮겨담지 않고 session에 담아줌
			
		}
		return "board/boardEdit";
	}
	
	@PutMapping
	public String boardUpdate(
			@Validated(UpdateGroup.class) @ModelAttribute("targetBoard") FreeBoardVO targetBoard
				// 입력된 데이터를 가지고 있는 커맨트 오브젝트도 request아니라 session 통해 공유
			, BindingResult errors
			, SessionStatus sessionStatus
				// session scope 안에 있는 model을 지워야 할 때 필요 .. 상태를 complete으로 만들면 session scope 안의 targetBoard 지워짐
			, RedirectAttributes redirectAttributes
				// errors를 전달하기 위해 필요
	) {
		String viewName = null;
		if(!errors.hasErrors()) {
			try {
				service.modifyBoard(targetBoard); 
					// 여기에서 예외를 발생시키는 경우도 있기 때문에 try catch 필요
				viewName = "redirect:/board/{boNo}";
			}catch (InvalidPasswordException e) {
				redirectAttributes.addFlashAttribute("message", e.getMessage());
				viewName = "redirect:/board/{boNo}/edit";
			}
		}else {
			String attrName = BindingResult.MODEL_KEY_PREFIX + "targetBoard";
			// 커스텀태그 사용 위해 프레임워크 내부에서 공식적으로 사용하고 있는 이름이 필요
			redirectAttributes.addFlashAttribute(attrName, errors);
			// 한 번 사용할 수 있는 모델 전달
			
			viewName = "redirect:/board/{boNo}/edit";
				// 잘못된 요청을 캐싱하지 않기 위해 redirect로 보냄 ..> get요청 발생
		}
		
		return viewName;
	}
}
