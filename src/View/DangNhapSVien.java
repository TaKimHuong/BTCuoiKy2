package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DangNhapSVien extends JFrame implements ActionListener, ItemListener{
	
	public DangNhapSVien() {
		this.setTitle("Sinh viên");
		this.setSize(370, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel pn = new JPanel();
		pn.setLayout(new BorderLayout());
		JLabel lb_DangNhap = new JLabel("ĐĂNG NHẬP", JLabel.CENTER);
	
		JLabel lb_Icon = new JLabel("   ");
		lb_Icon.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(HopDongView.class.getResource("contract1.png"))));
		pn.add(lb_Icon, BorderLayout.WEST);
		JPanel pn_Center = new JPanel();
		pn_Center.setLayout(new GridLayout(6,1,2,2));
		JLabel lb_TaiKhoan = new JLabel("Tài khoản");
		JTextField tf_TaiKhoan = new JTextField(30);
		JLabel lb_MatKhau = new JLabel("Mật khẩu");
		JPasswordField tf_MatKhau = new JPasswordField(30);

		JCheckBox checkbox_hienthi = new JCheckBox("Hiển thị");
		checkbox_hienthi.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (checkbox_hienthi.isSelected()) {
					tf_MatKhau.setEchoChar((char) 0);
				} else {
					tf_MatKhau.setEchoChar('*');
				}
			}
		});
		pn_Center.add(lb_DangNhap);
		pn_Center.add(lb_TaiKhoan);
		pn_Center.add(tf_TaiKhoan);
		pn_Center.add(lb_MatKhau);
		pn_Center.add(tf_MatKhau);
		pn_Center.add(checkbox_hienthi);
		JLabel lb_East = new JLabel( "      ");
		JPanel pn_South = new JPanel();
		pn_South.setLayout(new FlowLayout());
		JButton bt_DangNhap = new JButton("Đăng nhập");
		bt_DangNhap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = tf_TaiKhoan.getText();
                String password = new String(tf_MatKhau.getPassword());
                try (Socket socket = new Socket("192.168.1.3", 8080);
                     ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                     ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
                    
                    output.writeObject("LOGIN");
                    output.writeObject(username);
                    output.writeObject(password);

                    String response = (String) input.readObject();
                    if (response.equals("SUCCESS")) {
                        JOptionPane.showMessageDialog(null, "Login successful!");
                        new TrangChuView();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password.");
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Login failed.");
                    ex.printStackTrace();
                }
            }
        });
		JButton bt_quayLai = new JButton("Quay lại");
		pn_South.add(bt_DangNhap);
		pn_South.add(bt_quayLai);
		pn.add(pn_Center, BorderLayout.CENTER);
		pn.add(lb_East, BorderLayout.EAST);
		pn.add(pn_South, BorderLayout.SOUTH);
		this.add(pn);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		new DangNhapSVien();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
}
