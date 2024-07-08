import javax.swing.*;
import java.awt.*;

public class MainPanel extends JFrame {
    private CardLayout cardLayout;
    private JPanel panel;

    public MainPanel() {
        setTitle("MMU");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);

        cardLayout.show(panel, "Welcome Panel");

        // Create and add the panels
        JPanel welcomePanel = new WelcomePanel(this);
        JPanel homePanel = new HomePanel(this);
        JPanel registerPanel = new RegisterPanel(this);
        JPanel enrollmentPanel = new EnrollmentPanel(this);
        JPanel billingPanel = new BillingPanel(this);
        JPanel viewCoursePanel = new ViewCoursePanel(this);
        JPanel StudentList = new StudentList(this);

        panel.add(welcomePanel, "Welcome Panel");
        panel.add(homePanel, "Home Panel");
        panel.add(registerPanel, "Register Panel");
        panel.add(enrollmentPanel, "Enrollment Panel");
        panel.add(billingPanel, "Billing Panel");
        panel.add(viewCoursePanel, "View Course Panel");
        panel.add(StudentList, "Student List Panel");

        add(panel);
    }

    public void showPanel(String panelName) {
        cardLayout.show(panel, panelName);
    }
}
