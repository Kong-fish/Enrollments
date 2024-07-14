import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EnrollmentPanel extends JPanel {
    private JComboBox<String> studentNameBox;
    private JButton searchButton;
    private StudentData studentData;
    private MainPanel mainPanel;
    private Set<String> studentNames;
    private Map<String, String> studentLevels;

    public EnrollmentPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.studentData = new StudentData("students.txt");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel searchPanel = new JPanel();
        searchPanel.setBounds(380, 310, 165, 25); 

        studentNameBox = new JComboBox<>(studentData.getStudentNames());
        studentNameBox.setBounds(410, 310, 165, 25);

        searchButton = new JButton("Search");
        searchButton.setBounds(580, 310, 80, 25);

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputStudentName = (String) studentNameBox.getSelectedItem(); // Get the selected student name
                if (!isStudentRegistered(inputStudentName)) { // Check if the selected student name is registered
                    JOptionPane.showMessageDialog(EnrollmentPanel.this, "No such student registered in the system.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Get the course level of the selected student
                    String courseLevel = getStudentLevel();
                    // Switch to CourseSelectionPanel
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(EnrollmentPanel.this);
                    frame.setContentPane(new CourseSelectionPanel(mainPanel));
                    frame.revalidate();
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(290, 440, 150, 25);
        backButton.addActionListener(e -> mainPanel.showPanel(mainPanel.getHomePanel()));

        searchPanel.add(new JLabel("Student Name:"));
        searchPanel.add(studentNameBox);
        searchPanel.add(searchButton);
        add(searchPanel);
        add(backButton);

        // Read all student names and levels from the file
        studentNames = new HashSet<>();
        studentLevels = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 7) {
                    String studentName = fields[1].trim(); // Get the student's name, which is the second field
                    studentNames.add(studentName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }

    private boolean isStudentRegistered(String studentName) {
        return studentNames.contains(studentName);
    }    

    public String getStudentLevel() {
        // Get the selected student name from the JComboBox
        String selectedStudentName = (String) studentNameBox.getSelectedItem();
    
        // Get the student object by name
        Student selectedStudent = studentData.getStudentByName(selectedStudentName);
    
        if (selectedStudent != null) {
            // Return the level of the selected student
            return selectedStudent.getCourseLevel();
        } else {
            // Handle the case where the student does not exist
            throw new IllegalArgumentException("Student not found: " + selectedStudentName);
        }
    }       
    
    public String getSelectedStudentName() {
        // Get the selected student name from the JComboBox
        String selectedStudentName = (String) studentNameBox.getSelectedItem();
        return selectedStudentName;
    }
      
}
