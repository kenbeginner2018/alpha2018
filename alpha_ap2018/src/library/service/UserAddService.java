package library.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.bean.DeptBean;
import library.bean.UserBean;
import library.checker.ValidateChecker;
import library.dao.DeptDAO;
import library.dao.UserDAO;
import library.util.Changer;

public class UserAddService {

	// 学部データのリストを取得する
	public void init(HttpServletRequest request) throws SQLException {
		// 学部一覧格納用
		List<DeptBean> deptList = new ArrayList<DeptBean>();
		DeptDAO deptDAO = null;
		try {
			deptDAO = new DeptDAO();
			deptList = deptDAO.getAllDept();
			request.setAttribute("deptList", deptList);
		} finally {
			if(deptDAO != null) {
				deptDAO.close();
			}
		}
	}

	// ユーザ情報を追加する
	public boolean userDataAdd(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// ユーザ情報の入力を受け取る
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		String deptName = request.getParameter("deptName");
		String grade = request.getParameter("grade");
		String name = request.getParameter("name");

		Changer changer = new Changer();

		if(userId != null && userPw != null && deptName != null && grade != null && name != null) {
			if(!userId.isEmpty() && !userPw.isEmpty() && !deptName.isEmpty() && !grade.isEmpty() && !name.isEmpty()) {
				// 各入力値の正規表現チェック
				if(userDataValidate(request, userId, userPw, grade)) { // すべての入力チェックをクリアしたら
					// ユーザ情報としてまとめて格納する
					UserBean userBean = new UserBean();
					userBean.setUserId(userId);
					userBean.setUserPw(userPw);
					userBean.setDeptId(changer.deptNameToDeptId(deptName));
					userBean.setGrade(Integer.parseInt(grade));
					userBean.setName(name);

					// DAOを呼んでユーザ情報をDBに格納する
					UserDAO userDAO = new UserDAO();
					userDAO.addUserData(userBean);

					return true;
				} else { // 入力チェックに1つでも引っかかったら
					init(request);
					return false;
				}
			} else {
				request.setAttribute("notNullError", "すべての項目を入力してください。");
				init(request);
				return false;
			}
		} else {
			return true;
		}
	}

	// ユーザ追加情報のバリデーション結果を返す
	public boolean userDataValidate(HttpServletRequest request, String userId, String userPw, String grade) throws SQLException {

		// 各チェックをクリアしたかのフラグ (クリアしたらtrueにする)
		boolean userIdClear = false;
		boolean userPwClear = false;
//		boolean deptClear = false;
		boolean gradeClear = false;
//		boolean nameClear = false;
		ValidateChecker vc = new ValidateChecker();
		if(!vc.checkUserId(userId)) { //
			request.setAttribute("userIdError", vc.getUserIdErrorMessage());
		} else {
			userIdClear = true;
		}
		if(!vc.checkUserPw(userPw)) { //
			request.setAttribute("userPwError", vc.getUserPwErrorMessage());
		} else {
			userPwClear = true;
		}
		if(!vc.checkGrade(grade)) {
			request.setAttribute("gradeError", vc.getGradeErrorMessage());
		} else {
			gradeClear = true;
		}

		if(userIdClear && userPwClear && gradeClear) { // すべての入力チェックをクリアしたら
			return true;
		} else {
			return false;
		}

	}
}
