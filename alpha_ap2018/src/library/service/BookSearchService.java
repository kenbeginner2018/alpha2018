package library.service;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import library.bean.BookBean;
import library.dao.BookDAO;

public class BookSearchService {

	//蔵書一覧取得
	public void getAllBook(HttpServletRequest request) throws UnsupportedEncodingException {

		request.setCharacterEncoding("UTF-8");
		// 蔵書一覧格納用
		List<BookBean> bookList = new ArrayList<BookBean>();
		// 蔵書一覧を取得する
		try {
			BookDAO bookDAO = new BookDAO();
			bookList = bookDAO.getAllBookData();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		request.setAttribute("bookList", bookList);
	}

	//蔵書検索結果取得
	public void bookSearch(HttpServletRequest request) throws UnsupportedEncodingException {

		request.setCharacterEncoding("UTF-8");
		String btn = request.getParameter("button");
		try {
			BookDAO bookDAO = new BookDAO();
			List<BookBean> bookList = new ArrayList<BookBean>();
			bookList = bookDAO.getBookDataByInput(request.getParameter("title"),request.getParameter("author"),
						request.getParameter("publisher"),request.getParameter("subject"));
			request.setAttribute("bookList", bookList);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		if(!btn.equals("RESET")) {
			request.setAttribute("title",request.getParameter("title"));
			request.setAttribute("author",request.getParameter("author"));
			request.setAttribute("publisher",request.getParameter("publisher"));
			request.setAttribute("subject",request.getParameter("subject"));
		}
	}
}
