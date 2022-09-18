package com.spring.boardapp.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.boardapp.domain.Board;
import com.spring.boardapp.service.BoardService;

@Controller
public class BoardController {

	@Resource(name = "boardService")
	private BoardService boardService;

	@RequestMapping(value = "/regist")
	public String registBoard(@RequestParam Map<String, Object> paramMap, Model model) {
		if (paramMap == null)
			return "board/boardRegister";
		else {
			int result = boardService.insertBoard(paramMap);
			if (result > 0)
				return "redirect:list";
			else
				return "/board/boardRegister";
		}
	}

	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String getBoard(@PathVariable String id, Model model) {
		Board board = boardService.getBoardDetail(id);
		if (board == null)
			return "board/boardList";
		else {
			model.addAttribute("board", board);
			return "board/boardDetail";

		}
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String updateBoard(@PathVariable String id, Model model) {
		model.addAttribute("board",boardService.getBoardDetail(id));
		System.out.println(model.toString());
		return "board/boardEdit";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateBoard(@RequestParam Map<String, Object> paramMap, Model model) {
		System.out.println("post "+paramMap.toString());
		if(boardService.updateBoard(paramMap)) return "redirect:/list";
		else return "redirect:/detail/{id}";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBoard(@PathVariable String id) {
		boardService.deleteBoard(id);
		return "redirect:/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getBoardList(Model model) {
		List<Board> boardList = boardService.getBoardList();
		model.addAttribute("boardList", boardList);

		return "board/boardList";
	}

}
