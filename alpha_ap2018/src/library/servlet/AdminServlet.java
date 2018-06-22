package library.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.checker.LoginChecker;
import library.service.LogoutExecuter;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ログイン確認
		LoginChecker loginChecker = new LoginChecker();
		if(!loginChecker.checkLogin(request)) { // ログインしていない
			// ログイン画面に飛ばす処理
			response.sendRedirect("login");
		} else {

			RequestDispatcher rd = null;
		  //  	rd = getServletConfig().getServletContext().getRequestDispatcher("/ReturnServlet");
		    	rd = getServletConfig().getServletContext().getRequestDispatcher("/Admin.jsp");
		    	rd.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コード指定
		request.setCharacterEncoding("UTF-8");

		if(request.getParameter("btn").equals("ログアウト")) {
			// ログアウトしてログイン画面へ遷移
			LogoutExecuter logout = new LogoutExecuter();
			logout.LogoutExecute(request, response);
		}
	}

}
