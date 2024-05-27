package View;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;



public class TrangChuView extends JFrame implements ActionListener {
	
	
	public void TrangChuView(String text) {
		this.setTitle("Trang chủ");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 550);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		JMenuBar jMenuBar_TrangChu = new JMenuBar();
		JMenu jMenu_TaiKhoan = new JMenu("Tài khoản");
		JMenuItem jMenuItem_DanhSachTaiKhoan = new JMenuItem("Thông tin cá nhân");
		jMenuItem_DanhSachTaiKhoan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//DataHolder data = new DataHolder();
				// TODO Auto-generated method stub
				try (Socket socket = new Socket("192.168.1.3", 8080);
					    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
					    ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
					    output.writeObject("getPersonalInfo");
					    output.writeObject(text); // Gửi tên người dùng đến server
					    String userInfo = (String) input.readObject(); // Nhận thông tin cá nhân từ server
					    ThongTinCaNhan t = new  ThongTinCaNhan();
					    String[] userInfoArray = userInfo.split(":");
					    t.tf_TaiKhoan.setText(userInfoArray[0]);
					    t.tf_MK.setText(userInfoArray[1]);
					    
					    // Hiển thị thông tin cá nhân trên giao diện người dùng
					    // Ví dụ: String[] userInfoArray = userInfo.split(":");
					    // Ví dụ: usernameLabel.setText(userInfoArray[0]);
					    // Ví dụ: passwordLabel.setText(userInfoArray[1]);
					} catch (IOException | ClassNotFoundException ex) {
					    ex.printStackTrace();
					}
			}
		});
////		jMenuItem_DanhSachTaiKhoan.addActionListener(new ActionListener() {
////			
////			@Override
////			public void actionPerformed(ActionEvent e) {
////				// TODO Auto-generated method stub
////				new ThongTinCaNhan();
////			}
////		});
//		/
		jMenuItem_DanhSachTaiKhoan.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(TrangChuView.class.getResource("list1.png"))));
		JMenuItem jMenuItem_Thoat = new JMenuItem("Thoát");
		jMenuItem_Thoat.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(TrangChuView.class.getResource("exit.png"))));
		jMenuItem_Thoat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//System.exit(0);
				new DNViews();
			}
		});

		jMenu_TaiKhoan.add(jMenuItem_DanhSachTaiKhoan);
		jMenu_TaiKhoan.add(jMenuItem_Thoat);

		JMenu jMenu_ThongTinSinhVien = new JMenu("Thông tin sinh viên");
		JMenuItem jMenuItem_DanhsachSinhVien = new JMenuItem("Danh sách sinh viên");
		jMenuItem_DanhsachSinhVien.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(TrangChuView.class.getResource("list.png"))));
		jMenuItem_DanhsachSinhVien.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
					new DsSinhVien();
				
			}
		});
		JMenuItem jMenuItem_Themsinhvien = new JMenuItem("Thêm sinh viên");
		jMenuItem_Themsinhvien.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(TrangChuView.class.getResource("edit.png"))));
		jMenuItem_Themsinhvien.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new HopDongView();
			}
		});
		JMenuItem jMenuItem_DocFileSV = new JMenuItem("Đọc File");
		jMenuItem_DocFileSV.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(TrangChuView.class.getResource("list.png"))));
		jMenuItem_DocFileSV.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new MoFileView();
			}
		});
		

		jMenu_ThongTinSinhVien.add(jMenuItem_DanhsachSinhVien);
		jMenu_ThongTinSinhVien.add(jMenuItem_Themsinhvien);
		jMenu_ThongTinSinhVien.add(jMenuItem_DocFileSV);
		
	
		
		JMenu jMenu_phong = new JMenu("Phòng");
		JMenu jMenu_DienNuoc = new JMenu("Điện nước");
		JMenuItem jMenuItem_DienNuoc = new JMenuItem("Danh sách điện nước");
		jMenuItem_DienNuoc.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(TrangChuView.class.getResource("list.png"))));
		jMenu_DienNuoc.add(jMenuItem_DienNuoc);
		jMenuItem_DienNuoc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new DienNuocView();
			}
		});

		JMenuItem jMenuItem_Danhsachphong = new JMenuItem("Danh sách phòng");
		jMenuItem_Danhsachphong.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(TrangChuView.class.getResource("list.png"))));
		jMenuItem_Danhsachphong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new PhongView();
			//	new PhongviewVD();
			}
		});
		jMenu_phong.add(jMenuItem_Danhsachphong);
		jMenuBar_TrangChu.add(jMenu_TaiKhoan);
		jMenuBar_TrangChu.add(jMenu_ThongTinSinhVien);
	
		jMenuBar_TrangChu.add(jMenu_phong);
		jMenuBar_TrangChu.add(jMenu_DienNuoc);
		
		this.setJMenuBar(jMenuBar_TrangChu);
		URL url_icon_notpad = TrangChuView.class.getResource("person-icon.png");
		Image img = Toolkit.getDefaultToolkit().createImage(url_icon_notpad);
		this.setIconImage(img);
	    
        ImageIcon originalIcon = new ImageIcon("C:\\Users\\LAPTOP ACER\\eclipse-workspace\\DACK\\src\\picture\\KiTucXa.jpg");

        
        Image originalImage = originalIcon.getImage();

        
        int newWidth = 600; 
        int newHeight = 500; 
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        
        JLabel label = new JLabel(resizedIcon);
		
		this.setLayout(new BorderLayout());
		this.add(label, BorderLayout.CENTER);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
