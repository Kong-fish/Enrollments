import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentList extends JPanel {
    private JTable table;
    private List<Student> students;
    private MainPanel mainPanel;

    public StudentList(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        setLayout(new BorderLayout());
        students = new ArrayList<>();
        placeComponents(this);
    }

    private void placeComponents(JPanel panel) {
        loadStudents();
        String[] columnNames = {"ID", "Name", "Date of Birth", "Gender", "Phone Number", "Email"};
        Object[][] data = new Object[students.size()][6];
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            data[i][0] = student.getId();
            data[i][1] = student.getName();
            data[i][2] = student.getDob();
            data[i][3] = student.getGender();
            data[i][4] = student.getPhoneNumber();
            data[i][5] = student.getEmail();
        }
        table = new JTable(data, columnNames);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
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
