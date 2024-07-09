import javax.swing.*;

public class ViewCoursePanel extends JPanel {
    private MainPanel main;

    public ViewCoursePanel(MainPanel main) {
        this.main = main;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTextArea studentsArea = new JTextArea();
        studentsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(studentsArea);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setAlignmentX(CENTER_ALIGNMENT);
        refreshButton.addActionListener(e -> {
            StringBuilder students = new StringBuilder();
            /* Uncomment and complete the following block to display student information
            for (Student student : view.getController().getModel().getStudents()) {
                students.append("Student: ").append(student.getName()).append("\n");
                students.append("Courses:\n");
                for (Course course : student.getCourses()) {
                    students.append(course.getName()).append(" - ").append(course.getFee()).append("\n");
                }
                students.append("Needs Accommodation: ").append(student.needsAccommodation()).append("\n\n");
            }
            studentsArea.setText(students.toString());
            */
        });

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        backButton.addActionListener(e -> main.showPanel(main.getHomePanel()));

        add(Box.createVerticalStrut(10)); // Adds space at the top
        add(backButton);
        add(Box.createVerticalStrut(10)); // Adds space between components
        add(scrollPane);
        add(Box.createVerticalStrut(10)); // Adds space between components
        add(refreshButton);
        add(Box.createVerticalStrut(10)); // Adds space at the bottom
    }
}
