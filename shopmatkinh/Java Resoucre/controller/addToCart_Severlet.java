package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.DAO;
import entity.Sanpham;
import entity.Cart;
import entity.Item;


/**
 * Servlet implementation class addToCart_Severlet
 */
@WebServlet("/addtocart")
public class addToCart_Severlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addToCart_Severlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		int quantity = 1;
		HttpSession session = request.getSession(true);
		Cart cart = null;
		Object o = session.getAttribute("cart");
		DAO dao = new DAO();
		if(o != null) {
			cart = (Cart) o;
		}else {
			cart = new Cart();
		}
			String tqty = request.getParameter("quantity");
			if(tqty == null) {
				tqty = "1";
			}
			String tid = request.getParameter("productId");
			int qty, id;
			qty = Integer.parseInt(tqty);
			id = Integer.parseInt(tid);
			Sanpham pd = dao.getProductbyID(id);
			double price = pd.getGiasp()*qty;
			Item item = new Item(pd,qty,price);
			cart.addItem(item);
			
			List<Item> list_i = cart.getItems();
			session.setAttribute("cart", cart);
			session.setAttribute("qty", cart.getSize());
			session.setAttribute("total",cart.getTotalMoney());
			response.sendRedirect(request.getHeader("referer"));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
