package exercise06.link.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * DAOの抽象クラス
 *
 */
public abstract class BaseDAO {

	/**
	 * DBManagerクラスからコネクションを確立するメソッド
	 *
	 * @return コネクション
	 * @throws SQLException
	 */
	public Connection connectDAO() throws SQLException {

		//DBManager dbm = new DBManager();
		return DBManager.getConnection();

	}
}