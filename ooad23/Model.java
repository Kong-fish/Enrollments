import java.util.ArrayList;
import java.util.List;

public class Model extends Observable {
    private List<Student> students;
    private double discountRate;

    public Model() {
        students = new ArrayList<>();
        discountRate = 0.1; // Can change
    }

    public List<Student> getStudents() {
        
        System.out.print(students);
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
        notifyObservers();
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
        notifyObservers();
    }
}
