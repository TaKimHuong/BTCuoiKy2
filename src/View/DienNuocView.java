package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import Controller.DBControler;

public class DienNuocView extends JFrame implements ActionListener {
	Vector vData ;
	Vector vTitle ;
	ResultSet rs ;
	int collum = 0;
	ResultSetMetaData rss=null;
	private DefaultTableModel model_table;
	private DefaultTableModel df;
	private JTable jtb;
	private HopDongView suatt;
	private JFileChooser chooser;
	private FileWriter f;
	DBControler db;
	private JScrollPane tb_TK;
	public DienNuocView() {
		db = new DBControler();
		try {
			rs = db.stmt.executeQuery("SELECT * FROM quanlikitucxa.diennuoc");
			rss = (ResultSetMetaData) rs.getMetaData();
			 collum = rss.getColumnCount();
			 vTitle = new Vector(collum);
				for (int i = 1; i <= collum; i++) {
					vTitle.add(rss.getColumnLabel(i));
				}
				vData = new Vector(10,10);
			while(rs.next()) {
				Vector row = new Vector(collum);
				for (int i = 1; i <= collum; i++) 
					row.add(rs.getString(i));
					vData.add(row);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
}
		df = new DefaultTableModel(vData, vTitle);
		jtb = new JTable(df);
		JScrollPane tb = new JScrollPane(jtb, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.setTitle("Phòng");
		this.setSize(600,550);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		URL url_icon_notpad = PhongView.class.getResource("person-icon.png");
		Image img = Toolkit.getDefaultToolkit().createImage(url_icon_notpad);
		this.setIconImage(img);
		JLabel jLabel_DanhSachPhong = new JLabel("DANH SÁCH ĐIỆN NƯỚC" , JLabel.CENTER);
		JPanel jPanel_Nouth = new JPanel();
		jPanel_Nouth.setLayout(new BorderLayout());
		jPanel_Nouth.add(jLabel_DanhSachPhong, BorderLayout.CENTER);
		JLabel jLabel_kt = new JLabel("     ");
		JLabel jLabel_kt1 = new JLabel("     ");
		jPanel_Nouth.add(jLabel_kt , BorderLayout.NORTH);
		jPanel_Nouth.add(jLabel_kt1 , BorderLayout.SOUTH);
		Font font = new Font("Arial", Font.PLAIN , 30);
		jLabel_DanhSachPhong.setFont(font);
		JPanel jPanel_Center = new JPanel();
		JButton jButton_QuayLai = new JButton("Quay lại");
		jButton_QuayLai.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(PhongView.class.getResource("cancel.png"))));
//		JButton jButton_XacNhan = new JButton("Xác nhận");
//		jButton_XacNhan.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(PhongView.class.getResource("edit.png"))));
	//	jButton_XacNhan.addActionListener(this);
		JButton jButton_Save_As = new JButton("Lưu File .txt");
		jButton_Save_As.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(PhongView.class.getResource("ok.png"))));
		jButton_Save_As.addActionListener(this);
		jButton_QuayLai.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new TrangChuView();
//				SoLuongNguoiDaO();
			}
		});
		

		jPanel_Center.setLayout(new BorderLayout());
	//	jPanel_Center.setLayout(new GridLayout(1, 2));
		jPanel_Center.add(tb);
//		SoLuongNguoiO s = new SoLuongNguoiO();
//		jPanel_Center.add(s);
//		s.Start();
		JLabel jLabel_KhoangCach = new JLabel("   ");
		JLabel jLabel_KhoangCach1 = new JLabel("   ");
		
		jPanel_Center.add(jLabel_KhoangCach1 , BorderLayout.WEST);
		jPanel_Center.add(jLabel_KhoangCach , BorderLayout.EAST);
		JPanel jPanel_South = new JPanel();
		jPanel_South.setLayout(new FlowLayout());
		jPanel_South.add(jButton_Save_As);
	//	jPanel_South.add(jButton_XacNhan);
		jPanel_South.add(jButton_QuayLai);
		this.setLayout(new BorderLayout());
		this.add(jPanel_Center, BorderLayout.CENTER);
		this.add(jPanel_South, BorderLayout.SOUTH);
		this.add(jPanel_Nouth, BorderLayout.NORTH);
		this.setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String a = e.getActionCommand();
		if (a.equals("Lưu File .txt")) {
			System.out.println("Da nhan " + a);
			chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("."));
			if(chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				String filename = chooser.getSelectedFile().getAbsolutePath();
				saveFile(filename);
		}
		}
	}
	private void saveFile(String filename) {
		// TODO Auto-generated method stub
		try {
			vTitle = new Vector();
		// Truy vấn thông tin từ bảng Sinhvienktx
		rs = db.stmt.executeQuery("SELECT * FROM quanlikitucxa.diennuoc");
		// Lấy thông tin của Rs
		rss = (ResultSetMetaData) rs.getMetaData();
		// Lấy thông tin số cột từ bảng sinhvienktx từ rss
		collum = rss.getColumnCount();
		vTitle = new Vector(collum);
		try {
			f= new FileWriter(filename);
			for (int i = 1; i <= collum; i++) {
				vTitle.add(rss.getColumnLabel(i));
				System.out.println(rss.getColumnLabel(i) + "|");
				f.write(rss.getColumnLabel(i) + "|" + "\t" );
				f.flush();
				
			}
			f.write("\n");
			vData = new Vector(10, 10);
			while (rs.next()) {
				Vector row = new Vector(collum);
				for (int i = 1; i <= collum; i++) {
					row.add(rs.getString(i));
					System.out.println(rs.getString(i) + "|");
					f.write(rs.getString(i) + "|" + "\t");
					f.flush();
				}
				vData.add(row);
				f.write("\n");

			}
			rs.close();
			f.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public static void main(String[] args) {
		new DienNuocView();
	}

}
