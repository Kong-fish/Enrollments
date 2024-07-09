import javax.swing.*;

public class BillingPanel extends JPanel {
    private JCheckBox accommodationCheckBox;
    private MainPanel main;

    public BillingPanel(MainPanel main) {
        this.main = main;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTextArea billArea = new JTextArea(10, 30);
        billArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(billArea);

        JLabel accommodationLabel = new JLabel("Needs Accommodation?");
        accommodationCheckBox = new JCheckBox();

        JPanel topPanel = new JPanel();
        topPanel.add(accommodationLabel);
        topPanel.add(accommodationCheckBox);

        JButton generateBillButton = new JButton("Generate Bill");
        generateBillButton.addActionListener(e -> {
            StringBuilder bill = new StringBuilder();
            /* Uncomment and complete the following block to display student bill information
            for (Student student : view.getController().getModel().getStudents()) {
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
            }
            */
            billArea.setText(bill.toString());
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> main.showPanel(main.getHomePanel()));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(generateBillButton);

        add(topPanel);
        add(scrollPane);
        add(buttonPanel);
    }
}
