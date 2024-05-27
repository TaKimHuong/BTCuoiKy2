package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
//import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.print.DocFlavor.URL;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.mysql.cj.xdevapi.Table;

import Controller.DBControler;
import Model.QuanLiSinhVienKiTucXa;
import Model.SinhVien;

public class DsSinhVien extends JFrame implements ActionListener {
	private TrangChuView trangChuView;
	private SuaThongTinSinhVien suatt;
	public Vector vData;
	public Vector vTitle;
	ResultSet rs;
	int collum;
	// vị trí hàng đã chọn ở bảng dữ liệu
	ResultSetMetaData rss = null;
	public DefaultTableModel df;
	private JTable jtb;
	private JScrollPane tb;
	private DefaultTableModel model_table;
	public String masv;
	public String tenSinhVien;
	public String GioiTinh;
	public String QueQuan;
	public String SDT;
	public String NoiO;
	public String ngay;
	public String MaNhanVien;
	public String Maphong;
	public String CCCD;
	public String NgayVao;
	public String NgayRa;
	private JScrollPane tb_TK;
	private DBControler db = new DBControler();
	private JTextField jtf_TimKiem;
	int count = 0;
	private JButton jButton_XoaSv;
	private JButton jButton_SuaSv;
	private FileWriter f;
	private JFileChooser chooser;
	private JButton jButton_Save_As;
	private JButton jbt_Ql;
	private JButton jButton_LuuFileXML;
	private JButton jButton_MoFileXML;


