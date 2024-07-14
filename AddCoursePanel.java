import javax.swing.*;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class AddCoursePanel extends JPanel {
    private JTextField courseNameField;
    private JTextField coursePriceField;
    private JComboBox<String> courseLevelBox;
    private JButton addButton;
    private MainPanel main;
    private int courseIdCount = 1000;

    public AddCoursePanel(MainPanel main) {
        this.main = main;
        setLayout(null);
        placeComponents(this);
    }

    private void placeComponents(JPanel panel) {
        // Course Name
        JLabel nameLabel = new JLabel("Course Name:");
        nameLabel.setBounds(290, 120, 120, 30);
        panel.add(nameLabel);

        courseNameField = new JTextField(20);
        courseNameField.setBounds(410, 120, 165, 25);
        panel.add(courseNameField);

        // Course Price
        JLabel priceLabel = new JLabel("Course Price:");
        priceLabel.setBounds(290, 160, 120, 30);
        panel.add(priceLabel);

        coursePriceField = new JTextField(20);
        coursePriceField.setBounds(410, 160, 165, 25);
        panel.add(coursePriceField);

        // Course Level
        JLabel levelLabel = new JLabel("Course Level:");
        levelLabel.setBounds(290, 200, 120, 30);
        panel.add(levelLabel);

        String[] courseLevels = {"Remedial", "Matriculation", "Undergraduate", "Postgraduate"};
        courseLevelBox = new JComboBox<>(courseLevels);
        courseLevelBox.setBounds(410, 200, 165, 25);
        panel.add(courseLevelBox);

        addButton = new JButton("Add Course");
        addButton.setBounds(290, 240, 100, 25);
        addButton.addActionListener(e ->{
                try {
                    String courseName = courseNameField.getText();
                    String coursePrice = coursePriceField.getText();
                    String courseLevel = (String) courseLevelBox.getSelectedItem();
                    String courseId = generateCourseId(courseLevel);

                    if (courseName.isEmpty() || coursePrice.isEmpty()) {
                        throw new Exception("All fields must be filled!");
                    }

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(courseLevel + ".txt", true))) {
                        writer.write(courseId + "," + courseName + "," + coursePrice);
                        writer.newLine();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    courseNameField.setText("");
                    coursePriceField.setText("");
                    main.showPanel(main.getViewCoursePanel());
                    main.getViewCoursePanel().loadCoursesToTable();

                    JOptionPane.showMessageDialog(panel, "Course added successfully! Course ID: " + courseId, "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
        });
        panel.add(addButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(410, 240, 165, 25);
        backButton.addActionListener(e -> main.showPanel(main.getHomePanel()));
        panel.add(backButton);
    }

    private String generateCourseId(String courseLevel) {
        String prefix;

        switch (courseLevel) {
            case "Remedial":
                prefix = "R";
                break;
            case "Matriculation":
                prefix = "M";
                break;
            case "Undergraduate":
                prefix = "U";
                break;
            case "Postgraduate":
                prefix = "P";
                break;
            default:
                throw new IllegalArgumentException("Invalid course level: " + courseLevel);
        }
        courseIdCount = getLastCourseId(courseLevel);
        return prefix + courseIdCount++;
    }

    private int getLastCourseId(String courseLevel) {
        courseLevel = courseLevel + ".txt";
        String lastLine = "";
        String currentLine;
        try (BufferedReader reader = new BufferedReader(new FileReader(courseLevel))) {
            while ((currentLine = reader.readLine()) != null) {
                lastLine = currentLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        if (lastLine.isEmpty()) {
            return 1000; 
        } else {
            String lastCourseId = lastLine.split(",")[0];
            int lastCourseIdCount = Integer.parseInt(lastCourseId.substring(1)); // extract int
            return lastCourseIdCount + 1;
        }
    }

}