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
    private DiscountPanel discountPanel;
    private StudentList studentListPanel; 

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

    public DiscountPanel getDiscountPanel() {
        return discountPanel;
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
}
