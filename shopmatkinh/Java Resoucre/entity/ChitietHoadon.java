package entity;

public class ChitietHoadon {
	private int mahd;
	private int masp;
	private int luongmua;
	private double gia;
	
	
	public ChitietHoadon(int mahd, int masp, int luongmua, double gia) {
		this.mahd = mahd;
		this.masp = masp;
		this.luongmua = luongmua;
		this.gia = gia;
	}
	
	public int getMahd() {
		return mahd;
	}
	public void setMahd(int mahd) {
		this.mahd = mahd;
	}
	public int getMasp() {
		return masp;
	}
	public void setMasp(int masp) {
		this.masp = masp;
	}
	public int getLuongmua() {
		return luongmua;
	}
	public void setLuongmua(int luongmua) {
		this.luongmua = luongmua;
	}
	public double getGia() {
		return gia;
	}
	public void setGia(double gia) {
		this.gia = gia;
	}
	
	
}
