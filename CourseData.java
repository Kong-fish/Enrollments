import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseData {
    private List<Course> courses;
    private String level;
    private static List<Course> courses2;

    public CourseData(String filename) {
        this.level = filename.replace(".txt", "");
        courses = new ArrayList<>();
        courses2 = new ArrayList<>();
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
                courses2.add(course);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public CourseData() {
    }
    
    public static List<Course> getStaticCourses() {
        return courses2;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Course> getCoursesByLevel(String studentLevel) {
        List<Course> eligibleCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.getLevel().equals(studentLevel)) {
                eligibleCourses.add(course);
            }
        }
        return eligibleCourses;
    }

    public void loadCourses(String filename) {
        this.level = filename.replace(".txt", "");
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
                courses2.add(course);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
} 