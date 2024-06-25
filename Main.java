import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Model model = new Model();
            View view = new View();
            Controller controller = Controller.getInstance(model, view);
            view.setController(controller);
            model.addObserver(view);
            view.setVisible(true);
        });
    }
}
