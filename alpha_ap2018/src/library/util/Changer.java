package library.util;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import library.bean.BookBean;
import library.bean.DeptBean;
import library.bean.UserBean;
import library.dao.BookDAO;
import library.dao.DeptDAO;
import library.dao.UserDAO;

public class Changer {

	// 学部IDを受け取ったら対応する学部名にして返す
	public String deptIdToDeptName(int deptId) {
		// 学部一覧格納用
		List<DeptBean> deptList = new ArrayList<DeptBean>();
		try {
			DeptDAO deptDAO = new DeptDAO();
			deptList = deptDAO.getAllDept();
			for(int i = 0; i < deptList.size(); i++) {
				if(deptList.get(i).getDeptId() == deptId) {
					return deptList.get(i).getDeptName();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 該当学部ID無し
		return null;
	}

	// 学部名を受け取ったら対応する学部IDにして返す
	public int deptNameToDeptId(String deptName) {
		// 学部一覧格納用
		List<DeptBean> deptList = new ArrayList<DeptBean>();
		try {
			DeptDAO deptDAO = new DeptDAO();
			deptList = deptDAO.getAllDept();
			for(int i = 0; i < deptList.size(); i++) {
				if(deptList.get(i).getDeptName().equals(deptName)) {
					return deptList.get(i).getDeptId();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 該当学部ID無し
		return -1;
	}

	//返却予定日から延長返却日を取得
	public String rentalDateToExtendDate(String rentalDate) {
		//返却日を延長
		Calendar calender = new GregorianCalendar();

			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
	        Date date = null;
			try {
				date = sdFormat.parse(rentalDate);
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}
		    calender.setTime(date);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			calender.add(Calendar.DAY_OF_MONTH, +14);
			String time= sdf.format(calender.getTime());

			return time ;
		}

	// ラベルを受け取ったら対応する本のタイトルにして返す
	public String labelToTitle(String label) {
		// 一覧格納用
		List<BookBean> bookList = new ArrayList<BookBean>();
			try {
				BookDAO bookDAO = new BookDAO();
				bookList = bookDAO.getBookDataByLabel(label);
				return bookList.get(0).getTitle();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		// 該当無し
			return null;
	}

	// タイトルを受け取ったら対応する本のラベルにして返す
	public String titleTolabel(String title) {
		// 一覧格納用
		List<BookBean> bookList = new ArrayList<BookBean>();
			try {
				BookDAO bookDAO = new BookDAO();
				bookList = bookDAO.getBookDataByTitle(title);
				return bookList.get(0).getLabel();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		// 該当無し
			return null;
	}

	// ユーザIDから氏名を取得する
	public String userIdToName(String userId) {
		// ユーザ一覧格納用
		List<UserBean> userList = new ArrayList<UserBean>();
			try {
				UserDAO userDAO = new UserDAO();
				userList = userDAO.getAllUserData();
				for(int i = 0; i < userList.size(); i++) {
					if(userList.get(i).getUserId().equals(userId)) {
						return userList.get(i).getName();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		// 該当無し
			return null;
	}
}
