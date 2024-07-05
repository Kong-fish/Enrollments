import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JPanel {
    private JTextField studentNameField;
    private MainPanel main;

    public RegisterPanel(MainPanel main) {
        this.main = main;
        setLayout(new GridLayout(2, 2));
        setSize(400, 300);

        JLabel studentNameLabel = new JLabel("Student Name:");
        add(studentNameLabel);

        studentNameField = new JTextField();
        add(studentNameField);

        JButton registerButton = new JButton("Register");

        registerButton.addActionListener(e -> {
            String studentName = studentNameField.getText();
            //addStudent(studentName);
            JOptionPane.showMessageDialog(this, "Student register successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        
            main.showPanel("Home Panel");
        });

        

        add(new JLabel());
        add(registerButton);
    }
}
