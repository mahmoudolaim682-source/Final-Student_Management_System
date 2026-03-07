package service;

import model.Student;
import java.util.List;
import java.util.Optional;

public interface IStudentService {
    void addStudent(int id, String name);

    void removeStudent(int id);

    void updateStudentName(int id, String name);

    Optional<Student> getStudentById(int id);

    List<Student> getAllStudents();

    List<Student> searchByName(String query);

    void sortByName();

    void sortByGrade();

    List<Student> getTopStudents(int n);

    List<Student> getFailedStudents();
}
