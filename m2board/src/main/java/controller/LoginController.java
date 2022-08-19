package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.IMemberService;
import service.MemberService;
import vo.Member;

@WebServlet("/loginController")
public class LoginController extends HttpServlet {
	private IMemberService memberService;
		// doget 과 dopost 톰켓이 제공해주는것
		// 브라우져와 tomcat이 연결되면 session이 생겨 request를 쓸 수 있음
		// 로그인 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 세션 유효성은 항상 검사해야됨
		if (session.getAttribute("memberLogin") != null) {// 로그인 상태
			response.sendRedirect(request.getContextPath()+"/index");
			return;
		}
		RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/view/loginForm.jsp");
		rd.forward(request, response);
	}

		// 로그인 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("memberLogin") != null) {// 로그인 상태
			response.sendRedirect(request.getContextPath()+"/index");
			return;
		}
		
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("member_id");
		String pw = request.getParameter("member_pw");
		String name = request.getParameter("member_name");
		String createDate = request.getParameter("create_date");
		String updateDate = request.getParameter("update_date");
		// 디버깅
		System.out.println("id : " + id);
		System.out.println("pw : " + pw);
		
		
		// new
		Member paramMember = new Member();
		memberService = new MemberService();
		paramMember.setMemberId(id);
		paramMember.setMemberPw(pw);
		paramMember.setMemberName(name);
		Member member =  memberService.getMemberLogin(paramMember);
		if (member == null) {
			System.out.println("로그인 실패");
			response.sendRedirect(request.getContextPath()+"/loginController");
			return;
		}
		System.out.println("로그인 ");
		System.out.println(member.toString());
		session.setAttribute("memberLogin", member);
		
		// 내부에서 이뤄어질떄만 forwoard사용
		response.sendRedirect(request.getContextPath()+"/boardList");
	}
}
/*
	종아요
	
	3정규화 필요
	board_no(fk), member_id(fk) ,create_date /member_name
	
	
	select board_no, title, t.cnt
	from board b
	inner join
	( select board_no, count(*) cnt
	from nice
	group by board_no ) t
	on b.board_no = t.board_no
*/