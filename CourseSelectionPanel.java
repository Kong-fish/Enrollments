import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CourseSelectionPanel extends JPanel {
    private JTable courseTable;
    private JButton enrollButton;
    private String studentName;
    private MainPanel mainPanel; // Assuming you have a MainPanel class

    private CourseData remedialCourseData;
    private CourseData matriculationCourseData;
    private CourseData undergraduateCourseData;
    private CourseData postgraduateCourseData;

    public CourseSelectionPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        setLayout(new BorderLayout());

        remedialCourseData = new CourseData("Remedial.txt");
        matriculationCourseData = new CourseData("Matriculation.txt");
        undergraduateCourseData = new CourseData("Undergraduate.txt");
        postgraduateCourseData = new CourseData("Postgraduate.txt");

        loadCoursesToTable();
    }

    private void loadCoursesToTable() {
        String[] columns = {"Select", "Course Name"};
        List<Course> courses = getEligibleCourses();

        Object[][] data = new Object[courses.size()][2];
        for (int i = 0; i < courses.size(); i++) {
            data[i][0] = Boolean.FALSE;
            data[i][1] = courses.get(i).getName();
        }

        courseTable = new JTable(new DefaultTableModel(data, columns) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Boolean.class : super.getColumnClass(columnIndex);
            }
        });
        JScrollPane scrollPane = new JScrollPane(courseTable);
        add(scrollPane, BorderLayout.CENTER);

        enrollButton = new JButton("Enroll");
        enrollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enrollCourses();
            }
        });

        add(enrollButton, BorderLayout.SOUTH);
    }

    private List<Course> getEligibleCourses() {
        int studentLevel = mainPanel.getEnrollmentPanel().getStudentLevel();
        List<Course> courses;
        switch (studentLevel) {
            case 1:
                courses = remedialCourseData.getCourses();
                courses.addAll(matriculationCourseData.getCourses());
                courses.addAll(undergraduateCourseData.getCourses());
                courses.addAll(postgraduateCourseData.getCourses());
                break;
            case 2:
                courses = undergraduateCourseData.getCourses();
                courses.addAll(postgraduateCourseData.getCourses());
                break;
            case 3:
                courses = postgraduateCourseData.getCourses();
                break;
            default:
                throw new IllegalArgumentException("Invalid student level: " + studentLevel);
        }
        return courses;
    }

    private void enrollCourses() {
        studentName = mainPanel.getEnrollmentPanel().getSearchFieldText();
        for (int i = 0; i < courseTable.getRowCount(); i++) {
            Boolean isChecked = (Boolean) courseTable.getValueAt(i, 0);
            if (isChecked) {
                String courseName = (String) courseTable.getValueAt(i, 1);
                // Enroll the student in the course
                System.out.println("Enrolled " + studentName + " in " + courseName);
            }
        }
    }
}
