package dao;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.internal.compiler.batch.Main;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import context.DBConnection;
import entity.Cart;
import entity.Hoadon;
import entity.Item;
import entity.Loaisp;
import entity.MyParameterMap;
import entity.Nguoidung;
import entity.Sanpham;

public class DAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public List<Sanpham> getAllProduct(){
		List<Sanpham> list = new ArrayList<>();
		String query = "select s.*, tenloai from sanpham s, loaisp l  where s.maloai = l.maloai";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Sanpham(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getDouble(4),
						rs.getString(5),
						rs.getInt(6),
						new Loaisp(rs.getInt(7),rs.getString(8))));
			}
			conn.close();
		}catch (Exception e) {
			e.getStackTrace();
		}
		return list;
	}
	
	public Nguoidung checkAccount(String email, String matkhau) {
		String query = "select * from nguoidung \n"+
						" where Email = ?"+
						" and Matkhau = ?";
		try {
			 conn = new DBConnection().getConnection();
			 ps = conn.prepareStatement(query);
			 ps.setString(1, email);
			 ps.setString(2, matkhau);
			 rs = ps.executeQuery();
			 while(rs.next()) {
				 return new Nguoidung(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
			 }
			 conn.close();
		}catch(Exception e){
			e.getStackTrace();
		}
		return null;
	}
	
	public Nguoidung checkAccountExist(String email) {
		String query = "select * from nguoidung\n"+
				"where Email = ?\n";
		try {
			 conn = new DBConnection().getConnection();
			 ps = conn.prepareStatement(query);
			 ps.setString(1, email);
			 rs = ps.executeQuery();
			 while(rs.next()) {
				 return new Nguoidung(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
			 }
			 conn.close();
		}catch(Exception e){
			e.getStackTrace();
		}
		return null;
	}
	
	public void signUp (String email, String matkhau) {
		String query = "INSERT INTO nguoidung (Email, Matkhau, isAdmin) VALUES (?,?,0)";
		try {
			 conn = new DBConnection().getConnection();
			 ps = conn.prepareStatement(query);
			 ps.setString(1, email);
			 ps.setString(2, matkhau);
			 ps.executeUpdate();
			 conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Sanpham> getAllProduct_Type(String type, String tindex){
		List<Sanpham> list = new ArrayList<>();
		String query = "SELECT * FROM sanpham s, loaisp l\n"
				+ "WHERE s.maloai = l.maloai and tenloai = ?\n";
		int index = 0;
		if(tindex != null) {
			index = Integer.parseInt(tindex);
			query += "LIMIT ?, 6;";
		}
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, type);
			if(tindex != null) {
				ps.setInt(2, (index-1)*6);
			}
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Sanpham(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getDouble(4),
						rs.getString(5),
						rs.getInt(6),
						new Loaisp(rs.getInt(7),rs.getString(8))));
			}
			conn.close();
		}catch (Exception e) {
			e.getStackTrace();
		}
		return list;
	}
	
	public void  insertProduct(String tensp, String motasp, double giasp, String hinhsp, int soluong, int loaisp) {
		String query = "INSERT INTO sanpham\n"+
						"(TenSP, MotaSP, GiaSP, HinhSP, SoLuong, MaLoai)\n"+
						"VALUES (?,?,?,?,?,?)";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, tensp);
			ps.setString(2, motasp);
			ps.setDouble(3, giasp);
			ps.setString(4,hinhsp);
			ps.setInt(5, soluong);
			ps.setInt(6, loaisp);
			ps.executeUpdate();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<Loaisp> getType (){
		List<Loaisp> list = new ArrayList<>();
		String query = "select * from loaisp";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Loaisp(rs.getInt(1),rs.getString(2)));
			}
			conn.close();
		}catch (Exception e) {
			e.getStackTrace();
		}
		return list;
	}
	public Sanpham getProductbyID(int productId) {
		String query = "select s.*, tenloai from sanpham s, loaisp l where masp = ? and s.maloai = l.maloai";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, productId);
			rs = ps.executeQuery();
			while(rs.next()) {
				return new Sanpham (rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getDouble(4),
						rs.getString(5),
						rs.getInt(6),
						new Loaisp(rs.getInt(7),rs.getString(8)));
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void updateSanpham(int masp, String tensp, String motasp, double giasp, Object myfile, int soluong, int loaisp) {
		String hinhsp = (String) myfile;
		String query_1 = "UPDATE sanpham\r\n"
				+ "SET TenSP = ?, MotaSP = ?, GiaSP = ?, HinhSP = ?, SoLuong = ?, MaLoai = ?\r\n"
				+ "WHERE MaSP = ?";
		String query_2 = "UPDATE sanpham\r\n"
				+ "SET TenSP = ?, MotaSP = ?, GiaSP = ?, SoLuong = ?,MaLoai = ?\r\n"
				+ "WHERE MaSP = ?";
		try {
			conn = new DBConnection().getConnection();
			if(hinhsp != null){
				ps = conn.prepareStatement(query_1);
				ps.setString(1, tensp);
				ps.setString(2, motasp);
				ps.setDouble(3, giasp);
				ps.setString(4, hinhsp);
				ps.setInt(5, loaisp);
				ps.setInt(6, loaisp);
				ps.setInt(7, masp);
				ps.executeUpdate();
			}else {
				ps = conn.prepareStatement(query_2);
				ps.setString(1, tensp);
				ps.setString(2, motasp);
				ps.setDouble(3, giasp);
				ps.setInt(4, soluong);
				ps.setInt(5, loaisp);
				ps.setInt(6, masp);
				ps.executeUpdate();
			}
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteSanpham(int masp) {
		String query = "DELETE FROM sanpham WHERE MaSP = ?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, masp);
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getTotalProduct(String category, String action, MyParameterMap conditions) {
		String query = "SELECT COUNT(*) FROM sanpham";
		String from = "";
		String where = "";
		String orderby = "";
		if(action != null) {
			for(String condition : conditions.getParameterMap().keySet()) {
				String value = conditions.getParameterMap().get(condition)[0];
				if(value.isEmpty() == false) {
					switch (condition){
					   	case "thutu":
					    	 orderby += (value == "1") ? "ORDER BY GiaSP ASC" : "ORDER BY GiaSP DESC";
					         break;
					     case "name":
					         where += (where.isEmpty()) ? "TenSP LIKE '%"+value+"%'" : " AND TenSP LIKE '%"+value+"%'";
					         break;
					     case "min":
					    	 String min = conditions.getParameterMap().get("min")[0];
					    	 String max = conditions.getParameterMap().get("max")[0];
					    	 where += (where.isEmpty()) ? "GiaSP BETWEEN " +min+ " AND " + max : "AND GiaSP BETWEEN " +min+ " AND " + max;
					         break;
					     case "category":
					    	 where += (where.isEmpty()) ? "sanpham.masp = loaisp.masp AND TenLoai = '" + category + "'" : " AND sanpham.masp = loaisp.masp AND tenloai = '" + category + "'";
					    	 from += ", loaisp";
					    	 break;
					}
				}
			}
		}
		if(!where.isEmpty()) {
			if(!from.isEmpty()) {
				query += from + "\n WHERE " + where;
			}else
				query += "\n WHERE " +where;
		} 
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getInt(1);
			}
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public List<Sanpham> getSixProducts(int index) {
		String query = "SELECT s.*, tenloai\n"
					+ "FROM sanpham s, loaisp l\n"
					+ "WHERE s.maloai = l.maloai\n"
					+ "LIMIT ?, 6";
		List<Sanpham> list = new ArrayList<>();
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, (index-1)*6);
			rs = ps.executeQuery();
			while(rs.next()) {
				 list.add(new Sanpham (rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getDouble(4),
						rs.getString(5),
						rs.getInt(6),
						new Loaisp(rs.getInt(7),rs.getString(8))));
			}
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public void checkOut(Cart cart, Nguoidung user, String thanhtoan, String mathe) {
		try {
			conn = new DBConnection().getConnection();
			
			String sql1 = "INSERT INTO donhang(Ngay, MaKH, ThanhToan, MaThe, TongTien, TrangThai) "
					+ "VALUES (?,?,?,?,?,?)";
			LocalDateTime currentDate = LocalDateTime.now();
			String date = currentDate.toString();

			PreparedStatement ps1 = conn.prepareStatement(sql1);
			ps1.setString(1, date); 
			ps1.setInt(2, user.getId());
			ps1.setString(3, thanhtoan);
			ps1.setString(4, mathe);
			ps1.setDouble(5, cart.getTotalMoney());
			ps1.setString(6, "processing");
			ps1.execute();
			
			String sql2 = "SELECT LAST_INSERT_ID() AS last_id FROM donhang";
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ResultSet rs = ps2.executeQuery();
			int oid = 0;
			while(rs.next()) {
				 oid = rs.getInt(1);
			}
			
			String sql3 = "INSERT INTO chitietdonhang (MaHD, MaSP, LuongMua, Gia)\n"
					+ "VALUES (?,?,?,?)";
			PreparedStatement ps3 = conn.prepareStatement(sql3);
			
			for(Item i : cart.getItems()) {
				 ps3.setInt(1, oid);
		         ps3.setInt(2, i.getSanpham().getMasp());
		         ps3.setInt(3, i.getLuongMua());
		         ps3.setDouble(4, i.getGia());
		         ps3.executeUpdate();
			}
			
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updatePassword(String password, String email) {
		String sql = "UPDATE nguoidung SET MatKhau = ? WHERE Email = ?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, email);
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Sanpham> filterProduct(MyParameterMap conditions , int index , String category) {
		String where = "";
		String orderby = "";
		for(String condition : conditions.getParameterMap().keySet()) {
			String value = conditions.getParameterMap().get(condition)[0];
			if(value.isEmpty() == false) {
				switch (condition){
				   	case "thutu":
				    	 orderby += (value == "1") ? "ORDER BY GiaSP ASC" : "ORDER BY GiaSP DESC";
				         break;
				     case "name":
				         where += (where.isEmpty()) ? "TenSP LIKE '%"+value+"%'" : " AND TenSP LIKE '%"+value+"%'";
				         break;
				     case "min":
				         where += (where.isEmpty()) ? " GiaSP BETWEEN " +conditions.getParameterMap().get("min")[0]+ " AND " + conditions.getParameterMap().get("max")[0] : "AND GiaSP BETWEEN " +conditions.getParameterMap().get("min")[0]+ " AND " + conditions.getParameterMap().get("max")[0]; 
				         break;
				}
			}
		}
		if(category != null) {
			where += "AND TenLoai ='" + category + "'";
		}
		String query = "";
		if(where.isEmpty()) {
			if(orderby.isEmpty()) {
				query = "SELECT s.*, tenloai\n"
						+ "FROM sanpham s, loaisp l\n"
						+ "WHERE s.MaLoai = l.Maloai \n"
						+ "LIMIT ?, 6";
			}else {
			 query = "SELECT s.*, tenloai\n"
					+ "FROM sanpham s, loaisp l\n"
					+ "WHERE s.MaLoai = l.Maloai \n "
					+ orderby + "\n"
					+ "LIMIT ?, 6";
			} 
		}else {
			if(!orderby.isEmpty()) {
				 query = "SELECT s.*, tenloai\n"
							+ "FROM sanpham s, loaisp l\n"
							+ "WHERE s.MaLoai = l.Maloai AND " + where +"\n"
							+ "LIMIT ?, 6";
			}else {
				query = "SELECT s.*, tenloai\n"
						+ "FROM sanpham s, loaisp l\n"
						+ "WHERE s.MaLoai = l.Maloai AND " + where +"\n"
						+ orderby + "\n"
						+ "LIMIT ?, 6";
			}
		}
		List<Sanpham> list = new ArrayList<>();
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, (index-1)*6);
			rs = ps.executeQuery();
			while(rs.next()) {
				 list.add(new Sanpham (rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getDouble(4),
						rs.getString(5),
						rs.getInt(6),
						new Loaisp(rs.getInt(7),rs.getString(8))));
			}
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/*public static void main(String[] args) {
		Map<String, String[]> temp = new HashMap<>();
		temp.put("min", new String[]{"10"});
		temp.put("max", new String[]{"20"});
		DAO dao = new DAO();
		for(Sanpham s : dao.filterProduct(temp,1,null)) {
			System.out.println(s);
		}
	}*/
}
