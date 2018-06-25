package library.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.bean.ReserveBean;
import library.checker.LoginChecker;
import library.dao.ReserveDAO;
import library.util.Changer;

/**
 * Servlet implementation class ReserveServlet
 */
@WebServlet("/reserve")
public class ReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ログイン確認
		LoginChecker loginChecker = new LoginChecker();
		if(!loginChecker.checkLogin(request)) { // ログインしていない
			// ログイン画面に飛ばす処理
			response.sendRedirect("login");
		} else {
			// 予約状況一覧格納用
			List<ReserveBean> reserveList = new ArrayList<ReserveBean>();
			// 予約状況一覧を取得する
			try {
				ReserveDAO reserveDAO = new ReserveDAO();
				reserveList = reserveDAO.getAllReserveData();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			request.setAttribute("reserveList", reserveList);


			Changer changer = new Changer();
			request.setAttribute("changer", changer);


			// JSPに遷移
			ServletContext context = getServletContext();
			RequestDispatcher rd = context.getRequestDispatcher("/Reserve.jsp"); // 転送先のURL
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コード指定
		request.setCharacterEncoding("UTF-8");

		// 押されたボタンを取得
		String btn = request.getParameter("btn");
		// 遷移先JSPを格納
		String jsp = "";

		if(btn.equals("検索")) {
			// 検索タイトル名を取得
			String searchTitle = request.getParameter("title");
			if(searchTitle.isEmpty()) { // 検索条件が空のとき
					doGet(request, response);

			} else { // 検索条件が入力されているとき
			// 予約状況一覧格納用
			List<ReserveBean> reserveList = new ArrayList<ReserveBean>();
			// 本のタイトルで予約状況検索
				try {
					ReserveDAO reserveDAO = new ReserveDAO();
					reserveList = reserveDAO.getReserveDataByTitle(request.getParameter("title"));
					request.setAttribute("reserveList", reserveList);
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}

		} else if(btn.equals("削除")){
			// 	予約状況一覧格納用
			boolean reserveUpdata = false;
			// フラグの変更に成功したときに一覧表示に遷移
			try {
				ReserveDAO reserveDAO = new ReserveDAO();
				reserveUpdata = reserveDAO.disableReserveDataById(request.getParameter("targetId"));
				if(reserveUpdata) { // 成功したとき
					doGet(request, response);
				}
			} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

		} else {
			System.out.println("ボタン判定エラー");
		}

		jsp = "/Reserve.jsp";

		Changer changer = new Changer();
		request.setAttribute("changer", changer);

		// JSPに遷移
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher(jsp); // 転送先のURL
		rd.forward(request, response);
	}

}