import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BillingPanel extends JPanel {
    private JComboBox<String> studentComboBox; // Added JComboBox for student selection
    private JCheckBox accommodationCheckBox;
    private MainPanel main;
    private List<FeeComponent> feeComponents;
    private JTextArea billArea;
    private CourseData courseData;
    private Map<String, List<String>> studentCourses; // Store student courses for reference
    private StudentData studentData;

    public BillingPanel(MainPanel main) {
        this.main = main;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        // Initialize components
        billArea = new JTextArea(10, 30);
        billArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(billArea);

        JLabel studentLabel = new JLabel("Select Student:");
        studentComboBox = new JComboBox<>();

        JPanel topPanel = new JPanel();
        topPanel.add(studentLabel);
        topPanel.add(studentComboBox);

        // Read student and enrolled courses from file
        studentCourses = readStudentCoursesFromFile("studentCourse.txt");

        // Populate student names in the JComboBox
        for (String studentName : studentCourses.keySet()) {
            studentComboBox.addItem(studentName);
        }

        JLabel accommodationLabel = new JLabel("Needs Accommodation?");
        accommodationCheckBox = new JCheckBox();

        topPanel.add(accommodationLabel);
        topPanel.add(accommodationCheckBox);

        feeComponents = List.of(
            new ITNetworkFee(100),
            new LibraryFee(50),
            new ClubSocietyFee(30)
        );

        this.studentData = new StudentData("students.txt");

        JButton generateBillButton = new JButton("Generate Bill");
        generateBillButton.addActionListener(e -> {
            String selectedStudentName = (String) studentComboBox.getSelectedItem();
            if (selectedStudentName != null) {
                Student selectedStudent = studentData.getStudentByName(selectedStudentName);

                String fullLine = selectedStudent.getCourseLevel(); 
                //System.out.println(fullLine);
                String courseLevel = "";
                int colonIndex = fullLine.indexOf(":");
                if (colonIndex != -1) {
                    courseLevel = fullLine.substring(colonIndex + 1).trim(); 
                }

                if (courseLevel.equals("Remedial courses")){
                    courseLevel = "Remedial";
                }

                this.courseData = new CourseData("Remedial.txt");
                this.courseData.loadCourses("Matriculation.txt");
                this.courseData.loadCourses("Undergraduate.txt");
                this.courseData.loadCourses("Postgraduate.txt");
                generateBillForStudent(selectedStudentName);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a student.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(generateBillButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> main.showPanel(main.getHomePanel()));
        add(backButton);

        add(topPanel);
        add(scrollPane);
        add(buttonPanel);
    }

    // Method to generate bill for a specific student
    private void generateBillForStudent(String studentName) {
        List<String> courseNames = studentCourses.get(studentName);
        StringBuilder bill = new StringBuilder();

        bill.append("Student: ").append(studentName).append("\n");
        bill.append("Courses:\n");

        // Display each course for the student
        for (String courseName : courseNames) {
            bill.append(courseName).append("\n");
        }
        // Calculate total and discounted fees
        List<Course> courses = convertCourseNamesToCourses(courseNames);
        Bill studentbill = new Bill(0.1, accommodationCheckBox.isSelected());
        double totalFee = studentbill.calculateTotalFee(courses, feeComponents);
        double discountedFee = studentbill.calculateDiscountedFee(courses, feeComponents);

        // Include accommodation fee if selected
        if (accommodationCheckBox.isSelected()) {
            bill.append("Accommodation Fee: 500\n");
            //totalFee += 500; Add accommodation fee
        }

        bill.append("IT/Network Fee: 100\n");
        bill.append("Library Fee: 50\n");
        bill.append("Society Fee: 30\n");

        // Display total and discounted fees
        bill.append("Total Fee: ").append(totalFee).append("\n");
        bill.append("Discounted Fee: ").append(discountedFee).append("\n\n");

        // Display the generated bill in the JTextArea
        billArea.setText(bill.toString());
    }

    // Method to read student and enrolled courses from file
    private Map<String, List<String>> readStudentCoursesFromFile(String filename) {
        Map<String, List<String>> studentCourses = new LinkedHashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 2) {
                    System.out.println("Skipping line due to incorrect format: " + line);
                    continue;
                }
                String studentName = parts[0].trim();
                String courseName = parts[1].trim();

                // Add course to student's list of courses
                if (!studentCourses.containsKey(studentName)) {
                    studentCourses.put(studentName, new ArrayList<>());
                }
                studentCourses.get(studentName).add(courseName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return studentCourses;
    }

    private List<Course> convertCourseNamesToCourses(List<String> courseNames) {
        List<Course> courses = new ArrayList<>();
        Map<String, String> seenCourses = new HashMap<>();

        // Iterate through each course name
        for (String courseName : courseNames) {
            // Check if we have seen this course name before
            if (seenCourses.containsKey(courseName)) {
                // Get the level of the previously seen course
                String prevLevel = seenCourses.get(courseName);

                // Find the course with the same name and level
                Optional<Course> optionalCourse = courseData.getStaticCourses().stream()
                        .filter(course -> course.getName().equalsIgnoreCase(courseName) && course.getLevel().equals(prevLevel))
                        .findFirst();


                if (optionalCourse.isPresent()) {
                    courses.add(optionalCourse.get());
                } else {
                    System.out.println("Course not found for name: " + courseName + " and level: " + prevLevel);
                    // Handle case where course is not found
                }
            } else {
                // Find the course with the lowest level
                Optional<Course> optionalCourse = courseData.getStaticCourses().stream()
                        .filter(course -> course.getName().equalsIgnoreCase(courseName))
                        .min(Comparator.comparingInt(course -> getLevelIndex(course.getLevel())));

                if (optionalCourse.isPresent()) {
                    courses.add(optionalCourse.get());
                    seenCourses.put(courseName, optionalCourse.get().getLevel());
                } else {
                    System.out.println("Course not found for name: " + courseName);
                    // Handle case where course is not found
                }
            }
        }
        return courses;
    }

    private int getLevelIndex(String level) {
        switch (level.toLowerCase()) {
            case "level 1":
                return 1;
            case "level 2":
                return 2;
            case "level 3":
                return 3;
            default:
                return Integer.MAX_VALUE; // Handle other levels as needed
        }
    };

    public void setCourseData(CourseData courseData) {
        this.courseData = courseData;
    }

}
