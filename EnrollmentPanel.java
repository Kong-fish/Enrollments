import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class EnrollmentPanel extends JPanel {

    private JScrollPane scrollPane;
    private JTextField searchField;
    private MainPanel main;
    private StudentData studentData;
    private CourseData courseData;
    private List<JCheckBox> courseCheckBoxes;
    private Student currentStudent;
    private JPanel checkBoxPanel;
    private JPanel courseSelectionPanel;


    public EnrollmentPanel(MainPanel main) {
        this.main = main;
        studentData = new StudentData("students.txt");
        courseData = new CourseData();
        courseCheckBoxes = new ArrayList<>();
        setLayout(null); // Use null layout for absolute positioning

        JLabel searchLabel = new JLabel("Search Student by Name: ");
        searchLabel.setBounds(190, 150, 150, 25);
        add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(380, 150, 165, 25);
        searchField.addActionListener(e -> searchStudent(searchField.getText()));
        add(searchField);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(380, 180, 80, 25);
        searchButton.addActionListener(e -> {
            String query = searchField.getText();
            Student student = searchStudent(query);
            if (student != null) {
                // If the student is found, create a new CourseSelectionPanel and show it
                CourseSelectionPanel courseSelectionPanel = new CourseSelectionPanel(student, studentData, courseData);
                courseSelectionPanel.showEligibleCourses(student.getCourseLevel());
            }
        });
        add(searchButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(380, 210, 80, 25);
        backButton.addActionListener(e -> main.showPanel(main.getHomePanel()));
        
    }

    private Student searchStudent(String query) {
        // Search for the student
        Student student = studentData.searchStudent(query);
        
        if (student != null) {
            // If the student is found, return the student
            return student;
        } else {
            // If the student is not found, show an error message
            JOptionPane.showMessageDialog(this, "No such student registered in the system.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }    
      
}
