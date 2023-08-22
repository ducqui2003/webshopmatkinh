package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;
import entity.Cart;
import entity.Hoadon;
import entity.Nguoidung;

/**
 * Servlet implementation class Checkout_Severlet
 */
@WebServlet("/checkout")
public class Checkout_Severlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Checkout_Severlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String thanhtoan = request.getParameter("payment");
		String mathe = request.getParameter("numcard");
		HttpSession session = request.getSession(true);
		Cart cart = null;
		Object o = session.getAttribute("cart");
		DAO dao = new DAO();
		if(o != null) {
			cart = (Cart) o;
		}else {
			cart = new Cart();
		}
		Nguoidung acc = null;
		Object a = (Nguoidung) session.getAttribute("acc");
		if(a != null) {
			acc = (Nguoidung) a;
			dao.checkOut(cart, acc, thanhtoan,mathe);
			session.removeAttribute("cart");
			session.setAttribute("qty", 0);
			session.setAttribute("total", 0);
			response.sendRedirect("index");
		}else {
			response.sendRedirect("login-signup.jsp");
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
