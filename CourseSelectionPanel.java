import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.*;

public class CourseSelectionPanel extends JPanel {
    private MainPanel main;

    public CourseSelectionPanel(MainPanel main) {
        this.main = main;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Eligible Courses");
        titleLabel.setFont(new javax.swing.plaf.FontUIResource("Arial", java.awt.Font.BOLD, 80));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(Box.createVerticalStrut(20)); // Adds space at the top
        add(titleLabel);

        add(Box.createVerticalStrut(40)); // Adds space between the title and checkboxes

        // The checkboxes will be added here in the showEligibleCourses method

        JButton enrollButton = new JButton("Enroll");
        enrollButton.setAlignmentX(CENTER_ALIGNMENT);
        enrollButton.addActionListener(e -> enrollCourses());

        add(Box.createVerticalStrut(10)); // Adds space between checkboxes and
        add(enrollButton);
    }

    public void showEligibleCourses(String studentLevel) {
        // Remove old checkboxes
        for (JCheckBox checkBox : main.getCourseCheckBoxes()) {
            this.remove(checkBox);
        }
        main.clearCourseCheckBoxes();

        // Get the courses for the student's level
        List<Course> eligibleCourses = main.getCourseData().getCoursesByLevel(studentLevel);

        // If there are eligible courses, add new checkboxes
        if (eligibleCourses != null) {
            for (Course course : eligibleCourses) {
                JCheckBox checkBox = new JCheckBox(course.getName());
                checkBox.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (checkBox.isSelected()) {
                            main.getCurrentStudent().enrollCourse(course);
                        } else {
                            main.getCurrentStudent().unenrollCourse(course);
                        }
                    }
                });
                this.add(checkBox);
                main.addCourseCheckBox(checkBox);
            }
        }

        // Refresh the UI
        this.revalidate();
        this.repaint();
    }

    private void enrollCourses() {
        // Save the current student's courses to the StudentData or students.txt file
        main.getStudentData().saveStudent(main.getCurrentStudent());
    }
}
