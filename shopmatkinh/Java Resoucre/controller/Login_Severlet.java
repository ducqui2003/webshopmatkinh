package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;
import entity.Nguoidung;
import entity.Sanpham;

/**
 * Servlet implementation class Login_Severlet
 */
@WebServlet("/login")
public class Login_Severlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login_Severlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/login-signup.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		DAO login = new DAO();
		Nguoidung user = login.checkAccount(email, password);
		if(user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("acc", user);
			response.sendRedirect("index");
		}else {
			request.setAttribute("mess", "Wrong Email or Password");
			RequestDispatcher rd = request.getRequestDispatcher("/login-signup.jsp");
			rd.forward(request, response);
		}
	}
}
