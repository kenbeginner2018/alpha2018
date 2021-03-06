package library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library.bean.RentalBean;

public class RentalDao {

	private Connection connection;

	//■コンストラクタ■
	// Library_DBデータベースとの接続を行う
	public RentalDao() throws SQLException{

		String url = "jdbc:mysql://localhost:3306/Library_DB";
		String user = "root";
		String password = "root";
		connection = DriverManager.getConnection(url, user, password);
	}

	//■Library_DBデータベースとの接続を切断■
	public void close() {
		try {
			if(connection != null) {
				connection.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	//■貸出一覧を取得■
		public List<RentalBean> getRental(String labelId, String userId) throws SQLException{
			// 結果を返す用
			List<RentalBean> RentalList = new ArrayList<RentalBean>();
			PreparedStatement pstatement = null;
			ResultSet rs = null;
			BookDAO bookDao = new BookDAO();
			UserDAO userDao = new UserDAO();
			String sql = null;

			try {
				if(!"".equals(labelId) && "".equals(userId)) {				//labelIdで検索
					sql = "SELECT * FROM RENTALTABLE WHERE LABEL= ? ";
					pstatement = connection.prepareStatement(sql);
					pstatement.setString(1,labelId);
				}else if("".equals(labelId) && !"".equals(userId)) {				//userIdで検索
					sql = "SELECT * FROM RENTALTABLE WHERE USERID= ? ";
					pstatement = connection.prepareStatement(sql);					//connectionで問い合わせの準備
					pstatement.setString(1,userId);
				}else if( ("".equals(labelId) || labelId == null ) && ("".equals(userId)  || userId == null)  ) {						//検索結果が空白
					sql = "SELECT * FROM RENTALTABLE";								// SQL文発行
					pstatement = connection.prepareStatement(sql);
				}else if( bookDao.getBookLabel(labelId)  && userDao.getUserId(userId) ) {	//ユーザーIDと識別ラベルを入力
					sql = "SELECT * FROM RENTALTABLE WHERE USERID= ? AND LABEL = ?;";
					pstatement = connection.prepareStatement(sql);
					pstatement.setString(1,userId);
					pstatement.setString(2,labelId);
				}
					rs = pstatement.executeQuery();
					while(rs.next()) {
						RentalBean rentalBean = new RentalBean();
						rentalBean.setRentalId(rs.getInt("RENTALID"));
						rentalBean.setLabel(rs.getString("LABEL"));
						rentalBean.setUserId(rs.getString("USERID"));
						rentalBean.setRentalDate(rs.getString("RENTALDATE"));
						rentalBean.setExtendFlag(rs.getBoolean("EXTENDFLAG"));
						rentalBean.setReturnFlag(rs.getBoolean("RETURNFLAG"));
						RentalList.add(rentalBean);
					}
					rs.close();			// オブジェクトの開放
			}catch (Exception e) {
				return RentalList;
			}finally {
				pstatement.close();		// Preparedオブジェクトの開放
			}
			return RentalList;
		}


		//■返却処理■
		public void deleteRental(String rentalId) throws SQLException{

			PreparedStatement pstatement = null;
			int rentald = Integer.parseInt(rentalId);				//labelIdをint型に変更
			try {
					String sql = "UPDATE RENTALTABLE SET RETURNFLAG =TRUE WHERE RENTALID = ?";
					pstatement = connection.prepareStatement(sql);		//connectionで問い合わせの準備
					pstatement.setInt(1,rentald);						//?にrentalIdを割り当て
					pstatement.executeUpdate();							// SQL文発行
				}finally {
					pstatement.close();									// Preparedオブジェクトの開放
				}
		}

		//■貸出登録■
		public void setRental(String rabelId,String userId ,String time) throws SQLException{

			PreparedStatement pstatement = null;
			ResultSet rs = null;

			String rentalSql = "SELECT MAX(RENTALID) FROM RENTALTABLE ";		//rentalIdの最大値を検索
			pstatement = connection.prepareStatement(rentalSql);				//connectionで問い合わせの準備
			rs = pstatement.executeQuery();
			rs.next();
			try {
					String sql = "INSERT INTO RENTALTABLE (RENTALID,LABEL, USERID,RENTALDATE,EXTENDFLAG,RETURNFLAG) "
								+ "VALUES (?,?,?,?,false,false)";
					pstatement = connection.prepareStatement(sql);		//connectionで問い合わせの準備
					pstatement.setInt(1,rs.getInt("MAX(RENTALID)")+1);					//?にrentalIdを割り当て
					pstatement.setString(2,rabelId);
					pstatement.setString(3,userId);
					pstatement.setString(4,time);
					pstatement.executeUpdate();							// SQL文発行
				}finally {
					pstatement.close();									// Preparedオブジェクトの開放
				}
		}

		//■Rentalフラグを取得■
		public boolean getExtendFlag() throws SQLException{

			String sql=null;
			PreparedStatement statement=null;
			ResultSet rs =null;
			boolean check=false;
			try {
				sql = "SELECT count(EXTENDFLAG) from RENTALTABLE WHERE EXTENDFLAG = false"; //列　テーブル　行
				statement = connection.prepareStatement(sql);
				rs=statement.executeQuery();
				rs.next();
				if(rs.getInt("COUNT(EXTENDFLAG)") != 0){
					check=true;		//フラグのfalseが１つ以上ある場合（返却していない）
				}
			}finally {
				rs.close();
				statement.close();
			}
			return check;
		}

		// 対象IDのユーザの貸し出し情報を取得する
		public List<RentalBean> getAllRentalDataById(String userId) throws SQLException{
			// 結果を返す用
			List<RentalBean> rentalList = new ArrayList<RentalBean>();
			RentalBean rentalBean = null;
			PreparedStatement pstatement = null;
			ResultSet rs = null;
			try {
				// SQLを保持する
				String sql = "SELECT * FROM RENTALTABLE WHERE USERID=? AND RETURNFLAG=false";
				pstatement = connection.prepareStatement(sql);
				// INパラメータの設定
				pstatement.setString(1, userId);
				// SQL文発行
				rs = pstatement.executeQuery();
				while(rs.next()) {
					// 列名を指定して値を取得
					rentalBean = new RentalBean();
					rentalBean.setRentalId(rs.getInt("RENTALID"));
					rentalBean.setLabel(rs.getString("LABEL"));
					rentalBean.setUserId(rs.getString("USERID"));
					rentalBean.setRentalDate(rs.getString("RENTALDATE"));
					rentalBean.setExtendFlag(rs.getBoolean("EXTENDFLAG"));
					rentalBean.setReturnFlag(rs.getBoolean("RETURNFLAG"));
					rentalList.add(rentalBean);
				}
				// 結果オブジェクトの開放
				rs.close();
			} finally {
				// Preparedオブジェクトの開放
				pstatement.close();
			}
			return rentalList;
		}

}
