package service;

import model.Course;
import model.Enrollment;
import model.Student;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EnrollmentService implements IEnrollmentService {
    private IStudentService studentService;
    private ICourseService courseService;

    public EnrollmentService(IStudentService studentService, ICourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public void enrollStudentInCourse(int studentId, String courseCode) {
        Optional<Student> student = studentService.getStudentById(studentId);
        Optional<Course> course = courseService.getCourseByCode(courseCode);

        if (student.isPresent() && course.isPresent()) {
            // Check if already enrolled
            boolean alreadyEnrolled = student.get().getEnrollments().stream()
                    .anyMatch(e -> e.getCourse().equals(course.get()));

            if (alreadyEnrolled) {
                throw new IllegalArgumentException("Student " + studentId + " is already enrolled in " + courseCode);
            }

            Enrollment enrollment = new Enrollment(student.get(), course.get());
            student.get().addEnrollment(enrollment);
        } else if (student.isEmpty()) {
            throw new IllegalArgumentException("Student ID " + studentId + " not found.");
        } else {
            throw new IllegalArgumentException("Course code " + courseCode + " not found.");
        }
    }

    public void unenrollStudentFromCourse(int studentId, String courseCode) {
        Optional<Student> student = studentService.getStudentById(studentId);
        Optional<Course> course = courseService.getCourseByCode(courseCode);

        if (student.isPresent() && course.isPresent()) {
            student.get().removeEnrollment(course.get());
        } else if (student.isEmpty()) {
            throw new IllegalArgumentException("Student ID " + studentId + " not found.");
        } else {
            throw new IllegalArgumentException("Course code " + courseCode + " not found.");
        }
    }

    public void setGrade(int studentId, String courseCode, double grade) {
        Optional<Student> student = studentService.getStudentById(studentId);
        Optional<Course> course = courseService.getCourseByCode(courseCode);

        if (student.isPresent() && course.isPresent()) {
            Optional<Enrollment> enrollment = student.get().getEnrollments().stream()
                    .filter(e -> e.getCourse().equals(course.get()))
                    .findFirst();

            if (enrollment.isPresent()) {
                enrollment.get().setGrade(grade);
            } else {
                throw new IllegalArgumentException("Student is not enrolled in this course.");
            }
        } else if (student.isEmpty()) {
            throw new IllegalArgumentException("Student ID " + studentId + " not found.");
        } else {
            throw new IllegalArgumentException("Course code " + courseCode + " not found.");
        }
    }

    public List<Student> getStudentsInCourse(String courseCode) {
        Optional<Course> course = courseService.getCourseByCode(courseCode);
        if (course.isEmpty())
            throw new IllegalArgumentException("Course not found.");

        return studentService.getAllStudents().stream()
                .filter(s -> s.getEnrollments().stream().anyMatch(e -> e.getCourse().equals(course.get())))
                .collect(Collectors.toList());
    }

    public List<Enrollment> getEnrollmentsForStudent(int studentId) {
        Optional<Student> student = studentService.getStudentById(studentId);
        if (student.isEmpty())
            throw new IllegalArgumentException("Student not found.");
        return student.get().getEnrollments();
    }

    public double calculateStudentGPA(int studentId) {
        Optional<Student> student = studentService.getStudentById(studentId);
        if (student.isEmpty())
            throw new IllegalArgumentException("Student not found.");
        return student.get().getAverageGrade();
    }

    public String getSystemStatistics() {
        int totalStudents = studentService.getAllStudents().size();
        int totalCourses = courseService.getAllCourses().size();
        int totalEnrollments = (int) studentService.getAllStudents().stream()
                .flatMap(s -> s.getEnrollments().stream())
                .count();
        double avgGPA = studentService.getAllStudents().stream()
                .mapToDouble(Student::getAverageGrade)
                .average()
                .orElse(0.0);

        return String.format("""
                --- System Statistics ---
                Total Students:    %d
                Total Courses:     %d
                Total Enrollments: %d
                System Avg GPA:    %.2f
                """, totalStudents, totalCourses, totalEnrollments, avgGPA);
    }

    public List<String> getMostPopularCourses() {
        return courseService.getAllCourses().stream()
                .collect(Collectors.toMap(
                        c -> c.getCourseName() + " (" + c.getCourseCode() + ")",
                        c -> getStudentsInCourse(c.getCourseCode()).size()))
                .entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .map(e -> e.getKey() + " - " + e.getValue() + " students")
                .collect(Collectors.toList());
    }
}
