package library.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.bean.BookBean;
import library.bean.DeptBean;
import library.bean.RentalBean;
import library.bean.UserBean;
import library.checker.ValidateChecker;
import library.dao.BookDAO;
import library.dao.DeptDAO;
import library.dao.RentalDao;
import library.dao.UserDAO;
import library.util.Changer;

public class UserDataService {

	public void init(HttpServletRequest request) throws SQLException {
		// 詳細表示するユーザのIDを取得
		String targetId = request.getParameter("targetId");

		// 対象ユーザ
		UserBean targetUser = null;
		// 借りている本の一覧
		List<RentalBean> rentalList;
		// 各本の詳細リスト
		List<BookBean> bookList = new ArrayList<BookBean>();
		// 学部一覧格納用
		List<DeptBean> deptList = new ArrayList<DeptBean>();

		UserDAO userDAO = null;
		RentalDao rentalDAO = null;
		DeptDAO deptDAO = null;
		BookDAO bookDAO = null;
		try {
			// IDを基にユーザ情報と借りている本の一覧を取得
			userDAO = new UserDAO();
			targetUser = userDAO.getUserDataById(targetId).get(0);
			// 対象ユーザが借りている本の一覧を取得
			rentalDAO = new RentalDao();
			rentalList = rentalDAO.getAllRentalDataById(targetId);
			// 学部リストを取得
			deptDAO = new DeptDAO();
			deptList = deptDAO.getAllDept();
			// 各本の詳細リストを取得
			bookDAO = new BookDAO();
			for(int i = 0; i < rentalList.size(); i++) {
				bookList.add(bookDAO.getBookDataById(rentalList.get(i).getLabel()).get(0));
			}
	//		if(bookList.size() == 0) {
	//			bookList = null;
	//		}
		} finally {
			// DAOを閉じる
			if(userDAO != null) {
				userDAO.close();
			}
			if(rentalDAO != null) {
				rentalDAO.close();
			}
			if(deptDAO != null) {
				deptDAO.close();
			}
			if(bookDAO != null) {
				bookDAO.close();
			}
		}

		// 対象ユーザ情報をセット
		request.setAttribute("targetUser", targetUser);
		// 学部リスト情報をセット
		request.setAttribute("deptList", deptList);
		// 借りている本の情報をセット
		request.setAttribute("bookList", bookList);
	}

	// 指定したIDのユーザを利用停止状態にする
	public void userDisable(HttpServletRequest request, String targetId) throws SQLException {
		UserDAO userDAO = null;
		try {
			userDAO = new UserDAO();
			userDAO.disableUserDataById(targetId);
		} finally {
			if(userDAO != null) {
				userDAO.close();
			}
		}
	}

	// 指定したIDのユーザ情報のうち、学部名、学年、氏名を更新する
	public boolean userDataUpdate(HttpServletRequest request, HttpServletResponse response, String targetId) throws SQLException, IOException {

		String deptName = request.getParameter("deptName");
		String grade = request.getParameter("grade");
		String userName = request.getParameter("name");

		if(deptName != null && grade != null && userName != null) {
			if(!deptName.isEmpty() && !grade.isEmpty() && !userName.isEmpty()) {
				// 各入力値の正規表現チェック
				if(userDataValidate(request, grade)) { // すべての入力チェックをクリアしたら
					// 変換用 (IDから名前とか)
					Changer changer = new Changer();
					UserDAO userDAO = null;
					try {
						userDAO = new UserDAO();
						userDAO.updateUserDataById(targetId, changer.deptNameToDeptId(deptName), Integer.parseInt(grade), userName);
					} finally {
						if(userDAO != null) {
							userDAO.close();
						}
					}
					return true;
				} else { // 入力チェックに1つでも引っかかったら
					return false;
				}
			} else {
				request.setAttribute("notNullError", "すべての項目を入力してください。");
				return false;
			}
		} else {
			return false;
		}
	}

	// ユーザ情報更新のバリデーション結果を返す
	public boolean userDataValidate(HttpServletRequest request, String grade) throws SQLException {

		// 各チェックをクリアしたかのフラグ (クリアしたらtrueにする)
		boolean gradeClear = false;

		ValidateChecker vc = new ValidateChecker();
		if(!vc.checkGrade(grade)) {
			request.setAttribute("gradeError", vc.getGradeErrorMessage());
		} else {
			gradeClear = true;
		}

		if(gradeClear) { // すべての入力チェックをクリアしたら
			return true;
		} else {
			return false;
		}

	}
}
