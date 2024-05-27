package Model;

public class DienNuoc {
	private String MaDienNuoc;
	private String SoDauKi;
	private String SoCuoiKi;
	private String SoKWTieuThu;
	private double DonGia;
	private double thanhTien;
	private String maPhong;
	public DienNuoc(String maDienNuoc, String soDauKi, String soCuoiKi, String soKWTieuThu, double donGia,
			double thanhTien, String maPhong) {
		super();
		MaDienNuoc = maDienNuoc;
		SoDauKi = soDauKi;
		SoCuoiKi = soCuoiKi;
		SoKWTieuThu = soKWTieuThu;
		DonGia = donGia;
		this.thanhTien = thanhTien;
		this.maPhong = maPhong;
	}
	public String getMaDienNuoc() {
		return MaDienNuoc;
	}
	public void setMaDienNuoc(String maDienNuoc) {
		MaDienNuoc = maDienNuoc;
	}
	public String getSoDauKi() {
		return SoDauKi;
	}
	public void setSoDauKi(String soDauKi) {
		SoDauKi = soDauKi;
	}
	public String getSoCuoiKi() {
		return SoCuoiKi;
	}
	public void setSoCuoiKi(String soCuoiKi) {
		SoCuoiKi = soCuoiKi;
	}
	public String getSoKWTieuThu() {
		return SoKWTieuThu;
	}
	public void setSoKWTieuThu(String soKWTieuThu) {
		SoKWTieuThu = soKWTieuThu;
	}
	public double getDonGia() {
		return DonGia;
	}
	public void setDonGia(double donGia) {
		DonGia = donGia;
	}
	public double getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}
	public String getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
	

}
