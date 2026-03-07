package repository;

import model.Course;
import java.util.List;
import java.util.Optional;

public interface ICourseRepository {
    void add(Course course);

    void remove(String code);

    Optional<Course> findByCode(String code);

    List<Course> getAll();

    void clear();
}
