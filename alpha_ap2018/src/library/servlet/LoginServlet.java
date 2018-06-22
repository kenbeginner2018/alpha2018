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
		  String userPw = request.getParameter("password");
		  boolean exists = false;


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
	        	rd = getServletConfig().getServletContext().getRequestDispatcher("/Admin.jsp");
	        	rd.forward(request, response);

	    	} else { // ログイン失敗時
	    		RequestDispatcher rd = null;
	        	rd = getServletConfig().getServletContext().getRequestDispatcher("/Login.jsp");
	        	rd.forward(request, response);
	    	}
	  }

	    /*String formun = request.getParameter("username");
	    String formps = request.getParameter("password");

	    ub.setUserId(formun);
	    ub.setUserPw(formps);

	    /*
	    UserBean uab = null;
	    uab = new UserBean();

	    HttpSession session = null;
	    RequestDispatcher rd = null;
	    if (uab.execute() == true) {
	      //認証に成功
	      //セッションオブジェクトを作成
	      if (checkSession(request) == true) {
	        //セッションオブジェクトがある場合は獲得
	        session = request.getSession(false);
	      } else {
	        //セッションオブジェクトがない場合は新規作成
	        session = request.getSession(true);
	      }
	      //Beansをセッションオブジェクトに保存
	      session.setAttribute("userbeanses", ub);
	      rd = getServletConfig().getServletContext().getRequestDispatcher("/Login.jsp");
	    } else {
	      //認証に失敗
	      rd = getServletConfig().getServletContext().getRequestDispatcher("/Admin.jsp");
	    }
	    	rd.forward(request, response);
	  	}

/** Returns a short description of the servlet. */
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