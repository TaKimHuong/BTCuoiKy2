package Login;

//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.net.Socket;
//import java.util.Scanner;
//public class ClientRegister {
//    private static final String SERVER_IP = "192.168.1.3"; // Thay thế bằng IP của máy chủ nếu không chạy cục bộ
//    private static final int SERVER_PORT = 8080;
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Enter username: ");
//        String username = scanner.nextLine();
//
//        System.out.print("Enter password: ");
//        String password = scanner.nextLine();
//
//        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
//             ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
//             ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {
//
//            // Send username and password to server
//            outputStream.writeObject(username);
//            outputStream.writeObject(password);
//
//            // Read response from server
//            String response = (String) inputStream.readObject();
//            if ("SUCCESS".equals(response)) {
//                System.out.println("Registration successful!");
//            } else {
//                System.out.println("Registration failed.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        scanner.close();
//    }
//}
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class ClientRegister {
    private static final String SERVER_IP = "192.168.1.3"; // Thay thế bằng IP của máy chủ
    private static final int SERVER_PORT = 8080;
//	private static final ClientRegister PasswordUtils = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Do you want to Register or Login? (R/L): ");
        String choice = scanner.nextLine();

        if ("R".equalsIgnoreCase(choice)) {
            register(scanner);
        } else if ("L".equalsIgnoreCase(choice)) {
            login(scanner);
        } else {
            System.out.println("Invalid choice.");
        }

        scanner.close();
    }
 // ma hoa mat khau truoc khi gui
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
    private static void register(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Băm mật khẩu trước khi gửi
       // String hashedPassword = org.mindrot.jbcrypt.BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt());
       String hashedPassword = encryptMD5(password);
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {
            outputStream.writeObject("REGISTER");
            outputStream.writeObject(username);
            outputStream.writeObject(hashedPassword);

            String response = (String) inputStream.readObject();
            System.out.println(response.equals("SUCCESS") ? "Registration successful!" : "Registration failed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
       
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {
            outputStream.writeObject("LOGIN");
            outputStream.writeObject(username);
            outputStream.writeObject(password);
            String response = (String) inputStream.readObject();
            System.out.println(response.equals("SUCCESS") ? "Login successful!" : "Login failed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

