import javax.swing.*;
import java.awt.*;

public class ViewCoursePanel extends JPanel {
    public ViewCoursePanel(View view) {
        setLayout(new BorderLayout());

        JTextArea studentsArea = new JTextArea();
        studentsArea.setEditable(false);

        JButton refreshButton = new JButton("Refresh");

        refreshButton.addActionListener(e -> {
            StringBuilder students = new StringBuilder();
            for (Student student : view.getController().getModel().getStudents()) {
                students.append("Student: ").append(student.getName()).append("\n");
                students.append("Courses:\n");
                for (Course course : student.getCourses()) {
                    students.append(course.getName()).append(" - ").append(course.getFee()).append("\n");
                }
                students.append("Needs Accommodation: ").append(student.needsAccommodation()).append("\n\n");
            }
            studentsArea.setText(students.toString());
        });

        JButton backButton = new JButton("Back");

        backButton.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) view.getMainPanel().getLayout();
            cardLayout.show(view.getMainPanel(), "WelcomePanel");
        });

        add(backButton, BorderLayout.NORTH);

        add(new JScrollPane(studentsArea), BorderLayout.CENTER);
        add(refreshButton, BorderLayout.SOUTH);
    }
}
