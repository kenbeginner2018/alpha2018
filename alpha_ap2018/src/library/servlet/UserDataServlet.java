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
import library.service.UserDataService;

/**
 * Servlet implementation class UserDataServlet
 */
@WebServlet("/userdata")
public class UserDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserDataService userDataService = new UserDataService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ログイン確認
		LoginChecker loginChecker = new LoginChecker();
		if(!loginChecker.checkLogin(request)) { // ログインしていない
			// ログイン画面に飛ばす処理
			response.sendRedirect("login");
		} else {
			try {
				// 選択されたユーザの詳細情報を表示
				userDataService.init(request);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			// JSPに遷移
			ServletContext context = getServletContext();
			RequestDispatcher rd = context.getRequestDispatcher("/userdata.jsp"); // 転送先のURL
			rd.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コード指定
		request.setCharacterEncoding("UTF-8");

		// 押されたボタンの取得
		String btn = request.getParameter("btn");
		// 指定ユーザIDの取得
		String targetId = request.getParameter("targetId");

		if(btn != null) {
			if(btn.equals("削除")) { // 削除ボタンが押されたら
				// ダイアログの結果を取得(OKかキャンセルか)
				try {
					userDataService.userDisable(request, targetId);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				// ユーザ一覧のGETへ飛ばす
				response.sendRedirect("userlist");
			} else if(btn.equals("更新")) { // ユーザ情報更新
				try {
					if(userDataService.userDataUpdate(request, response, targetId)) { // 更新成功
						// ユーザ一覧のGETへ飛ばす
						response.sendRedirect("userlist");
					} else { // 更新失敗
						doGet(request, response);
					}
				} catch(IOException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if(btn.equals("RESET")) {
				doGet(request, response);
			}
		}
	}
}
