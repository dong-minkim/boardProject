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

import com.spring.boardapp.domain.Board;
import com.spring.boardapp.service.BoardService;

@Controller
@RequestMapping("/")
public class BoardController {
	
	@Resource(name = "boardService")
	private BoardService boardService;
	
	@RequestMapping(value = "/detail/{id}")
	public String getBoard(@PathVariable String id, Model model) {
		model.addAttribute("board",boardService.getBoardDetail(id));
		return "board/boardDetail";
	}
	
	@RequestMapping(value = "/regist")
		public String registBoard(@RequestParam Map<String, Object> paramMap, Model model) {
			if(paramMap == null) return "/board/boardWriter";
			else {
				int result = boardService.insertBoard(paramMap);
				if(result > 0) return "redirect:/board/boardList";
				else return "/board/boardWriter";
			}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String updateBoard(@RequestParam Map<String, Object> paramMap, Model model){
		
		
		
		return "redirect:/board/boardList";
	} 
	
	@RequestMapping(value = "/list", method = RequestMethod.GET )
	public String getBoardList(Model model) {
		List<Board> boardList = boardService.getBoardList();
		model.addAttribute("boardList", boardList);
		
		return "board/boardList";
	}
	
	
	
}
