package library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library.bean.BookBean;
import library.bean.ReserveBean;
import library.util.Changer;

public class ReserveDAO {
	private Connection connection;

	// コンストラクタ
	// Library_DBデータベースとの接続を行う
	public ReserveDAO() throws SQLException{
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


	// 予約状況一覧を取得する
	public List<ReserveBean> getAllReserveData() throws SQLException{
	// 結果を返す用
		List<ReserveBean> reserveList = new ArrayList<ReserveBean>();
		ReserveBean reserveBean = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
			try {
				// SQLを保持する
				String sql = "SELECT * FROM RESERVETABLE WHERE RESERVECHECKFLAG = false";
				pstatement = connection.prepareStatement(sql);
				// SQL文発行
				rs = pstatement.executeQuery();
				while(rs.next()) {
					// 列名を指定して値を取得
					reserveBean = new ReserveBean();
					reserveBean.setReserveId(rs.getInt("RESERVEID"));
					reserveBean.setUserId(rs.getString("USERID"));
					reserveBean.setReserveDate(rs.getString("RESERVEDATE"));
					reserveBean.setLabel(rs.getString("LABEL"));
					// TODO I → A
					reserveBean.setReserveCheckFlag(rs.getBoolean("RESERVECHECKFLAG"));
					reserveList.add(reserveBean);
				}
				// 結果オブジェクトの開放
				rs.close();
			} finally {
				// Preparedオブジェクトの開放
				pstatement.close();
			}
			return reserveList;
		}


		// 本のタイトルで検索を行い、検索結果を返す
		public List<ReserveBean> getReserveDataByTitle(String title) throws SQLException{
			// 結果を返す用
			List<ReserveBean> reserveList = new ArrayList<ReserveBean>();
			ReserveBean reserveBean = null;
			PreparedStatement pstatement = null;
			ResultSet rs = null;
			try {
				Changer changer = new Changer();
				List<BookBean> bookList = changer.likeTitleToBookList(title);
				// SQLを保持する
				String sql = "SELECT * FROM RESERVETABLE WHERE LABEL=?";
				pstatement = connection.prepareStatement(sql);
				for(int i = 0; i < bookList.size(); i++) {
					// INパラメータの設定
					pstatement.setString(1, bookList.get(i).getLabel());
					// SQL文発行
					rs = pstatement.executeQuery();
					if(rs.next()) {
						// 列名を指定して値を取得
						reserveBean = new ReserveBean();
						reserveBean.setReserveId(rs.getInt("RESERVEID"));
						reserveBean.setUserId(rs.getString("USERID"));
						reserveBean.setReserveDate(rs.getString("RESERVEDATE"));
						reserveBean.setLabel(rs.getString("LABEL"));
						reserveBean.setReserveCheckFlag(rs.getBoolean("RESERVECHECKFLAG"));
						reserveList.add(reserveBean);
					}
				}
				if(rs != null) {
					// 結果オブジェクトの開放
					rs.close();
				}
			} finally {
				// Preparedオブジェクトの開放
				pstatement.close();
			}
			if(reserveList.size() == 0) {
				reserveList = null;
			}

			return reserveList;
		}


		// 指定された予約IDの予約確認フラグをtrueに切り替える
		public boolean disableReserveDataById(String reserveId) throws SQLException{
			int numRow = 0;
			PreparedStatement pstatement = null;
			try {
				// トランザクション開始
				connection.setAutoCommit(false);
				// SQLを保持するPreparedStatementの生成
				String sql = "UPDATE RESERVETABLE SET RESERVECHECKFLAG = true WHERE RESERVEID=?";

				pstatement = connection.prepareStatement(sql);
				// INパラメータの設定
				pstatement.setString(1, reserveId);
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
