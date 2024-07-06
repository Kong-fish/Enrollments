import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EnrollmentPanel extends JPanel {
    private JCheckBox accommodationCheckBox;
    private JComboBox<String> courseComboBox;
    private MainPanel main;

    public EnrollmentPanel(MainPanel main) {
        this.main = main;
        setLayout(new GridLayout(3, 2));
        setSize(400, 300);

        JLabel studentNameLabel = new JLabel("Student Name:");
        add(studentNameLabel);

        JLabel nameLabel = new JLabel();
        add(nameLabel);

        JLabel courseNameLabel = new JLabel("Select Course:");
        add(courseNameLabel);

        courseComboBox = new JComboBox<>(new String[]{
                "English Remedial", "Math Remedial",
                "Engineering Matriculation", "IT Matriculation", "Business Matriculation", "Law Matriculation",
                "Engineering Undergraduate", "IT Undergraduate", "Business Undergraduate", "Law Undergraduate",
                "Engineering Postgraduate", "IT Postgraduate", "Business Postgraduate", "Law Postgraduate"
        });
        add(courseComboBox);

        JButton enrollButton = new JButton("Enroll");

        enrollButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(this, "Student enrolled successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton backButton = new JButton("Back");

        backButton.addActionListener(e -> {
            main.showPanel("Home Panel");
        });
        
        add(backButton);
        add(enrollButton);
    }
}
