package library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library.bean.DeptBean;

public class DeptDAO {
	private Connection connection;

	// コンストラクタ
	// Library_DBデータベースとの接続を行う
	public DeptDAO() throws SQLException{
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

	// 学部のリストを取得する
	public List<DeptBean> getAllDept() throws SQLException{
		// 結果を返す用
		List<DeptBean> deptList = new ArrayList<DeptBean>();
		DeptBean deptBean = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		try {
			// SQLを保持する
			String sql = "SELECT * FROM DEPARTMENTTABLE";
			pstatement = connection.prepareStatement(sql);
			// SQL文発行
			rs = pstatement.executeQuery();
			while(rs.next()) {
				// 列名を指定して値を取得
				deptBean = new DeptBean();
				deptBean.setDeptId(rs.getInt("DEPARTMENTID"));
				deptBean.setDeptName(rs.getString("DEPARTMENTNAME"));
				deptList.add(deptBean);
			}
			// 結果オブジェクトの開放
			rs.close();
		} finally {
			// Preparedオブジェクトの開放
			pstatement.close();
		}
		return deptList;
	}
}
