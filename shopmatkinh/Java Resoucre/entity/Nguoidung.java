package entity;

public class Nguoidung {
	private int id;
	private String email;
	private String matkhau;
	private int admin;

	
	public Nguoidung(int id, String email, String matkhau, int admin) {
		super();
		this.id = id;
		this.email = email;
		this.matkhau = matkhau;
		this.admin = admin;
	}

	public Nguoidung(int id, String email, String matkhau) {
		super();
		this.id = id;
		this.email = email;
		this.matkhau = matkhau;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMatkhau() {
		return matkhau;
	}

	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}

	@Override
	public String toString() {
		return "Nguoidung [id=" + id + ", email=" + email + ", matkhau=" + matkhau + "]";
	}
}
