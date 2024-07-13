import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class EnrollmentPanel extends JPanel {
    private JTextField studentNameField;
    private JButton searchButton;
    private MainPanel mainPanel;
    private Set<String> studentNames;
    private StudentData studentData;

    public EnrollmentPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.studentData = new StudentData();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel searchPanel = new JPanel();
        searchPanel.setBounds(380, 120, 165, 25); // Use null layout to set bounds manually

        studentNameField = new JTextField(20);
        studentNameField.setBounds(410, 120, 165, 25); // Set bounds as per your requirement
        searchButton = new JButton("Search");
        searchButton.setBounds(580, 120, 80, 25);

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String studentName = readStudentNameFromFile("students.txt");
                if (!isStudentRegistered(studentName)) {
                    JOptionPane.showMessageDialog(EnrollmentPanel.this, "No such student registered in the system.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Switch to CourseSelectionPanel
                    // Assuming CourseSelectionPanel is another JPanel and frame is the JFrame containing this panel
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(EnrollmentPanel.this);
                    frame.setContentPane(new CourseSelectionPanel(mainPanel));
                    frame.revalidate();
                }
            }
        });

        searchPanel.add(new JLabel("Student Name:"));
        searchPanel.add(studentNameField);
        searchPanel.add(searchButton);

        add(searchPanel);

        // Read all student names from the file
        studentNames = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length > 1) {
                    studentNames.add(fields[1].trim()); // Get the student's name, which is the second field
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readStudentNameFromFile(String fileName) {
        String studentName = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            if (line != null) {
                String[] fields = line.split(",");
                if (fields.length > 1) {
                    studentName = fields[1].trim(); // Get the student's name, which is the second field
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return studentName;
    }

    private boolean isStudentRegistered(String studentName) {
        return studentNames.contains(studentName);
    }

    public String getSearchFieldText() {
        return studentNameField.getText();
    }

    public int getStudentLevel() {
        // Get the current student's name
        String currentStudentName = studentData.getCurrentStudentName();
    
        // Get the Student object for the current student
        Student currentStudent = studentData.getStudentByName(currentStudentName);
    
        // Check if the student exists
        if (currentStudent != null) {
            // Return the current student's level
            return currentStudent.getLevel();
        } else {
            // Handle the case where the student does not exist
            // For example, return a default level or throw an exception
            return 1; // default level
        }
    }
    
}
