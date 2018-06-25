/**
 *
 */
package library.service;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import library.bean.BookBean;
import library.checker.ValidateChecker;
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

	//doPostから呼ばれて処理 (返り値 0:詳細or更新処理, 1:削除or追加成功, 2:追加失敗)
	public int BookManage(HttpServletRequest request) throws UnsupportedEncodingException {

		request.setCharacterEncoding("UTF-8");
		String button = request.getParameter("button");
		int get = -1;
		if(button.equals("詳細")) {
			get = 0;
		} else if(button.equals("更新")){
			if(renewBook(request)) {
				get = 1; // 更新成功
			} else {
				get = 3; // 更新失敗
			}
		} else if(button.equals("削除")) {
			deleteBook(request);
			get = 1;
		} else if(button.equals("追加")){
			if(addBook(request)) {
				get = 1;
			} else {
				get = 2;
			}
		}else{
			System.out.println("ボタン判定エラー");
		}
		return get;
	}

	//BookManageから呼ばれて処理
	public boolean renewBook(HttpServletRequest request) {

		String label = request.getParameter("label");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		String publicationYear = request.getParameter("publicationYear");
		String stockNum = request.getParameter("stockNum");
		String subName = request.getParameter("subjectName");
		String imagePath = request.getParameter("imagePath");

		// リクエストが送られてきているか(JSPを介して来ているか)
		if((label != null) && (title != null) && (author != null) && (publisher != null)
		&& (publicationYear != null) && (stockNum != null) && (subName != null) && (imagePath != null) ) {
			// 必須の入力欄に値が入っているか
			if(!label.isEmpty() && !title.isEmpty() && !author.isEmpty() && !publisher.isEmpty()
			&& !publicationYear.isEmpty() && !stockNum.isEmpty()) {
				// 各パラメータのバリデーションチェックを行う
				// 入力チェックをクリアしたら
				if(checkBookData(request, publicationYear, stockNum)) {
					try {
						// 蔵書データの更新を行う
						BookDAO bookDAO = new BookDAO();
						bookDAO.renewBookData(label, title, author, publisher, publicationYear, stockNum, subName, imagePath);
					} catch (SQLException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
					// 更新成功
					return true;
				} else { // 1つでもバリデーションに引っかかったら
					// 更新失敗 (JSPに遷移してエラーメッセージ出力)
					return false;
				}
			} else {
				request.setAttribute("notNullError", "すべての項目を入力してください。");
				// 更新失敗 (JSPに遷移してエラーメッセージ出力)
				return false;
			}
		} else { // 不正アクセス
			return false;
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
	public boolean addBook(HttpServletRequest request) {

		String label = request.getParameter("label");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		String publicationYear = request.getParameter("publicationYear");
		String stockNum = request.getParameter("stockNum");
		String subName = request.getParameter("subjectName");
		String imagePath = request.getParameter("imagePath");

		ValidateChecker vc = new ValidateChecker();
		// リクエストが送られてきているか(JSPを介して来ているか)
		if((label != null) && (title != null) && (author != null) && (publisher != null)
		&& (publicationYear != null) && (stockNum != null) && (subName != null) && (imagePath != null) ) {
			// 必須の入力欄に値が入っているか
			if(!label.isEmpty() && !title.isEmpty() && !author.isEmpty() && !publisher.isEmpty()
			&& !publicationYear.isEmpty() && !stockNum.isEmpty()) {
				// 各パラメータのバリデーションチェックを行う
				// 入力チェックをクリアしたら
				if(checkBookData(request, publicationYear, stockNum)) {
					try {
						// 蔵書の追加を行う
						BookDAO bookDAO = new BookDAO();
						bookDAO.addBookData(label, title, author, publisher, publicationYear, stockNum, subName, imagePath);
					}catch(SQLException e) {
						e.printStackTrace();
					}
					// 蔵書追加に成功
					return true;
				} else {
					// 蔵書追加失敗 (JSPに遷移してエラーメッセージ出力)
					return false;
				}
			} else {
				request.setAttribute("notNullError", "すべての項目を入力してください。");
				// 蔵書追加失敗 (JSPに遷移してエラーメッセージ出力)
				return false;
			}
		} else { // 不正アクセス
			return false;
		}
	}

	// 蔵書追加の際にパラメータのバリデーションチェックを行う (返り値 true:クリア, false:エラー)
	private boolean checkBookData(HttpServletRequest request, String pubYear, String stockNum) {
		// 各チェックをクリアしたかのフラグ (クリアしたらtrueにする)
		boolean pubYearClear = false;
		boolean stockNumClear = false;
		ValidateChecker vc = new ValidateChecker();
		if(!vc.checkPubYear(pubYear)) { //
			request.setAttribute("pubYearError", vc.getPubYearErrorMessage());
		} else {
			pubYearClear = true;
		}
		if(!vc.checkStockNum(stockNum)) { //
			request.setAttribute("stockNumError", vc.getStockNumErrorMessage());
		} else {
			stockNumClear = true;
		}

		if(pubYearClear && stockNumClear) { // すべての入力チェックをクリアしたら
			return true;
		} else {
			return false;
		}
	}
}
