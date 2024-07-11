import java.util.*;
import java.io.*;

public class CourseData {
    private ArrayList<Course> courses;
    private String[] courseLevels = {"Remedial", "Matriculation", "Undergraduate", "Postgraduate"};

    public CourseData() {
        courses = new ArrayList<>();
        for (String level : courseLevels) {
            try (Scanner scanner = new Scanner(new File(level + ".txt"))) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",");
                    String courseId = parts[0];
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    switch (level) {
                        case "Remedial":
                            courses.add(new RemedialCourse(courseId, name, price));
                            break;
                        case "Matriculation":
                            courses.add(new MatriculationCourse(courseId, name, price));
                            break;
                        case "Undergraduate":
                            courses.add(new UndergraduateCourse(courseId, name, price));
                            break;
                        case "Postgraduate":
                            courses.add(new PostgraduateCourse(courseId, name, price));
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid course level: " + level);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public ArrayList<Course> getCoursesByLevel(String level) {
        ArrayList<Course> coursesByLevel = new ArrayList<>();
        for (Course course : courses) {
            if (getCourseLevel(course.getLevel()).equals(level)) {
                coursesByLevel.add(course);
            }
        }
        return coursesByLevel;
    }

    String getCourseLevel(int level) {
        switch (level) {
            case 1:
                return "Level 1";
            case 2:
                return "Undergraduate";
            case 3:
                return "Postgraduate";
            default:
                throw new IllegalArgumentException("Invalid course level: " + level);
        }
    }
    
    public String[] getCourseNames() {
        return courses.stream().map(Course::getName).toArray(String[]::new);
    }

    public Course getCourseByName(String name) {
        for (Course course : courses) {
            if (course.getName().equals(name)) {
                return course;
            }
        }
        return null;
    }

    public String[] getCourseIds() {
        return courses.stream().map(Course::getCourseId).toArray(String[]::new);
    }    

    public Course searchCourse(String query) {
        for (Course course : courses) {
            if (course.getName().equals(query) || course.getCourseId().equals(query)) {
                return course;
            }
        }
        return null;
    }

    public void updateCourseDataFile() {
        for (String level : courseLevels) {
            try (PrintWriter writer = new PrintWriter(new File(level + ".txt"))) {
                for (Course course : getCoursesByLevel(level)) {
                    writer.println(course.getCourseId() + "," + course.getName() + "," + course.getPrice());
                }
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
}
