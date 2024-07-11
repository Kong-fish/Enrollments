import java.util.*;

public abstract class Course {
    protected String name;
    protected String courseId;
    protected double price;
    private String level;
    private static List<Course> courses = new ArrayList<>();

    public Course(String courseId, String name, double price, String level) {
        this.name = name;
        this.price = price;
        this.courseId = courseId;
        this.level = level;
    }

    
    public String getCourseId(){
        return courseId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getLevel(){
        return level;
    }

    public static void addCourse(Course course) {
        courses.add(course);
    }

    public static Course getCourse(int index) {
        return courses.get(index);
    }

    public static List<String> getCourseNames() {
        List<String> courseNames = new ArrayList<>();
        for (Course course : courses) {
            courseNames.add(course.getName());
        }
        return courseNames;
    }

}
