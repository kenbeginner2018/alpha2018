/**
 *
 */
package library.service;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import library.bean.BookBean;
import library.dao.BookDAO;
import library.dao.SubjectDAO;

/**
 * @author ken-newc
 *
 */
public class BookManagementService {

	//doGetから呼ばれて処理
	public String transitionPage(HttpServletRequest request) {

		String jsp=null;
		String button = request.getParameter("button");
		jsp="/error.jsp";
		if(button.equals("書籍の追加")) {
			jsp="/addBook.jsp";
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

			jsp="/BookManagement.jsp";
		}else {
			System.out.println("error");
		}
		return jsp;
	}

	//doPostから呼ばれて処理
	public boolean BookManage(HttpServletRequest request) throws UnsupportedEncodingException {

		request.setCharacterEncoding("UTF-8");
		String button = request.getParameter("button");
		boolean get=false;
		if(button.equals("詳細")) {
			get=true;
		} else if(button.equals("更新")){
			renewBook(request);
			get=true;
		} else if(button.equals("削除")) {
			deleteBook(request);
		} else if(button.equals("追加")){
			addBook(request);
		}else{
			System.out.println("ボタン判定エラー");
		}
		return get;
	}

	//BookManageから呼ばれて処理
	public void renewBook(HttpServletRequest request) {

		try {
			BookDAO bookDAO = new BookDAO();
			bookDAO.renewBookData(request.getParameter("label"),request.getParameter("title"),request.getParameter("author"),
				request.getParameter("publisher"),request.getParameter("publicationYear"),
				request.getParameter("stockNum"),request.getParameter("subjectName"),request.getParameter("imagePath"));
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	//BookManageから呼ばれて処理
	public void deleteBook(HttpServletRequest request) {

		try {
			BookDAO bookDAO = new BookDAO();
			bookDAO.deleteBookData(request.getParameter("label"));
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	//BookManageから呼ばれて処理
	public void addBook(HttpServletRequest request) {

		try {
			BookDAO bookDAO = new BookDAO();
			bookDAO.addBookData(request.getParameter("label"),request.getParameter("title"),request.getParameter("author"),
					request.getParameter("publisher"),request.getParameter("publicationYear"),
					request.getParameter("stockNum"),request.getParameter("subjectName"),request.getParameter("imagePath"));
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
