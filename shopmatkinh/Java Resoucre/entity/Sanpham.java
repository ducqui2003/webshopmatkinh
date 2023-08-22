package entity;

public class Sanpham {

	private int masp;
	private String tensp;
	private String motasp;
	private double giasp;
	private String hinhsp;
	private int soluong;
	private Loaisp loaisp;
	
	public Sanpham(int masp, String tensp, String motasp, double giasp, String hinhsp, int soluong, Loaisp loaisp) {
		this.masp = masp;
		this.tensp = tensp;
		this.motasp = motasp;
		this.giasp = giasp;
		this.hinhsp = hinhsp;
		this.soluong = soluong;
		this.loaisp = loaisp;
	}


	public int getMasp() {
		return masp;
	}


	public void setMasp(int masp) {
		this.masp = masp;
	}

	public String getTensp() {
		return tensp;
	}

	public void setTensp(String tensp) {
		this.tensp = tensp;
	}

	public String getMotasp() {
		return motasp;
	}

	public void setMotasp(String motasp) {
		this.motasp = motasp;
	}

	public double getGiasp() {
		return giasp;
	}

	public void setGiasp(double giasp) {
		this.giasp = giasp;
	}

	public String getHinhsp() {
		return hinhsp;
	}

	public void setHinhsp(String hinhsp) {
		this.hinhsp = hinhsp;
	}
	
	public int getSoluong() {
		return soluong;
	}


	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}


	public Loaisp getLoaisp() {
		return loaisp;
	}


	public void setLoaisp(Loaisp loaisp) {
		this.loaisp = loaisp;
	}


	@Override
	public String toString() {
		return "Sanpham [masp=" + masp + ", tensp=" + tensp + ", motasp=" + motasp + ", giasp=" + giasp + ", hinhsp="
				+ hinhsp + ", soluong=" + soluong + ", loaisp=" + loaisp + "]";
	}
}
