package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;
import entity.Cart;
import entity.Item;

/**
 * Servlet implementation class UpdateCart_Severlet
 */
@WebServlet("/updatecart")
public class updateCart_Severlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateCart_Severlet() {
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productId = Integer.parseInt(request.getParameter("product_id"));
	    int quantity = Integer.parseInt(request.getParameter("quantity"));
	    HttpSession session = request.getSession(true);
		Cart cart = null;
		Object o = session.getAttribute("cart");
		cart = (Cart) o;
		Item i = cart.getItemById(productId);
		i.setLuongMua(quantity);
		i.setGia(quantity*i.getSanpham().getGiasp());
		cart.setSize();
		session.setAttribute("cart",cart);
		session.setAttribute("qty", cart.getSize());
		session.setAttribute("total",cart.getTotalMoney());
	    JSONObject jsonResponse = new JSONObject();
		try {   
	    	jsonResponse.put("price", i.getGia());
		    jsonResponse.put("total", cart.getTotalMoney());
		}catch(JSONException e) {
			e.printStackTrace();
		}	      
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(jsonResponse.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
