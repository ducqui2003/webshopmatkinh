package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.DAO;

/**
 * Servlet implementation class Update_Severlet
 */
@MultipartConfig()
@WebServlet("/update")
public class Update_Severlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update_Severlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAO dao = new DAO();
		int masp = Integer.parseInt(request.getParameter("productcode"));
		String tensp = request.getParameter("name");
		String motasp = request.getParameter("description");
		double giasp = Double.parseDouble(request.getParameter("price"));
		int soluong = Integer.parseInt(request.getParameter("quantity"));
		int loaisp =Integer.parseInt(request.getParameter("category"));
		if(request.getPart("myfile").getSize() != 0) {
			Part hinhsp = request.getPart("myfile");
			String realPath = request.getServletContext().getRealPath("/assets/images");
			String filename = Path.of(hinhsp.getSubmittedFileName()).getFileName().toString();
			if(!Files.exists(Path.of(realPath))) {
				Files.createDirectories(Path.of(realPath));
			}
			hinhsp.write(realPath+"/"+filename);
			dao.updateSanpham(masp, tensp, motasp, giasp,"assets/images"+filename,soluong, loaisp);
		}else {
			dao.updateSanpham(masp, tensp, motasp, giasp, null,soluong,loaisp);
		}
		response.sendRedirect("manageproduct");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
