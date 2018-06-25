package library.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.checker.LoginChecker;
import library.service.BookManagementService;
import library.service.BookSearchService;

/**
 * Servlet implementation class BookManagement
 */
@WebServlet("/BookManagement")
public class BookManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BookManagementService bm = new BookManagementService();
	BookSearchService bs = new BookSearchService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookManagement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// ログイン確認
		LoginChecker loginChecker = new LoginChecker();

		if(!loginChecker.checkLogin(request)) { // ログインしていない
			// ログイン画面に飛ばす処理
			response.sendRedirect("login");
		} else {

			ServletContext context = getServletContext();
			//遷移先ページを取得
			RequestDispatcher rd = context.getRequestDispatcher(bm.transitionPage(request));
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

//		if(bm.BookManage(request)) {
//			doGet(request,response);
//		}
		// 遷移先JSPを格納する
		String jsp = null;
		switch(bm.BookManage(request)) {
		case 0:
			doGet(request, response);
			break;
		case 1:
			// 追加or削除成功
			bs.getAllBook(request);
			jsp = "/BookSearch.jsp";
			break;
		case 2:
			// 追加失敗
			jsp = "/addBook.jsp";
			break;
		case 3:
			// 更新失敗
			jsp = bm.transitionPage(request);
		}


		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher(jsp);
		rd.forward(request, response);
	}
}