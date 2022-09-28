package com.spring.boardapp.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.boardapp.dao.BoardAttachDao;
import com.spring.boardapp.domain.Board;
import com.spring.boardapp.domain.BoardAttach;
import com.spring.boardapp.domain.paging.Paging;
import com.spring.boardapp.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Resource(name = "boardService")
	private BoardService boardService;

	@RequestMapping(value = "/regist")
	public String registBoard(@RequestParam Map<String, Object> paramMap, Model model) {
		if (paramMap.isEmpty())
			return "board/boardRegister";
		else {
				System.out.println(paramMap.size());
				System.out.println(paramMap.toString());
			int result = boardService.insertBoard(paramMap);
			if (result > 0)
				return "redirect:/board/list";
			else
				return "/board/boardRegister";
		}
	}

	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String getBoard(@PathVariable String id,@RequestParam String pageNum, @RequestParam String pageAmount, 
							@RequestParam(required=false) String searchType, @RequestParam(required=false) String searchWord,Model model) {
		Board board = boardService.getBoardDetail(id);
		if (board == null)
			return "board/boardList";
		else {
			model.addAttribute("board", board);
			model.addAttribute("pageNum", pageNum);
			model.addAttribute("pageAmount", pageAmount);
			model.addAttribute("searchType",searchType);
			model.addAttribute("searchWord",searchWord);
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
		return "redirect:/board/detail/{id}?pageNum="+pageNum+"&pageAmount="+pageAmount;
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBoard(@PathVariable String id,@RequestParam String pageNum, @RequestParam String pageAmount) {
		
		List<BoardAttach> attachList = boardService.getAttachList(id);
		
		if(boardService.deleteBoard(id)) {
			deleteFiles(attachList);
		}
		
		return "redirect:/board/list?pageNum="+pageNum+"&pageAmount="+pageAmount;
	}
	
	//폴더에 있는 파일 지우기
	private void deleteFiles(List<BoardAttach> attachList) {

		if (attachList == null || attachList.size() == 0) {
			return;
		}

		for (BoardAttach attach : attachList) {
			try {
				Path file = Paths.get(
						"C:\\upload\\" + attach.getUploadPath() + "\\" + attach.getUuid() + "_" + attach.getFileName());

				Files.deleteIfExists(file);

				// 이미지 파일인 경우 섬네일 파일도 추가 삭제
				if (Files.probeContentType(file).startsWith("image")) {

					Path thumbNail = Paths.get("C:\\upload\\" + attach.getUploadPath() + "\\s_" + attach.getUuid() + "_"
							+ attach.getFileName());

					Files.delete(thumbNail);
				}

			} catch (Exception e) {
				System.out.println("delete file error" + e.getMessage());
			} // end catch
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getBoardList(@RequestParam(required = false) String pageNum, @RequestParam(required = false) String pageAmount,
								@RequestParam(required=false) String searchType, @RequestParam(required=false) String searchWord, Model model) {

		if(pageNum==null) pageNum="1";
		if(pageAmount==null) pageAmount="5";
		if(searchType==null) searchType="";
		if(searchWord==null) searchWord="";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNum", Integer.parseInt(pageNum));
		map.put("pageAmount", Integer.parseInt(pageAmount));
		map.put("searchWord", searchWord);
		map.put("searchType", searchType);
		
		
		List<Board> boardList = boardService.getBoardListWithPaging(map);
		int total = boardService.getBoardTotalCnt(map);
		model.addAttribute("boardList", boardList);
		
		Paging paging = new Paging(Integer.parseInt(pageNum), Integer.parseInt(pageAmount), searchType, searchWord, total);
		model.addAttribute("pageMaker", paging);
		return "board/boardList";
	}
	
	@GetMapping(value = "/getAttachList", 
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<BoardAttach>> getAttachList(String id) {
		System.out.println(id);
		return new ResponseEntity<List<BoardAttach>>(boardService.getAttachList(id), HttpStatus.OK);
	}

}
