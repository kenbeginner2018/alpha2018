package library.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import library.bean.RentalBean;
import library.dao.RentalDao;
import library.util.Changer;

public class ReturnService {

	public void rentalListSearch(HttpServletRequest request) throws SQLException {

		List<RentalBean> rentalList = new ArrayList<RentalBean>();	// レンタルBean格納変数
		Changer changer = new Changer();							//Changerインスタンス生成

		String  labelId = request.getParameter("labelId");
		String userId = request.getParameter("userId");

		try {
			RentalDao rentalDao = new RentalDao();
			rentalList = rentalDao.getRental(labelId,userId);		//戻り値でBeanのリストを取得
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("RentalList", rentalList);				//requestにrentalListを登録
		request.setAttribute("Changer", changer);
	}

	public void updateReturnFlag(HttpServletRequest request) throws SQLException {

		Changer changer = new Changer();
		String userId = request.getParameter("userId");		 //ユーザーIDを取得
		String rentalId = request.getParameter("rentalId");	 //予約IDを取得
		String labelId = request.getParameter("labelId");		 //ラベルIDを取得

		if(null != changer.userIdToName(userId) ) {//返却ボタンが押されているか
			request.setAttribute("message","「"+changer.userIdToName(userId)+"」さんが「"
					+ changer.labelToTitle(labelId)+"」の本を返却しました");
			try {
				RentalDao rentalDao = new RentalDao();
				rentalDao.deleteRental(rentalId);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}else {
			request.setAttribute("message","返却処理に失敗しました");
		}
	}

}
