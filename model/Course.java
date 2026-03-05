package model;

import java.util.Objects;

public class Course {
    private String courseCode;
    private String courseName;

    public Course(String courseCode, String courseName) {
        validateCode(courseCode);
        validateName(courseName);
        this.courseCode = courseCode.trim();
        this.courseName = courseName.trim().toLowerCase();
    }

    private void validateCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Error: Course code cannot be null or empty");
        }
        if (!code.matches("^[0-9]+$")) {
            throw new IllegalArgumentException("Error: Course code must contain only numbers");
        }
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty() || 
            !name.trim().toLowerCase().matches("^(math|physics|chemistry|biology|computer science)$")) {
            throw new IllegalArgumentException(
                "Error: Course name must be one of: (math, physics, chemistry, biology, computer science)");
        }
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        validateName(courseName);
        this.courseName = courseName.trim().toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseCode, course.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode);
    }

    @Override
    public String toString() {
        return String.format("Course [%s] - %s", courseCode, 
               courseName.substring(0, 1).toUpperCase() + courseName.substring(1));
    }
}
