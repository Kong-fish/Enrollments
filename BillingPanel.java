import javax.swing.*;
import java.awt.*;

public class BillingPanel extends JPanel {
    private JCheckBox accommodationCheckBox;
    private MainPanel main;

    public BillingPanel(MainPanel main) {
        this.main = main;
        setLayout(new BorderLayout());

        JTextArea billArea = new JTextArea();
        billArea.setEditable(false);

        JLabel accommodationLabel = new JLabel("Needs Accommodation?");
        accommodationCheckBox = new JCheckBox();
        JButton generateBillButton = new JButton("Generate Bill");

        generateBillButton.addActionListener(e -> {
            StringBuilder bill = new StringBuilder();
            /*for (Student student : view.getController().getModel().getStudents()) {
                bill.append("Student: ").append(student.getName()).append("\n");
                bill.append("Courses:\n");
                for (Course course : student.getCourses()) {
                    bill.append(course.getName()).append(" : ").append(course.getFee()).append("\n");
                }
        
                double totalFee = view.getController().calculateTotalFee(student);
                if (accommodationCheckBox.isSelected()) {
                    bill.append("Accommodation Fee: ").append("500").append("\n");
                    totalFee += 500; // Add accommodation fee
                }

                double itNetworkFee = 100;
                double libraryFee = 50;
                double clubSocietyFee = 30;

                totalFee += itNetworkFee + libraryFee + clubSocietyFee;

                double discountedFee = view.getController().calculateDiscountedFee(student);
                bill.append("IT/Network Fee: ").append(itNetworkFee).append("\n");
                bill.append("Library Fee: ").append(libraryFee).append("\n");
                bill.append("Club and Society Fee: ").append(clubSocietyFee).append("\n");
                bill.append("Total Fee: ").append(totalFee).append("\n");
                bill.append("Discounted Fee: ").append(discountedFee).append("\n\n");
            }*/
            billArea.setText(bill.toString());
        });

        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        topPanel.add(accommodationLabel, gbc);

        gbc.gridx = 1;
        topPanel.add(accommodationCheckBox, gbc);
        
        JButton backButton = new JButton("Back");

        backButton.addActionListener(e -> {
            main.showPanel("Home Panel");
        });

        // Adding the back button to the north side of the panel
        add(backButton, BorderLayout.NORTH);

        add(new JScrollPane(billArea), BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);
        add(generateBillButton, BorderLayout.SOUTH);
    }
}
