import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    private MainPanel main;

    public HomePanel(MainPanel main) {
        this.main = main;
        setLayout(new GridLayout(2, 4));
        setSize(400, 300);

        JLabel titleLabel = new JLabel("Hello" );
        titleLabel.setFont(new Font("Serif", Font.BOLD, 48));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel);

        JLabel empty = new JLabel();

        JButton btnEnroll = new JButton("Enroll Course");
        btnEnroll.addActionListener(e -> {
            main.showPanel("Enrollment Panel");
        });

        JButton btnBill = new JButton("Bill");
        btnBill.addActionListener(e -> {
            main.showPanel("Billing Panel");
        });

        JButton btnView = new JButton("View Course");
        btnView.addActionListener(e -> {
            main.showPanel("View Course Panel");
        });    

        add(new JLabel());
        add(empty);
        add(btnEnroll);
        add(btnBill);
        add(btnView);
    }
}
