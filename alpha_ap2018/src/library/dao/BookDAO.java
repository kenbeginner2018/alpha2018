package library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library.bean.BookBean;

public class BookDAO {

	private static Connection connection;

	public BookDAO() throws SQLException{
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

	public List<BookBean> getAllBookData() throws SQLException{
		// TODO 自動生成されたメソッド・スタブ
		List<BookBean> bookList = new ArrayList<BookBean>();
		BookBean bookBean = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		try {
			// SQLを保持する
			String sql = "SELECT * FROM BOOKTABLE";
			pstatement = connection.prepareStatement(sql);
			// SQL文発行
			rs = pstatement.executeQuery();
			while(rs.next()) {
				// 列名を指定して値を取得
				bookBean = new BookBean();
				bookBean.setLabel(rs.getString("LABEL"));
				bookBean.setTitle(rs.getString("TITLE"));
				bookBean.setPublisher(rs.getString("PUBLISHER"));
				bookBean.setPublicationYear(rs.getInt("PUBLICATIONYEAR"));
				bookBean.setAuthor(rs.getString("AUTHOR"));
				bookBean.setSubjectId(rs.getInt("SUBJECTID"));
				bookBean.setImageFileName(rs.getString("IMAGEFILENAME"));
				bookBean.setStockNum(rs.getInt("STOCKNUM"));
				bookList.add(bookBean);
			}
			// 結果オブジェクトの開放
			rs.close();
		} finally {
			// Preparedオブジェクトの開放
			pstatement.close();
		}
		return bookList;
	}

	public List<BookBean> getBookDataByInput(String title, String author, String publisher, String subject) throws SQLException{
		// 結果を返す用
		List<BookBean> bookList = new ArrayList<BookBean>();
		BookBean bookBean = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		try {
			// SQLを保持する
			String sql = "SELECT * FROM BOOKTABLE";
			int flug=0;

			if((title!=null&&!title.equals(""))||(author!=null&&!author.equals(""))||
					(publisher!=null&&!publisher.equals(""))||(subject!=null&&!subject.equals(""))) {
				if(title!=null&&!title.equals("")) {
					sql += " WHERE TITLE LIKE '%"+title+"%'";
					flug++;
				}
				if(author!=null&&!author.equals("")) {
					if(flug!=0) {
						sql += " AND AUTHOR LIKE '%"+author+"%'";
					}else {
						sql += " WHERE AUTHOR LIKE '%"+author+"%'";
					}
					flug++;
				}
				if(publisher!=null&&!publisher.equals("")) {
					if(flug!=0) {
						sql+=" AND PUBLISHER LIKE '%"+publisher+"%'";
					}else {
						sql += " WHERE PUBLISHER LIKE '%"+publisher+"%'";
					}
					flug++;
				}
				if(subject!=null&&!subject.equals("")) {

					SubjectDAO subDAO = new SubjectDAO();
					int subId=-2;
					if(subDAO.checkSubjectName(subject,true)) {
						subId=subDAO.getSubjectId(subject,true);
					}
					if(flug!=0) {
						sql+=" AND SUBJECTID ="+subId;
					}else {
						sql += " WHERE SUBJECTID ="+subId;
					}
				}
			}
			pstatement = connection.prepareStatement(sql);
			// SQL文発行
			rs = pstatement.executeQuery();
			while(rs.next()) {
				// 列名を指定して値を取得
				bookBean = new BookBean();
				bookBean.setLabel(rs.getString("LABEL"));
				bookBean.setTitle(rs.getString("TITLE"));
				bookBean.setPublisher(rs.getString("PUBLISHER"));
				bookBean.setPublicationYear(rs.getInt("PUBLICATIONYEAR"));
				bookBean.setAuthor(rs.getString("AUTHOR"));
				bookBean.setSubjectId(rs.getInt("SUBJECTID"));
				bookBean.setImageFileName(rs.getString("IMAGEFILENAME"));
				bookBean.setStockNum(rs.getInt("STOCKNUM"));
				bookList.add(bookBean);
			}
			// 結果オブジェクトの開放
			rs.close();
		} finally {
			// Preparedオブジェクトの開放
			pstatement.close();
		}
		if(bookList.size() == 0) {
			bookList = null;
		}
		return bookList;
	}

	public BookBean getBookBean(String label) throws SQLException {
		// TODO 自動生成されたメソッド・スタブ

		BookBean bookBean = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM BOOKTABLE WHERE LABEL= ?";
			pstatement = connection.prepareStatement(sql);
			pstatement.setString(1, label);
			rs = pstatement.executeQuery();
			rs.next();
			bookBean = new BookBean();
			bookBean.setLabel(rs.getString("LABEL"));
			bookBean.setTitle(rs.getString("TITLE"));
			bookBean.setPublisher(rs.getString("PUBLISHER"));
			bookBean.setPublicationYear(rs.getInt("PUBLICATIONYEAR"));
			bookBean.setAuthor(rs.getString("AUTHOR"));
			bookBean.setSubjectId(rs.getInt("SUBJECTID"));
			bookBean.setImageFileName(rs.getString("IMAGEFILENAME"));
			bookBean.setStockNum(rs.getInt("STOCKNUM"));
		} finally {
			// Preparedオブジェクトの開放
			rs.close();
			pstatement.close();
		}
		return bookBean;
	}

