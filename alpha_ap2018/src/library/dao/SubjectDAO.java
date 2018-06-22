package library.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectDAO {
	private Connection connection;

	public SubjectDAO() throws SQLException{
		String url = "jdbc:mysql://localhost:3306/Library_DB";
		String user = "root";
		String password = "root";
		connection = DriverManager.getConnection(url, user, password);
	}

	public int getSubjectId(String subName) throws SQLException {
		String sql=null;
		ResultSet rs=null;
		PreparedStatement statement=null;
		int subId=-1;

		try {
			sql = "SELECT subjectId FROM SUBJECTTABLE WHERE SUBJECTNAME=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1,subName);
			rs = statement.executeQuery();
			rs.next();
			subId=rs.getInt("SUBJECTID");
		}finally {
			rs.close();
			statement.close();
		}
		return subId;
	}

	public String getSubjectName(int subId) throws SQLException{

		String sql=null;
		ResultSet rs=null;
		PreparedStatement statement=null;
		String subName=null;

		if(subId!=-1) {
			try {
				sql = "SELECT subjectName FROM SUBJECTTABLE WHERE SUBJECTID=?";
				statement = connection.prepareStatement(sql);
				statement.setInt(1,subId);
				rs = statement.executeQuery();
				rs.next();
				subName=rs.getString("SUBJECTNAME");
			}finally {
				rs.close();
				statement.close();
			}
		}else {
			subName="";
		}
		return subName;
	}

	//指定された科目名が科目テーブルに存在するか確認する
	public boolean checkSubjectName(String subName) throws SQLException {

		String sql=null;
		PreparedStatement statement=null;
		ResultSet rs =null;
		boolean check=false;
		try {
			sql = "SELECT count(subjectname) from subjecttable where subjectname=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, subName);
			rs=statement.executeQuery();
			rs.next();
			if(rs.getInt("COUNT(SUBJECTNAME)")==1){
				check=true;
			}
		}finally {
			rs.close();
			statement.close();
		}
		return check;
	}
}
