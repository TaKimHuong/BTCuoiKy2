package Model;
public class Phong {
	public String MaPhong;
	private String TenPhong;
	private String DienTich;
	private String Tang;
	private String SoNguoiO;
	private String GiaTien;
	public Phong() {
	}
	public Phong(String maPhong, String tenPhong, String dienTich, String tang, String soNguoiO, String giaTien) {
		
		MaPhong = maPhong;
		TenPhong = tenPhong;
		DienTich = dienTich;
		Tang = tang;
		SoNguoiO = soNguoiO;
		GiaTien = giaTien;
	}
	public String getMaPhong() {
		return MaPhong;
	}
	public void setMaPhong(String maPhong) {
		MaPhong = maPhong;
	}
	public String getTenPhong() {
		return TenPhong;
	}
	public void setTenPhong(String tenPhong) {
		TenPhong = tenPhong;
	}
	public String getDienTich() {
		return DienTich;
	}
	public void setDienTich(String dienTich) {
		DienTich = dienTich;
	}
	public String getTang() {
		return Tang;
	}
	public void setTang(String tang) {
		Tang = tang;
	}
	public String getSoNguoiO() {
		return SoNguoiO;
	}
	public void setSoNguoiO(String soNguoiO) {
		SoNguoiO = soNguoiO;
	}
	public String getGiaTien() {
		return GiaTien;
	}
	public void setGiaTien(String giaTien) {
		GiaTien = giaTien;
	}
	@Override
	public String toString() {
		return "Phong [MaPhong=" + MaPhong + ", TenPhong=" + TenPhong + ", DienTich=" + DienTich + ", Tang=" + Tang
				+ ", SoNguoiO=" + SoNguoiO + ", GiaTien=" + GiaTien + "]";
	}
	
	
	
	

}
