package Model;

import java.util.ArrayList;

public class QuanLiSinhVienKiTucXa {
	private ArrayList<SinhVien> dsSinhVien;
	private String luaChon;
	public QuanLiSinhVienKiTucXa() {
		
		this.dsSinhVien = new ArrayList<SinhVien>();
		this.luaChon = "";
	}
	public ArrayList<SinhVien> getDsSinhVien() {
		return dsSinhVien;
	}
	public void setDsSinhVien(ArrayList<SinhVien> dsSinhVien) {
		this.dsSinhVien = dsSinhVien;
	}
	public String getLuaChon() {
		return luaChon;
	}
	public void setLuaChon(String luaChon) {
		this.luaChon = luaChon;
	}
	public void insert(SinhVien sv) {
		this.dsSinhVien.add(sv);
	}
	public void delete (SinhVien sv) {
		this.dsSinhVien.remove(sv);
	}

}
