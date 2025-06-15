import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import javax.swing.*;

public class Attendance24 extends JFrame {

    public Attendance24() {
        setTitle("Attendance Calendar 2022-2023");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("AMIT Attendance Calendar 2022-2023", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        // Tabbed pane for months
        JTabbedPane tabbedPane = new JTabbedPane();

        // Loop through each month of 2022 and 2023
        for (int year = 2022; year <= 2023; year++) {
            for (int month = 0; month < 12; month++) {
                JPanel monthPanel = createMonthPanel(year, month);
                tabbedPane.add(getMonthName(month) + " " + year, monthPanel);
            }
        }

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createMonthPanel(int year, int month) {
        JPanel monthPanel = new JPanel(new BorderLayout());
        JPanel daysPanel = new JPanel(new GridLayout(0, 7));
        daysPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Adding day headers
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : days) {
            JLabel label = new JLabel(day, JLabel.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 14));
            daysPanel.add(label);
        }

        // Calendar instance for setting up days
        Calendar calendar = new GregorianCalendar(year, month, 1);
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        // Filling initial blanks
        for (int i = 0; i < firstDayOfWeek; i++) {
            daysPanel.add(new JLabel("")); // Add empty labels for initial blank spaces
        }

        // Prepare attendance list with 80% P and 20% A for non-Sunday days
        ArrayList<String> attendanceList = new ArrayList<>();
        int nonSundayDays = 0;

        // Count non-Sunday days in the month
        for (int day = 1; day <= daysInMonth; day++) {
            calendar.set(Calendar.DAY_OF_MONTH, day);
            if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                nonSundayDays++;
            }
        }

        // Fill attendance list with 80% P and 20% A
        int presentDays = (int) (nonSundayDays * 0.8);
        int absentDays = nonSundayDays - presentDays;

        for (int i = 0; i < presentDays; i++) {
            attendanceList.add("P");
        }
        for (int i = 0; i < absentDays; i++) {
            attendanceList.add("A");
        }

        // Shuffle attendance list
        Collections.shuffle(attendanceList);

        // Reset the calendar for the specific month and year
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        // Add buttons for each day with shuffled attendance values
        int attendanceIndex = 0;
        int totalPresent = 0;
        int totalAbsent = 0;
        for (int day = 1; day <= daysInMonth; day++) {
            calendar.set(Calendar.DAY_OF_MONTH, day);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

            JButton dayButton = new JButton(String.valueOf(day)); // Display the day number
            dayButton.setEnabled(dayOfWeek != Calendar.SUNDAY); // Disable button for Sundays
            if (dayOfWeek != Calendar.SUNDAY) {
                // Set shuffled attendance
                String attendance = attendanceList.get(attendanceIndex++);
                dayButton.setText(day + " - " + attendance);
                dayButton.setBackground(attendance.equals("P") ? Color.GREEN : Color.RED);
                dayButton.setForeground(Color.WHITE);
                
                if (attendance.equals("P")) {
                    totalPresent++;
                } else {
                    totalAbsent++;
                }
            }

            daysPanel.add(dayButton);
        }

        // Calculate percentages
        double presentPercentage = ((double) totalPresent / nonSundayDays) * 100;
        double absentPercentage = ((double) totalAbsent / nonSundayDays) * 100;

        // Create and add label for percentage display
        JLabel percentageLabel = new JLabel(String.format("Present: %.2f%%, Absent: %.2f%%", presentPercentage, absentPercentage), JLabel.CENTER);
        percentageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        monthPanel.add(daysPanel, BorderLayout.CENTER);
        monthPanel.add(percentageLabel, BorderLayout.SOUTH);

        return monthPanel;
    }

    private String getMonthName(int month) {
        String[] months = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };
        return months[month];
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Attendance24 frame = new Attendance24();
            frame.setVisible(true);
        });
    }
}
