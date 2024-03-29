package com.bitschool.mentorschool.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Hashtable;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bitschool.mentorschool.service.BoardService;
import com.bitschool.mentorschool.vo.BoardVO;
import com.bitschool.mentorschool.vo.MemberVO;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Inject
	private BoardService service;
	
	@RequestMapping(value={"/","/list"}, method=RequestMethod.GET)
	public ModelAndView showList(
			@RequestParam(required=false, defaultValue="1") Integer page,
			@RequestParam(required=false, defaultValue="10") Integer pageSize,
			@RequestParam(required=false) String searchScope,	// 검색 범위
			@RequestParam(required=false) String search		// 검색어
			) throws IOException {
		
		if (search != null) {
			search = new String(search.getBytes("ISO-8859-1"), "UTF-8");
		}
		
		System.out.println(searchScope);
		System.out.println(search);
		
		Map<String, Object> models = null;
		try {
			models = service.getBoardList(page, pageSize, searchScope, search);
			models.put("currentPage", page);
		} catch (Exception e) {
			e.printStackTrace();
			models = null;
		}
		
		return new ModelAndView("board/list", "models", models);
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public ModelAndView showWriteForm(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required=false, value="p_bno") BigInteger pBno) throws IOException {
		if (!ControlUtils.authAndRedirect(request, response)) {
			return null;
		}
		BoardVO pBoard = null;
		if (pBno != null) {
			try {
				pBoard = service.read(pBno);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Map<String, Object> models = new Hashtable<String, Object>();
		if (pBno != null)
			models.put("p_bno", pBno);
		if (pBoard != null)
			models.put("pBoard", pBoard);
		
		return new ModelAndView("board/writeForm", "models", models);
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public void write(@ModelAttribute BoardVO board,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean isSuccess = false;
		String alertMessage = null;
		
		if (!ControlUtils.authAndRedirect(request, response)) {
			return;
		}
		
		HttpSession session = request.getSession(false);
		MemberVO member = (MemberVO) session.getAttribute("member");
		
		if (board == null) {
			isSuccess = false;
			alertMessage = "잘못된 접근!";
		}
		else {
			board.setWriter(new BigInteger(member.getMemberId().toString()));
			
			int writeResult = 0;
			
			try {
				writeResult = service.write(board);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (writeResult > 0) {
				isSuccess = true;
			} else {
				isSuccess = false;
				alertMessage = "게시판 모듈 오류! 글 쓰기 실패!";
			}
		}
		
		if (isSuccess) {
			response.sendRedirect(request.getContextPath() + "/board/");
		} else {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			PrintWriter writer = response.getWriter();
			writer.append("<!DOCTYPE html>\n");
			writer.append("<html>");
			writer.append("<head>");
			writer.append("<script type='text/javascript'>");
			writer.append("window.onload = function() { alert('" + alertMessage + "'); }");
			writer.append("</script>");
			writer.append("</head>");
			writer.append("<body>");
			writer.append("<a href='" + request.getContextPath() + "/board/'>목록으로</a>");
			writer.append("</body>");
			writer.append("</html>");
		}
	}
	
	@RequestMapping(value="/read/{bno}")
	public ModelAndView read(
			HttpServletRequest request, HttpServletResponse response,
			@PathVariable BigInteger bno
			) {
		if (bno == null) {
			return null;
		}
		BoardVO board = null;
		try {
			board = service.read(bno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("board/read", "board", board);
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public ModelAndView showModifyForm(
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required=true) BigInteger bno
			) throws IOException {
		// 사용자 인증/권한 체크
		if (ControlUtils.authAndRedirect(request, response, "/board/modify?bno=" + bno)) {
			BoardVO board = null;
			try {
				board = service.read(bno);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return new ModelAndView("board/modifyForm", "board", board);
		}
		return null;
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public void modify(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute BoardVO board) throws IOException {
		try {
			service.modify(board);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/board/");
	}
	
}


























