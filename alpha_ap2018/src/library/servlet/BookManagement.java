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

import library.bean.BookBean;
import library.dao.BookDAO;
import library.dao.SubjectDAO;

/**
 * Servlet implementation class BookManagement
 */
@WebServlet("/BookManagement")
public class BookManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;

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

		String button = request.getParameter("button");
		ServletContext context = getServletContext();
		RequestDispatcher rd =context.getRequestDispatcher("/error.jsp");
		if(button.equals("書籍の追加")) {
			rd = context.getRequestDispatcher("/addBook.jsp");
		}else if(button.equals("詳細")||button.equals("更新")) {
			BookBean bbn = new BookBean();
			String subName="";
			try {
				BookDAO bookDAO = new BookDAO();
				bbn = bookDAO.getBookBean(request.getParameter("label"));
				SubjectDAO subDAO = new SubjectDAO();
				subName=subDAO.getSubjectName(bbn.getSubjectId());
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			request.setAttribute("bookData",bbn);
			request.setAttribute("subName", subName);

			rd = context.getRequestDispatcher("/BookManagement.jsp");
		}else {
			System.out.println("error");
		}
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		String button = request.getParameter("button");
		if(button.equals("詳細")) {
			doGet(request,response);
		} else if(button.equals("更新")){
			try {
				BookDAO bookDAO = new BookDAO();
				bookDAO.renewBookData(request.getParameter("label"),request.getParameter("title"),request.getParameter("author"),
					request.getParameter("publisher"),request.getParameter("publicationYear"),
					request.getParameter("stockNum"),request.getParameter("subjectName"),request.getParameter("imagePath"));
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			doGet(request,response);
		} else if(button.equals("削除")) {
			try {
				BookDAO bookDAO = new BookDAO();
				bookDAO.deleteBookData(request.getParameter("label"));
			}catch(SQLException e) {
				e.printStackTrace();
			}
		} else if(button.equals("追加")){
			try {
				BookDAO bookDAO = new BookDAO();
				bookDAO.addBookData(request.getParameter("label"),request.getParameter("title"),request.getParameter("author"),
						request.getParameter("publisher"),request.getParameter("publicationYear"),
						request.getParameter("stockNum"),request.getParameter("subjectName"),request.getParameter("imagePath"));
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("ボタン判定エラー");
		}

		// ユーザー一覧格納用
		List<BookBean> bookList = new ArrayList<BookBean>();
		// ユーザー一覧を取得する
		try {
			BookDAO bookDAO = new BookDAO();
			bookList = bookDAO.getAllBookData();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		request.setAttribute("bookList", bookList);
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher("/BookSearch.jsp");
		rd.forward(request, response);
	}
}
