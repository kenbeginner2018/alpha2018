package exercise06.link.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exercise06.link.bean.LinkCategoryBean;

/**
 * 「リンク種別」テーブルにアクセスするDAOクラス
 *
 */
public class LinkCategoryDAO extends BaseDAO {

	/**
	 * リンク種別一覧を取得します
	 *
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<LinkCategoryBean> getCategoryList()
			throws SQLException {
		// 初期化
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		// LinkCategoryBeanを作成
		LinkCategoryBean categoryBean = null;

		// ArrayListを作成
		ArrayList<LinkCategoryBean> categoryList = new ArrayList<LinkCategoryBean>();

		String sql = "SELECT CATEGORYID, CATEGORYNAME FROM LINKCATEGORY ORDER BY CATEGORYID ";
		try {

			con = super.connectDAO();
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				categoryBean = new LinkCategoryBean();

				categoryBean.setCategoryId(rs.getString("CATEGORYID"));
				categoryBean.setCategoryName(rs.getString("CATEGORYNAME"));

				categoryList.add(categoryBean);
			}


		} finally {
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
		return categoryList;

	}
}