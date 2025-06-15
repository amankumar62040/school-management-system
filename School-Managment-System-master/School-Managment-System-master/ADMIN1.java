import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ADMIN1 extends JFrame {
    public ADMIN1() {
        // Set up frame properties
        setTitle("Admin Window");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title at the top of the frame
        JLabel titleLabel = new JLabel("ADMIN WINDOW", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Create a panel for buttons with vertical layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // Create buttons and add animations
        JButton button1 = new JButton("S23");
        JButton button2 = new JButton("S24");
        JButton button3 = new JButton("CGC25");

        // Center-align the buttons
        button1.setAlignmentX(Component.CENTER_ALIGNMENT);
        button2.setAlignmentX(Component.CENTER_ALIGNMENT);
        button3.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add action listeners to buttons to link with respective classes
        button1.addActionListener(e -> {
            RAM23 ramPage = new RAM23();
            ramPage.setVisible(true);
        });

        button2.addActionListener(e -> {
            AMIT24 amitPage = new AMIT24();
            amitPage.setVisible(true);
        });

        button3.addActionListener(e -> {
            CGC25 cgcPage = new CGC25();
            cgcPage.setVisible(true);
        });

        // Add buttons to the panel with spacing
        buttonPanel.add(button1);
        buttonPanel.add(Box.createVerticalStrut(40)); // 4 cm space
        buttonPanel.add(button2);
        buttonPanel.add(Box.createVerticalStrut(40)); // 4 cm space
        buttonPanel.add(button3);

        // Center the buttons panel in the frame
        add(buttonPanel, BorderLayout.CENTER);

        // Create and start animations for each button
        addPulseAnimation(button1, Color.LIGHT_GRAY, Color.CYAN);
        addPulseAnimation(button2, Color.LIGHT_GRAY, Color.GREEN);
        addPulseAnimation(button3, Color.LIGHT_GRAY, Color.MAGENTA);
    }

    private static void addPulseAnimation(JButton button, Color startColor, Color endColor) {
        Timer timer = new Timer(50, new ActionListener() {
            private float colorStep = 0.0f;
            private boolean forward = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                // Adjust colorStep based on the direction of the pulse
                if (forward) {
                    colorStep += 0.05f;
                    if (colorStep >= 1.0f) {
                        colorStep = 1.0f;
                        forward = false;
                    }
                } else {
                    colorStep -= 0.05f;
                    if (colorStep <= 0.0f) {
                        colorStep = 0.0f;
                        forward = true;
                    }
                }

                // Calculate the new color based on colorStep
                int red = (int) (startColor.getRed() * (1 - colorStep) + endColor.getRed() * colorStep);
                int green = (int) (startColor.getGreen() * (1 - colorStep) + endColor.getGreen() * colorStep);
                int blue = (int) (startColor.getBlue() * (1 - colorStep) + endColor.getBlue() * colorStep);

                button.setBackground(new Color(red, green, blue));
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        // Run the admin window
        ADMIN1 adminWindow = new ADMIN1();
        adminWindow.setVisible(true);
    }
}
