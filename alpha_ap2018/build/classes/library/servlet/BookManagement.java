package library.servlet;

import java.io.IOException;
import java.sql.SQLException;

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


//RESET�{�^����X�V�{�^�����������Ƃ��E�̂悤�ȃG���[���\�������i�Ӑ}�������쎩�̂͐���ɂȂ���Ă���j�@�d��: �T�[�u���b�g library.servlet.BookManagement ��Servlet.service()����O�𓊂��܂��� [�� 6 20 16:18:00 JST 2018]

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
		if(button.equals("���Ђ̒ǉ�")) {
			ServletContext context = getServletContext();
			RequestDispatcher rd = context.getRequestDispatcher("/AddBook.jsp");
			rd.forward(request, response);
		}else if(button.equals("�ڍ�")) {
			BookBean bbn = new BookBean();
			String subName="";
			try {
				BookDAO bookDAO = new BookDAO();
				bbn = bookDAO.getBookBean(request.getParameter("label"));
				SubjectDAO subDAO = new SubjectDAO();
				subName=subDAO.getSubjectName(bbn.getSubjectId());
			} catch (SQLException e) {
				// TODO �����������ꂽ catch �u���b�N
				e.printStackTrace();
			}
			request.setAttribute("bookData",bbn);
			request.setAttribute("subName", subName);

			ServletContext context = getServletContext();
			RequestDispatcher rd = context.getRequestDispatcher("/BookManagement.jsp");
			rd.forward(request, response);
		}else {
			System.out.println("error");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		String button = request.getParameter("button");
		if(button.equals("�ڍ�")) {
			doGet(request,response);
		} else if(button.equals("�X�V")){
			try {
				BookDAO bookDAO = new BookDAO();
				bookDAO.renewBookData(request.getParameter("label"),request.getParameter("title"),request.getParameter("author"),
					request.getParameter("publisher"),request.getParameter("publicationYear"),
					request.getParameter("stockNum"),request.getParameter("subName"),request.getParameter("imagePath"));
			} catch (SQLException e) {
				// TODO �����������ꂽ catch �u���b�N
				e.printStackTrace();
			}
			doGet(request,response);
		} else if(button.equals("�폜")) {
			try {
				BookDAO bookDAO = new BookDAO();
				bookDAO.deleteBookData(request.getParameter("label"));
			}catch(SQLException e) {
				e.printStackTrace();
			}
		} else if(button.equals("�ǉ�")){
			try {
				BookDAO bookDAO = new BookDAO();
				bookDAO.addBookData(request.getParameter("label"),request.getParameter("title"),request.getParameter("author"),
						request.getParameter("publisher"),request.getParameter("publicationYear"),
						request.getParameter("stockNum"),request.getParameter("subName"),request.getParameter("imagePath"));
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("�{�^������G���[");
		}
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher("/BookSearch.jsp");
		rd.forward(request, response);
	}
}
