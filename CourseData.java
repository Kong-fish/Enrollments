import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseData {
    private List<Course> courses;
    private String level;

    public CourseData(String filename) {
        this.level = filename.replace(".txt", "");
        courses = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length < 3) {
                    System.out.println("Skipping line due to incorrect format: " + line);
                    continue;
                }
                String courseId = parts[0];
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                Course course;
                switch (level) {
                    case "Remedial":
                        course = new RemedialCourse(courseId, name, price, level);
                        break;
                    case "Matriculation":
                        course = new MatriculationCourse(courseId, name, price, level);
                        break;
                    case "Undergraduate":
                        course = new UndergraduateCourse(courseId, name, price, level);
                        break;
                    case "Postgraduate":
                        course = new PostgraduateCourse(courseId, name, price, level);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid course level: " + level);
                }
                courses.add(course);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public CourseData() {
        //TODO Auto-generated constructor stub
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Course> getCoursesByLevel(String studentLevel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCoursesByLevel'");
    }
}
