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
import library.service.UserAddService;

/**
 * Servlet implementation class UserAddServlet
 */
@WebServlet("/useradd")
public class UserAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserAddService userAddService = new UserAddService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ログイン確認
		LoginChecker loginChecker = new LoginChecker();
		if(!loginChecker.checkLogin(request)) { // ログインしていない
			// ログイン画面に飛ばす処理
			response.sendRedirect("login");
		} else {
			try {
				// 学部一覧をリクエストに追加する
				userAddService.init(request);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			// JSPに遷移
			ServletContext context = getServletContext();
			RequestDispatcher rd = context.getRequestDispatcher("/useradd.jsp"); // 転送先のURL
			rd.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コード指定
		request.setCharacterEncoding("UTF-8");

		try {
			// 入力情報を基にユーザ追加処理を行う
			if(userAddService.userDataAdd(request, response)) { // 追加成功
				// ユーザ一覧のGETへ飛ばす
				response.sendRedirect("userlist");
			} else { // 追加失敗
				// JSPに遷移
				ServletContext context = getServletContext();
				RequestDispatcher rd = context.getRequestDispatcher("/useradd.jsp"); // 転送先のURL
				rd.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
