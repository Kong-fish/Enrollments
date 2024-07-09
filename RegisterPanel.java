import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterPanel extends JPanel {
    private static int studentId = 1000;
    private MainPanel main;

    public RegisterPanel(MainPanel main) {
        this.main = main;
        setLayout(null);
        placeComponents(this);
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

        // Register button
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(290, 320, 110, 25);
        panel.add(registerButton);

        // Action listener for register button
        registerButton.addActionListener(e -> {
            String studentName = nameText.getText();
            Date dob = (Date) dobSpinner.getValue();
            String gender = (String) genderBox.getSelectedItem();
            String phoneNumber = phoneText.getText();
            String email = emailText.getText();
            try {
                Long.parseLong(phoneNumber);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = formatter.format(dob);
                Student student = new Student(studentId++, studentName, formattedDate, gender, phoneNumber, email);
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
        showListButton.setBounds(290, 360, 150, 25);
        panel.add(showListButton);

        // Action listener for show student list button
        showListButton.addActionListener(e -> main.showPanel(main.getStudentListPanel()));

        JButton backButton = new JButton("Back");
        backButton.setBounds(290, 400, 150, 25);
        panel.add(backButton);

        backButton.addActionListener(e -> main.showPanel(main.getHomePanel()));
    }

    // Method to save student information to file
    private void saveStudent(Student student) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("students.txt", true))) {
            writer.println(student.getId() + "," + student.getName() + "," + student.getDob() + ","
                    + student.getGender() + "," + student.getPhoneNumber() + "," + student.getEmail());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
