package library.util;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
}
