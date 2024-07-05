import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    private Controller controller;
    private String student;

    public WelcomePanel(View view) {
        setLayout(new GridLayout(2, 4));
        setSize(400, 300);

        JLabel titleLabel = new JLabel("Hello" );//+ controller.getCurrentStudentName()); //cannot get the student name
        titleLabel.setFont(new Font("Serif", Font.BOLD, 48));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel);

        JLabel empty = new JLabel();

        JButton btnEnroll = new JButton("Enroll Course");
        btnEnroll.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) view.getMainPanel().getLayout();
            cardLayout.show(view.getMainPanel(), "EnrollmentPanel");
        });

        JButton btnBill = new JButton("Bill");
        btnBill.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) view.getMainPanel().getLayout();
            cardLayout.show(view.getMainPanel(), "BillingPanel");
        });

        JButton btnView = new JButton("View Course");
        btnView.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) view.getMainPanel().getLayout();
            cardLayout.show(view.getMainPanel(), "ViewCoursePanel");
        });    

        add(new JLabel());
        add(empty);
        add(btnEnroll);
        add(btnBill);
        add(btnView);
    }
}
