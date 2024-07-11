import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class CourseSelectionPanel extends JFrame {
    private JPanel courseSelectionPanel;
    private List<JCheckBox> courseCheckBoxes;
    private Student currentStudent;
    private StudentData studentData;
    private CourseData courseData;

    public CourseSelectionPanel(Student currentStudent, StudentData studentData, CourseData courseData) {
        this.currentStudent = currentStudent;
        this.studentData = studentData;
        this.courseData = courseData;
        this.courseSelectionPanel = new JPanel();
        this.courseCheckBoxes = new ArrayList<>();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(courseSelectionPanel);
        this.pack();
        this.setVisible(true);
    }

    public void showEligibleCourses(String studentLevel) {
        // Remove old checkboxes
        for (JCheckBox checkBox : courseCheckBoxes) {
            courseSelectionPanel.remove(checkBox);
        }
        courseCheckBoxes.clear();

        // Get the courses for the student's level
        List<Course> eligibleCourses = courseData.getCoursesByLevel(studentLevel);

        // If there are eligible courses, add new checkboxes
        if (eligibleCourses != null) {
            for (Course course : eligibleCourses) {
                JCheckBox checkBox = new JCheckBox(course.getName());
                checkBox.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (checkBox.isSelected()) {
                            currentStudent.enrollCourse(course);
                        } else {
                            currentStudent.unenrollCourse(course);
                        }
                    }
                });
                courseSelectionPanel.add(checkBox);
                courseCheckBoxes.add(checkBox);
            }
        }

        // Refresh the UI
        courseSelectionPanel.revalidate();
        courseSelectionPanel.repaint();
    }

    public void enrollCourses() {
        // Save the current student's courses to the StudentData or students.txt file
        studentData.saveStudent(currentStudent);
    }
}
