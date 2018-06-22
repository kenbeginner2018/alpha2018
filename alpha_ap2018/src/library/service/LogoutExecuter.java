package library.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// ログアウトを扱うクラス
public class LogoutExecuter {

	// ログアウトする
	public void LogoutExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 HttpSession session = request.getSession(false);
		 // セッションを破棄
		 session.invalidate();
		 // ログイン画面に遷移
		 response.sendRedirect("login");
	}
}
