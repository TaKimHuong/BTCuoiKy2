package Model;

import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import Controller.DBControler;

public class PhongXML {


	public static void main(String[] args) {
		int count = 0;
		Vector vDa;
		Vector vTi;
		ResultSet rs = null;
		DBControler db = new DBControler();
		ResultSetMetaData ras;
		int collum;
		
		try {
			
			 DocumentBuilderFactory dbFactory = 
	                    DocumentBuilderFactory.newInstance();
	            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	            Document doc = dBuilder.newDocument();
	            // tạo phần tử gốc có tên class
	            Element rootElement = doc.createElement("Phong");
	            // thêm thuộc tính totalStudents vào thẻ class
	            doc.appendChild(rootElement);
	            Attr totalStudentAttr = doc.createAttribute("totalPhong");
	            totalStudentAttr.setValue(count + "");
	            rootElement.setAttributeNode(totalStudentAttr);
			vDa = new Vector();
			vTi = new Vector();
			
			// Truy vấn thông tin từ bảng Sinhvienktx
			try {
				
				rs = db.stmt.executeQuery("SELECT * FROM quanlikitucxa.phongktx");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Lấy thông tin của Rs
			 ras = (ResultSetMetaData) rs.getMetaData();
			// Lấy thông tin số cột từ bảng sinhvienktx từ rss
			 collum = ras.getColumnCount();
			vTi = new Vector(collum);
				vDa = new Vector(10, 10);
				
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
			            
			            Element ab = doc.createElement(ras.getColumnLabel(i));
			            ab.appendChild(doc.createTextNode(rs.getString(i)));
			            student1.appendChild(ab);
						vTi.add(ras.getColumnLabel(i) );
						System.out.println(ras.getColumnLabel(i));
						row.add(rs.getString(i));
						System.out.println(rs.getString(i));
				
					}
					System.out.println("\n");
					vDa.add(row);
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
                   new File("D:\\Download\\phong.xml"));
           transformer.transform(source, result);

           // ghi kết quả ra console để kiểm tra
           StreamResult consoleResult = new StreamResult(System.out);
           transformer.transform(source, consoleResult);
       } catch (Exception e) {
           e.printStackTrace();
       }
	}

}
