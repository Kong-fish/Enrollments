import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class StudentData {
    private ArrayList<Student> students;
    private String currentStudentName;

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

    public StudentData() {
        students = new ArrayList<>();
    }    

    public ArrayList<Student> getStudents() {
        return students;
    }

    public String[] getStudentNames() {
        String[] names = new String[students.size()];
        for (int i = 0; i < students.size(); i++) {
            names[i] = students.get(i).getName();
        }
        return names;
    }
    

    public Student getStudentByName(String name) {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return null;
    }

    public Student searchStudent(String query) {
        for (Student student : students) {
            if (student.getName().equals(query) || String.valueOf(student.getId()).equals(query)) {
                return student;
            }
        }
        return null;
    }

    public void updateStudentDataFile() {
        try (PrintWriter writer = new PrintWriter(new File("students.txt"))) {
            for (Student student : students) {
                writer.println(student.getId() + "," + student.getName() + "," + student.getDob() + "," + student.getGender() + "," + student.getPhoneNumber() + "," + student.getEmail() + "," + student.getCourseLevel() + "," + String.join(";", student.getEnrolledCourses().stream().map(Course::getCourseId).collect(Collectors.toList())));
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void saveStudent(Student student) {
        // Write the student's data to the students.txt file
        try {
            FileWriter writer = new FileWriter("students.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(student.toString());
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String getCurrentStudentName() {
        return currentStudentName;
    }

    public void setCurrentStudentName(String currentStudentName) {
        this.currentStudentName = currentStudentName;
    }
}
