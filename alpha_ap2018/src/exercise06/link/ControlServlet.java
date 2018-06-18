package exercise06.link;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exercise06.link.dao.LinkCategoryDAO;
import exercise06.link.dao.LinkDAO;
import library.bean.LinkBean;
import library.bean.LinkCategoryBean;

/**
 * クライアントからのリクエストや表示画面をコントロールするサーブレットです。
 *
 */
@WebServlet("/ControlServlet")
public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("utf-8");

		// actionnameの取得
		String actionname = request.getParameter("actionname");
		if(actionname == null){
			actionname = "";
		}

		String nextPage = "LinkError.jsp";

		// actionnameによって処理の振り分け
		try{
			System.out.println("actionname = " + actionname);
			if(actionname.equals("linkMenu")){
				nextPage = menuService(request, response);
			} else if(actionname.equals("linkList")){
				nextPage = listService(request, response);
			} else if(actionname.equals("linkEntry")){
				nextPage = entryService(request, response);
			} else if(actionname.equals("linkEntryResult")){
				nextPage = entryResultService(request, response);
			} else if(actionname.equals("linkEdit")){
				nextPage = editService(request, response);
			} else if(actionname.equals("linkEditResult")){
				nextPage = editResultService(request, response);
			} else if(actionname.equals("linkDelete")){
				nextPage = deleteService(request, response);
			} else if(actionname.equals("linkDeleteResult")){
				nextPage = deleteResultService(request, response);
			} else {
				// actionnameが未設定の場合は、エラー画面を表示させる
				request.setAttribute("errorMsg", "未対応の処理が実行されました");
			}
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}

		// 処理結果によって指定のJSPに遷移させる
		request.getRequestDispatcher(nextPage)
			.forward(request, response);
	}


	/**
	 * メニュー画面を表示します。
	 *
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 */
	private String menuService(HttpServletRequest request,
			HttpServletResponse response)
		throws ServletException, IOException {

		return "LinkMenu.jsp";
	}

	/**
	 * 一覧画面を表示します。
	 *
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 */
	private String listService(HttpServletRequest request,
			HttpServletResponse response)
		throws ServletException, IOException, SQLException {

		// 画面入力情報の取得
		String searchCtgId = request.getParameter("searchCtgId");

		// リンク種別一覧を取得
		LinkCategoryDAO categoryDao = new LinkCategoryDAO();
		ArrayList<LinkCategoryBean> categoryList = categoryDao.getCategoryList();
		request.setAttribute("categoryList", categoryList);

		// リンク情報一覧を取得
		LinkDAO dao = new LinkDAO();
		ArrayList<LinkBean> linkList = dao.getLinkList(searchCtgId);
		request.setAttribute("linkList", linkList);

		// セレクトボックスを選択するため指定れたリンク種別IDを設定
		request.setAttribute("searchCtgId", searchCtgId);

		return "LinkList.jsp";
	}

	/**
	 * 登録画面を表示します。
	 *
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 */
	private String entryService(HttpServletRequest request,
			HttpServletResponse response)
		throws ServletException, IOException, SQLException {

		// リンク種別一覧を取得
		LinkCategoryDAO categoryDao = new LinkCategoryDAO();
		ArrayList<LinkCategoryBean> categoryList = categoryDao.getCategoryList();
		request.setAttribute("categoryList", categoryList);

		return "LinkEntry.jsp";
	}

	/**
	 * 登録を実行し、登録確認画面を表示します。
	 *
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 */
	private String entryResultService(HttpServletRequest request,
			HttpServletResponse response)
		throws ServletException, IOException, SQLException {

		// 画面遷移先
		String nextPage = null;

		// 画面入力情報の取得
		String linkId     = request.getParameter("linkId");
		String categoryId = request.getParameter("categoryId");
		String linkName   = request.getParameter("linkName");
		String url        = request.getParameter("url");

		LinkBean bean = new LinkBean();
		bean.setLinkId(linkId);
		bean.setCategoryId(categoryId);
		bean.setLinkName(linkName);
		bean.setUrl(url);

		LinkDAO dao = new LinkDAO();

		// リンクIDの重複チェック
		if(dao.getLinkData(linkId) == null){
			nextPage = "LinkResult.jsp";

			// 入力情報の登録
			dao.insertLinkData(bean);

			// 登録情報の取得
			LinkBean entryBean = dao.getLinkData(bean.getLinkId());
			request.setAttribute("linkBean", entryBean);

			// 処理名の設定
			request.setAttribute("action", "登録");
		}else{
			nextPage = "LinkError.jsp";
			request.setAttribute("errorMsg", "指定されたリンクIDは既に使用されています");
		}

		return nextPage;
	}

	/**
	 * 変更画面を表示します。
	 *
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 */
	private String editService(HttpServletRequest request,
			HttpServletResponse response)
		throws ServletException, IOException, SQLException {

		// 画面入力情報の取得
		String linkId = request.getParameter("linkId");

		// リンク種別一覧の取得
		LinkCategoryDAO categoryDao = new LinkCategoryDAO();
		ArrayList<LinkCategoryBean> categoryList = categoryDao.getCategoryList();
		request.setAttribute("categoryList", categoryList);

		// 変更リンク情報の取得
		LinkDAO dao = new LinkDAO();
		LinkBean linkBean = dao.getLinkData(linkId);
		request.setAttribute("linkBean", linkBean);

		return "LinkEdit.jsp";
	}

	/**
	 * 変更を実施し、変更結果画面を表示します。
	 *
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 */
	private String editResultService(HttpServletRequest request,
			HttpServletResponse response)
		throws ServletException, IOException, SQLException {

		// 画面入力情報の取得
		String linkId     = request.getParameter("linkId");
		String categoryId = request.getParameter("categoryId");
		String linkName   = request.getParameter("linkName");
		String url        = request.getParameter("url");

		LinkBean bean = new LinkBean();
		bean.setLinkId(linkId);
		bean.setCategoryId(categoryId);
		bean.setLinkName(linkName);
		bean.setUrl(url);

		LinkDAO dao = new LinkDAO();

		// 入力情報の更新
		dao.updateLinkData(bean);

		// 変更情報の取得
		LinkBean linkBean = dao.getLinkData(bean.getLinkId());
		request.setAttribute("linkBean", linkBean);

		// 処理名の設定
		request.setAttribute("action", "変更");

		return "LinkResult.jsp";
	}

	/**
	 * 削除画面を表示します。
	 *
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 */
	private String deleteService(HttpServletRequest request,
			HttpServletResponse response)
		throws ServletException, IOException, SQLException {

		// 画面入力情報の取得
		String linkId = request.getParameter("linkId");

		// 削除リンク情報の取得
		LinkDAO dao = new LinkDAO();
		LinkBean linkBean = dao.getLinkData(linkId);
		request.setAttribute("linkBean", linkBean);

		return "LinkDelete.jsp";
	}

	/**
	 * 削除を実行し、削除結果画面を表示します。
	 *
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 */
	private String deleteResultService(HttpServletRequest request,
			HttpServletResponse response)
		throws ServletException, IOException, SQLException {

		// 画面入力情報の取得
		String linkId = request.getParameter("linkId");

		LinkDAO dao = new LinkDAO();

		// 削除情報の取得
		LinkBean linkBean = dao.getLinkData(linkId);

		// 削除の実行
		dao.deleteLinkData(linkId);

		// 削除情報を表示するため入力情報を設定
		request.setAttribute("linkBean", linkBean);

		// 処理名の設定
		request.setAttribute("action", "削除");

		return "LinkResult.jsp";

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service(request, response);
	}

}
