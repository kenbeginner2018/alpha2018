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

	// Library_DB�f�[�^�x�[�X�Ƃ̐ڑ���ؒf����
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
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		List<BookBean> bookList = new ArrayList<BookBean>();
		BookBean bookBean = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		try {
			// SQL��ێ�����
			String sql = "SELECT * FROM BOOKTABLE";
			pstatement = connection.prepareStatement(sql);
			// SQL�����s
			rs = pstatement.executeQuery();
			while(rs.next()) {
				// �񖼂��w�肵�Ēl���擾
				bookBean = new BookBean();
				bookBean.setLabel(rs.getString("LABEL"));
				bookBean.setTitle(rs.getString("TITLE"));
				bookBean.setPublisher(rs.getString("PUBLISHER"));
				bookBean.setPublicationYear(rs.getInt("PUBLICATIONYEAR"));
				bookBean.setAuthor(rs.getString("AUTHOR"));
				bookBean.setSubjectId(rs.getInt("SUBJECTID"));
				bookBean.setImagePath(rs.getString("IMAGEFILENAME"));
				bookBean.setStockNum(rs.getInt("STOCKNUM"));
				bookList.add(bookBean);
			}
			// ���ʃI�u�W�F�N�g�̊J��
			rs.close();
		} finally {
			// Prepared�I�u�W�F�N�g�̊J��
			pstatement.close();
		}
		return bookList;
	}

	public List<BookBean> getBookDataByInput(String title, String author, String publisher, String subject) throws SQLException{
		// ���ʂ�Ԃ��p
		List<BookBean> bookList = new ArrayList<BookBean>();
		BookBean bookBean = null;
		PreparedStatement pstatement = null;
		ResultSet rs = null;
		try {
			// SQL��ێ�����
			String sql = "SELECT * FROM BOOKTABLE";
			int flug=0;
			if((title!=null&&!title.equals(""))||(author!=null&&!author.equals(""))||
					(publisher!=null&&!publisher.equals(""))||(subject!=null&&!subject.equals(""))) {
				if(title!=null&&!title.equals("")) {
					sql += " WHERE TITLE LIKE '%"+title.trim()+"%'";
					flug++;
				}
				if(author!=null&&!author.equals("")) {
					if(flug!=0) {
						sql += " AND AUTHOR LIKE '%"+author.trim()+"%'";
					}else {
						sql += " WHERE AUTHOR LIKE '%"+author.trim()+"%'";
					}
					flug++;
				}
				if(publisher!=null&&!publisher.equals("")) {
					if(flug!=0) {
						sql+=" AND PUBLISHER LIKE '%"+publisher.trim()+"%'";
					}else {
						sql += " WHERE PUBLISHER LIKE '%"+publisher.trim()+"%'";
					}
					flug++;
				}
				if(subject!=null&&!subject.equals("")) {
					if(flug!=0) {
						sql+=" AND SUBJECT LIKE '%"+subject.trim()+"%'";
					}else {
						sql += " WHERE SUBJECT LIKE '%"+subject.trim()+"%'";
					}
				}
			}
			pstatement = connection.prepareStatement(sql);
			// SQL�����s
			rs = pstatement.executeQuery();
			while(rs.next()) {
				// �񖼂��w�肵�Ēl���擾
				bookBean = new BookBean();
				bookBean.setLabel(rs.getString("LABEL"));
				bookBean.setTitle(rs.getString("TITLE"));
				bookBean.setPublisher(rs.getString("PUBLISHER"));
				bookBean.setPublicationYear(rs.getInt("PUBLICATIONYEAR"));
				bookBean.setAuthor(rs.getString("AUTHOR"));
				bookBean.setSubjectId(rs.getInt("SUBJECTID"));
				bookBean.setImagePath(rs.getString("IMAGEFILENAME"));
				bookBean.setStockNum(rs.getInt("STOCKNUM"));
				bookList.add(bookBean);
			}
			// ���ʃI�u�W�F�N�g�̊J��
			rs.close();
		} finally {
			// Prepared�I�u�W�F�N�g�̊J��
			pstatement.close();
		}
		if(bookList.size() == 0) {
			bookList = null;
		}
		return bookList;
	}

	public BookBean getBookBean(String label) throws SQLException {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

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
			bookBean.setImagePath(rs.getString("IMAGEFILENAME"));
			bookBean.setStockNum(rs.getInt("STOCKNUM"));
		} finally {
			// Prepared�I�u�W�F�N�g�̊J��
			rs.close();
			pstatement.close();
		}
		return bookBean;
	}

	public void renewBookData(String label,String title, String author, String publisher, String publicationYear,
			String stockNum, String subName, String imagePath) throws SQLException {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		PreparedStatement pstatement = null;
		String sql="UPDATE BOOKTABLE SET title=?,author=?,publisher=?,publicationYear=?,"
				+ "stockNum=?,subjectId=?,IMAGEFILENAME=? WHERE label=?";
		int subId=-1;
		try {
			//�Ȗږ��͓��͂���Ă��邩�H
			if(subName!=null&&!subName.equals("")) {

				//���̉Ȗڂ��f�[�^�x�[�X�ɓo�^����Ă��邩�m�F����
				SubjectDAO subDAO = new SubjectDAO();
				if(subDAO.checkSubjectName(subName)) {

					//���͂��ꂽ�Ȗڂ����݂����ꍇ�͂���ID���擾
					subId=subDAO.getSubjectId(subName);
				}
			//�Ȗږ�����,�������͑��݂��Ȃ��Ȗږ������͂���Ă����ꍇ��subjectID=-1�Ƃ���
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
		// TODO �����������ꂽ���\�b�h�E�X�^�u

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
		// TODO �����������ꂽ���\�b�h�E�X�^�u

		PreparedStatement pstatement = null;
		int subId=-1;
		try {
			//�Ȗږ��͓��͂���Ă��邩�H
			if(subName!=null&&!subName.equals("")) {

				//���̉Ȗڂ��f�[�^�x�[�X�ɓo�^����Ă��邩�m�F����
				SubjectDAO subDAO =new SubjectDAO();
				if(subDAO.checkSubjectName(subName)) {

					//���͂��ꂽ�Ȗڂ����݂����ꍇ�͂���ID���擾
					subId=subDAO.getSubjectId(subName);
				}
			//�Ȗږ�����,�������͑��݂��Ȃ��Ȗږ������͂���Ă����ꍇ��subjectID=-1�Ƃ���
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
}