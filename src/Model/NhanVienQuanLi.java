package Model;

import java.util.Objects;

public class NhanVienQuanLi {
	public String MaNhanVien;
	private String MatKhau;
	private String TenNhanVien;
	private String GioiTinh;
	private String ngaySinh;
	private String QueQuan;
	private String NoiO;
	private String ChucVu;
	private String CCCD;
	
	public NhanVienQuanLi() {
	}
	public NhanVienQuanLi(String maNhanVien, String matKhau, String tenNhanVien, String gioiTinh, String ngaySinh,
			String queQuan, String noiO, String chucVu, String cCCD) {
		MaNhanVien = maNhanVien;
		MatKhau = matKhau;
		TenNhanVien = tenNhanVien;
		GioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		QueQuan = queQuan;
		NoiO = noiO;
		ChucVu = chucVu;
		CCCD = cCCD;
	}
	public String getMaNhanVien() {
		return MaNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		MaNhanVien = maNhanVien;
	}
	public String getMatKhau() {
		return MatKhau;
	}
	public void setMatKhau(String matKhau) {
		MatKhau = matKhau;
	}
	public String getTenNhanVien() {
		return TenNhanVien;
	}
	public void setTenNhanVien(String tenNhanVien) {
		TenNhanVien = tenNhanVien;
	}
	public String getGioiTinh() {
		return GioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		GioiTinh = gioiTinh;
	}
	public String getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getQueQuan() {
		return QueQuan;
	}
	public void setQueQuan(String queQuan) {
		QueQuan = queQuan;
	}
	public String getNoiO() {
		return NoiO;
	}
	public void setNoiO(String noiO) {
		NoiO = noiO;
	}
	public String getChucVu() {
		return ChucVu;
	}
	public void setChucVu(String chucVu) {
		ChucVu = chucVu;
	}
	public String getCCCD() {
		return CCCD;
	}
	public void setCCCD(String cCCD) {
		CCCD = cCCD;
	}
	@Override
	public String toString() {
		return "NhanVienQuanLi [MaNhanVien=" + MaNhanVien + ", MatKhau=" + MatKhau + ", TenNhanVien=" + TenNhanVien
				+ ", GioiTinh=" + GioiTinh + ", ngaySinh=" + ngaySinh + ", QueQuan=" + QueQuan + ", NoiO=" + NoiO
				+ ", ChucVu=" + ChucVu + ", CCCD=" + CCCD + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(CCCD, ChucVu, GioiTinh, MaNhanVien, MatKhau, NoiO, QueQuan, TenNhanVien, ngaySinh);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVienQuanLi other = (NhanVienQuanLi) obj;
		return Objects.equals(CCCD, other.CCCD) && Objects.equals(ChucVu, other.ChucVu)
				&& Objects.equals(GioiTinh, other.GioiTinh) && Objects.equals(MaNhanVien, other.MaNhanVien)
				&& Objects.equals(MatKhau, other.MatKhau) && Objects.equals(NoiO, other.NoiO)
				&& Objects.equals(QueQuan, other.QueQuan) && Objects.equals(TenNhanVien, other.TenNhanVien)
				&& Objects.equals(ngaySinh, other.ngaySinh);
	}
	
	
	

}