package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;
import entity.MyParameterMap;
import entity.Sanpham;

/**
 * Servlet implementation class ProductList_Severlet
 */
@WebServlet("/product")
public class ProductList_Severlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductList_Severlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAO dao = new DAO();
		String indexP = request.getParameter("index");
		String category = request.getParameter("category");
		String action = request.getParameter("action");	
		if(indexP == null) {
			indexP = "1";
		}
		int index = Integer.parseInt(indexP);
		List<Sanpham> list= new ArrayList<>();
		if(category != null) {
			list = dao.getAllProduct_Type(category, indexP);
			request.setAttribute("category", category);
		}else {
			list = dao.getSixProducts(index);
		}

		request.setAttribute("listP", list);
		request.setAttribute("tag", index);
		int count = dao.getTotalProduct(category, null, null);
		int totalPage = dao.getTotalProduct(category, null, null)/ 6 ;
		if(count % 6 != 0) {
			totalPage ++ ;
		}
		request.setAttribute("endPage", totalPage);
		RequestDispatcher dispatcher = request.getRequestDispatcher("products.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
