package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DangKyView extends JFrame implements ActionListener {
	public DangKyView() {
		this.setTitle("Đăng ký tài khoản");
		this.setSize(350,200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		JPanel pn_DangKy = new JPanel();
		pn_DangKy.setLayout(new BorderLayout());
		JLabel lb_DangKy = new JLabel("ĐĂNG KÝ" , JLabel.CENTER);
		JLabel lb_North = new JLabel("    ");
		Font font = new Font("Arial", Font.BOLD, 25);
		lb_DangKy.setFont(font);
		pn_DangKy.add(lb_DangKy, BorderLayout.CENTER);
		pn_DangKy.add(lb_North, BorderLayout.NORTH);
		JPanel pn_Center = new JPanel();
		pn_Center.setLayout(new BorderLayout());
		JPanel pn_TKMK = new JPanel();
		pn_TKMK.setLayout(new GridLayout(2,2,5,5));
		JLabel lb_TaiKhoan = new JLabel("TÀI KHOẢN:", JLabel.RIGHT);
		JTextField tf_TaiKhoan = new JTextField(30);
		JLabel lb_MatKhau = new JLabel("MẬT KHẨU:", JLabel.RIGHT);
		JPasswordField tf_MatKhau = new JPasswordField(30);
		pn_TKMK.add(lb_TaiKhoan);
		pn_TKMK.add(tf_TaiKhoan);
		pn_TKMK.add(lb_MatKhau);
		pn_TKMK.add(tf_MatKhau);
		JLabel lb_DOng = new JLabel("       ");
		JPanel pn_South = new JPanel();
		pn_South.setLayout(new FlowLayout());
		JButton bt_DangKy = new JButton("Đăng ký");
		bt_DangKy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = tf_TaiKhoan.getText();
                String password = new String(tf_MatKhau.getPassword());
                String hashedPassword = encryptMD5(password);
                try (Socket socket = new Socket("192.168.1.3", 8080);
                     ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                     ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
                    
                    output.writeObject("REGISTER");
                    output.writeObject(username);
                    output.writeObject(hashedPassword);

                    String response = (String) input.readObject();
                    if (response.equals("SUCCESS")) {
                        JOptionPane.showMessageDialog(null, "Registration successful!");
                      //  mainApp.showLogin();
                        new DNViews();
                    } else {
                        JOptionPane.showMessageDialog(null, "Registration failed.");
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Registration failed.");
                    ex.printStackTrace();
                }
            }
        });
		JButton bt_QuayLai = new JButton("Quay lại");
		bt_QuayLai.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new DNViews();
			}
		});
		pn_South.add(bt_DangKy);
		pn_South.add(bt_QuayLai);
		
		pn_Center.add(pn_TKMK, BorderLayout.CENTER);
		this.add(pn_Center, BorderLayout.CENTER);
		this.add(pn_DangKy , BorderLayout.NORTH );
		this.add(lb_DOng, BorderLayout.EAST);
		this.add(pn_South, BorderLayout.SOUTH);
		this.setVisible(true);
	}
	 public static String encryptMD5(String password) {
	        try {
	            // Tạo instance của MessageDigest với thuật toán MD5
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            // Chuyển đổi mật khẩu thành mảng byte
	            byte[] messageDigest = md.digest(password.getBytes());
	            // Chuyển đổi mảng byte thành đối tượng String Hex
	            StringBuilder hexString = new StringBuilder();
	            for (byte b : messageDigest) {
	                String hex = Integer.toHexString(0xff & b);
	                if (hex.length() == 1) hexString.append('0');
	                hexString.append(hex);
	            }
	            return hexString.toString();
	        } catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e);
	        }
	    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		new DangKyView();
	}

}
