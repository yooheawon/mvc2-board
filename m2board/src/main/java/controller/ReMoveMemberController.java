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


@WebServlet("/removeMember")
public class ReMoveMemberController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("memberLogin") == null) { // 로그아웃상태
			response.sendRedirect(request.getContextPath()+"/index");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/removeForm.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("memberLogin") == null) { // 로그아웃상태
			response.sendRedirect(request.getContextPath()+"/index");
			return;
		}
		// 값 불러오기
		String memberId = request.getParameter("member_id");
		String memberPw = request.getParameter("member_pw");
		// 디버깅
		System.out.println("member_id : "+memberId + " / member_pw : "+memberPw);
		
		// 파라미터 객체 생성
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		int result = 0;
		MemberService memberService = new MemberService();
		try {
		result =	memberService.removeMember(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 디버깅
		System.out.println("result : "+result);
		
		if (result == 1) {
			session.invalidate();
			System.out.println("탈!퇴!");
			response.sendRedirect(request.getContextPath()+"/loginController");
		}else {
			System.out.println("너는 못나간다");
			 response.sendRedirect(request.getContextPath()+"/index");
		}
	}
}
