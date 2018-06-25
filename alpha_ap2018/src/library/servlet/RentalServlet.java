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

import library.checker.LoginChecker;
import library.service.RentalService;

/**
 * Servlet implementation class RentalServlet
 */
@WebServlet("/RentalServlet")
public class RentalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RentalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    RentalService rentalService = new RentalService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ログイン確認
		LoginChecker loginChecker = new LoginChecker();
		if(!loginChecker.checkLogin(request)) { // ログインしていない
			// ログイン画面に飛ばす処理
			response.sendRedirect("login");
		} else {
			//JSPへの転送
			ServletContext context = getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/Rental.jsp");//Rental.jspの呼び出し
			dispatcher.forward(request,  response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


			try {
				rentalService.addRentalData(request);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

			//JSPへの転送
			ServletContext context = getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/Rental.jsp");//Rental.jspの呼び出し
			dispatcher.forward(request,  response);
	}

}
