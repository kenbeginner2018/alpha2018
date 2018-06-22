package library.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import library.bean.DeptBean;
import library.bean.UserBean;
import library.dao.DeptDAO;
import library.dao.UserDAO;

public class UserListService {

	// ユーザ一覧を表示する(UserListServletの初期化用)
	public void init(HttpServletRequest request) throws SQLException {
		// ユーザ一覧格納用
		List<UserBean> userList = new ArrayList<UserBean>();
		// 学部一覧格納用
		List<DeptBean> deptList = new ArrayList<DeptBean>();

		UserDAO userDAO = null;
		DeptDAO deptDAO = null;
		try {
			// ユーザ一覧を取得する
			userDAO = new UserDAO();
			userList = userDAO.getAllUserData();
			deptDAO = new DeptDAO();
			deptList = deptDAO.getAllDept();
		} finally {
			if(userDAO != null) {
				userDAO.close();
			}
			if(deptDAO != null) {
				deptDAO.close();
			}
		}
		request.setAttribute("deptList", deptList);
		request.setAttribute("userList", userList);
	}

	// ユーザ検索(IDで検索)
	public void userSearchById(HttpServletRequest request, String searchId) throws SQLException {
		// ユーザ一覧格納用
		List<UserBean> userList = new ArrayList<UserBean>();
		// 学部一覧格納用
		List<DeptBean> deptList = new ArrayList<DeptBean>();

		UserDAO userDAO = null;
		DeptDAO deptDAO = null;
		try {
			// ユーザ一覧を取得する
			userDAO = new UserDAO();
			userList = userDAO.getUserDataById(searchId);
			// 学部一覧を取得する
			deptDAO = new DeptDAO();
			deptList = deptDAO.getAllDept();
		} finally {
			if(userDAO != null) {
				userDAO.close();
			}
			if(deptDAO != null) {
				deptDAO.close();
			}
		}
		request.setAttribute("userList", userList);
		request.setAttribute("deptList", deptList);
	}
}
