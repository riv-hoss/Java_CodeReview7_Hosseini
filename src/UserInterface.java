import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class UserInterface {

    private static DBAccess dbAccess;
    public UserInterface() {
        //this.dbAccess = dbAccess;
    }

    public void displayMenu() {
        String hLine = "\n+" + "-".repeat(31) + "+";
        String welcome = "\n|\t\t\t Welcome \t\t\t|";
        String toThe = "\n|\t\t\t to the \t\t\t|";
        String zName = "\n|\t\t\t SCHL \t\t\t\t|";
        System.out.printf(hLine + welcome + toThe + zName + hLine + "%n");


        boolean stop = false;
        while (!stop) {

            System.out.println("1) Display all students.");
            System.out.println("2) Display all teachers.");
            System.out.println("3) Display all classes.");
            System.out.println("4) Display classes of a specific teacher.");
            System.out.println("0) EXIT\n");

            System.out.println(" Enter your choice: ");
            Scanner scan = new Scanner(System.in);
            int inputMenu = Integer.parseInt(scan.next());

            switch (inputMenu) {

                case 0:
                    System.out.println("Thanks! BYE BYE!");
                    stop = true;
                    break;
                case 1:
                    init();
                    List<Student> students = new ArrayList<>();
                    try {
                        displayStudents(dbAccess.getStudentsList());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        stop();
                    }

                case 2:
                    init();
                    List<Teacher> teachers = new ArrayList<>();
                    try {
                        displayTeachers(dbAccess.getTeachersList());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        stop();
                    }

                case 3:
                    init();
                    List<Class> classes = new ArrayList<>();
                    try {
                        displayClasses(dbAccess.getClassList());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        stop();
                    }

                case 4:
                    System.out.println("\n Enter a teacher ID: ");
                    int teacherID = Integer.parseInt(scan.next());
                    dbAccess.displayTeacherClasses(teacherID);

                    break;
            }
        }
    }

    static public void init() {
        try {
            dbAccess = new DBAccess();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static public void stop() {
        try {
            dbAccess.closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public void displayStudents(List<Student> students) {
        System.out.printf("%-10s %-20s %-20s %n", "id", "name", "email");
        System.out.println("-".repeat(60));
        for (Student std : students) {
            String format = String.format("%n%-10d %-20s %-20s",
                    std.getId(), std.getFullName(), std.getEmail());
            System.out.println(format);
        }
    }

    static public void displayTeachers(List<Teacher> teachers) {
        System.out.printf("%-10s %-20s %-20s %n", "id", "name", "email");
        System.out.println("-".repeat(60));
        for (Teacher tch : teachers) {
            String format = String.format("%n%-10d %-20s %-20s",
                    tch.getId(), tch.getFullName(), tch.getEmail());
            System.out.println(format);
        }
    }


    static public void displayClasses (List<Class> classes) {
        System.out.printf("%-10s %-20s %n", "id", "name");
        System.out.println("-".repeat(30));
        for (Class cls : classes) {
            String format = String.format("%n%-10d %-20s",
                    cls.getId(), cls.getName());
            System.out.println(format);
        }
    }



}
