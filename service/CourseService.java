package service;

import model.Course;
import repository.CourseRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourseService {
    private CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public void addCourse(String code, String name) {
        repository.add(new Course(code, name));
    }

    public void removeCourse(String code) {
        repository.remove(code);
    }

    public void updateCourseName(String code, String name) {
        Optional<Course> course = repository.findByCode(code);
        if (course.isPresent()) {
            course.get().setCourseName(name);
        } else {
            throw new IllegalArgumentException("Course code " + code + " not found");
        }
    }

    public Optional<Course> getCourseByCode(String code) {
        return repository.findByCode(code);
    }

    public List<Course> getAllCourses() {
        return repository.getAll();
    }

    public List<Course> searchCourses(String query) {
        if (query == null || query.trim().isEmpty())
            return List.of();
        String lowerQuery = query.toLowerCase();
        return repository.getAll().stream()
                .filter(c -> c.getCourseName().toLowerCase().contains(lowerQuery) ||
                        c.getCourseCode().toLowerCase().contains(lowerQuery))
                .collect(Collectors.toList());
    }

    public void sortByName() {
        List<Course> courses = repository.getAll();
        courses.sort(Comparator.comparing(Course::getCourseName));
    }
}
