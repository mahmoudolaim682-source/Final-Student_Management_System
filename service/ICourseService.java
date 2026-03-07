package service;

import model.Course;
import java.util.List;
import java.util.Optional;

public interface ICourseService {
    void addCourse(String code, String name);

    void removeCourse(String code);

    void updateCourseName(String code, String name);

    Optional<Course> getCourseByCode(String code);

    List<Course> getAllCourses();

    List<Course> searchCourses(String query);

    void sortByName();
}
