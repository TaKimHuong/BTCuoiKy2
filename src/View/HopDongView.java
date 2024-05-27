package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Model.QuanLiSinhVienKiTucXa;
import Model.SinhVien;

public class HopDongView extends JFrame implements ActionListener {
	public QuanLiSinhVienKiTucXa quanLi;
	private JLabel jLabel_QuanQuan;
	public JTextField jtf_QueQuan;
	public JTextField jtf_TenPhong;
	private JLabel jLabel_Tenphong;
	public JTextField jtf_MaPhong;
	private JLabel jLabel_MaPhong;
	public JTextField jtf_Sdt;
	private JLabel jLabel_Sdt;
	private JLabel jLabel_GioiTinh;
	public JTextField jtf_CCCD;
	private JLabel jLabel_CCCd;
	private JLabel jLabel_NoiO;
	public JTextField jtf_Masv;
	public JTextField jtf_Tensv;
	public JTextField jtf_GioiTinh;
	public JTextField jtf_NoiO;
	public JTextField jtf_MaNV;
	public DefaultTableModel df;
	private JTextField jtf_NgaySinh;
	public JLabel jLabel_NgayVao;
	public JTextField jtf_NgayVao;
	public JLabel jLabel_NgayRa;
	public JTextField jtf_NgayRa;

