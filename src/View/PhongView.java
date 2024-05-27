package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
import javax.tools.Tool;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import Controller.DBControler;
import Model.Phong;

public class PhongView extends JFrame implements ActionListener{
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
	public PhongView() {
		 db = new DBControler();
		try {
			rs = db.stmt.executeQuery("SELECT * FROM quanlikitucxa.phongktx");
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
		JLabel jLabel_DanhSachPhong = new JLabel("DANH SÁCH PHÒNG" , JLabel.CENTER);
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
		JButton jButton_XacNhan = new JButton("Xác nhận");
		jButton_XacNhan.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(PhongView.class.getResource("edit.png"))));
		jButton_XacNhan.addActionListener(this);
		JButton jButton_Save_As = new JButton("Lưu File .txt");
		jButton_Save_As.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(PhongView.class.getResource("ok.png"))));
		jButton_Save_As.addActionListener(this);
		JButton jButton_DocFileXML = new JButton("Đọc File .xml");
		JButton jButton_LuuFileXML = new JButton("Luu File .xml");
		jButton_LuuFileXML.addActionListener(this);
		jButton_DocFileXML.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(PhongView.class.getResource("ok.png"))));
		jButton_DocFileXML.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new MoFilePhongXML();
			}
		});
		jButton_QuayLai.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new TrangChuView();

			}
		});
		

	//	jPanel_Center.setLayout(new BorderLayout());
		jPanel_Center.setLayout(new GridLayout(1, 2));
		jPanel_Center.add(tb);
		SoLuongNguoiO s = new SoLuongNguoiO();
		jPanel_Center.add(s);
		s.Start();
		JPanel jPanel_South = new JPanel();
		jPanel_South.setLayout(new FlowLayout());
		jPanel_South.add(jButton_LuuFileXML);
		jPanel_South.add(jButton_DocFileXML);
		jPanel_South.add(jButton_Save_As);
		jPanel_South.add(jButton_XacNhan);
		jPanel_South.add(jButton_QuayLai);
		this.setLayout(new BorderLayout());
		this.add(jPanel_Center, BorderLayout.CENTER);
		this.add(jPanel_South, BorderLayout.SOUTH);
		this.add(jPanel_Nouth, BorderLayout.NORTH);
		this.setVisible(true);
	}
	public void HienThiThongTinPhong() {
		suatt = new HopDongView();
		model_table = (DefaultTableModel) jtb.getModel();
		int i_row = jtb.getSelectedRow();
		String MaPhong = model_table.getValueAt(i_row, 0) + "";
		String TenPhong = model_table.getValueAt(i_row, 1) + "";
		suatt.jtf_MaPhong.setText(MaPhong);
		suatt.jtf_TenPhong.setText(TenPhong);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String a = e.getActionCommand();
		if (a.equals("Xác nhận")) {
			HienThiThongTinPhong();
		} else if (a.equals("Lưu File .txt")) {
			System.out.println("Da nhan " + a);
			chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("."));
			if(chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				String filename = chooser.getSelectedFile().getAbsolutePath();
				saveFile(filename);
			}
		} else if (a.equals("Luu File .xml")) {
			System.out.println("Da nhan " + a);
			chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("."));
			if(chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				String filename = chooser.getSelectedFile().getAbsolutePath();
				saveFileXML(filename);
			}
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
	            Element rootElement = doc.createElement("Phong");
	            // thêm thuộc tính totalStudents vào thẻ class
	            doc.appendChild(rootElement);
	            Attr totalStudentAttr = doc.createAttribute("totalRooms");
	            totalStudentAttr.setValue(count + "");
	            rootElement.setAttributeNode(totalStudentAttr);
			vData = new Vector();
			vTitle = new Vector();
			
			// Truy vấn thông tin từ bảng Sinhvienktx
			try {
				
				rs = db.stmt.executeQuery("SELECT * FROM quanlikitucxa.phongktx");
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
					 Element student1 = doc.createElement("phong");
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
		rs = db.stmt.executeQuery("SELECT * FROM quanlikitucxa.phongktx");
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
		
	}

}