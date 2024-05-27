package Login;

//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class ServerRegister {
//    private static final int PORT = 8080;
//
//    // JDBC URL, username, and password of MySQL server
//    private static final String URL = "jdbc:mysql://localhost:3306/dangnhap";
//    private static final String USER = "root";
//    private static final String PASSWORD = "123456789";
//
//    public static void main(String[] args) {
//        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
//            System.out.println("Server is running...");
//
//            while (true) {
//                // Accept client connection
//                Socket clientSocket = serverSocket.accept();
//                System.out.println("Client connected: " + clientSocket.getInetAddress());
//
//                // Handle client request in a separate thread
//                new ClientHandler(clientSocket).start();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    static class ClientHandler extends Thread {
//        private Socket clientSocket;
//
//        public ClientHandler(Socket socket) {
//            this.clientSocket = socket;
//        }
//
//        @Override
//        public void run() {
//            try (ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
//                 ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream())) {
//                // Read data from client
//                String username = (String) inputStream.readObject();
//                String password = (String) inputStream.readObject();
//
//                // Register user in database
//                boolean success = registerUser(username, password);
//
//                // Send response to client
//                outputStream.writeObject(success ? "SUCCESS" : "FAILURE");
//            } catch (IOException | ClassNotFoundException | SQLException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    // Close the client socket
//                    clientSocket.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    // Method to register a new user in the database
//    private static boolean registerUser(String Tai_Khoan, String Mat_Khau) throws SQLException {
//        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
//          //  String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
//            String sql = "INSERT INTO `dangnhap`.`taikhoan` (`Tai_Khoan`, `Mat_Khau`) VALUES (?, ?);";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, Tai_Khoan);
//            preparedStatement.setString(2, Mat_Khau);
//            int rowsAffected = preparedStatement.executeUpdate();
//            return rowsAffected == 1;
//        }
//    }
//}

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;

public class ServerRegister {
   private static final int PORT = 8080;
   private static final String URL = "jdbc:mysql://localhost:3306/dangnhap";
  private static final String USER = "root";
  private static final String PASSWORD = "123456789";

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        
    }

    static class ClientHandler extends Thread {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }
     // ma hoa mat khau truoc khi gui
 
        @Override
        public void run() {
            try (ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
                 ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream())) {
                String requestType = (String) inputStream.readObject();

                if ("REGISTER".equals(requestType)) {
                    handleRegister(inputStream, outputStream);
                } else if ("LOGIN".equals(requestType)) {
                    handleLogin(inputStream, outputStream);
                } else if ("getPersonalInfo".equals(requestType)) {
                	getUserInfo(inputStream, outputStream);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
       
		private void getUserInfo(ObjectInputStream inputStream, ObjectOutputStream outputStream) throws SQLException {
			  try {
	                String username = (String) inputStream.readObject();
	                String userInfo = UserInfo(username);
	                outputStream.writeObject(userInfo);
	            } catch (IOException | ClassNotFoundException e) {
	                e.printStackTrace();
	            }
		
	}
//		private String UserInfo(String username) {
//			// TODO Auto-generated method stub
//			String sql = "SELECT Tai_Khoan, Mat_Khau FROM dangnhap.taikhoan WHERE Tai_Khoan = '"+username+"'";
//			return null;
//		}
		 private String UserInfo(String username) throws SQLException {
		        String userInfo = "";
		        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
		            String sql = "SELECT Tai_Khoan, Mat_Khau FROM dangnhap.taikhoan WHERE Tai_Khoan = ?";
		            PreparedStatement preparedStatement = connection.prepareStatement(sql);
		            preparedStatement.setString(1, username);
		            ResultSet resultSet = preparedStatement.executeQuery();

		            if (resultSet.next()) {
		                String retrievedUsername = resultSet.getString("Tai_Khoan");
		                String retrievedPassword = resultSet.getString("Mat_Khau");
		                userInfo = retrievedUsername + ":" + retrievedPassword;
		            }
		        }
		        return userInfo;
		    }
		

		public static String encryptMD5(String password) {
            try {
                // Tạo instance của MessageDigest với thuật toán MD5
                MessageDigest md = MessageDigest.getInstance("MD5");
                // Chuyển đổi mật khẩu thành mảng byte
                byte[] messageDigest = md.digest(password.getBytes());
                // Chuyển đổi mảng byte thành đối tượng String Hex
                StringBuilder hexString = new StringBuilder();
                for (byte b : messageDigest) {
                    String hex = Integer.toHexString(0xff & b);
                    if (hex.length() == 1) hexString.append('0');
                    hexString.append(hex);
                }
                return hexString.toString();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
        private void handleRegister(ObjectInputStream inputStream, ObjectOutputStream outputStream) {
            try {
                String username = (String) inputStream.readObject();
                String password = (String) inputStream.readObject();

               // String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
                
                String hashedPassword = encryptMD5(password);
                boolean success = registerUser(username, hashedPassword);

                outputStream.writeObject(success ? "SUCCESS" : "FAILURE");
            } catch (IOException | ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }

        private void handleLogin(ObjectInputStream inputStream, ObjectOutputStream outputStream) {
            try {
                String username = (String)inputStream.readObject();
                String password = (String)inputStream.readObject();
                String hashedPassword = encryptMD5(password);
                boolean success = authenticateUser(username, hashedPassword);
                outputStream.writeObject(success ? "SUCCESS" : "FAILURE");
            } catch (IOException | ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    

        private boolean registerUser(String username, String hashedPassword) throws SQLException {
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
               // String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            	String sql = "INSERT INTO `dangnhap`.`taikhoan` (`Tai_Khoan`, `Mat_Khau`) VALUES (?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, hashedPassword);
                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected == 1;
            }
        }

        public boolean authenticateUser(String username, String plainPassword) throws SQLException {
        	
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "SELECT Mat_Khau FROM dangnhap.taikhoan WHERE Tai_Khoan = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();
               
                if (resultSet.next()) {
                    String storedHashedPassword = resultSet.getString("Mat_Khau");
                 
                    return encryptMD5(plainPassword).equals(storedHashedPassword);
                } else {
                    return false;
                }
            }
        }
    
    }
}