	public HopDongView() {
		this.setTitle("Thêm Hợp đồng");
		this.setSize(500, 670);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		JLabel jLabel_HopDong = new JLabel("THÊM HỢP ĐỒNG ", JLabel.CENTER);
		JPanel jPanel_Centerr = new JPanel();
		jPanel_Centerr.setLayout(new BorderLayout());
		JPanel jPanel_North = new JPanel();
		jPanel_North.setLayout(new BorderLayout());
		jPanel_North.add(jLabel_HopDong, BorderLayout.CENTER);
		JLabel jLabel_kt = new JLabel("     ");
		JLabel jLabel_kt1 = new JLabel("     ");
		jPanel_North.add(jLabel_kt1, BorderLayout.SOUTH);
		jPanel_North.add(jLabel_kt, BorderLayout.NORTH);
		Font font = new Font("Arial", Font.PLAIN, 30);
		jLabel_HopDong.setFont(font);
		this.add(jPanel_North, BorderLayout.NORTH);
		JLabel jLabel_Masv = new JLabel("MÃ SINH VIÊN: ", JLabel.RIGHT);
		jtf_Masv = new JTextField(30);
		JLabel jLabel_Tensv = new JLabel("TÊN SINH VIÊN: ", JLabel.RIGHT);
		jtf_Tensv = new JTextField(30);
		JLabel jLabel_NgaySinh = new JLabel("NGÀY SINH (yyyy-mm-dd):", JLabel.RIGHT);
		jtf_NgaySinh = new JTextField(30);
		jLabel_QuanQuan = new JLabel("QUÊ QUÁN: ", JLabel.RIGHT);
		jtf_QueQuan = new JTextField(30);
		jLabel_NoiO = new JLabel("NƠI Ở: ", JLabel.RIGHT);
		jtf_NoiO = new JTextField(30);
		jLabel_CCCd = new JLabel("SỐ CCCD : ", JLabel.RIGHT);
		jtf_CCCD = new JTextField(30);
		URL url_icon_notpad = TrangChuView.class.getResource("person-icon.png");
		Image img = Toolkit.getDefaultToolkit().createImage(url_icon_notpad);
		this.setIconImage(img);
		jLabel_GioiTinh = new JLabel("GIỚI TÍNH: ", JLabel.RIGHT);
		jtf_GioiTinh = new JTextField(30);
		JPanel jPanel_Center = new JPanel();
		jLabel_Sdt = new JLabel("SỐ ĐIỆN THOẠI: ", JLabel.RIGHT);
		jtf_Sdt = new JTextField(20);
		jLabel_NgayVao = new JLabel("NGÀY VÀO: " , JLabel.RIGHT);
		jtf_NgayVao = new JTextField(30);
		jLabel_NgayRa = new JLabel("NGÀY RA: " , JLabel.RIGHT);
		jtf_NgayRa = new JTextField(30);
		JLabel jLabel_MaNV = new JLabel("MÃ NHÂN VIÊN:", JLabel.RIGHT);
		jtf_MaNV = new JTextField(30);
		jPanel_Center.setLayout(new GridLayout(14, 2, 5, 5));
		jLabel_MaPhong = new JLabel("MÃ PHÒNG:", JLabel.RIGHT);
		JButton jButton_ChonPhong = new JButton("Chọn phòng");
		jButton_ChonPhong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new PhongView();
			//	new PhongviewVD();

			}
		});

		jtf_MaPhong = new JTextField(30);
		jLabel_Tenphong = new JLabel("TÊN PHÒNG", JLabel.RIGHT);
		jtf_TenPhong = new JTextField(30);
		JLabel jLabel_Kt = new JLabel("  ");
		jPanel_Center.add(jLabel_Kt);
		jPanel_Center.add(jButton_ChonPhong);
		jPanel_Center.add(jLabel_MaPhong);
		jPanel_Center.add(jtf_MaPhong);
		jPanel_Center.add(jLabel_Tenphong);
		jPanel_Center.add(jtf_TenPhong);

		jPanel_Center.add(jLabel_Masv);
		jPanel_Center.add(jtf_Masv);
		jPanel_Center.add(jLabel_Tensv);
		jPanel_Center.add(jtf_Tensv);
		jPanel_Center.add(jLabel_GioiTinh);
		jPanel_Center.add(jtf_GioiTinh);
		jPanel_Center.add(jLabel_NgaySinh);
		jPanel_Center.add(jtf_NgaySinh);
		jPanel_Center.add(jLabel_QuanQuan);
		jPanel_Center.add(jtf_QueQuan);
		jPanel_Center.add(jLabel_NoiO);
		jPanel_Center.add(jtf_NoiO);
		jPanel_Center.add(jLabel_NgayVao);
		jPanel_Center.add(jtf_NgayVao);
		jPanel_Center.add(jLabel_NgayRa);
		jPanel_Center.add(jtf_NgayRa);
		jPanel_Center.add(jLabel_CCCd);
		jPanel_Center.add(jtf_CCCD);
		jPanel_Center.add(jLabel_Sdt);
		jPanel_Center.add(jtf_Sdt);
		jPanel_Center.add(jLabel_MaNV);
		jPanel_Center.add(jtf_MaNV);

		JLabel jLabel_Icon = new JLabel(" ", JLabel.RIGHT);

		jLabel_Icon.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(HopDongView.class.getResource("contract1.png"))));
		JButton jButton_XacNhan = new JButton("Thêm");
		jButton_XacNhan.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(HopDongView.class.getResource("edit.png"))));
		jButton_XacNhan.addActionListener(this);
		JButton jButton_QuayLai = new JButton("Quay lại");
		jButton_QuayLai.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(HopDongView.class.getResource("cancel.png"))));
		JButton jButton_OK = new JButton("OK");
		jButton_OK.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(HopDongView.class.getResource("ok.png"))));
		jButton_OK.addActionListener(this);
		jPanel_Centerr.add(jPanel_Center, BorderLayout.CENTER);
		jPanel_Centerr.add(jLabel_Icon, BorderLayout.WEST);
		jButton_QuayLai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new TrangChuView();

			}
		});
		JLabel jLabel_East = new JLabel("     ");
		JLabel jLabel_West = new JLabel("               ");
		jLabel_East.setFont(font);
		JPanel jPanel_South = new JPanel();
		jPanel_South.setLayout(new FlowLayout());
		jPanel_South.add(jButton_OK);
		jPanel_South.add(jButton_XacNhan);
		jPanel_South.add(jButton_QuayLai);
		this.add(jLabel_East, BorderLayout.EAST);
		this.add(jPanel_South, BorderLayout.SOUTH);
		this.add(jLabel_West, BorderLayout.WEST);
		this.add(jPanel_Centerr, BorderLayout.CENTER);
		this.setVisible(true);
	}

	// Xóa form trong bảng thêm hợp đồng
	public void xoaForm() {
		jtf_Masv.setText(" ");
		jtf_Tensv.setText(" ");
		jtf_QueQuan.setText("");
		jtf_GioiTinh.setText("");
		jtf_CCCD.setText("");
		jtf_Sdt.setText("");
		jtf_NoiO.setText("");
		jtf_NgaySinh.setText("");
		jtf_MaPhong.setText("");
		jtf_TenPhong.setText("");
		jtf_NgayVao.setText("");
		jtf_NgayRa.setText("");

	}

	Frame f = new JFrame();
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cm = e.getActionCommand();
		JOptionPane.showMessageDialog(f, "Bạn vừa nhấn vào" + cm);
		if (cm.equals("OK")) {
			xoaForm();
		} else if (cm.equals("Thêm")) {
			try {
				// Thêm dữ liệu của sinh viên
				String masv = jtf_Masv.getText();
				String tenSinhVien = jtf_Tensv.getText();
				String GioiTinh = jtf_GioiTinh.getText();
				String QueQuan = jtf_QueQuan.getText();
				String SDT = jtf_Sdt.getText();
				String NgayVao = jtf_NgayVao.getText();
				String NgayRa = jtf_NgayRa.getText();
				String NoiO = jtf_NoiO.getText();
				String ngaysinh = jtf_NgaySinh.getText();
				String MaNhanVien = jtf_MaNV.getText();
				String Maphong = jtf_MaPhong.getText();
				String CCCD = jtf_CCCD.getText();
				SinhVien sv = new SinhVien(masv, tenSinhVien, GioiTinh, ngaysinh, QueQuan, NoiO, SDT, CCCD,NgayVao, NgayRa, MaNhanVien,
						Maphong);
				DsSinhVien dsSinhVien = new DsSinhVien();
				dsSinhVien.ThemHoacSuaSinhVien(sv);
				System.out.println("Thành công");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
