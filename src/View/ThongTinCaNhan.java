package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import Controller.DBControler;

public class ThongTinCaNhan extends JFrame {
//	public Vector vData;
//	public Vector vTitle;
//	ResultSet rs;
//	int collum = 0;
//	ResultSetMetaData rss = null;
//	public DefaultTableModel df;
//	private JTable jtb;
//	private JScrollPane tb;
	private DBControler db = new DBControler();
public JLabel tf_TaiKhoan;
public JLabel tf_MK;

	public ThongTinCaNhan() {
		this.setTitle("Thông tin cá nhân");
		this.setSize(1200, 350);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel pn_West = new JPanel();
		pn_West.setLayout(new BorderLayout());
		JPanel pn_North = new JPanel();
		pn_North.setLayout(new BorderLayout());
		java.net.URL url_icon_notpad = ThongTinCaNhan.class.getResource("person-icon.png");
		Image img = Toolkit.getDefaultToolkit().createImage(url_icon_notpad);
		this.setIconImage(img);
		JLabel jLabel_ThongTinCaNhan = new JLabel("THÔNG TIN CÁ NHÂN", JLabel.CENTER);
		Font font = new Font("Arial", Font.PLAIN, 30);
		jLabel_ThongTinCaNhan.setFont(font);
		pn_North.add(jLabel_ThongTinCaNhan, BorderLayout.CENTER);
		JLabel jLabel_kt2 = new JLabel("         ");
		JLabel jLabel_kt3 = new JLabel("         ");
		pn_North.add(jLabel_kt3, BorderLayout.NORTH);
		pn_North.add(jLabel_kt2, BorderLayout.SOUTH);
		JLabel jLabel_Kt = new JLabel("      ");
		JLabel jLabel_Center = new JLabel();
		jLabel_Center.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(TrangChuView.class.getResource("girl.png"))));
		pn_West.add(jLabel_Center, BorderLayout.CENTER);
		pn_West.add(jLabel_Kt, BorderLayout.WEST);

		JPanel pn_Center = new JPanel();
		pn_Center.setLayout(new GridLayout(2,1));
		 tf_TaiKhoan = new JLabel("taikhoan");
		 tf_MK = new JLabel("mat khau");
		pn_Center.add(tf_TaiKhoan);
		pn_Center.add(tf_MK);
		// kết nối và thêm dữ liệu vào java1
//		try {
//			rs = db.stmt.executeQuery("SELECT * FROM quanlikitucxa.nhanvienquanli");
//			rss = (ResultSetMetaData) rs.getMetaData();
//			collum = rss.getColumnCount();
//			vTitle = new Vector(collum);
//			for (int i = 1; i <= collum; i++) {
//				vTitle.add(rss.getColumnLabel(i));
//			}
//			vData = new Vector(10, 10);
//			while (rs.next()) {
//				Vector row = new Vector(collum);
//				for (int i = 1; i <= collum; i++)
//					row.add(rs.getString(i));
//				vData.add(row);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		df = new DefaultTableModel(vData, vTitle);
//		jtb = new JTable(df);
//
//		tb = new JScrollPane(jtb, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		JLabel jlabel_East = new JLabel("             ");
	//	pn_Center.add(tb, BorderLayout.CENTER);
		JPanel pn_South = new JPanel();
		pn_South.setLayout(new FlowLayout());
		JButton jButton_QuayLai = new JButton("Quay lại");
		pn_South.add(jButton_QuayLai);
		jButton_QuayLai.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TrangChuView t = new TrangChuView();
				t.TrangChuView(tf_TaiKhoan.getText());
				System.out.println("kkkk");
			}
		});

		this.setLayout(new BorderLayout());
		this.add(pn_North, BorderLayout.NORTH);
		this.add(pn_West, BorderLayout.WEST);
		this.add(pn_Center, BorderLayout.CENTER);
		this.add(jlabel_East, BorderLayout.EAST);
		this.add(pn_South, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new ThongTinCaNhan();
	}
}
