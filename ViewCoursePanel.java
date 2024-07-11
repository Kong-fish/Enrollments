import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewCoursePanel extends JPanel {
    private MainPanel main;
    private JTable courseTable;
    private CourseData courseData;

    public ViewCoursePanel(MainPanel main) {
        this.main = main;
        setLayout(null);

        JButton addButton = new JButton("Add Course");
        addButton.addActionListener(e -> main.showPanel(main.getAddCoursePanel()));
        addButton.setBounds(280, 380, 110, 25);
        add(addButton);

        courseTable = createTable();
        JScrollPane scrollPane = new JScrollPane(courseTable);
        scrollPane.setBounds(30, 30, 480, 340);
        add(scrollPane);

        courseData = new CourseData();
        loadCoursesToTable();
    }

    private JTable createTable() {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Course ID", "Course Name", "Price", "Level"}, 0);
        return new JTable(model);
    }

    public void loadCoursesToTable() {
        DefaultTableModel model = (DefaultTableModel) courseTable.getModel();
        model.setRowCount(0); // Clear the table
        List<Course> courses = courseData.getCourses();
        for (Course course : courses) {
            model.addRow(new Object[]{course.getCourseId(), course.getName(), course.getPrice(), courseData.getCourseLevel(course.getLevel())});
        }
    }
}
