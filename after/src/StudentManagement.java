import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagement {
    private static final int MAX_MARKS = 100;
    private static final int MIN_MARKS = 0;
    private static final Scanner INPUT = new Scanner(System.in);
    private static final List<Student> STUDENTS = new ArrayList<>();

    private static class Student {
        private final String name;
        private final int marks;

        private Student(String name, int marks) {
            this.name = name;
            this.marks = marks;
        }
    }

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = INPUT.nextLine().trim();
            switch (choice) {
                case "1" -> addStudent();
                case "2" -> printAverage();
                case "3" -> printHighestMarks();
                case "4" -> displayStudents();
                case "5" -> searchStudent();
                case "0" -> running = false;
                default -> System.out.println("Invalid option. Choose 0-5.");
            }
        }
        INPUT.close();
    }

    private static void printMenu() {
        System.out.println("\n1 Add student\n2 Average marks\n3 Highest marks\n4 Display students\n5 Search student\n0 Exit");
    }

    private static void addStudent() {
        System.out.print("Student name: ");
        String name = INPUT.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        System.out.print("Marks (0-100): ");
        String rawMarks = INPUT.nextLine().trim();
        final int marks;
        try {
            marks = Integer.parseInt(rawMarks);
        } catch (NumberFormatException exception) {
            System.out.println("Marks must be a whole number.");
            return;
        }

        if (marks < MIN_MARKS || marks > MAX_MARKS) {
            System.out.println("Marks must be between 0 and 100.");
            return;
        }
        STUDENTS.add(new Student(name, marks));
        System.out.println("Student added.");
    }

    private static void printAverage() {
        if (STUDENTS.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        int total = 0;
        for (Student student : STUDENTS) {
            total += student.marks;
        }
        System.out.printf("Average: %.2f%n", (double) total / STUDENTS.size());
    }

    private static void printHighestMarks() {
        if (STUDENTS.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        int highest = MIN_MARKS;
        for (Student student : STUDENTS) {
            highest = Math.max(highest, student.marks);
        }
        System.out.println("Highest marks: " + highest);
    }

    private static void displayStudents() {
        if (STUDENTS.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        for (int index = 0; index < STUDENTS.size(); index++) {
            printStudent(index, STUDENTS.get(index));
        }
    }

    private static void searchStudent() {
        System.out.print("Name to search: ");
        String searchName = INPUT.nextLine().trim();
        boolean found = false;
        for (int index = 0; index < STUDENTS.size(); index++) {
            Student student = STUDENTS.get(index);
            if (student.name.equalsIgnoreCase(searchName)) {
                printStudent(index, student);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching student found.");
        }
    }

    private static void printStudent(int index, Student student) {
        System.out.printf("Student %d: %s - %d%n", index, student.name, student.marks);
    }
}
