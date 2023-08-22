package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class FilterProduct_JSP
 */
@WebServlet("/filterproduct")
public class FilterProduct_JSP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilterProduct_JSP() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		DAO dao = new DAO();
		String indexP = request.getParameter("index");
		String category = request.getParameter("category");
		if(indexP == null) {
			indexP = "1";
		}
		int index = Integer.parseInt(indexP);
		List<Sanpham> list= new ArrayList<>();
		MyParameterMap parameterMap = null;
		Object pfilter = session.getAttribute("pfilter");
		if(pfilter != null) {
			if(request.getParameter("filter") != null) {
				parameterMap = new MyParameterMap(request.getParameterMap());
			}else {
				parameterMap = (MyParameterMap) pfilter;
			}
		}else {
			parameterMap = new MyParameterMap(request.getParameterMap());
		}
		
		if(category != null) {
			list = dao.filterProduct(parameterMap, index, "");
			request.setAttribute("category", category);
		}else {
			list = dao.filterProduct(parameterMap, index, category);
		}
		String action = "filter";
		request.setAttribute("action", action);
		request.setAttribute("listP", list);
		request.setAttribute("tag", index);
		session.setAttribute("pfilter", parameterMap);
		System.out.println(session.getAttribute("pfilter"));
		System.out.println(parameterMap.toString());
		int count = dao.getTotalProduct(category, "filter", parameterMap);
		int totalPage = dao.getTotalProduct(category, "filter", parameterMap)/ 6 ;
		if(count % 6 != 0) {
			totalPage ++ ;
		}
		System.out.println(count);
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
