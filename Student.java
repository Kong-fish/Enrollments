import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private List<Student> students;
    private List<Course> courses;
    private boolean needsAccommodation;

    public Student(String name) {
        this.name = name;
        this.courses = new ArrayList<>();
        students = new ArrayList<>();
    }

    public void addStudent(String name) {
        Student student = new Student(name);
        students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public boolean needsAccommodation() {
        return needsAccommodation;
    }

    public void setNeedsAccommodation(boolean needsAccommodation) {
        this.needsAccommodation = needsAccommodation;
    }
}
