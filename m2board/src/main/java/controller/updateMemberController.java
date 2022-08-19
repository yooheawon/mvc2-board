package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;
import vo.Member;


@WebServlet("/updateMember")
public class updateMemberController extends HttpServlet {
	// 업데이트 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션검사
		HttpSession session = request.getSession();
		// 로그인 상태가 아니면 로그인 페이지로
		if (session.getAttribute("memberLogin") == null) {
			response.sendRedirect(request.getContextPath()+"/loginController");
			return;
		}
		// 로그인 상태면 수정 페이지 연결
		RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/view/updateMemberForm.jsp");
		rd.forward(request, response);
	}
	// 폼jsp에서 받아온 정보를 저장 - 업데이트 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션검사
		HttpSession session = request.getSession();
		// 로그인 상태가 아니면 로그인 페이지로
		if (session.getAttribute("memberLogin") == null) {
			response.sendRedirect(request.getContextPath()+"/loginController");
			return;
		}
		// 한글로 입력된게 있을수 있으므로 인코딩
		request.setCharacterEncoding("utf-8");
		// 객체 생성
		MemberService memberService = new MemberService();
		Member paramMember = new Member();
		//세션으로 로그인된 정보 불러오기
		//session.setAttribute("memberLogin", paramMember);
		// 업데이트 폼에서 받아온값 전달
		paramMember.setMemberId(request.getParameter("member_id"));
		paramMember.setMemberPw(request.getParameter("member_pw"));
		paramMember.setMemberName(request.getParameter("member_name"));
		paramMember.setMemberAddr(request.getParameter("memberAddr"));
		paramMember.setMemberDetailAddr(request.getParameter("memberAddrDtail"));
		paramMember.setUpdateDate(request.getParameter("update_date"));
		paramMember.setCreateDate(request.getParameter("create_date"));
		
		int row = 0;
		try {
			// 생성한 객체에 service에서 받아 저장
			 row = memberService.motifyMember(paramMember);
			 // 디버깅
			 System.out.println("row : "+row);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

							
		 if (row == 0) {
			 // 디버깅
			System.out.println("변경실패");
			// 수정화면으로 
			response.sendRedirect(request.getContextPath() + "/updateMember");
	        return;

		}else {
			// 디버깅
			System.out.println("변경성공");
			// 기존 세션 내용 제거
			 session.removeAttribute("memberLogin");
			 // 새로운 내용 세션에 저장
	         session.setAttribute("memberLogin", paramMember);
	         // 디버깅
	         System.out.println("변경된 파람 :" +paramMember);
	        // 수정된화면 보여주기
	         response.sendRedirect(request.getContextPath() + "/index");

		}
	}

}
