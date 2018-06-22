package library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library.bean.RequestBean;

public class RequestListDAO {
	private Connection connection;

	// コンストラクタ
	// Library_DBデータベースとの接続を行う
	public RequestListDAO() throws SQLException{
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


	// リクエスト一覧を取得する
	public List<RequestBean> getAllRequestData() throws SQLException{
	// 結果を返す用
		List<RequestBean> requestList = new ArrayList<RequestBean>();
		RequestBean requestBean = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
			try {
				// SQLを保持する
				String sql = "SELECT * FROM REQUESTTABLE WHERE CHECKFLAG = false";
				pstatement = connection.prepareStatement(sql);
				// SQL文発行
				rs = pstatement.executeQuery();
				while(rs.next()) {
					// 列名を指定して値を取得
					requestBean = new RequestBean();
					requestBean.setRequestId(rs.getInt("REQUESTID"));
					requestBean.setUserId(rs.getString("USERID"));
					requestBean.setRequestDate(rs.getString("REQUESTDATE"));
					requestBean.setCheckFlag(rs.getBoolean("CHECKFLAG"));
					requestBean.setAuthor(rs.getString("AUTHOR"));
					requestBean.setTitle(rs.getString("TITLE"));
					requestBean.setPublisher(rs.getString("PUBLISHER"));
					requestList.add(requestBean);
				}
				// 結果オブジェクトの開放
				rs.close();
			} finally {
				// Preparedオブジェクトの開放
				pstatement.close();
			}
			return requestList;
		}




	// 指定されたリクエストIDの確認フラグをtrueに切り替える
		public boolean disableRequestDataById(String requestId) throws SQLException{
			int numRow = 0;
			PreparedStatement pstatement = null;
			try {
				// トランザクション開始
				connection.setAutoCommit(false);
				// SQLを保持するPreparedStatementの生成
				String sql = "UPDATE REQUESTTABLE SET CHECKFLAG = true WHERE REQUESTID=?";

				pstatement = connection.prepareStatement(sql);
				// INパラメータの設定
				pstatement.setString(1, requestId);
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
				return false;
			} else {
				return true;
			}
		}

	}
