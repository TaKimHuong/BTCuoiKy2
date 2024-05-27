package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class DBControler {
     public Connection con;
     public Statement stmt;
     public DBControler() {
	   try {
		   // Nạp driver 
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			// Kết nối đến cơ sở dữ liệu
			this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlikitucxa","root","123456789");
			// Tạo đối tượng Statement để gửi các câu lệnh SQL
			this.stmt = con.createStatement();
			System.out.println("Successfully");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
   }
     public Connection getCon() {
    	 return con;
     }
     public ResultSet queryDB(String query) throws Exception {
         Statement st=this.getCon().createStatement();
         return st.executeQuery(query);
     }
     
     
     
     
     public int updateDB(String query) throws Exception {
         Statement st=this.getCon().createStatement();
         return st.executeUpdate(query);
     }
//     public ResultSet Print() {
//    	 String sql = "SELECT * FROM quanlikitucxa.sinhvien";
//    	 ResultSet rs = null;
//    	 try {
//			rs = stmt.executeQuery(sql);
//			System.out.println(sql);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//    	 return rs;
//     }
     
//     public static void main(String[] args) {
//    		DBControler db = new DBControler();
//    		ResultSet rs = db.Print();
//    		try {
//    			while(rs.next()) {
//    				System.out.println(rs.getString(1));
//    				System.out.println(rs.getString(2));
//    				System.out.println(rs.getString(3));
//    				System.out.println(rs.getString(4));
//    				System.out.println(rs.getString(5));
//    				System.out.println(rs.getString(6));
//    				System.out.println(rs.getString(7));
//    				System.out.println(rs.getString(8));
//    				System.out.println();
//    			}
//    		} catch (SQLException e) {
//    			// TODO Auto-generated catch block
//    			e.printStackTrace();
//    		}
//    	}
	
	
}
