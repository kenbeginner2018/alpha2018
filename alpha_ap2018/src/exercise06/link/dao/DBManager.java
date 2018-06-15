package exercise06.link.dao;


import java.sql.Connection;
import java.sql.DriverManager;

/**
 * DB接続、SQLの実行を行うクラスです。
 *
 */
public class DBManager {

	/**
	 * DBとのコネクションを確立します。
	 */
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", "root", "root");
			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
}
