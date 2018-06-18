package exercise06.link.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import library.bean.LinkBean;

/**
 * 「リンク」テーブルにアクセスするDAOクラス
 *
 */
public class LinkDAO extends BaseDAO {

	/**
	 * リンク情報を取得します
	 *
	 * @param リンクID
	 * @return リンク情報
	 * @throws SQLException
	 */
	public LinkBean getLinkData(String linkId)
			throws SQLException {

		// 初期化
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		// LinkBeanを作成
		LinkBean linkBean = null;

		String sql = "SELECT L.LINKID, L.CATEGORYID, C.CATEGORYNAME, L.LINKNAME, L.URL ";
		sql += "FROM LINK L ";
		sql += "JOIN LINKCATEGORY C ON L.CATEGORYID = C.CATEGORYID ";
		sql += "WHERE LINKID=? ";

		try {

			con = super.connectDAO();
			ps = con.prepareStatement(sql);
			ps.setString(1, linkId);

			rs = ps.executeQuery();

			if (rs.next()) {
				linkBean = new LinkBean();

				linkBean.setLinkId(rs.getString("LINKID"));
				linkBean.setCategoryId(rs.getString("CATEGORYID"));
				linkBean.setCategoryName(rs.getString("CATEGORYNAME"));
				linkBean.setLinkName(rs.getString("LINKNAME"));
				linkBean.setUrl(rs.getString("URL"));
			}


		} finally {
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
		return linkBean;

	}

	/**
	 * リンク一覧を取得します
	 *
	 * @param リンク種別ID
	 * @return リンク情報一覧
	 * @throws SQLException
	 */
	public ArrayList<LinkBean> getLinkList()
			throws SQLException {

		// 初期化
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		// LinkBeanを作成
		LinkBean linkBean = null;

		// ArrayListを作成
		ArrayList<LinkBean> linkList = new ArrayList<LinkBean>();

		String sql = "SELECT LINKID, CATEGORYID, LINKNAME, URL FROM LINK ";
		sql += "ORDER BY LINKID ";

		try {
			con = super.connectDAO();
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				linkBean = new LinkBean();

				linkBean.setLinkId(rs.getString("LINKID"));
				linkBean.setCategoryId(rs.getString("CATEGORYID"));
				linkBean.setLinkName(rs.getString("LINKNAME"));
				linkBean.setUrl(rs.getString("URL"));

				linkList.add(linkBean);
			}


		} finally {
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
		return linkList;

	}

	/**
	 * リンク一覧を取得します
	 *
	 * @param リンク種別ID
	 * @return リンク情報一覧
	 * @throws SQLException
	 */
	public ArrayList<LinkBean> getLinkList(String searchCtgId)
			throws SQLException {

		// 初期化
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		// LinkBeanを作成
		LinkBean linkBean = null;

		// ArrayListを作成
		ArrayList<LinkBean> linkList = new ArrayList<LinkBean>();

		String sql = "SELECT LINKID, CATEGORYID, LINKNAME, URL FROM LINK ";

		if(searchCtgId!=null && !searchCtgId.equals("")){
			sql += "WHERE CATEGORYID = '" + searchCtgId + "' ";
		}

		sql += "ORDER BY LINKID ";

		try {
			con = super.connectDAO();
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				linkBean = new LinkBean();

				linkBean.setLinkId(rs.getString("LINKID"));
				linkBean.setCategoryId(rs.getString("CATEGORYID"));
				linkBean.setLinkName(rs.getString("LINKNAME"));
				linkBean.setUrl(rs.getString("URL"));

				linkList.add(linkBean);
			}


		} finally {
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
		return linkList;

	}

	/**
	 * リンク情報を登録します
	 *
	 * @param リンク情報
	 * @return
	 * @throws SQLException
	 */
	public void insertLinkData(LinkBean bean)
			throws SQLException {
		// 初期化

		Connection con = null;
		PreparedStatement ps = null;
		//ResultSet rs = null;

		String sql = "INSERT INTO LINK VALUES(?, ?, ?, ?) ";

		try {

			con = super.connectDAO();
			ps = con.prepareStatement(sql);

			ps.setString(1, bean.getLinkId());
			ps.setString(2, bean.getCategoryId());
			ps.setString(3, bean.getLinkName());
			ps.setString(4, bean.getUrl());

			ps.executeUpdate();
			con.commit();

		} finally {
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
	}

	/**
	 * リンク情報を修正します
	 *
	 * @param リンク情報
	 * @return
	 * @throws SQLException
	 */
	public void updateLinkData(LinkBean bean)
			throws SQLException {

		// 初期化
		Connection con = null;
		PreparedStatement ps = null;
		//ResultSet rs = null;

		String sql = "UPDATE LINK SET ";
		sql += "CATEGORYID = ?, LINKNAME = ?, URL = ? WHERE LINKID = ? ";

		try {

			con = super.connectDAO();
			ps = con.prepareStatement(sql);

			ps.setString(1, bean.getCategoryId());
			ps.setString(2, bean.getLinkName());
			ps.setString(3, bean.getUrl());
			ps.setString(4, bean.getLinkId());

			ps.executeUpdate();
			con.commit();

		} finally {
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
	}

	/**
	 * リンク情報を削除します
	 *
	 * @param linkId
	 * @return
	 * @throws SQLException
	 */
	public void deleteLinkData(String linkId)
			throws SQLException {

		// 初期化
		Connection con = null;
		PreparedStatement ps = null;
		//ResultSet rs = null;

		String sql = "DELETE FROM LINK WHERE LINKID = ? ";

		try {

			con = super.connectDAO();
			ps = con.prepareStatement(sql);

			ps.setString(1, linkId);

			ps.executeUpdate();
			con.commit();

		} finally {
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		}
	}
}