	public void renewBookData(String label,String title, String author, String publisher, String publicationYear,
			String stockNum, String subName, String imagePath) throws SQLException {
		// TODO 自動生成されたメソッド・スタブ
		PreparedStatement pstatement = null;
		String sql="UPDATE BOOKTABLE SET title=?,author=?,publisher=?,publicationYear=?,"
				+ "stockNum=?,subjectId=?,IMAGEFILENAME=? WHERE label=?";
		int subId=-1;
		try {
			//科目名は入力されているか？
			if(subName!=null&&!subName.equals("")) {

				//その科目がデータベースに登録されているか確認する
				SubjectDAO subDAO = new SubjectDAO();
				if(subDAO.checkSubjectName(subName)) {

					//入力された科目が存在した場合はそのIDを取得
					subId=subDAO.getSubjectId(subName);
				}
			//科目名が空白,もしくは存在しない科目名が入力されていた場合はsubjectID=-1とする
			}
			pstatement = connection.prepareStatement(sql);
			pstatement.setString(1,title);
			pstatement.setString(2,author);
			pstatement.setString(3,publisher);
			pstatement.setInt(4,Integer.parseInt(publicationYear));
			pstatement.setInt(5,Integer.parseInt(stockNum));
			pstatement.setInt(6,subId);
			pstatement.setString(7,imagePath);
			pstatement.setString(8,label);
			pstatement.executeUpdate();
		}finally {
			pstatement.close();
		}
	}

	public void deleteBookData(String label) throws SQLException {
		// TODO 自動生成されたメソッド・スタブ

		PreparedStatement pstatement = null;
		try {
			String sql ="delete from booktable where label=?";
			pstatement = connection.prepareStatement(sql);
			pstatement.setString(1,label);
			pstatement.executeUpdate();
		}finally {
			pstatement.close();
		}
	}

	public void addBookData(String label,String title, String author, String publisher, String publicationYear,
			String stockNum, String subName, String imagePath) throws SQLException{
		// TODO 自動生成されたメソッド・スタブ

		PreparedStatement pstatement = null;
		int subId=-1;
		try {
			//科目名は入力されているか？
			if(subName!=null&&!subName.equals("")) {

				//その科目がデータベースに登録されているか確認する
				SubjectDAO subDAO =new SubjectDAO();
				if(subDAO.checkSubjectName(subName)) {

					//入力された科目が存在した場合はそのIDを取得
					subId=subDAO.getSubjectId(subName);
				}
			//科目名が空白,もしくは存在しない科目名が入力されていた場合はsubjectID=-1とする
			}
			String sql ="insert into booktable values(?,?,?,?,?,?,?,?)";
			pstatement = connection.prepareStatement(sql);
			pstatement.setString(1,label);
			pstatement.setString(2,title);
			pstatement.setString(3,publisher);
			pstatement.setInt(4,Integer.parseInt(publicationYear));
			pstatement.setString(5,author);
			pstatement.setInt(6,Integer.parseInt(stockNum));
			pstatement.setInt(7,subId);
			pstatement.setString(8,imagePath);
			pstatement.executeUpdate();
		}finally {
			pstatement.close();
		}
	}

	// 対象ラベルのタイトルを取得する(実質1つ)
	public List<BookBean> getBookDataByLabel(String bookLabel) throws SQLException{
		// 結果を返す用
		List<BookBean> bookList = new ArrayList<BookBean>();
		BookBean bookBean = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		try {
			// SQLを保持する
			String sql = "SELECT * FROM BOOKTABLE WHERE LABEL=?";
			pstatement = connection.prepareStatement(sql);
			// INパラメータの設定
			pstatement.setString(1, bookLabel);
			// SQL文発行
			rs = pstatement.executeQuery();
			while(rs.next()) {
				// 列名を指定して値を取得
				bookBean = new BookBean();
				bookBean.setLabel(rs.getString("LABEL"));
				bookBean.setTitle(rs.getString("TITLE"));
				bookBean.setPublisher(rs.getString("PUBLISHER"));
				bookBean.setPublicationYear(rs.getInt("PUBLICATIONYEAR"));
				bookBean.setAuthor(rs.getString("AUTHOR"));
				bookBean.setStockNum(rs.getInt("STOCKNUM"));
				bookBean.setSubjectId(rs.getInt("SUBJECTID"));
			//	bookBean.setImageFileName("IMAGEFILENAME");
				bookList.add(bookBean);
			}
			// 結果オブジェクトの開放
			rs.close();
		} finally {
			// Preparedオブジェクトの開放
			pstatement.close();
		}
		return bookList;
	}

