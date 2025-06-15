import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class RAM23 extends JFrame {

    public RAM23() {
        setTitle("Profile");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Profile panel to hold the image and information
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        profilePanel.setBackground(Color.WHITE);

        // Profile image in circular form
        JLabel imageLabel = new JLabel();
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        try {
            BufferedImage image = ImageIO.read(new File("D:\\thirdyearproject\\RAM23.jpg")); // Replace with your image path
            imageLabel.setIcon(new ImageIcon(getCircularImage(image, 150))); // Set to circular image
        } catch (IOException e) {
            e.printStackTrace();
            imageLabel.setText("Image not found"); // Placeholder text if image not found
        }

        // Table model to store profile information
        String[] columnNames = {"Label", "Details"};
        Object[][] data = {
            {"Name", "Ram Verma"},
            {"Class", "6th"},
            {"Roll Number", "19"},
            {"Father's Name", "Mr. Amit Verma"},
            {"Contact Number", "872438232"},
            {"Address", "xyz 23 colony"}
        };
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable infoTable = new JTable(model);
        infoTable.setRowHeight(30);
        infoTable.setFont(new Font("Arial", Font.PLAIN, 16));
        infoTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        infoTable.getTableHeader().setBackground(new Color(224, 224, 224));

        // Center align table text
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < infoTable.getColumnCount(); i++) {
            infoTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Scroll pane for the table
        JScrollPane tableScrollPane = new JScrollPane(infoTable);
        tableScrollPane.setPreferredSize(new Dimension(380, infoTable.getRowHeight() * data.length + 30)); // Set height based on row count

        // Button panel for Attendance and Result buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton attendanceButton = new JButton("Attendance");
        attendanceButton.setFont(new Font("Arial", Font.BOLD, 16));

        // Add ActionListener to the Attendance button
        attendanceButton.addActionListener(e -> {
            // Open the Attendance23 window
            Attendance23 attendancePage = new Attendance23();
            attendancePage.setVisible(true);
        });

        JButton resultButton = new JButton("Result");
        resultButton.setFont(new Font("Arial", Font.BOLD, 16));

        // Add ActionListener to the Result button
        resultButton.addActionListener(e -> {
            // Open the Result23 window
            Result23 resultPage = new Result23();
            resultPage.setVisible(true);
        });

        buttonPanel.add(attendanceButton);
        buttonPanel.add(resultButton);

        // Add components to the profile panel
        profilePanel.add(Box.createVerticalStrut(20));  // Add space at top
        profilePanel.add(imageLabel);
        profilePanel.add(Box.createVerticalStrut(20));  // Add space between image and table
        profilePanel.add(tableScrollPane);
        profilePanel.add(Box.createVerticalStrut(20));  // Add space between table and buttons
        profilePanel.add(buttonPanel);

        // Add profile panel to frame
        add(profilePanel, BorderLayout.CENTER);

        setLocationRelativeTo(null); // Center the frame
    }

    // Method to create a circular version of the image
    private Image getCircularImage(BufferedImage image, int diameter) {
        BufferedImage output = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();

        g2.setClip(new Ellipse2D.Float(0, 0, diameter, diameter));  // Set clip to circular shape
        g2.drawImage(image, 0, 0, diameter, diameter, null);        // Draw scaled circular image
        g2.dispose();

        return output;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RAM23 profilePage = new RAM23();
            profilePage.setVisible(true);
        });
    }
}
