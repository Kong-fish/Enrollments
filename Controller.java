import javax.swing.JComboBox;

public class Controller {
    private static Controller instance;
    private Model model;
    private View view;

    private Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public static Controller getInstance(Model model, View view) {
        if (instance == null) {
            instance = new Controller(model, view);
        }
        return instance;
    }

    public Model getModel() {
        return model;
    }

    public void addStudent(String name, JComboBox<String> courseComboBox, boolean needsAccommodation) {
        Student student = new Student(name);
        student.setNeedsAccommodation(needsAccommodation);
        Course course = new Course((String) courseComboBox.getSelectedItem(), determineCourseFee((String) courseComboBox.getSelectedItem()));
        student.addCourse(course);
        model.addStudent(student);
    }

    public void updateDiscountRate(double newRate) {
        model.setDiscountRate(newRate);
    }

    public double calculateTotalFee(Student student) {
        double totalFee = 0;
        for (Course course : student.getCourses()) {
            totalFee += course.getFee();
        }
        if (student.needsAccommodation()) {
            totalFee += 500; // Assume accommodation fee = 500
        }
        return totalFee;
    }

    public double calculateDiscountedFee(Student student) {
        double totalFee = calculateTotalFee(student);
        return totalFee * (1 - model.getDiscountRate());
    }

    private double determineCourseFee(String courseName) {
        switch (courseName) {
            case "English Remedial":
            case "Math Remedial":
                return 300;
            case "Engineering Matriculation":
            case "IT Matriculation":
            case "Business Matriculation":
            case "Law Matriculation":
                return 500;
            case "Engineering Undergraduate":
            case "IT Undergraduate":
            case "Business Undergraduate":
            case "Law Undergraduate":
                return 1000;
            case "Engineering Postgraduate":
            case "IT Postgraduate":
            case "Business Postgraduate":
            case "Law Postgraduate":
                return 1500;
            default:
                return 0;
        }
    }
}