	// 対象タイトルのラベルを取得する(実質1つ)
	public List<BookBean> getBookDataByTitle(String title) throws SQLException{
		// 結果を返す用
		List<BookBean> bookList = new ArrayList<BookBean>();
		BookBean bookBean = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		try {
			// SQLを保持する
			String sql = "SELECT * FROM BOOKTABLE WHERE TITLE=?";
			pstatement = connection.prepareStatement(sql);
			// INパラメータの設定
			pstatement.setString(1, title);
			// SQL文発行
			rs = pstatement.executeQuery();
			while(rs.next()) {
				// 列名を指定して値を取得
				bookBean = new BookBean();
				bookBean.setLabel(rs.getString("LABEL"));
				bookBean.setTitle(rs.getString("TITLE"));
				bookBean.setPublisher(rs.getString("PUBLISHER"));
				bookBean.setPublicationYear(rs.getInt("PUBLICATIONYEAR"));
				bookBean.setAuthor(rs.getString("AUTHOR"));
				bookBean.setStockNum(rs.getInt("STOCKNUM"));
				bookBean.setSubjectId(rs.getInt("SUBJECTID"));
			//	bookBean.setImageFileName("IMAGEFILENAME");
				bookList.add(bookBean);
			}
			// 結果オブジェクトの開放
			rs.close();
		} finally {
			// Preparedオブジェクトの開放
			pstatement.close();
		}
		return bookList;
	}

	// 対象タイトルのラベルをあいまい検索で取得する
	public List<BookBean> getBookDataByLikeTitle(String title) throws SQLException{
		// 結果を返す用
		List<BookBean> bookList = new ArrayList<BookBean>();
		BookBean bookBean = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		try {
			// SQLを保持する
			String sql = "SELECT * FROM BOOKTABLE WHERE TITLE LIKE ?";
			pstatement = connection.prepareStatement(sql);
			// INパラメータの設定
			pstatement.setString(1, "%" + title + "%");
			// SQL文発行
			rs = pstatement.executeQuery();
			while(rs.next()) {
				// 列名を指定して値を取得
				bookBean = new BookBean();
				bookBean.setLabel(rs.getString("LABEL"));
				bookBean.setTitle(rs.getString("TITLE"));
				bookBean.setPublisher(rs.getString("PUBLISHER"));
				bookBean.setPublicationYear(rs.getInt("PUBLICATIONYEAR"));
				bookBean.setAuthor(rs.getString("AUTHOR"));
				bookBean.setStockNum(rs.getInt("STOCKNUM"));
				bookBean.setSubjectId(rs.getInt("SUBJECTID"));
			//	bookBean.setImageFileName("IMAGEFILENAME");
				bookList.add(bookBean);
			}
			// 結果オブジェクトの開放
			rs.close();
		} finally {
			// Preparedオブジェクトの開放
			pstatement.close();
		}
		return bookList;
	}

	// 識別ラベルと同名のラベルを検索
	public boolean getBookLabel(String labelId) throws SQLException{

		String sql=null;
		PreparedStatement statement=null;
		ResultSet rs =null;
		boolean check=false;
		try {
			sql = "SELECT count(label) from booktable where label=?"; //列　テーブル　行
			statement = connection.prepareStatement(sql);
			statement.setString(1, labelId);
			rs=statement.executeQuery();
			rs.next();
			if(rs.getInt("COUNT(LABEL)") != 0){
				check=true;
			}
		}finally {
			rs.close();
			statement.close();
		}
		return check;
	}

	// 対象IDの本を取得する(実質1つ)
	public List<BookBean> getBookDataById(String bookLabel) throws SQLException{
		// 結果を返す用
		List<BookBean> bookList = new ArrayList<BookBean>();
		BookBean bookBean = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		try {
			// SQLを保持する
			String sql = "SELECT * FROM BOOKTABLE WHERE LABEL=?";
			pstatement = connection.prepareStatement(sql);
			// INパラメータの設定
			pstatement.setString(1, bookLabel);
			// SQL文発行
			rs = pstatement.executeQuery();
			while(rs.next()) {
				// 列名を指定して値を取得
				bookBean = new BookBean();
				bookBean.setLabel(rs.getString("LABEL"));
				bookBean.setTitle(rs.getString("TITLE"));
				bookBean.setPublisher(rs.getString("PUBLISHER"));
				bookBean.setPublicationYear(rs.getInt("PUBLICATIONYEAR"));
				bookBean.setAuthor(rs.getString("AUTHOR"));
				bookBean.setStockNum(rs.getInt("STOCKNUM"));
				bookBean.setSubjectId(rs.getInt("SUBJECTID"));
				bookBean.setImageFileName("IMAGEFILENAME");
				bookList.add(bookBean);
			}
			// 結果オブジェクトの開放
			rs.close();
		} finally {
			// Preparedオブジェクトの開放
			pstatement.close();
		}
		return bookList;
	}

}