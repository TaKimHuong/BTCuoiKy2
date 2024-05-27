package View;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import Controller.DBControler;

import Model.Phong;
import Model.SinhVien;

public class MoFileDsSinhVienXML extends JFrame implements ActionListener {
	private JFileChooser chooser ;
	private JTextArea text_File;
	private ExecutorService executor;
	private MoFileView m;
	ResultSetMetaData rss=null;
	ResultSet rs ;
	int collum = 0;
	private DBControler db = new DBControler();
	 public MoFileDsSinhVienXML() {
		this.setTitle("Đọc File XML");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 400);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		 text_File = new JTextArea();
		 JScrollPane pan =  new JScrollPane(text_File, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.add(pan, BorderLayout.CENTER);
		JPanel pn_SOuth = new JPanel();
		pn_SOuth.setLayout(new FlowLayout());
		JButton jButton_MoFile = new JButton("Đọc File XML");
		pn_SOuth.add(jButton_MoFile);
		jButton_MoFile.addActionListener(this);
		JButton jButton_QuayLai = new JButton("Quay lại");
		jButton_QuayLai.addActionListener(this);
		pn_SOuth.add(jButton_QuayLai);
		this.add(pn_SOuth, BorderLayout.SOUTH);
		executor = Executors.newFixedThreadPool(2);
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String a = e.getActionCommand();
		if (a.equals("Đọc File XML")) {
			executor.execute(new Runnable() {
				
				private static String filename;

				@Override
				public void run() {
					// TODO Auto-generated method stub
					chooser = new JFileChooser();
					chooser.setCurrentDirectory(new java.io.File("."));
					if(chooser.showOpenDialog(m) == JFileChooser.APPROVE_OPTION) {
						 filename = chooser.getSelectedFile().getAbsolutePath();
						readFile(filename);
					}
					while(true) {
						System.out.println("Mo File");
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				private void readFile(String filename) {
					// TODO Auto-generated method stub
					text_File.setText("");
					try {
						List<SinhVien> listPhong = readList();
						// hien thi cac doi tuong phong ra man hinh
						for (SinhVien sv : listPhong) {
							text_File.append(sv.toString() + "\n");
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				private List<SinhVien> readList() {
					List<SinhVien> listPhong = new ArrayList<>();
					SinhVien sv = null;
					try {
						 File inputFile = new File(filename);
				         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				         Document doc = dBuilder.parse(inputFile);
				         doc.getDocumentElement().normalize();
				         // in phan tu goc ra man hinh
				         System.out.println("Phan tu goc " + doc.getDocumentElement().getNodeName());
				         NodeList nodeListStudent = doc.getElementsByTagName("student");
				         for(int i = 0 ; i < nodeListStudent.getLength() ; i++) {
				        	 sv = new SinhVien();
				        	 Node nNode = nodeListStudent.item(i);
				        	 if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				        		Element eElement = (Element) nNode;
				        		sv.setMaSV(eElement.getElementsByTagName("MaSV").item(0).getTextContent());
				        		sv.setTenSinhVien(eElement.getElementsByTagName("TenSV").item(0).getTextContent());
				        		sv.setGioiTinh(eElement.getElementsByTagName("GioiTinh").item(0).getTextContent());
				        		sv.setNgaySinh(eElement.getElementsByTagName("NgaySinh").item(0).getTextContent());
				        		sv.setQueQuan(eElement.getElementsByTagName("QueQuan").item(0).getTextContent());
				        		sv.setNoiO(eElement.getElementsByTagName("NoiO").item(0).getTextContent());
				        		sv.setSDT(eElement.getElementsByTagName("SDT").item(0).getTextContent());
				        		sv.setCCCD(eElement.getElementsByTagName("CCCD").item(0).getTextContent());
				        		sv.setNgayVao(eElement.getElementsByTagName("NgayVao").item(0).getTextContent());
				        		sv.setNgayRa(eElement.getElementsByTagName("NgayRa").item(0).getTextContent());
				        		sv.setMaNhanVien(eElement.getElementsByTagName("MaNV").item(0).getTextContent());
				        		sv.setMaPhong(eElement.getElementsByTagName("MaPhong").item(0).getTextContent());
				        	 }
				        	 listPhong.add(sv);
				         }
					}catch (Exception e) {
						e.printStackTrace();
					}
					 
					return listPhong;
					
				}
			});
			
		} else if (a.equals("Quay lại")) {
			new DsSinhVien();
		}
	}
	public static void main(String[] args) {
		new MoFileDsSinhVienXML();
	}

}
