package main;

import model.Course;
import model.Enrollment;
import model.Student;
import repository.CourseRepository;
import repository.StudentRepository;
import service.CourseService;
import service.EnrollmentService;
import service.StudentService;
import util.DataManager;
import util.InputHelper;

import java.util.List;

public class Main {
    private static final String DATA_FILE = "school_data.csv";
    private static final StudentRepository studentRepository = new StudentRepository();
    private static final CourseRepository courseRepository = new CourseRepository();
    private static final StudentService studentService = new StudentService(studentRepository);
    private static final CourseService courseService = new CourseService(courseRepository);
    private static final EnrollmentService enrollmentService = new EnrollmentService(studentService, courseService);
    private static final DataManager dataManager = new DataManager();

    private static final String[] AVAILABLE_COURSES = {
            "Math", "Physics", "Chemistry", "Biology", "Computer Science"
    };

    public static void main(String[] args) {
        try {
            dataManager.loadDataFromFile(DATA_FILE, studentRepository, courseRepository);
            System.out.println("Data loaded successfully.");
        } catch (Exception e) {
            System.out.println("No existing data found or error loading data.");
        }

        while (true) {
            printMenu();
            int choice = InputHelper.readIntSafe("Choose an option: ");

            try {
                switch (choice) {
                    case 1 -> handleAddStudent();
                    case 2 -> handleViewStudents();
                    case 3 -> handleRemoveStudent();
                    case 4 -> handleUpdateStudent();
                    case 5 -> handleAddCourse();
                    case 6 -> handleUpdateCourse();
                    case 7 -> handleRemoveCourse();
                    case 8 -> handleViewCourses();
                    case 9 -> handleEnrollStudent();
                    case 10 -> handleRecordGrade();
                    case 11 -> handleSearchStudent();
                    case 12 -> handleSortStudents();
                    case 13 -> handleViewAllData();
                    case 14 -> handleUnenrollStudent();
                    case 15 -> handleViewCoursesOfStudent();
                    case 16 -> handleViewStudentsInCourse();
                    case 17 -> handleSearchCourse();
                    case 18 -> handleSortCourses();
                    case 0 -> {
                        handleExit();
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please select (0-18).");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Student Management System (Refactored) ---");
        System.out.println("1. Add Student                    2. View Students                  3. Remove Student");
        System.out.println("4. Update Student                 5. Add Course                     6. Update Course");
        System.out.println("7. Remove Course                  8. View Courses                   9. Enroll Student");
        System.out.println("10. Record Grade                  11. Search Student                12. Sort Students");
        System.out.println(
                "13. View All Data                 14. Unenroll Student              15. View Student Courses");
        System.out.println("16. View Students in Course       17. Search Course                 18. Sort Courses");
        System.out.println("0. Save and Exit");
    }

    private static void handleAddStudent() {
        int id = InputHelper.readIntSafe("Enter Student ID: ");
        String name = InputHelper.readString("Enter Name: ");
        studentService.addStudent(id, name);
        System.out.println("Success: Student added.");
    }

    private static void handleViewStudents() {
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty())
            System.out.println("No students found.");
        else
            students.forEach(System.out::println);
    }

    private static void handleRemoveStudent() {
        int id = InputHelper.readIntSafe("Enter ID to remove: ");
        studentService.removeStudent(id);
        System.out.println("Success: Student removed.");
    }

    private static void handleUpdateStudent() {
        int id = InputHelper.readIntSafe("Enter ID: ");
        String name = InputHelper.readString("Enter new Name: ");
        studentService.updateStudentName(id, name);
        System.out.println("Success: Updated.");
    }

    private static void handleAddCourse() {
        String code = InputHelper.readString("Enter Course Code: ");
        System.out.println("Available types: " + String.join(", ", AVAILABLE_COURSES));
        String name = InputHelper.readString("Enter Course Name: ");
        courseService.addCourse(code, name);
        System.out.println("Success: Course added.");
    }

    private static void handleUpdateCourse() {
        String code = InputHelper.readString("Enter Course Code: ");
        String name = InputHelper.readString("Enter new Name: ");
        courseService.updateCourseName(code, name);
        System.out.println("Success: Course updated.");
    }

    private static void handleRemoveCourse() {
        String code = InputHelper.readString("Enter Course Code: ");
        courseService.removeCourse(code);
        System.out.println("Success: Course removed.");
    }

    private static void handleViewCourses() {
        List<Course> courses = courseService.getAllCourses();
        if (courses.isEmpty())
            System.out.println("No courses found.");
        else
            courses.forEach(System.out::println);
    }

    private static void handleEnrollStudent() {
        int id = InputHelper.readIntSafe("Enter Student ID: ");
        String code = InputHelper.readString("Enter Course Code: ");
        enrollmentService.enrollStudentInCourse(id, code);
        System.out.println("Success: Enrolled.");
    }

    private static void handleRecordGrade() {
        int id = InputHelper.readIntSafe("Enter Student ID: ");
        String code = InputHelper.readString("Enter Course Code: ");
        double grade = InputHelper.readDoubleSafe("Enter Grade (0-100): ");
        enrollmentService.setGrade(id, code, grade);
        System.out.println("Success: Grade recorded.");
    }

    private static void handleSearchStudent() {
        String query = InputHelper.readString("Search Name: ");
        studentService.searchByName(query).forEach(System.out::println);
    }

    private static void handleSortStudents() {
        System.out.println("1. By Name  2. By Grade");
        int c = InputHelper.readIntSafe("Choice: ");
        if (c == 1)
            studentService.sortByName();
        else
            studentService.sortByGrade();
        handleViewStudents();
    }

    private static void handleViewAllData() {
        System.out.println("\n--- Data Report ---");
        handleViewCourses();
        handleViewStudents();
    }

    private static void handleUnenrollStudent() {
        int id = InputHelper.readIntSafe("Enter Student ID: ");
        String code = InputHelper.readString("Enter Course Code: ");
        enrollmentService.unenrollStudentFromCourse(id, code);
        System.out.println("Success: Unenrolled.");
    }

    private static void handleViewCoursesOfStudent() {
        int id = InputHelper.readIntSafe("Enter Student ID: ");
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsForStudent(id);
        if (enrollments.isEmpty()) {
            System.out.println("Student is not enrolled in any courses.");
        } else {
            System.out.println("Courses for Student:");
            enrollments.forEach(System.out::println);
        }
    }

    private static void handleViewStudentsInCourse() {
        String code = InputHelper.readString("Enter Course Code: ");
        List<Student> students = enrollmentService.getStudentsInCourse(code);
        if (students.isEmpty()) {
            System.out.println("No students enrolled in this course.");
        } else {
            System.out.println("Students in Course " + code + ":");
            students.forEach(System.out::println);
        }
    }

    private static void handleSearchCourse() {
        String query = InputHelper.readString("Enter Course Name or Code to search: ");
        List<Course> results = courseService.searchCourses(query);
        if (results.isEmpty()) {
            System.out.println("No courses found matching your search.");
        } else {
            results.forEach(System.out::println);
        }
    }

    private static void handleSortCourses() {
        courseService.sortByName();
        System.out.println("Courses sorted alphabetically.");
        handleViewCourses();
    }

    private static void handleExit() {
        try {
            dataManager.saveDataToFile(DATA_FILE, studentRepository, courseRepository);
            System.out.println("Data saved. Goodbye!");
        } catch (Exception e) {
            System.out.println("Save failed: " + e.getMessage());
        }
        InputHelper.close();
    }
}