	public DsSinhVien()  {
		try {
			vData = new Vector();
			vTitle = new Vector();
			
			// Truy vấn thông tin từ bảng Sinhvienktx
			rs = db.stmt.executeQuery("SELECT * FROM quanlikitucxa.sinhvienktx");
			// Lấy thông tin của Rs
			rss = (ResultSetMetaData) rs.getMetaData();
			// Lấy thông tin số cột từ bảng sinhvienktx từ rss
			collum = rss.getColumnCount();
			vTitle = new Vector(collum);
		//	try {
			//	f= new FileWriter("C:\\Users\\LAPTOP ACER\\eclipse-workspace\\DACK\\src\\File.txt\\danhSachSinhVien.txt");
				for (int i = 1; i <= collum; i++) {
					vTitle.add(rss.getColumnLabel(i));
			//		System.out.println(rss.getColumnLabel(i) + "|");
				//	f.write(rss.getColumnLabel(i) + "|" + "\t" );
			//		f.flush();
					
				}
			//	f.write("\n");
				vData = new Vector(10, 10);
				while (rs.next()) {
					Vector row = new Vector(collum);
					for (int i = 1; i <= collum; i++) {
						row.add(rs.getString(i));
				//		System.out.println(rs.getString(i) + "|");
				//		f.write(rs.getString(i) + "|" + "\t");
				//		f.flush();
					}
					vData.add(row);
			//		f.write("\n");

				}
				rs.close();
			//	f.close();
				
		//	} catch (IOException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
		//	}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Tạo bảng để hiện thị thông tin lên cửa sổ
		df = new DefaultTableModel(vData, vTitle);
		jtb = new JTable(df);

		tb = new JScrollPane(jtb, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		this.setTitle("Danh sách sinh viên");
		this.setSize(600, 550);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel jLabel_Danhsachsv = new JLabel("DANH SÁCH SINH VIÊN", JLabel.CENTER);
		java.net.URL url_icon_notpad = DsSinhVien.class.getResource("person-icon.png");
		Image img = Toolkit.getDefaultToolkit().createImage(url_icon_notpad);
		this.setIconImage(img);
		JPanel jPanel_North = new JPanel();
		jPanel_North.setLayout(new BorderLayout());
		jPanel_North.add(jLabel_Danhsachsv, BorderLayout.CENTER);
		JLabel jLabel_Kt1 = new JLabel("   ");
		jPanel_North.add(jLabel_Kt1, BorderLayout.NORTH);
		JPanel pn_TimKiem = new JPanel();
		pn_TimKiem.setLayout(new GridLayout(1, 5, 5, 5));
		jtf_TimKiem = new JTextField(30);
		JButton jButton_TimKiem = new JButton("Tìm kiếm");
		JLabel jlb_ktkt = new JLabel("     ");
		JLabel jlb_ktkt1 = new JLabel("     ");
		JLabel jlb_ktkt2 = new JLabel("     ");
		JLabel jlb_ktkt3 = new JLabel("              ");
		JLabel jlb_ktkt4 = new JLabel("              ");
		pn_TimKiem.add(jlb_ktkt);
		pn_TimKiem.add(jlb_ktkt1);
		pn_TimKiem.add(jlb_ktkt2);
		pn_TimKiem.add(jtf_TimKiem);
		pn_TimKiem.add(jButton_TimKiem);
		jButton_TimKiem.addActionListener(this);

		// PHƯƠNG THỨC TÌM KIẾM SINH VIÊN

		jButton_TimKiem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println(jtf_TimKiem.getText());
				JFrame j = new JFrame();
				j.setTitle("Kết quả");
				j.setSize(1200, 350);
				j.setLocationRelativeTo(null);
				j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				java.net.URL url_icon_notpad = DsSinhVien.class.getResource("person-icon.png");
				Image img = Toolkit.getDefaultToolkit().createImage(url_icon_notpad);
				j.setIconImage(img);
				j.setLayout(new BorderLayout());
				// TODO Auto-generated method stub

				try {
					rs = db.stmt.executeQuery(
							"SELECT * FROM quanlikitucxa.sinhvienktx where MaSV = '" + jtf_TimKiem.getText() + "' "
									+ "or MaPhong = '"+jtf_TimKiem.getText()+"'");
					rss = (ResultSetMetaData) rs.getMetaData();
					collum = rss.getColumnCount();
					vTitle = new Vector(collum);
					for (int i = 1; i <= collum; i++) {
						vTitle.add(rss.getColumnLabel(i));
					}
					vData = new Vector(10, 10);
					while (rs.next()) {
						Vector row = new Vector(collum);

						for (int i = 1; i <= collum; i++) {
							row.add(rs.getString(i));
						}
						vData.add(row);
					}

					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				df = new DefaultTableModel(vData, vTitle);
				jtb = new JTable(df);

				tb_TK = new JScrollPane(jtb, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
						JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				JPanel pn_South = new JPanel();
				pn_South.setLayout(new FlowLayout());
				jbt_Ql = new JButton("Quay lại");
				pn_South.add(jButton_SuaSv);
				pn_South.add(jButton_XoaSv);
				pn_South.add(jbt_Ql);
				
				jbt_Ql.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						new DsSinhVien();

					}
				});
				JLabel jlb_TimKiem = new JLabel("Tìm kiếm", JLabel.CENTER);
				Font fo = new Font("Arial", Font.BOLD, 20);
				jlb_TimKiem.setFont(fo);
				j.add(tb_TK, BorderLayout.CENTER);
				j.add(pn_South, BorderLayout.SOUTH);
				j.add(jlb_TimKiem, BorderLayout.NORTH);

				j.setVisible(true);
			}
		});
		jButton_TimKiem.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(DsSinhVien.class.getResource("search.png"))));

		JPanel pn1 = new JPanel();
		pn1.setLayout(new BorderLayout());
		pn1.add(pn_TimKiem, BorderLayout.CENTER);
		pn1.add(jlb_ktkt4, BorderLayout.SOUTH);

		jPanel_North.add(pn1, BorderLayout.SOUTH);
		jPanel_North.add(jlb_ktkt3, BorderLayout.EAST);
		Font font = new Font("Arial", Font.PLAIN, 30);

		jLabel_Danhsachsv.setFont(font);
		this.setLayout(new BorderLayout());
		JPanel pn = new JPanel();
		pn.setLayout(new FlowLayout());
		jButton_XoaSv = new JButton("Xóa SV");
		jButton_XoaSv.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(DsSinhVien.class.getResource("delete.png"))));
		jButton_XoaSv.addActionListener(this);
		jButton_SuaSv = new JButton("Sửa SV");
		jButton_SuaSv.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(DsSinhVien.class.getResource("edit.png"))));
		jButton_SuaSv.addActionListener(this);
		jButton_Save_As = new JButton("Lưu File .txt");
		jButton_Save_As.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(DsSinhVien.class.getResource("ok.png"))));
		jButton_Save_As.addActionListener(this);
		jButton_LuuFileXML = new JButton("Lưu File .xml");
		jButton_LuuFileXML.addActionListener(this);
		jButton_MoFileXML = new JButton("Mo File .xml");
		jButton_MoFileXML.addActionListener(this);
		JPanel jPanel_Center = new JPanel();
		jPanel_Center.setLayout(new BorderLayout());
		jPanel_Center.add(tb);

		JLabel jLabel_Kt3 = new JLabel("   ");
		JLabel jLabel_Kt4 = new JLabel("   ");
		jPanel_Center.add(jLabel_Kt4, BorderLayout.EAST);
		jPanel_Center.add(jLabel_Kt3, BorderLayout.WEST);
		this.add(jPanel_Center, BorderLayout.CENTER);

		JButton jButton_Back = new JButton("Quay lại");
		jButton_Back.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(DsSinhVien.class.getResource("cancel.png"))));
		pn.add(jButton_XoaSv);
		pn.add(jButton_SuaSv);
		pn.add(jButton_Back);
		pn.add(jButton_Save_As);
		pn.add(jButton_LuuFileXML);
		pn.add(jButton_MoFileXML);
		this.add(pn, BorderLayout.SOUTH);
		this.add(jPanel_North, BorderLayout.NORTH);
		jButton_Back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new TrangChuView();
			}
		});

		this.setVisible(true);
	}
	// Phương thức xóa sinh viên ra khỏi kí túc xá

	public void XoaSinhVien() {
		try {
			int i_row = jtb.getSelectedRow();

			Vector st = (Vector) vData.elementAt(i_row);
			// tạo câu lệnh SQl xóa dữ liệu ra khỏi Bảng
			String sql = "Delete from sinhvienktx where MaSV = '" + st.elementAt(0) + "'";
			db.stmt.executeUpdate(sql);
			// xóa nội dùng của hàng tương ứng trong bảng
			vData.remove(i_row);

			// cập nhật lại

			df.fireTableDataChanged();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	QuanLiSinhVienKiTucXa ql = new QuanLiSinhVienKiTucXa();

	// Phương thức thêm sinh viên vào trong danh sách

	public void ThemHoacSuaSinhVien(SinhVien sv) {
		this.ql.insert(sv);

		try {
			df.addRow(new Object[] { sv.getMaSV().toString(), sv.getTenSinhVien().toString(),
					sv.getGioiTinh().toString(), sv.getNgaySinh().toString(), sv.getQueQuan().toString(),
					sv.getNoiO().toString(), sv.getSDT(), sv.getCCCD().toString(), sv.getNgayVao().toString(), sv.getNgayRa().toString(), sv.getMaNhanVien().toString(),
					sv.getMaPhong().toString() });
			df.fireTableDataChanged();
			String sql = " INSERT INTO `quanlikitucxa`.`sinhvienktx` (`MaSV`, `TenSV`, `GioiTinh`, `NgaySinh`, `QueQuan`, `NoiO`, `SDT`, `CCCD`,`NgayVao`,`NgayRa`, `MaNV`, `MaPhong`)"
					+ " VALUES ('" + sv.getMaSV().toString() + "', '" + sv.getTenSinhVien().toString() + "', '"
					+ sv.getGioiTinh().toString() + "', '" + sv.getNgaySinh().toString() + "', '"
					+ sv.getQueQuan().toString() + "', '" + sv.getNoiO().toString() + "', '" + sv.getSDT() + " "
					+ "', '" + sv.getCCCD().toString() + "','"+ sv.getNgayVao().toString() + "','"+sv.getNgayRa().toString() +"', '" + sv.getMaNhanVien().toString() + "', '"
					+ sv.getMaPhong().toString() + "')";
			this.db.stmt.executeUpdate(sql);
			System.out.println("Ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void hienThiThongTinDaChon() {

		// TODO Auto-generated method stub
		model_table = (DefaultTableModel) jtb.getModel();
		int i_row = jtb.getSelectedRow();
		masv =  model_table.getValueAt(i_row, 0) + "";
		tenSinhVien = model_table.getValueAt(i_row, 1) + "";
		GioiTinh = model_table.getValueAt(i_row, 2) + "";
		QueQuan = model_table.getValueAt(i_row, 4) + "";
		SDT = (String) model_table.getValueAt(i_row, 6);
		NoiO = model_table.getValueAt(i_row, 5) + "";
		ngay = model_table.getValueAt(i_row, 3) + "";
		MaNhanVien = model_table.getValueAt(i_row, 10) + "";
		Maphong = model_table.getValueAt(i_row, 11) + "";
		CCCD = model_table.getValueAt(i_row, 7) + "";
		NgayVao = model_table.getValueAt(i_row, 8) + "";
		NgayRa = model_table.getValueAt(i_row, 9) + "";
		suatt = new SuaThongTinSinhVien();
		suatt.jlb_Masv.setText(masv);
		suatt.jtf_Tensv.setText(tenSinhVien);
		suatt.jtf_GioiTinh.setText(GioiTinh);
		suatt.jtf_NgaySinh.setText(ngay);
		suatt.jtf_QueQuan.setText(QueQuan);
		suatt.jtf_NoiO.setText(NoiO);
		suatt.jtf_CCCD.setText(CCCD);
		suatt.jtf_Sdt.setText(SDT);
		suatt.jtf_NgayVao.setText(NgayVao);
		suatt.jtf_NgayRa.setText(NgayRa);
		suatt.jtf_MaMV.setText(MaNhanVien);
		suatt.jtf_MaPhong.setText(Maphong);
	}	
	@Override
	public void actionPerformed(ActionEvent e) {
		String a = e.getActionCommand();
		if (a.equals("Xóa SV")) {
			int b = JOptionPane.showOptionDialog(null, "Bạn muốn xóa sinh viên không?", "Xác nhận",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (b == JOptionPane.YES_OPTION) {
				XoaSinhVien();
				System.out.println("OK");
			} else if (b == JOptionPane.NO_OPTION) {
				System.out.println("No");
			} else if (b == JOptionPane.CANCEL_OPTION) {
				System.out.println("Cancel");
			}
		} else if (a.equals("Sửa SV")) {
			hienThiThongTinDaChon();

		} else if (a.equals("Lưu File .txt")) {
			System.out.println("Da nhan " + a);
			chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("."));
			if(chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				String filename = chooser.getSelectedFile().getAbsolutePath();
				saveFile(filename);
			}
			
		} else if (a.equals("Lưu File .xml")) {
			chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("."));
			if(chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				String filename = chooser.getSelectedFile().getAbsolutePath();
				saveFileXML(filename);
			}
		} else if (a.equals("Mo File .xml")) {
			new MoFileDsSinhVienXML();
		}
	}
	private void saveFileXML(String filename) {
		// TODO Auto-generated method stub
		int count =0;
		try {
			 DocumentBuilderFactory dbFactory = 
	                    DocumentBuilderFactory.newInstance();
	            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	            Document doc = dBuilder.newDocument();
	            // tạo phần tử gốc có tên class
	            Element rootElement = doc.createElement("DsSinhVien");
	            // thêm thuộc tính totalStudents vào thẻ class
	            doc.appendChild(rootElement);
	            Attr totalStudentAttr = doc.createAttribute("totalStudents");
	            totalStudentAttr.setValue(count + "");
	            rootElement.setAttributeNode(totalStudentAttr);
			vData = new Vector();
			vTitle = new Vector();
			
			// Truy vấn thông tin từ bảng Sinhvienktx
			try {
				
				rs = db.stmt.executeQuery("SELECT * FROM quanlikitucxa.sinhvienktx");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Lấy thông tin của Rs
			 rss = (ResultSetMetaData) rs.getMetaData();
			// Lấy thông tin số cột từ bảng sinhvienktx từ rss
			 collum = rss.getColumnCount();
			vTitle = new Vector(collum);
				vData = new Vector(10, 10);
				
				while (rs.next()) {
					Vector row = new Vector(collum);
					// tạo phần tử student1
					 Element student1 = doc.createElement("student");
			         rootElement.appendChild(student1);
					for (int i = 1; i <= collum; i++) {
						 
			           
			            // tạo thuộc tính rollno cho student1
			            Attr attr1 = doc.createAttribute("rollno");
			            attr1.setValue(count + 1 + "");
			            student1.setAttributeNode(attr1);
			            // tạo thẻ firstname
			            
			            Element firstname = doc.createElement(rss.getColumnLabel(i));
			            firstname.appendChild(doc.createTextNode(rs.getString(i)));
			            student1.appendChild(firstname);
						vTitle.add(rss.getColumnLabel(i));
						System.out.println(rss.getColumnLabel(i));
						row.add(rs.getString(i));
						System.out.println(rs.getString(i));
				
					}
					System.out.println("\n");
					vData.add(row);
					count = count + 1;
				}
				System.out.println(count);
            // ghi nội dung vào file XML
            TransformerFactory transformerFactory = 
                    TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // tao cho cac dong xml duoc dep hon
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
        
            StreamResult result = new StreamResult(
                    new File(filename));
            transformer.transform(source, result);
 
            // ghi kết quả ra console để kiểm tra
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	private void saveFile(String filename) {
		// TODO Auto-generated method stub
		try {
			vTitle = new Vector();
		// Truy vấn thông tin từ bảng Sinhvienktx
		rs = db.stmt.executeQuery("SELECT * FROM quanlikitucxa.sinhvienktx");
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
}
