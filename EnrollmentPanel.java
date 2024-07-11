import javax.swing.*;
import java.awt.event.*;

public class EnrollmentPanel extends JPanel {
    private JTextField studentNameField;
    private JButton searchButton;
    private MainPanel main;

    public EnrollmentPanel(MainPanel main) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));

        studentNameField = new JTextField(20);
        searchButton = new JButton("Search");

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String studentName = studentNameField.getText();
                if (!isStudentRegistered(studentName)) {
                    JOptionPane.showMessageDialog(EnrollmentPanel.this, "No such student registered in the system.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Switch to CourseSelectionPanel
                    // Assuming CourseSelectionPanel is another JPanel and frame is the JFrame containing this panel
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(EnrollmentPanel.this);
                    frame.setContentPane(new CourseSelectionPanel(main));
                    frame.revalidate();
                }
            }
        });

        searchPanel.add(new JLabel("Student Name:"));
        searchPanel.add(studentNameField);
        searchPanel.add(searchButton);

        add(searchPanel);
    }

    private boolean isStudentRegistered(String studentName) {
        StudentData studentData = new StudentData("students.txt");
        String[] studentNames = studentData.getStudentNames();

        for (String name : studentNames) {
            if (name.equals(studentName)) {
                return true;
            }
        }
        return false;
    }
}
