import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class MainPanel extends JFrame {
    private JPanel currentPanel;
    private HomePanel homePanel;
    private WelcomePanel welcomePanel;
    private EnrollmentPanel enrollmentPanel;
    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;
    private BillingPanel billingPanel;
    private ViewCoursePanel viewCoursePanel;
    private StudentList studentListPanel;
    private AddCoursePanel addCoursePanel;
    private CourseSelectionPanel courseSelectionPanel;
    private List<JCheckBox> courseCheckBoxes = new ArrayList<>();
    private StudentData studentData = new StudentData();
    private CourseData courseData = new CourseData();
    private Student currentStudent;


    public MainPanel() {
        setTitle("MMU");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize panels
        welcomePanel = new WelcomePanel(this);
        homePanel = new HomePanel(this);
        registerPanel = new RegisterPanel(this);
        enrollmentPanel = new EnrollmentPanel(this);
        billingPanel = new BillingPanel(this);
        viewCoursePanel = new ViewCoursePanel(this);
        loginPanel = new LoginPanel(this);
        studentListPanel = new StudentList(this); 
        viewCoursePanel = new ViewCoursePanel(this);
        addCoursePanel = new AddCoursePanel(this);
        courseSelectionPanel = new CourseSelectionPanel(this);

        currentPanel = welcomePanel;
        add(currentPanel);

        setVisible(true);
    }

    // Method to switch between panels
    public void showPanel(JPanel panel) {
        currentPanel.setVisible(false); 
        currentPanel = panel; 
        getContentPane().removeAll(); 
        getContentPane().add(currentPanel); 
        revalidate(); 
        repaint(); 
        currentPanel.setVisible(true); 
    }

    public StudentList getStudentListPanel() {
        return studentListPanel;
    }

    public LoginPanel getLoginPanel() {
        return loginPanel;
    }

    public HomePanel getHomePanel() {
        return homePanel;
    }

    public BillingPanel getBillingPanel() {
        return billingPanel;
    }

    public EnrollmentPanel getEnrollmentPanel() {
        return enrollmentPanel;
    }

    public RegisterPanel getRegisterPanel() {
        return registerPanel;
    }

    public ViewCoursePanel getViewCoursePanel() {
        return viewCoursePanel;
    }

    public WelcomePanel getWelcomePanel() {
        return welcomePanel;
    }

    public AddCoursePanel getAddCoursePanel() {
        return addCoursePanel;
    }

    public CourseSelectionPanel courseSelectionPanel() {
        return courseSelectionPanel;
    }

    public List<JCheckBox> getCourseCheckBoxes() {
        return courseCheckBoxes;
    }

    public void clearCourseCheckBoxes() {
        courseCheckBoxes.clear();
    }

    public void addCourseCheckBox(JCheckBox checkBox) {
        courseCheckBoxes.add(checkBox);
    }

    public StudentData getStudentData() {
        return studentData;
    }

    public CourseData getCourseData() {
        return courseData;
    }

    public Student getCurrentStudent() {
        return currentStudent;
    }

    public void setCurrentStudent(Student student) {
        this.currentStudent = student;
    }
}
