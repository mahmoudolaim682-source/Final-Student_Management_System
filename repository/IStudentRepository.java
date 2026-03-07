package repository;

import model.Student;
import java.util.List;
import java.util.Optional;

public interface IStudentRepository {
    void add(Student student);

    void remove(int id);

    Optional<Student> findById(int id);

    List<Student> getAll();

    void clear();
}
