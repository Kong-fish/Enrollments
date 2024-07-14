import javax.swing.*;

public class HomePanel extends JPanel {
    private MainPanel main;

    public HomePanel(MainPanel main) {
        this.main = main;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Hello");
        titleLabel. setFont(new javax.swing.plaf.FontUIResource("Arial", java.awt.Font.BOLD, 80));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(Box.createVerticalStrut(20)); // Adds space at the top
        add(titleLabel);

        add(Box.createVerticalStrut(40)); // Adds space between the title and buttons

        JButton btnRegister = new JButton("Register New Student");
        btnRegister.setAlignmentX(CENTER_ALIGNMENT);
        btnRegister.addActionListener(e -> main.showPanel(main.getRegisterPanel()));

        JButton btnShowStudList = new JButton("Show Student List");
        btnShowStudList.setAlignmentX(CENTER_ALIGNMENT);
        btnShowStudList.addActionListener(e -> main.showPanel(main.getStudentListPanel()));

        JButton btnEnroll = new JButton("Enroll Course");
        btnEnroll.setAlignmentX(CENTER_ALIGNMENT);
        btnEnroll.addActionListener(e -> main.showPanel(main.getEnrollmentPanel()));

        JButton btnBill = new JButton("Bill");
        btnBill.setAlignmentX(CENTER_ALIGNMENT);
        btnBill.addActionListener(e -> main.showPanel(main.getBillingPanel()));

        JButton btnView = new JButton("View Course");
        btnView.setAlignmentX(CENTER_ALIGNMENT);
        btnView.addActionListener(e -> main.showPanel(main.getViewCoursePanel()));

        JButton btnLogout = new JButton("Log Out");
        btnLogout.setAlignmentX(CENTER_ALIGNMENT);
        btnLogout.addActionListener(e -> main.showPanel(main.getWelcomePanel()));

        add(btnRegister);
        add(Box.createVerticalStrut(10)); // Adds space between buttons
        add(btnShowStudList);
        add(Box.createVerticalStrut(10));
        add(btnEnroll);
        add(Box.createVerticalStrut(10)); 
        add(btnBill);
        add(Box.createVerticalStrut(10)); 
        add(btnView);
        add(Box.createVerticalStrut(10));
        add(btnLogout);
    }
}
