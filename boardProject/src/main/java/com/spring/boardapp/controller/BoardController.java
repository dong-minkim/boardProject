package com.spring.boardapp.controller;

import java.util.HashMap;
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
import com.spring.boardapp.domain.paging.Paging;
import com.spring.boardapp.service.BoardService;

@Controller
public class BoardController {

	@Resource(name = "boardService")
	private BoardService boardService;

	@RequestMapping(value = "/regist")
	public String registBoard(@RequestParam Map<String, Object> paramMap, Model model) {
		if (paramMap.isEmpty())
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
	public String getBoard(@PathVariable String id,@RequestParam String pageNum, @RequestParam String pageAmount, Model model) {
		Board board = boardService.getBoardDetail(id);
		if (board == null)
			return "board/boardList";
		else {
			model.addAttribute("board", board);
			model.addAttribute("pageNum", pageNum);
			model.addAttribute("pageAmount", pageAmount);
			return "board/boardDetail";

		}
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String updateBoard(@PathVariable String id,@RequestParam String pageNum, @RequestParam String pageAmount, Model model) {
		model.addAttribute("board",boardService.getBoardDetail(id));
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pageAmount", pageAmount);
		return "board/boardEdit";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateBoard(@RequestParam Map<String, Object> paramMap) {
		boardService.updateBoard(paramMap);
		String pageNum = (String) paramMap.get("pageNum");
		String pageAmount = (String) paramMap.get("pageAmount");
		return "redirect:/detail/{id}?pageNum="+pageNum+"&pageAmount="+pageAmount;
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBoard(@PathVariable String id,@RequestParam String pageNum, @RequestParam String pageAmount) {
		boardService.deleteBoard(id);
		return "redirect:/list?pageNum="+pageNum+"&pageAmount="+pageAmount;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getBoardList(@RequestParam(required = false) String pageNum, @RequestParam(required = false) String pageAmount, Model model) {

		if(pageNum==null) pageNum="1";
		if(pageAmount==null) pageAmount="2";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNum", Integer.parseInt(pageNum));
		map.put("pageAmount", Integer.parseInt(pageAmount));
		
		List<Board> boardList = boardService.getBoardListWithPaging(map);
		int total = boardService.getBoardTotalCnt();
		model.addAttribute("boardList", boardList);
		
		Paging paging = new Paging(Integer.parseInt(pageNum), Integer.parseInt(pageAmount), total);
		model.addAttribute("pageMaker", paging);
		
		return "board/boardList";
	}

}
