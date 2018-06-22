package library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library.bean.UserBean;

public class UserDAO {
	private Connection connection;

	// コンストラクタ
	// Library_DBデータベースとの接続を行う
	public UserDAO() throws SQLException{
		String url = "jdbc:mysql://localhost:3306/Library_DB";
		String user = "root";
		String password = "root";
		connection = DriverManager.getConnection(url, user, password);
	}

	// Library_DBデータベースとの接続を切断する
	public void close() {
		try {
			if(connection != null) {
				connection.close();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	// ユーザー一覧を取得する
	public List<UserBean> getAllUserData() throws SQLException{
		// 結果を返す用
		List<UserBean> userList = new ArrayList<UserBean>();
		UserBean userBean = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		try {
			// SQLを保持する
			String sql = "SELECT * FROM USERTABLE";
			pstatement = connection.prepareStatement(sql);
			// SQL文発行
			rs = pstatement.executeQuery();
			while(rs.next()) {
				// 列名を指定して値を取得
				userBean = new UserBean();
				userBean.setUserId(rs.getString("USERID"));
				userBean.setUserPw(rs.getString("USERPW"));
				// TODO WING
				userBean.setBorrowNum(rs.getInt("BORROWNUM"));
				// TODO DEPARTMENT
				userBean.setDeptId(rs.getInt("DEPT"));
				userBean.setName(rs.getString("NAME"));
				userBean.setGrade(rs.getInt("GRADE"));
				// TODO I → A
				userBean.setAvailability(rs.getBoolean("IVAILABILITY"));
				userList.add(userBean);
			}
			// 結果オブジェクトの開放
			rs.close();
		} finally {
			// Preparedオブジェクトの開放
			pstatement.close();
		}
		return userList;
	}

	//IDとPWでユーザーを検索
	public boolean getUserData(String userId,String userPw) throws SQLException{
		// 結果を返す用
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		boolean isExists = false;
		try {
			// SQLを保持する
			String sql = "SELECT * FROM USERTABLE WHERE userId = ? AND userPw = ?";
			pstatement = connection.prepareStatement(sql);
			pstatement.setString(1, userId);
			pstatement.setString(2, userPw);
			// SQL文発行
			rs = pstatement.executeQuery();
			isExists = rs.next();
/*
			while(rs.next()) {
				//列名を指定してResultSetオブジェクトからの値を取得
				userBean = new UserBean();
				userBean.setUserId(rs.getString("userId"));
				userBean.setUserPw(rs.getString("userPw"));
				userBean.setBorrowNum(rs.getInt("borrowNum"));
				userBean.setDept(rs.getString("dept"));
				userBean.setName(rs.getString("name"));
				userBean.setGrade(rs.getInt("grade"));
				userBean.setIvailability(rs.getBoolean("Ivailability"));
				userList.add(userBean);
			}
*/
			//ResultSetオブジェクトの解放
			rs.close();
		}finally {
			//preparedStatementオブジェクトの解放
			pstatement.close();
		}
		return isExists;
	}

	// IDでユーザー検索を行い、検索結果を返す
	public List<UserBean> getUserDataById(String userId) throws SQLException{
		// 結果を返す用
		List<UserBean> userList = new ArrayList<UserBean>();
		UserBean userBean = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		try {
			// SQLを保持する
			String sql = "SELECT * FROM USERTABLE WHERE USERID=?";
			pstatement = connection.prepareStatement(sql);
			// INパラメータの設定
			pstatement.setString(1, userId);
			// SQL文発行
			rs = pstatement.executeQuery();
			while(rs.next()) {
				// 列名を指定して値を取得
				userBean = new UserBean();
				userBean.setUserId(rs.getString("USERID"));
				userBean.setUserPw(rs.getString("USERPW"));
				// TODO WING
				userBean.setBorrowNum(rs.getInt("BORROWNUM"));
				// TODO DEPARTMENT
				userBean.setDeptId(rs.getInt("DEPT"));
				userBean.setName(rs.getString("NAME"));
				userBean.setGrade(rs.getInt("GRADE"));
				// TODO I → A
				userBean.setAvailability(rs.getBoolean("IVAILABILITY"));
				userList.add(userBean);
			}
			// 結果オブジェクトの開放
			rs.close();
		} finally {
			// Preparedオブジェクトの開放
			pstatement.close();
		}
		if(userList.size() == 0) {
			userList = null;
		}

		return userList;
	}

	// ユーザーIDと同名のIDを検索
		public boolean getUserId(String userId) throws SQLException{

			String sql=null;
			PreparedStatement statement=null;
			ResultSet rs =null;
			boolean check=false;
			try {
				sql = "SELECT COUNT(USERID) FROM USERTABLE WHERE USERID=?";
				statement = connection.prepareStatement(sql);
				statement.setString(1, userId);
				rs=statement.executeQuery();
				rs.next();
				if(rs.getInt("COUNT(USERID)") != 0){
					check=true;
				}
			}finally {
				rs.close();
				statement.close();
			}
			return check;
		}
}
