package entity;

public class Hoadon {
	private int mahd;
	private String date;
	private int makh;
	private double tongtien;
	
	public Hoadon(int mahd, String date, int makh, double tongtien) {
		super();
		this.mahd = mahd;
		this.date = date;
		this.makh = makh;
		this.tongtien = tongtien;
	}

	public int getMahd() {
		return mahd;
	}

	public void setMahd(int mahd) {
		this.mahd = mahd;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getMakh() {
		return makh;
	}

	public void setMakh(int makh) {
		this.makh = makh;
	}

	public double getTongtien() {
		return tongtien;
	}

	public void setTongtien(double tongtien) {
		this.tongtien = tongtien;
	}

	@Override
	public String toString() {
		return "Hoadon [mahd=" + mahd + ", date=" + date + ", makh=" + makh + ", tongtien=" + tongtien + "]";
	}
}
