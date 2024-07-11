import java.util.ArrayList;
import javax.swing.*;

public class EnrollmentPanel extends JPanel {
    private MainPanel main;
    private JTextField searchField;

    public EnrollmentPanel(MainPanel main) {
        this.main = main;
        setLayout(null); // Use null layout for absolute positioning

        JLabel searchLabel = new JLabel("Search Student by Name: ");
        searchLabel.setBounds(190, 150, 150, 25);
        add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(380, 150, 165, 25);
        add(searchField);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(380, 180, 80, 25);
        searchButton.addActionListener(e -> {
            String query = searchField.getText();
            Student student = main.getStudentData().searchStudent(query);
            if (student != null) {
                // If the student is found, create a new CourseSelectionPanel and show it
                CourseSelectionPanel courseSelectionPanel = new CourseSelectionPanel(main);
                courseSelectionPanel.showEligibleCourses(student.getCourseLevel());
                main.showPanel(courseSelectionPanel);
            } else {
                // If the student is not found, show an error message
                JOptionPane.showMessageDialog(this, "No such student registered in the system.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(searchButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(380, 210, 80, 25);
        backButton.addActionListener(e -> main.showPanel(main.getHomePanel()));
        add(backButton);
    }
}
