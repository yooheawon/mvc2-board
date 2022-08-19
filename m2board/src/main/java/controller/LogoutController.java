package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logoutController")
public class LogoutController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션검사
		HttpSession session = request.getSession();
		// 로그인 상태가 아니면 로그인 페이지로
		if (session.getAttribute("memberLogin") == null) {
			response.sendRedirect(request.getContextPath()+"/loginController");
			return;
		}
		
		session.invalidate(); // 세션 리셋
	
		response.sendRedirect(request.getContextPath() + "/loginController");
	}

}
