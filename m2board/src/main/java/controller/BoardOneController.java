package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import vo.Board;

/**
 * Servlet implementation class BoardOneController
 */
@WebServlet("/boardOne")
public class BoardOneController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 컨드롤러
		// 1. 요청받아 분석
		request.setCharacterEncoding("utf-8");
		int boardNo = Integer.parseInt(request.getParameter("board_no"));
		//int boardRead = Integer.parseInt(request.getParameter("boardRead"));
		System.out.println(request.getParameter("boardNo")+"보드");
		
		// 2) 서비스 레이어를 요청(메서드 호출) -> 모델값(자료구조) 구하기 위함 
		// new
		BoardService boardService = new BoardService();
		List<Board> list = null;
		try {
			list = boardService.selectBoardOne(boardNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list != null) {
			request.setAttribute("list", list);
		}
		
		// 조회수 증가
		//boardService = new BoardService();
		//boardService.modifyBoardRead(boardRead, boardNo);
		
		// 뷰 전송
		request.getRequestDispatcher("/WEB-INF/view/boardOne.jsp").forward(request, response);
	}

}
