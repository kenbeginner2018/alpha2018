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

/**
 * Servlet implementation class BookSearch
 */
@WebServlet("/BookSearch")
public class BookSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// ���[�U�[�ꗗ�i�[�p
		List<BookBean> bookList = new ArrayList<BookBean>();
		// ���[�U�[�ꗗ���擾����
		try {
			BookDAO bookDAO = new BookDAO();
			bookList = bookDAO.getAllBookData();
		} catch (SQLException e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		}

		request.setAttribute("bookList", bookList);
		request.setAttribute("message", "������������͂��Ă�������");
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher("/BookSearch.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");

		// �J�ڐ�JSP���i�[
		String jsp = "/BookSearch.jsp";
		String btn = request.getParameter("button");


		try {
			BookDAO bookDAO = new BookDAO();
			List<BookBean> bookList = new ArrayList<BookBean>();
			bookList = bookDAO.getBookDataByInput(request.getParameter("title"),request.getParameter("author"),
						request.getParameter("publisher"),request.getParameter("subject"));
			request.setAttribute("bookList", bookList);
		} catch (SQLException e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		}
		if(!btn.equals("RESET")) {
			request.setAttribute("title",request.getParameter("title"));
			request.setAttribute("author",request.getParameter("author"));
			request.setAttribute("publisher",request.getParameter("publisher"));
			request.setAttribute("subject",request.getParameter("subject"));
		}

		// JSP�ɑJ��
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher(jsp); // �]�����URL
		rd.forward(request, response);
	}
}