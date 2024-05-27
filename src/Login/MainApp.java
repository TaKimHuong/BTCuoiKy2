package Login;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainApp {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public static void main(String[] args) {
        MainApp app = new MainApp();
        app.initialize();
    }

    public void initialize() {
        frame = new JFrame("Login and Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        LoginPanel loginPanel = new LoginPanel(this);
        RegisterPanel registerPanel = new RegisterPanel(this);

        mainPanel.add(loginPanel, "login");
        mainPanel.add(registerPanel, "register");

        frame.add(mainPanel);
        frame.setVisible(true);

        showLogin();
    }

    public void showLogin() {
        cardLayout.show(mainPanel, "login");
    }

    public void showRegister() {
        cardLayout.show(mainPanel, "register");
    }
}

class LoginPanel extends JPanel {
    private MainApp mainApp;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPanel(MainApp mainApp) {
        this.mainApp = mainApp;
        initialize();
    }

    private void initialize() {
        setLayout(new GridLayout(4, 1));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                try (Socket socket = new Socket("localhost", 8080);
                     ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                     ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
                    
                    output.writeObject("LOGIN");
                    output.writeObject(username);
                    output.writeObject(password);

                    String response = (String) input.readObject();
                    if (response.equals("SUCCESS")) {
                        JOptionPane.showMessageDialog(null, "Login successful!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password.");
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Login failed.");
                    ex.printStackTrace();
                }
            }
        });

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainApp.showRegister();
            }
        });

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(registerButton);
    }
}

class RegisterPanel extends JPanel {
    private MainApp mainApp;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public RegisterPanel(MainApp mainApp) {
        this.mainApp = mainApp;
        initialize();
    }
   // ham bao mat mat khau
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

    private void initialize() {
        setLayout(new GridLayout(4, 1));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String hashedPassword = encryptMD5(password);
                try (Socket socket = new Socket("localhost", 8080);
                     ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                     ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
                    
                    output.writeObject("REGISTER");
                    output.writeObject(username);
                    output.writeObject(hashedPassword);

                    String response = (String) input.readObject();
                    if (response.equals("SUCCESS")) {
                        JOptionPane.showMessageDialog(null, "Registration successful!");
                        mainApp.showLogin();
                    } else {
                        JOptionPane.showMessageDialog(null, "Registration failed.");
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Registration failed.");
                    ex.printStackTrace();
                }
            }
        });

        JButton backButton = new JButton("Back to Login");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainApp.showLogin();
            }
        });

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(registerButton);
        add(backButton);
    }
}

