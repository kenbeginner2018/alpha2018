package library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
	private Connection connection;

	// コンストラクタ
	// Library_DBデータベースとの接続を行う
	public AdminDAO() throws SQLException{
		String url = "jdbc:mysql://localhost:3306/Library_DB";
		String user = "root";
		String password = "root";
		connection = DriverManager.getConnection(url, user, password);
	}

	public boolean getAdminData(String adminId, String adminPw) throws SQLException {
		// TODO 自動生成されたメソッド・スタブ
		// 結果を返す用
				PreparedStatement pstatement = null;
				ResultSet rs = null;
				boolean isExists = false;
				try {
					// SQLを保持する
					String sql = "SELECT * FROM ADMINTABLE WHERE adminId = ? AND adminPw = ?";
					pstatement = connection.prepareStatement(sql);
					pstatement.setString(1, adminId);
					pstatement.setString(2, adminPw);
					// SQL文発行
					rs = pstatement.executeQuery();
					isExists = rs.next();

					//ResultSetオブジェクトの解放
					rs.close();
				}finally {
					//preparedStatementオブジェクトの解放
					pstatement.close();
				}
				return isExists;

	}

}
