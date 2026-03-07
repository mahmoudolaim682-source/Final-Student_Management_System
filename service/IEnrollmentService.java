package service;

import model.Enrollment;
import model.Student;
import java.util.List;

public interface IEnrollmentService {
    void enrollStudentInCourse(int studentId, String courseCode);

    void unenrollStudentFromCourse(int studentId, String courseCode);

    void setGrade(int studentId, String courseCode, double grade);

    List<Student> getStudentsInCourse(String courseCode);

    List<Enrollment> getEnrollmentsForStudent(int studentId);

    double calculateStudentGPA(int studentId);

    String getSystemStatistics();

    List<String> getMostPopularCourses();
}
