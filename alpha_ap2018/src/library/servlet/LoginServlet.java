package library.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.bean.UserBean;
import library.dao.AdminDAO;
import library.dao.UserDAO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    UserBean ub = new UserBean();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	// 結果を返す用


    	RequestDispatcher rd = null;
    	rd = getServletConfig().getServletContext().getRequestDispatcher("/Login.jsp");
    	rd.forward(request, response);
    }

    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
	  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  List<UserBean> userList = new ArrayList<UserBean>();
		  String userId = request.getParameter("username");
		  boolean flag = true;
		  String userPw = request.getParameter("password");
		  String test = userId.substring(0,1) ;
		  boolean exists = false;
		  if(userId.substring(0,1).equals("a")) {
			  flag = true;
		  }else if(userId.substring(0,1).equals("b")) {
			  flag = false;
		  }else {		//頭文字がaでもbでもない場合 ログイン失敗扱い
	    		RequestDispatcher rd = null;
	        	rd = getServletConfig().getServletContext().getRequestDispatcher("/Login.jsp");
	        	rd.forward(request, response);
		  }

		  	if(flag) {
		    	try {
		    		UserDAO userDao = new UserDAO();
					exists = userDao.getUserData(userId,userPw);
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
		    	if(exists) { // ログイン成功時

		    		HttpSession session = request.getSession(true);
		    		session.setAttribute("userId", userId);

		    		RequestDispatcher rd = null;
		        	rd = getServletConfig().getServletContext().getRequestDispatcher("/User.jsp");
		        	rd.forward(request, response);

		    	} else { // ログイン失敗時
		    		RequestDispatcher rd = null;
		        	rd = getServletConfig().getServletContext().getRequestDispatcher("/Login.jsp");
		        	rd.forward(request, response);
		    	}

		  	}else {
		  		try {
		    		AdminDAO AdminDao = new AdminDAO();
					exists = AdminDao.getAdminData(userId,userPw);
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			  	if(exists) { // ログイン成功時

		    		HttpSession session = request.getSession(true);
		    		session.setAttribute("userId", userId);

		    		RequestDispatcher rd = null;
		        	rd = getServletConfig().getServletContext().getRequestDispatcher("/Admin.jsp");
		        	rd.forward(request, response);

		    	} else { // ログイン失敗時
		    		RequestDispatcher rd = null;
		        	rd = getServletConfig().getServletContext().getRequestDispatcher("/Login.jsp");
		        	rd.forward(request, response);
		    	}
		  	}

	  }

  public String getServletInfo() {
    return "Short description";
  }

  //セッションオブジェクトのチェックメソッド
  public boolean checkSession(HttpServletRequest req) {
    HttpSession session = req.getSession(false);
    if (session != null) {
      return true;
    } else {
      return false;
    }
  }
}