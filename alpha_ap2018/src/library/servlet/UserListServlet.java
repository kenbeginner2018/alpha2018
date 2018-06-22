package library.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.checker.LoginChecker;
import library.service.UserListService;

/**
 * Servlet implementation class UserListServlet
 */
@WebServlet("/userlist")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserListService userListService = new UserListService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ログイン確認
		LoginChecker loginChecker = new LoginChecker();
		if(!loginChecker.checkLogin(request)) { // ログインしていない
			/* TODO
			 * ログイン画面に飛ばす処理
			 */
		}

		try {
			// ユーザ一覧表示
			userListService.init(request);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// JSPに遷移
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher("/userlist.jsp"); // 転送先のURL
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コード指定
		request.setCharacterEncoding("UTF-8");

		// 押されたボタンを取得
		String btn = request.getParameter("btn");

		if(btn.equals("検索")) {
			// 検索IDを取得
			String searchId = request.getParameter("searchId");
			if(searchId.isEmpty()) { // 検索条件が空のとき
				doGet(request, response);
			} else { // 検索条件が入力されているとき
				 // ユーザをIDで検索
				try {
					userListService.userSearchById(request, searchId);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				// JSPに遷移
				ServletContext context = getServletContext();
				RequestDispatcher rd = context.getRequestDispatcher("/userlist.jsp"); // 転送先のURL
				rd.forward(request, response);
			}
		} else if(btn.equals("ユーザ追加")) {
			// UserAddServletにリダイレクト
			response.sendRedirect("useradd");
		}
	}
}
