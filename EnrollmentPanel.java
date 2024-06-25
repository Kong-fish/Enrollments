import javax.swing.*;
import java.awt.*;

public class EnrollmentPanel extends JPanel {
    private JTextField studentNameField;
    private JCheckBox accommodationCheckBox;
    private JComboBox<String> courseComboBox;

    public EnrollmentPanel(View view) {
        setLayout(new GridLayout(4, 2));
        setSize(400, 300);

        JLabel studentNameLabel = new JLabel("Student Name:");
        add(studentNameLabel);

        studentNameField = new JTextField();
        add(studentNameField);

        JLabel courseNameLabel = new JLabel("Select Course:");
        add(courseNameLabel);

        courseComboBox = new JComboBox<>(new String[]{
            "English Remedial", "Math Remedial",
            "Engineering Matriculation", "IT Matriculation", "Business Matriculation", "Law Matriculation",
            "Engineering Undergraduate", "IT Undergraduate", "Business Undergraduate", "Law Undergraduate",
            "Engineering Postgraduate", "IT Postgraduate", "Business Postgraduate", "Law Postgraduate"
        });
        add(courseComboBox);

        JLabel accommodationLabel = new JLabel("Needs Accommodation:");
        add(accommodationLabel);

        accommodationCheckBox = new JCheckBox();
        add(accommodationCheckBox);

        JButton enrollButton = new JButton("Enroll");

        enrollButton.addActionListener(e -> {
            String studentName = studentNameField.getText();
            boolean needsAccommodation = accommodationCheckBox.isSelected();
            view.getController().addStudent(studentName, courseComboBox, needsAccommodation);
            JOptionPane.showMessageDialog(this, "Student added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });

        add(new JLabel());
        add(enrollButton);
    }
}
