import javax.swing.*;
import java.awt.*;

public class ViewCoursePanel extends JPanel {
    private MainPanel main;

    public ViewCoursePanel(MainPanel main) {
        this.main = main;
        setLayout(new BorderLayout());

        JTextArea studentsArea = new JTextArea();
        studentsArea.setEditable(false);

        JButton refreshButton = new JButton("Refresh");

        refreshButton.addActionListener(e -> {
            StringBuilder students = new StringBuilder();
            /*for (Student student : view.getController().getModel().getStudents()) {
                students.append("Student: ").append(student.getName()).append("\n");
                students.append("Courses:\n");
                for (Course course : student.getCourses()) {
                    students.append(course.getName()).append(" - ").append(course.getFee()).append("\n");
                }
                students.append("Needs Accommodation: ").append(student.needsAccommodation()).append("\n\n");
            }
            studentsArea.setText(students.toString());*/
        });

        JButton backButton = new JButton("Back");

        backButton.addActionListener(e -> {
            main.showPanel("Home Panel");
        });

        add(backButton, BorderLayout.NORTH);

        add(new JScrollPane(studentsArea), BorderLayout.CENTER);
        add(refreshButton, BorderLayout.SOUTH);
    }
}
