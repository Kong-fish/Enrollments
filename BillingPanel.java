import javax.swing.*;
import java.awt.*;

public class BillingPanel extends JPanel {
    public BillingPanel(View view) {
        setLayout(new BorderLayout());

        JTextArea billArea = new JTextArea();
        billArea.setEditable(false);

        JButton generateBillButton = new JButton("Generate Bill");

        generateBillButton.addActionListener(e -> {
            StringBuilder bill = new StringBuilder();
            for (Student student : view.getController().getModel().getStudents()) {
                bill.append("Student: ").append(student.getName()).append("\n");
                bill.append("Courses:").append("\n");
                for (Course course : student.getCourses()) {
                    bill.append(course.getName()).append(" : ").append(course.getFee()).append("\n");
                }
        
                double totalFee = view.getController().calculateTotalFee(student);
                if(student.needsAccommodation()) {
                    bill.append("Accommodation Fee: ").append("500").append("\n");
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
            billArea.setText(bill.toString());
        });

        add(new JScrollPane(billArea), BorderLayout.CENTER);
        add(generateBillButton, BorderLayout.SOUTH);
    }
}
