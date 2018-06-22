package library.checker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

// ログイン情報をチェックするクラス
public class LoginChecker {

	// 	リクエストオブジェクトを受け取り、ログインしているかをチェックし、返す
	public boolean checkLogin(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session != null) {
			if(session.getAttribute("userId") != null) {
				return true;
			} else {
				return false;
			}
		}

		return false;
	}
}
