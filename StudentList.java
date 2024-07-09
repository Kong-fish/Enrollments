import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StudentList extends JPanel {
    private JTable table;
    private List<Student> students;
    private MainPanel main;

    public StudentList(MainPanel main) {
        this.main = main;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        StudentData studentData = new StudentData("students.txt");
        students = studentData.getStudents();
        placeComponents(this);
    }

    private void placeComponents(JPanel panel) {
        String[] columnNames = {"ID", "Name", "Date of Birth", "Gender", "Phone Number", "Email","Course Level"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        for (Student student : students) {
            String dob = student.getDob();
            try {
                Date date = parser.parse(dob);
                dob = formatter.format(date); // Format the date as "yyyy-MM-dd"
            } catch (ParseException e) {
                e.printStackTrace();
            }
            model.addRow(new Object[]{student.getId(), student.getName(), dob, student.getGender(), student.getPhoneNumber(), student.getEmail(),student.getCourseLevel()});
        }
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(scrollPane);

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        backButton.addActionListener(e -> main.showPanel(main.getHomePanel()));
        panel.add(Box.createVerticalStrut(10)); // Adds space between components
        panel.add(backButton);
    }

    public void refreshStudentList() {
        StudentData studentData = new StudentData("students.txt");
        students = studentData.getStudents();
        // Refresh the table model
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear the table
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        for (Student student : students) {
            String dob = student.getDob();
            try {
                Date date = parser.parse(dob);
                dob = formatter.format(date); // Format the date as "yyyy-MM-dd"
            } catch (ParseException e) {
                e.printStackTrace();
            }
            model.addRow(new Object[]{student.getId(), student.getName(), dob, student.getGender(), student.getPhoneNumber(), student.getEmail(), student.getCourseLevel()}); // Added Course Level
        }
    }
}
