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

import library.bean.RequestBean;
import library.dao.RequestListDAO;
import library.util.Changer;

/**
 * Servlet implementation class ReserveServlet
 */
@WebServlet("/requestList")
public class RequestListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエスト一覧格納用
		List<RequestBean> requestList = new ArrayList<RequestBean>();
		// リクエスト一覧を取得する
		try {
			RequestListDAO requestListDAO = new RequestListDAO();
			requestList = requestListDAO.getAllRequestData();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		request.setAttribute("requestList", requestList);


		Changer changer = new Changer();
		request.setAttribute("changer", changer);


		// JSPに遷移
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher("/RequestList.jsp"); // 転送先のURL
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コード指定
		request.setCharacterEncoding("UTF-8");

		// 押されたボタンを取得
		String btn = request.getParameter("btn");
		// 遷移先JSPを格納
		String jsp = "";

		if(btn.equals("承認")) {
			// リクエスト一覧格納用
			boolean requestUpdata = false;
			// フラグの変更に成功したときに一覧表示に遷移
			try {
				RequestListDAO requestListDAO = new RequestListDAO();
				requestUpdata = requestListDAO.disableRequestDataById(request.getParameter("targetId"));
				if(requestUpdata) { // 成功したとき
					doGet(request, response);
				}
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

		} else {
			System.out.println("ボタン判定エラー");
		}

		jsp = "/RequestList.jsp";

		// JSPに遷移
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher(jsp); // 転送先のURL
		rd.forward(request, response);
	}

}