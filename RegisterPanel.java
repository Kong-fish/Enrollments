import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterPanel extends JPanel {
    private static int studentId;
    private MainPanel main;

    public RegisterPanel(MainPanel main) {
        this.main = main;
        setLayout(null);
        placeComponents(this);
        studentId = getLastStudentId() + 1;
    }

    private void placeComponents(JPanel panel) {
        // Name
        JLabel nameLabel = new JLabel("Student Name:");
        nameLabel.setBounds(290, 120, 120, 30);
        panel.add(nameLabel);

        JTextField nameText = new JTextField(20);
        nameText.setBounds(410, 120, 165, 25);
        panel.add(nameText);

        // DOB
        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(290, 160, 100, 25);
        panel.add(dobLabel);

        JSpinner dobSpinner = new JSpinner(new SpinnerDateModel());
        dobSpinner.setBounds(410, 160, 165, 25);
        dobSpinner.setEditor(new JSpinner.DateEditor(dobSpinner, "yyyy-MM-dd"));
        panel.add(dobSpinner);

        // Gender
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(290, 200, 100, 25);
        panel.add(genderLabel);

        String[] genders = { "Male", "Female" };
        JComboBox<String> genderBox = new JComboBox<>(genders);
        genderBox.setBounds(410, 200, 165, 25);
        panel.add(genderBox);

        // Phone Number
        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setBounds(290, 240, 100, 25);
        panel.add(phoneLabel);

        JTextField phoneText = new JTextField(20);
        phoneText.setBounds(410, 240, 165, 25);
        panel.add(phoneText);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(290, 280, 100, 25);
        panel.add(emailLabel);

        JTextField emailText = new JTextField(20);
        emailText.setBounds(410, 280, 165, 25);
        panel.add(emailText);

        // Course Level
        JLabel courseLevelLabel = new JLabel("Course Level:");
        courseLevelLabel.setBounds(290, 320, 100, 25);
        panel.add(courseLevelLabel);

        String[] courseLevels = { "Level 1: Remedial courses, Matriculation", "Level 2: Undergraduate", "Level 3: Postgraduate" };
        JComboBox<String> courseLevelBox = new JComboBox<>(courseLevels);
        courseLevelBox.setBounds(410, 320, 165, 25);
        panel.add(courseLevelBox);

        // Register button
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(290, 360, 110, 25);
        panel.add(registerButton);

        // Action listener for register button
        registerButton.addActionListener(e -> {
            String studentName = nameText.getText();
            Date dob = (Date) dobSpinner.getValue();
            String gender = (String) genderBox.getSelectedItem();
            String phoneNumber = phoneText.getText();
            String email = emailText.getText();
            String courseLevel = (String) courseLevelBox.getSelectedItem(); // Get the selected course level
            try {
                Long.parseLong(phoneNumber);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = formatter.format(dob);
                Student student = new Student(studentId++, studentName, formattedDate, gender, phoneNumber, email, courseLevel);
                saveStudent(student);
                main.getStudentListPanel().refreshStudentList();
                // Show a dialog box with a success message
                JOptionPane.showMessageDialog(panel, "Student Information successfully registered.",
                        "Registration Successful", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Phone number must be numeric.", "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Show student list button
        JButton showListButton = new JButton("Show Student List");
        showListButton.setBounds(290, 400, 150, 25);
        panel.add(showListButton);

        // Action listener for show student list button
        showListButton.addActionListener(e -> main.showPanel(main.getStudentListPanel()));

        JButton backButton = new JButton("Back");
        backButton.setBounds(290, 440, 150, 25);
        panel.add(backButton);

        backButton.addActionListener(e -> main.showPanel(main.getHomePanel()));
    }

    // save student information to file
    private void saveStudent(Student student) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("students.txt", true))) {
            writer.println(student.getId() + "," + student.getName() + "," + student.getDob() + ","
                    + student.getGender() + "," + student.getPhoneNumber() + "," + student.getEmail() + "," + student.getCourseLevel()); // Save the course level
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to get the last used studentId from the students.txt file to update the studentID
    private int getLastStudentId() {
        int lastId = 1000; // Default value
        try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                lastId = Integer.parseInt(parts[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastId;
    }
}
