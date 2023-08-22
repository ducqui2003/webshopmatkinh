package entity;

public class Item {
	private Sanpham sanpham;
	private int luongMua;
	private double gia;
	public Item(Sanpham sanpham, int luongMua, double gia) {
		super();
		this.sanpham = sanpham;
		this.luongMua = luongMua;
		this.gia = gia;
	}
	public Sanpham getSanpham() {
		return sanpham;
	}
	public void setSanpham(Sanpham sanpham) {
		this.sanpham = sanpham;
	}
	public int getLuongMua() {
		return luongMua;
	}
	public void setLuongMua(int luongMua) {
		this.luongMua = luongMua;
	}
	public double getGia() {
		return gia;
	}
	public void setGia(double gia) {
		this.gia = gia;
	}	
}
