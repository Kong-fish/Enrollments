import javax.swing.*;
import java.awt.*;

public class View extends JFrame implements Observer{
    private Controller controller;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public View() {
        setTitle("MMU");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        cardLayout.show(mainPanel, "MPanel");

        JPanel enrollmentPanel = new EnrollmentPanel(this);
        JPanel billingPanel = new BillingPanel(this);
        JPanel discountPanel = new DiscountPanel(this);
        JPanel viewCoursePanel = new ViewCoursePanel(this);
        JPanel registerPanel = new RegisterPanel(this);
        JPanel welcomePanel = new WelcomePanel(this);
        JPanel mPanel = new Welcome(this); 

        mainPanel.add(mPanel, "MPanel");
        mainPanel.add(registerPanel, "Register");
        mainPanel.add(welcomePanel, "WelcomePanel");
        mainPanel.add(enrollmentPanel, "EnrollmentPanel");
        mainPanel.add(billingPanel, "BillingPanel");
        mainPanel.add(discountPanel, "DiscountPanel");
        mainPanel.add(viewCoursePanel, "ViewCoursePanel");

        setController(controller);

        add(mainPanel, BorderLayout.CENTER);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Controller getController() {
        return controller;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void update() {
    }
}
