import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ViewCoursePanel extends JPanel {
    private MainPanel main;
    private JTable courseTable;
    private CourseData remedialCourseData;
    private CourseData matriculationCourseData;
    private CourseData undergraduateCourseData;
    private CourseData postgraduateCourseData;

    public ViewCoursePanel(MainPanel main) {
        this.main = main;
        setLayout(null);

        // Initialize courseTable with a DefaultTableModel
        courseTable = new JTable(new DefaultTableModel(new Object[]{"Course ID", "Name", "Price", "Level"}, 0));
        courseTable.setBounds(30, 30, 600, 300); // Set the position and size of the table
        add(courseTable); // Add the table to the panel

        JButton addButton = new JButton("Add Course");
        addButton.addActionListener(e -> main.showPanel(main.getAddCoursePanel()));
        addButton.setBounds(280, 380, 110, 25);
        add(addButton);

        remedialCourseData = new CourseData("Remedial.txt");
        matriculationCourseData = new CourseData("Matriculation.txt");
        undergraduateCourseData = new CourseData("Undergraduate.txt");
        postgraduateCourseData = new CourseData("Postgraduate.txt");
        loadCoursesToTable();
    }

    public void loadCoursesToTable() {
        DefaultTableModel model = (DefaultTableModel) courseTable.getModel();
        model.setRowCount(0); // Clear the table
        model.setRowCount(0); // Clear the table
        addCoursesToTable(remedialCourseData.getCourses(), model);
        addCoursesToTable(matriculationCourseData.getCourses(), model);
        addCoursesToTable(undergraduateCourseData.getCourses(), model);
        addCoursesToTable(postgraduateCourseData.getCourses(), model);
    }

    private void addCoursesToTable(List<Course> courses, DefaultTableModel model) {
        for (Course course : courses) {
            model.addRow(new Object[]{course.getCourseId(), course.getName(), course.getPrice(), course.getLevel()});
        }
    }

    public String getNextCourseId() {
        int maxId = 1000; // Start from R1000
        maxId = Math.max(maxId, getMaxCourseId(remedialCourseData.getCourses()));
        maxId = Math.max(maxId, getMaxCourseId(matriculationCourseData.getCourses()));
        maxId = Math.max(maxId, getMaxCourseId(undergraduateCourseData.getCourses()));
        maxId = Math.max(maxId, getMaxCourseId(postgraduateCourseData.getCourses()));
        return "R" + (maxId + 1); // Return the next course ID
    }

    private int getMaxCourseId(List<Course> courses) {
        int maxId = 1000;
        for (Course course : courses) {
            String courseId = course.getCourseId().substring(1); // Remove the 'R'
            maxId = Math.max(maxId, Integer.parseInt(courseId));
        }
        return maxId;
    }
}
