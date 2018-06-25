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
			String sql = "SELECT * FROM USERTABLE WHERE AVAILABILITY=true";
			pstatement = connection.prepareStatement(sql);
			// SQL文発行
			rs = pstatement.executeQuery();
			while(rs.next()) {
				// 列名を指定して値を取得
				userBean = new UserBean();
				userBean.setUserId(rs.getString("USERID"));
				userBean.setUserPw(rs.getString("USERPW"));
				userBean.setBorrowNum(rs.getInt("BORROWNUM"));
				userBean.setDeptId(rs.getInt("DEPTID"));
				userBean.setName(rs.getString("NAME"));
				userBean.setGrade(rs.getInt("GRADE"));
				userBean.setAvailability(rs.getBoolean("AVAILABILITY"));
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
			String sql = "SELECT * FROM USERTABLE WHERE USERID=? AND AVAILABILITY=true";
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
				userBean.setBorrowNum(rs.getInt("BORROWNUM"));
				userBean.setDeptId(rs.getInt("DEPTID"));
				userBean.setName(rs.getString("NAME"));
				userBean.setGrade(rs.getInt("GRADE"));
				userBean.setAvailability(rs.getBoolean("AVAILABILITY"));
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

		// 指定されたIDのユーザを無効にする(フラグを操作)
		public boolean disableUserDataById(String userId) throws SQLException{
			int numRow = 0;
			PreparedStatement pstatement = null;
			try {
				// トランザクション開始
				connection.setAutoCommit(false);
				// SQLを保持するPreparedStatementの生成
				String sql = "UPDATE USERTABLE SET AVAILABILITY = false WHERE USERID=?";

				pstatement = connection.prepareStatement(sql);
				// INパラメータの設定
				pstatement.setString(1, userId);
				// SQLを発行し、登録された行数を取得
				numRow = pstatement.executeUpdate();
			} finally {
				if(numRow > 0) { // 登録成功
					connection.commit();
				} else { // 登録失敗
					connection.rollback();
				}
				// PreparedStatementオブジェクトの開放
				pstatement.close();
			}

			if(numRow > 0) {
				return true;
			} else {
				return false;
			}
		}

		// 指定されたユーザ情報をDBに追加, 返り値は成功したかどうか
		public boolean addUserData(UserBean userBean) throws SQLException{
			int numRow = 0;
			PreparedStatement pstatement = null;
			try {
				// トランザクション開始
				connection.setAutoCommit(false);
				// SQLを保持するPreparedStatementの生成
				// 1:ID, 2:PW, 借りている数, 3:学部名, 4:氏名, 5:学年, 使用可否
				String sql = "INSERT INTO USERTABLE VALUES (?, ?, 0, ?, ?, ?, true)";
				pstatement = connection.prepareStatement(sql);
				// INパラメータの設定
				pstatement.setString(1, userBean.getUserId());
				pstatement.setString(2, userBean.getUserPw());
				pstatement.setInt(3, userBean.getDeptId());
				pstatement.setString(4, userBean.getName());
				pstatement.setInt(5, userBean.getGrade());
				// SQLを発行し、登録された行数を取得
				numRow = pstatement.executeUpdate();
			} finally {
				if(numRow > 0) { // 登録成功
					connection.commit();
				} else { // 登録失敗
					connection.rollback();
				}
				// PreparedStatementオブジェクトの開放
				pstatement.close();
			}

			if(numRow > 0) {
				return true;
			} else {
				return false;
			}
		}

		public boolean updateUserDataById(String targetId, int deptId, int grade, String name) throws SQLException {
			int numRow = 0;
			PreparedStatement pstatement = null;
			try {
				// トランザクション開始
				connection.setAutoCommit(false);
				// SQLを保持するPreparedStatementの生成
				String sql = "UPDATE USERTABLE SET DEPTID = ?, GRADE = ?, NAME = ? WHERE USERID=?";

				pstatement = connection.prepareStatement(sql);
				// INパラメータの設定
				pstatement.setInt(1, deptId);
				pstatement.setInt(2, grade);
				pstatement.setString(3, name);
				pstatement.setString(4, targetId);
				// SQLを発行し、登録された行数を取得
				numRow = pstatement.executeUpdate();
			} finally {
				if(numRow > 0) { // 登録成功
					connection.commit();
				} else { // 登録失敗
					connection.rollback();
				}
				// PreparedStatementオブジェクトの開放
				pstatement.close();
			}

			if(numRow > 0) {
				return true;
			} else {
				return false;
			}
		}
}
