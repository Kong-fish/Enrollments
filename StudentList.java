import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.*;

public class StudentList extends JPanel {
    private JTable table;
    private List<Student> students;
    private MainPanel main;

    public StudentList(MainPanel main) {
        this.main = main;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        students = new ArrayList<>();
        placeComponents(this);
    }

    private void placeComponents(JPanel panel) {
        loadStudents();
        String[] columnNames = {"ID", "Name", "Date of Birth", "Gender", "Phone Number", "Email"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        SimpleDateFormat parser = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        for (Student student : students) {
            String dob = student.getDob();
            try {
                Date date = parser.parse(dob);
                dob = formatter.format(date); // Format the date as "yyyy-MM-dd"
            } catch (ParseException e) {
                e.printStackTrace();
            }
            model.addRow(new Object[]{student.getId(), student.getName(), dob, student.getGender(), student.getPhoneNumber(), student.getEmail()});
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
        students.clear();
        loadStudents();
        // Refresh the table model
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear the table
        SimpleDateFormat parser = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        for (Student student : students) {
            String dob = student.getDob();
            try {
                Date date = parser.parse(dob);
                dob = formatter.format(date); // Format the date as "yyyy-MM-dd"
            } catch (ParseException e) {
                e.printStackTrace();
            }
            model.addRow(new Object[]{student.getId(), student.getName(), dob, student.getGender(), student.getPhoneNumber(), student.getEmail()});
        }
    }

    private void loadStudents() {
        try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String dob = parts[2];
                String gender = parts[3];
                String phoneNumber = parts[4];
                String email = parts[5];
                students.add(new Student(id, name, dob, gender, phoneNumber, email));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
