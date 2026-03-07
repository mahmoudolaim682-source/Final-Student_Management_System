package model;

public class Enrollment {
    private Student student;
    private Course course;
    private Double grade;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.grade = null;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        if (grade != null && (grade < 0.0 || grade > 100.0)) {
            throw new IllegalArgumentException("Error: Grade must be between (0-100)");
        }
        this.grade = grade;
    }

    @Override
    public String toString() {
        return String.format("Enrollment [Student: %s, Course: %s, Grade: %s]",
                student.getName(), course.getCourseName(), (grade == null ? "N/A" : grade));
    }
}
