package library.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.dao.BookDAO;
import library.dao.RentalDao;
import library.dao.UserDAO;
import library.util.Changer;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//JSPへの転送
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/Rental.jsp");//Rental.jspの呼び出し
		dispatcher.forward(request,  response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String time = null ;
		String labelId = request.getParameter("rabelId");
		String userId = request.getParameter("userId");
		Changer changer = new Changer();		//Changerインスタンス生成

		Calendar calender = Calendar.getInstance();					//現在時刻の取得
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		calender.add(Calendar.DAY_OF_MONTH, +14);
		time= sdf.format(calender.getTime());


		try {
			BookDAO bookDao = new BookDAO();
			UserDAO userDao = new UserDAO();

			if( bookDao.getBookLabel(labelId) && userDao.getUserId(userId)){
				RentalDao rentalDao = new RentalDao();
				rentalDao.setRental(labelId,userId,time);			//貸出本をデータベースに追加
				request.setAttribute("message","タイトル名:「 "+changer.labelToTitle(labelId)+" 」を「 "
									+ changer.userIdToName(userId)+" 」さんに貸出しました");
			}else if(bookDao.getBookLabel(labelId)){
				request.setAttribute("message","ユーザーIDが間違っています");
			}else if(userDao.getUserId(userId)){
				request.setAttribute("message","識別ラベルが間違っています");
			}
			else {
				request.setAttribute("message","識別ラベルとユーザーIDが間違っています");
			}
		} catch (SQLException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

			//JSPへの転送
			ServletContext context = getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/Rental.jsp");//Rental.jspの呼び出し
			dispatcher.forward(request,  response);
	}

}
