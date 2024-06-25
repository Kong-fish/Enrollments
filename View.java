import javax.swing.*;
import java.awt.*;

public class View extends JFrame implements Observer {
    private Controller controller;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public View() {
        setTitle("MMU Enrollment System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new EnrollmentPanel(this), "EnrollmentPanel");
        mainPanel.add(new BillingPanel(this), "BillingPanel");
        mainPanel.add(new DiscountPanel(this), "DiscountPanel");
        mainPanel.add(new ViewStudentsPanel(this), "ViewStudentsPanel");

        JPanel navPanel = new JPanel();
        navPanel.setLayout(new GridLayout(1, 4));

        JButton btnEnrollment = new JButton("Register New Student");
        btnEnrollment.addActionListener(e -> cardLayout.show(mainPanel, "EnrollmentPanel"));

        JButton btnViewStudents = new JButton("View Students");
        btnViewStudents.addActionListener(e -> cardLayout.show(mainPanel, "ViewStudentsPanel"));

        JButton btnUpdateDiscount = new JButton("Update Discount Rate");
        btnUpdateDiscount.addActionListener(e -> cardLayout.show(mainPanel, "DiscountPanel"));

        JButton btnBilling = new JButton("Generate Bill");
        btnBilling.addActionListener(e -> cardLayout.show(mainPanel, "BillingPanel"));

        navPanel.add(btnEnrollment);
        navPanel.add(btnViewStudents);
        navPanel.add(btnUpdateDiscount);
        navPanel.add(btnBilling);

        add(navPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Controller getController() {
        return controller;
    }

    @Override
    public void update() {
    }
}
