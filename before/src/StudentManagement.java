import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagement {
    static List<String> names = new ArrayList<>();
    static List<Integer> marks = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int unusedCounter = 0; // Intentional issue: unused variable
        boolean running = true;
        while (running) {
            System.out.println("\n1 Add student\n2 Average marks\n3 Highest marks\n4 Display students\n5 Search student\n0 Exit");
            String choice = sc.nextLine();
            if (choice.equals("1")) {
                add();
            } else if (choice.equals("2")) {
                average();
            } else if (choice.equals("3")) {
                highest();
            } else if (choice.equals("4")) {
                display();
            } else if (choice.equals("5")) {
                search();
            } else if (choice.equals("0")) {
                running = false;
            } else {
                System.out.println("Wrong option");
            }
        }
    }

    // Intentional quality issues: poor name, weak validation, hard-coded limit.
    static void add() {
        try {
            System.out.print("Student name: ");
            String n = sc.nextLine();
            System.out.print("Marks (0-100): ");
            int m = Integer.parseInt(sc.nextLine());
            if (m <= 100) {
                names.add(n);
                marks.add(m);
                System.out.println("Added");
            } else {
                System.out.println("Marks too high");
            }
        } catch (Exception e) { // Intentional issue: overly broad exception handling
            System.out.println("Error");
        }
    }

    static void average() {
        int total = 0;
        for (int i = 0; i < marks.size(); i++) total += marks.get(i);
        if (marks.size() > 0) System.out.println("Average: " + (total / marks.size()));
        else System.out.println("No data");
    }

    static void highest() {
        int max = 0;
        for (int i = 0; i < marks.size(); i++) {
            if (marks.get(i) > max) max = marks.get(i);
        }
        System.out.println("Highest: " + max);
    }

    // Intentional duplication: repeats similar output/loop logic.
    static void display() {
        if (names.size() == 0) {
            System.out.println("No students");
        } else {
            for (int i = 0; i < names.size(); i++) {
                System.out.println("Student " + i + ": " + names.get(i) + " - " + marks.get(i));
            }
        }
    }

    // Intentional poor method/variable naming and unvalidated input.
    static void search() {
        System.out.print("Name to search: ");
        String x = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).toLowerCase().equals(x.toLowerCase())) {
                System.out.println("Student " + i + ": " + names.get(i) + " - " + marks.get(i));
                found = true;
            }
        }
        if (!found) System.out.println("Not found");
    }
}
