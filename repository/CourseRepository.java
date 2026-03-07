package repository;

import model.Course;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepository implements ICourseRepository {
    private List<Course> courses;

    public CourseRepository() {
        this.courses = new ArrayList<>();
    }

    public void add(Course course) {
        if (course == null)
            throw new IllegalArgumentException("Cannot add a null course");
        if (findByCode(course.getCourseCode()).isPresent())
            throw new IllegalArgumentException("Course code already exists: " + course.getCourseCode());
        courses.add(course);
    }

    public void remove(String code) {
        boolean removed = courses.removeIf(c -> c.getCourseCode().equalsIgnoreCase(code));
        if (!removed)
            throw new IllegalArgumentException("Course code " + code + " not found");
    }

    public Optional<Course> findByCode(String code) {
        return courses.stream()
                .filter(c -> c.getCourseCode().equalsIgnoreCase(code))
                .findFirst();
    }

    public List<Course> getAll() {
        return new ArrayList<>(courses);
    }

    public void clear() {
        courses.clear();
    }
}
