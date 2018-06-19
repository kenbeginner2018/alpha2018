package library.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library.bean.DeptBean;
import library.dao.DeptDAO;

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
}
