import javax.swing.*;

public class EnrollmentPanel extends JPanel {
    private JCheckBox accommodationCheckBox;
    private JComboBox<String> courseComboBox;
    private MainPanel main;

    public EnrollmentPanel(MainPanel main) {
        this.main = main;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel studentNameLabel = new JLabel("Student Name:");
        studentNameLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(10));
        add(studentNameLabel);

        JLabel nameLabel = new JLabel();
        nameLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(10));
        add(nameLabel);

        JLabel courseNameLabel = new JLabel("Select Course:");
        courseNameLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(10));
        add(courseNameLabel);

        courseComboBox = new JComboBox<>(new String[]{
                "English Remedial", "Math Remedial",
                "Engineering Matriculation", "IT Matriculation", "Business Matriculation", "Law Matriculation",
                "Engineering Undergraduate", "IT Undergraduate", "Business Undergraduate", "Law Undergraduate",
                "Engineering Postgraduate", "IT Postgraduate", "Business Postgraduate", "Law Postgraduate"
        });
        courseComboBox.setAlignmentX(CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(10));
        add(courseComboBox);

        JButton enrollButton = new JButton("Enroll");
        enrollButton.setAlignmentX(CENTER_ALIGNMENT);
        enrollButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Student enrolled successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        backButton.addActionListener(e -> main.showPanel(main.getHomePanel()));
        add(Box.createVerticalStrut(10));
        add(enrollButton);
        add(Box.createVerticalStrut(10));
        add(backButton);
        add(Box.createVerticalStrut(10)); // Adds space at the bottom
    }
}
