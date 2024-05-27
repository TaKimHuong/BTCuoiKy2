package MaHoa;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import Controller.DBControler;

public class MaHoa extends JFrame implements ActionListener{
	private Vector vData;
	private Vector vTitle;
	private ResultSet rs;
	private ResultSetMetaData rss;
	private DBControler db  = new DBControler();
	private int collum;
	private DefaultTableModel df;
	private JTextField tf_TaiKhoan;
	private JTextField tf_MatKhau;
	private int count=0;
	public MaHoa() {
		this.setSize(400,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel lb_DangNhap = new JLabel("Dang Nhap", JLabel.CENTER);
		JPanel pn_Form = new JPanel();
		pn_Form.setLayout(new GridLayout(2,2,5,5));
		JLabel lb_TaiKhoan = new JLabel("Tai khoan: ");
		 tf_TaiKhoan = new JTextField(30);
		JLabel lb_MatKhau = new JLabel("Mat khau: ");
		 tf_MatKhau = new JTextField(30);
		pn_Form.add(lb_TaiKhoan);
		pn_Form.add(tf_TaiKhoan);
		pn_Form.add(lb_MatKhau);
		pn_Form.add(tf_MatKhau);
		JPanel pn_South = new JPanel();
		pn_South.setLayout(new FlowLayout());
		JButton bu_DangNhap = new JButton("Dang Nhap");
		bu_DangNhap.addActionListener(this);
		JButton bt_DangKy = new JButton("Dang ky");
		bt_DangKy.addActionListener(this);
		pn_South.add(bu_DangNhap);
		pn_South.add(bt_DangKy);
		
		this.setLayout(new BorderLayout());
		this.add(lb_DangNhap, BorderLayout.NORTH);
		this.add(pn_Form, BorderLayout.CENTER);
		this.add(pn_South, BorderLayout.SOUTH);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	 public static String encryptMD5(String input) {
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            byte[] messageDigest = md.digest(input.getBytes());
	            BigInteger number = new BigInteger(1, messageDigest);
	            String hashtext = number.toString(16);
	            while (hashtext.length() < 32) {
	                hashtext = "0" + hashtext;
	            }
	            return hashtext;
	        } catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e);
	        }
	    }
	 public void Insert() {
		 try {
				vData = new Vector();
				vTitle = new Vector();
				
				// Truy vấn thông tin từ bảng Sinhvienktx
				rs = db.stmt.executeQuery("SELECT * FROM dangnhap.taikhoan");
				// Lấy thông tin của Rs
				rss = (ResultSetMetaData) rs.getMetaData();
				// Lấy thông tin số cột từ bảng sinhvienktx từ rss
				collum = rss.getColumnCount();
				vTitle = new Vector(collum);
					for (int i = 1; i <= collum; i++) {
						vTitle.add(rss.getColumnLabel(i));
						
					}
					vData = new Vector(10, 10);
					while (rs.next()) {
						Vector row = new Vector(collum);
						row.add(rs.getString(1));
						row.add(rs.getString(2));
						if (tf_TaiKhoan.getText().equals(rs.getString(1)) && encryptMD5(tf_MatKhau.getText()).equals(rs.getString(2))) {
							System.out.println("ok");
							count = 1;
						} else {
							System.out.println("Not ok");
							count = 0;
						}
						vData.add(row);
				

					}
					if (count == 1) {
						System.out.println("Ba da thanh cong");
					}
					rs.close();
			

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Tạo bảng để hiện thị thông tin lên cửa sổ
			df = new DefaultTableModel(vData, vTitle);
		 
	 }


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if (cmd.equals("Dang Nhap")) {
			Insert();
			if (count == 1) {
				JOptionPane.showMessageDialog(null, "Welcome");
			} else {
				JOptionPane.showMessageDialog(null, " Not Welcome");
			}
		}
	}
	public static void main(String[] args) {
		new MaHoa();
	}

}
