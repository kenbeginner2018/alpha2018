package library.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		RequestDispatcher rd = null;
		  //  	rd = getServletConfig().getServletContext().getRequestDispatcher("/ReturnServlet");
		    	rd = getServletConfig().getServletContext().getRequestDispatcher("/Admin.jsp");
		    	rd.forward(request, response);



		// 遷移先のサーブレットを決める文字列

/*
		RequestDispatcher login = null;
    	login = getServletConfig().getServletContext().getRequestDispatcher("/login");
    	login.forward(request, response);

    	RequestDispatcher booksearch = null;
    	booksearch = getServletConfig().getServletContext().getRequestDispatcher("/BookSearch");
    	booksearch.forward(request, response);

    	RequestDispatcher rentalservlet = null;
    	rentalservlet = getServletConfig().getServletContext().getRequestDispatcher("/RentalServlet");
    	rentalservlet.forward(request, response);

    	RequestDispatcher reserve = null;
    	reserve = getServletConfig().getServletContext().getRequestDispatcher("/Reserve");
    	reserve.forward(request, response);

    	RequestDispatcher requestlistservlet = null;
    	requestlistservlet = getServletConfig().getServletContext().getRequestDispatcher("/RequestListServlet");
    	requestlistservlet.forward(request, response);
*/

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
/*	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String servlet = null;

		servlet = request.getParameter("RentalServlet");

		servlet = request.getParameter("name");

		if(request.getParameter("userList") != null ){
			servlet = "/login";
		}else if(request.getParameter("RentalServlet") != null ){
			servlet = "/BookSearch";
		}else if(request.getParameter("RentalServlet") != null ){
			servlet = "/ReturnServlet";
		}else if(request.getParameter("RentalServlet") != null ){
			servlet = "/Reserve";
		}else if(request.getParameter("RentalServlet") != null ){
			servlet = "/RequestListServlet";
		}else if(request.getParameter("name") == "ReturnServlet") {
			servlet = "/ReturnServlet";
		}


		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher(servlet);
		dispatcher.forward(request,  response);

		//RequestDispatcher rd = null;
		  //  	rd = getServletConfig().getServletContext().getRequestDispatcher("/ReturnServlet");
		  //  	rd = getServletConfig().getServletContext().getRequestDispatcher(servlet);
		    //	rd.forward(request, response);

	}*/

}
