import java.awt.Dimension;

import javax.swing.*;

public class EnrollmentPanel extends JPanel {

    private JComboBox<String> studentComboBox;
    private JComboBox<String> courseComboBox;
    private JTextField searchField;
    private MainPanel main;
    private StudentData studentData;

    public EnrollmentPanel(MainPanel main) {
        this.main = main;
        studentData = new StudentData("students.txt");
        setLayout(null); // Use null layout for absolute positioning

        JLabel studentNameLabel = new JLabel("Student Name:");
        studentNameLabel.setBounds(260, 100, 100, 25);
        add(studentNameLabel);

        studentComboBox = new JComboBox<>(studentData.getStudentNames());
        studentComboBox.setBounds(380, 100, 165, 25);
        add(studentComboBox);

        JLabel searchLabel = new JLabel("Search by name or ID: ");
        searchLabel.setBounds(260, 150, 150, 25);
        add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(380, 150, 165, 25);
        searchField.addActionListener(e -> searchStudent(searchField.getText()));
        add(searchField);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(380, 180, 80, 25);
        searchButton.addActionListener(e -> searchStudent(searchField.getText()));
        add(searchButton);

        JButton enrollButton = new JButton("Enroll");
        enrollButton.setBounds(380, 180, 80, 25);
        enrollButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Student enrolled successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });
        add(enrollButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(380, 210, 80, 25);
        backButton.addActionListener(e -> main.showPanel(main.getHomePanel()));
        
    }

    private void searchStudent(String query) {
        Student student = studentData.searchStudent(query);
        if (student != null) {
            studentComboBox.setSelectedItem(student.getName());
        } else {
            JOptionPane.showMessageDialog(this, "No such student registered in the system.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
