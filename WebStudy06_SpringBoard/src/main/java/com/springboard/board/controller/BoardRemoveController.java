package com.springboard.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboard.board.InvalidPasswordException;
import com.springboard.board.service.BoardService;
import com.springboard.board.vo.FreeBoardVO;
import com.springboard.validate.grouphint.DeleteGroup;

@Controller
public class BoardRemoveController {
	@Inject
	private BoardService service;
	
	@DeleteMapping("/board/{boNo}")
	public String boardDelete(
		@Validated(DeleteGroup.class) FreeBoardVO inputData
		, BindingResult errors
		, RedirectAttributes redirectAttributes
	) {
		String viewName = null;
		if(!errors.hasErrors()) {
			try {
				service.removeBoard(inputData);
				viewName = "redirect:/board";
			}catch (InvalidPasswordException e) {
				redirectAttributes.addFlashAttribute("message", e.getMessage());
				viewName = "redirect:/board/{boNo}";
			}
		}else {
			redirectAttributes.addFlashAttribute("message", "비밀번호 누락");
			viewName = "redirect:/board/{boNo}";
		}
		
		return viewName;
	}
}
