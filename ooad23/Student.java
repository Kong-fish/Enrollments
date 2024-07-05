import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private List<Course> courses;
    private boolean needsAccommodation;

    public Student(String name) {
        this.name = name;
        this.courses = new ArrayList<>();
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
