package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.DAO;

/**
 * Servlet implementation class Insert_Severlet
 */
@MultipartConfig()
@WebServlet("/insert")
public class Insert_Severlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insert_Severlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/manageproducts.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String tensp = request.getParameter("name");
			String motasp = request.getParameter("description");
			double giasp = Double.parseDouble(request.getParameter("price"));
			Part hinhsp = request.getPart("myfile");
			int loaisp =Integer.parseInt(request.getParameter("category"));
			int soluong = Integer.parseInt(request.getParameter("quantity"));
			String realPath = request.getServletContext().getRealPath("/assets/images");
			String filename = Path.of(hinhsp.getSubmittedFileName()).getFileName().toString();
			if(!Files.exists(Path.of(realPath))) {
				Files.createDirectories(Path.of(realPath));
			}
			hinhsp.write(realPath+"/"+filename);
			DAO dao = new DAO();
			dao.insertProduct(tensp, motasp, giasp,"assets/images/"+filename,soluong, loaisp);
			response.sendRedirect("manageproduct");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
