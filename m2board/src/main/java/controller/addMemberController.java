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


@WebServlet("/addMember")
public class addMemberController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("memberLogin") != null) { // 로그인상태
			response.sendRedirect(request.getContextPath()+"/index");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/addMemberForm.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("id") != null) { // 로그인상태
			response.sendRedirect(request.getContextPath()+"/index");
			return;
		}
		
		request.setCharacterEncoding("utf-8");
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		String memberName = request.getParameter("memberName");
		String memberAddr = request.getParameter("memberAddr");
		String memberAddrDtail = request.getParameter("memberAddrDtail");
		
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		member.setMemberName(memberName);
		member.setMemberAddr(memberAddr);
		member.setMemberDetailAddr(memberAddrDtail);
		
		MemberService memberService = new MemberService();
		try {
			memberService.addMember(member);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath()+"/loginController");
	}

}
