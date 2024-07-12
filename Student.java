import java.util.ArrayList;
import java.util.List;

public class Student {
    private int id;
    private String name;
    private String dob;
    private String gender;
    private String phoneNumber;
    private String email;
    private String courseLevel;
    private List<Course> enrolledCourses = new ArrayList<>();

    public Student(int id, String name, String dob, String gender, String phoneNumber, String email, String courseLevel) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.courseLevel = courseLevel;
    }

    public int getLevel() {
        // Replace this with your actual logic for determining the student level
        switch (courseLevel) {
            case "Remedial":
                return 1;
            case "Matriculation":
                return 2;
            case "Undergraduate":
                return 3;
            case "Postgraduate":
                return 4;
            default:
                throw new IllegalArgumentException("Invalid course level: " + courseLevel);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourseLevel() {
        return courseLevel;
    }
    

    public void setCourseLevel(String courseLevel) {
        this.courseLevel = courseLevel;
    }

    public void enrollCourse(Course course) {
        enrolledCourses.add(course);
    }

    public void unenrollCourse(Course course) {
        enrolledCourses.remove(course);
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

}