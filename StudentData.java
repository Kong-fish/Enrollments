import java.util.*;
import java.io.*;

public class StudentData {
    private ArrayList<Student> students;

    public StudentData(String filename) {
        students = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String dob = parts[2];
                String gender = parts[3];
                String phoneNumber = parts[4];
                String email = parts[5];
                String courseLevel = parts[6];
                students.add(new Student(id, name, dob, gender, phoneNumber, email, courseLevel));
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}
