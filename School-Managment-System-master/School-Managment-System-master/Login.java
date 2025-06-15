import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class Login extends JFrame {

    // Shake effect for the frame
    public static void shakeFrame(JFrame frame) {
        final int originalX = frame.getLocation().x;
        final int originalY = frame.getLocation().y;

        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(10);
                frame.setLocation(originalX + 5, originalY);
                Thread.sleep(10);
                frame.setLocation(originalX - 5, originalY);
            }
            frame.setLocation(originalX, originalY);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        showWelcomeScreen();
    }

    public static void showWelcomeScreen() {
        JWindow welcomeScreen = new JWindow();
        welcomeScreen.setSize(500, 300);
        welcomeScreen.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(34, 45, 65));
        panel.setLayout(new BorderLayout());

        // Create a label to animate the welcome text
        JLabel welcomeLabel = new JLabel("", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);
        panel.add(welcomeLabel, BorderLayout.CENTER);

        // Loading label for showing the loading percentage
        JLabel loadingLabel = new JLabel("Loading... 0%", JLabel.CENTER);
        loadingLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        loadingLabel.setForeground(Color.WHITE);
        loadingLabel.setVisible(false); // Initially hidden
        panel.add(loadingLabel, BorderLayout.SOUTH);

        welcomeScreen.add(panel);
        welcomeScreen.setVisible(true);

        // Animate the welcome text (typewriter effect)
        String welcomeText = "School Management System";
        Timer timer = new Timer();

        // Timer to update the text with a delay for each character
        timer.schedule(new TimerTask() {
            int index = 0;

            @Override
            public void run() {
                if (index < welcomeText.length()) {
                    welcomeLabel.setText(welcomeText.substring(0, index + 1));
                    index++;
                } else {
                    loadingLabel.setVisible(true);
                }
            }
        }, 0, 100);

        // Timer to update loading percentage
        timer.schedule(new TimerTask() {
            int count = 0;

            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    count++;
                    loadingLabel.setText("Loading... " + count + "%");

                    if (count >= 100) {
                        welcomeScreen.dispose();
                        showLoginScreen();
                        timer.cancel();
                    }
                });
            }
        }, 2000, 75);
    }

    public static void showLoginScreen() {
        JFrame f = new JFrame("Login Student");

        JPanel panel = new JPanel();
        panel.setLayout(null);
        Color aquaColor = new Color(176, 247, 241); // Aqua background color
        panel.setBackground(aquaColor);

        JLabel imgLabel = new JLabel();
        imgLabel.setBounds(20, 50, 200, 200);
        ImageIcon icon = new ImageIcon("C:\\Users\\DELL\\Downloads\\employee-owned.png"); // Replace with your image path
        Image scaledImage = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        imgLabel.setIcon(new ImageIcon(scaledImage));

        Font font = new Font("Arial", Font.PLAIN, 20);

        JLabel emailLabel = new JLabel("ID Number:");
        emailLabel.setBounds(250, 50, 150, 40);
        emailLabel.setFont(font);
        emailLabel.setForeground(new Color(49, 93, 94));

        JTextField emailField = new JTextField();
        emailField.setBounds(400, 50, 250, 40);
        emailField.setFont(font);

        emailField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                String input = emailField.getText();
                char c = e.getKeyChar();

                if (c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
                    return;
                }

                if (input.isEmpty() && Character.isLetter(c)) {
                } else if (!input.isEmpty() && Character.isDigit(c)) {
                } else {
                    e.consume();
                }
            }
        });

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(250, 120, 150, 40);
        passwordLabel.setFont(font);
        passwordLabel.setForeground(new Color(49, 93, 94));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(400, 120, 250, 40);
        passwordField.setFont(font);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(400, 200, 150, 40);
        loginButton.setFont(font);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = emailField.getText();
                String password = new String(passwordField.getPassword());

                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(f, "ID Number cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    shakeFrame(f);
                    panel.setBackground(Color.RED);
                } else if (password.isEmpty()) {
                    JOptionPane.showMessageDialog(f, "Password cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    shakeFrame(f);
                    panel.setBackground(Color.RED);
                } else {
                    if (id.equals("S23") && password.equals("RAM23")) {
                        RAM23 ramPage = new RAM23();
                        ramPage.setVisible(true);
                        f.dispose();

                    } else if (id.equals("S24") && password.equals("AMIT24")) {
                        AMIT24 amitPage = new AMIT24();
                        amitPage.setVisible(true);
                        f.dispose();
                    } else if (id.equals("T1") && password.equals("CGC25")) {
                        CGC25 cgcPage = new CGC25();
                        cgcPage.setVisible(true);
                        f.dispose();
                    }               
                    else if (id.equals("A101") && password.equals("ADMIN7526")) {
                        ADMIN1 adminPage = new ADMIN1();
                        adminPage.setVisible(true);
                        f.dispose();
                    } else {
                        JOptionPane.showMessageDialog(f, "Invalid ID Number or Password.", "Login Error", JOptionPane.ERROR_MESSAGE);
                        shakeFrame(f);
                        panel.setBackground(Color.RED);
                    }
                }
            }
        });
        panel.add(imgLabel);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        f.add(panel);
        f.setSize(700, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}