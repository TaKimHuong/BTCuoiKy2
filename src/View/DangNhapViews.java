package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class DangNhapViews extends JFrame implements ActionListener, ItemListener{
	private JPasswordField jtf_MatKhau;
	private JCheckBox jCheckBox_HienThiMk;
	private JButton jButton_Dangnhap;
	private JTextField jtf_Taikhoan;
	public DangNhapViews() {
		this.setTitle("Đăng nhập tài khoản");
		this.setSize(650,350);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel jPanel_Center = new JPanel();
		jPanel_Center.setLayout(new GridLayout(3, 3, 5, 5));
		URL url_icon_notpad = DangNhapViews.class.getResource("person-icon.png");
		Image img = Toolkit.getDefaultToolkit().createImage(url_icon_notpad);
		this.setIconImage(img);
		JLabel jLabel_taiKhoan = new JLabel("TÀI KHOẢN: ", JLabel.RIGHT);
		Font font = new Font("Arial", Font.BOLD, 13);
		jLabel_taiKhoan.setFont(font);

		JLabel jLabel_MatKhau = new JLabel(" MẬT KHẨU: ", JLabel.RIGHT);
		jLabel_MatKhau.setFont(font);

		jtf_Taikhoan = new JTextField(20);
		jtf_MatKhau = new JPasswordField(20);
		jtf_MatKhau.setEchoChar('*');

		jButton_Dangnhap = new JButton("Đăng nhập");
		jButton_Dangnhap.addActionListener(this);
		JPanel pn_Dn = new JPanel();
		pn_Dn.setLayout(new GridLayout(1, 2, 5, 5));
		jCheckBox_HienThiMk = new JCheckBox("Hiển thị");
		pn_Dn.add(jCheckBox_HienThiMk);
		pn_Dn.add(jButton_Dangnhap);
		
		JLabel jlb_Kt = new JLabel("  ");
		jCheckBox_HienThiMk.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (jCheckBox_HienThiMk.isSelected()) {
					jtf_MatKhau.setEchoChar((char) 0);
				} else {
					jtf_MatKhau.setEchoChar('*');
				}
			}
		});
		
		jPanel_Center.add(jLabel_taiKhoan);
		jPanel_Center.add(jtf_Taikhoan);
		jPanel_Center.add(jLabel_MatKhau);
		jPanel_Center.add(jtf_MatKhau);
		jPanel_Center.add(jlb_Kt);
		jPanel_Center.add(pn_Dn);
		JPanel pn = new JPanel();
		pn.setLayout(new BorderLayout());
	
		JLabel lb_east = new JLabel("         ");
		ImageIcon imageIcon1 = new ImageIcon("C:\\Users\\LAPTOP ACER\\eclipse-workspace\\DACK\\src\\View\\User1a-icon.png"); // Thay đổi đường dẫn đến ảnh của bạn
	    Image originalImage1 = imageIcon1.getImage();

	  
	    int newWidth1 = 120; // New width of the icon
	    int newHeight1 = 120; // New height of the icon
	    Image resizedImage1 = originalImage1.getScaledInstance(newWidth1, newHeight1, Image.SCALE_SMOOTH);

	    ImageIcon resizedIcon1 = new ImageIcon(resizedImage1);
	    JLabel label_West = new JLabel(resizedIcon1);
	    JLabel jlabel_North = new JLabel("   ");
	    JLabel jlabel_South = new JLabel("   ");
	    JPanel pn_South = new JPanel();
	    pn_South.setLayout(new FlowLayout());
	    JButton bt_DangKy = new JButton("Đăng ký");
	    bt_DangKy.addActionListener(this);
	    pn_South.add(bt_DangKy);
	    label_West.setBounds(125, 90, 120, 120);
		pn.add(jPanel_Center, BorderLayout.CENTER);
		pn.add(lb_east, BorderLayout.EAST);
		pn.add(jlabel_North, BorderLayout.NORTH);
		pn.add(pn_South, BorderLayout.SOUTH);
		pn.setBounds(110, 80, 450, 170);
		JLayeredPane layeredPane = new JLayeredPane();
		ImageIcon imageIcon = new ImageIcon("C:\\Users\\LAPTOP ACER\\eclipse-workspace\\DACK\\src\\picture\\h1.jpg"); // Thay đổi đường dẫn đến ảnh của bạn
	    Image originalImage = imageIcon.getImage();
	    int newWidth = 650; // New width of the icon
        int newHeight = 350; // New height of the icon
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel label = new JLabel(resizedIcon);
		label.setBounds(0, 0, 650, 350);
		layeredPane.add(label, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(pn, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(label_West, JLayeredPane.MODAL_LAYER);
		this.add(layeredPane);
		this.setVisible(true);
	}
	private static boolean isPassWordCorrect(char[] in) {
		boolean isCorrect = true;
		char[] correctPass = { '1', '2', '3', '4', '5' };
		if (in.length != correctPass.length) {
			isCorrect = false;
		} else {
			isCorrect = Arrays.equals(in, correctPass);
		}
		Arrays.fill(correctPass, '0');
		return isCorrect;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if (cmd.equals("Đăng nhập")) {
			char[] input = jtf_MatKhau.getPassword();
			if (isPassWordCorrect(input) == true && "001TK".equals(jtf_Taikhoan.getText())) {
				JOptionPane.showMessageDialog(null, "Bạn đã đăng nhập thành công");
				new TrangChuView();
			} else {
				JOptionPane.showMessageDialog(null, "Lỗi", cmd, JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

	}
}

