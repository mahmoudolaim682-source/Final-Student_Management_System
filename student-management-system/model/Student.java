package model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private int id;
    private String name;
    private List<Enrollment> enrollments;

    public Student(int id, String name) {
        setId(id);
        setName(name);
        this.enrollments = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Error: ID must be a positive number");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Error: Name cannot be null or empty");
        }
        String trimmedName = name.trim();
        if (trimmedName.length() < 3 || trimmedName.length() > 50) {
            throw new IllegalArgumentException("Error: Name must be between (3-50) characters");
        }
        if (!trimmedName.matches("^[A-Za-z\\s]+$")) {
            throw new IllegalArgumentException("Error: Name must contain only English letters");
        }
        this.name = trimmedName;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void addEnrollment(Enrollment enrollment) {
        if (enrollment == null)
            throw new IllegalArgumentException("Enrollment cannot be null");
        this.enrollments.add(enrollment);
    }

    public void removeEnrollment(Course course) {
        enrollments.removeIf(e -> e.getCourse().equals(course));
    }

    public double getAverageGrade() {
        if (enrollments.isEmpty())
            return 0.0;

        double sum = 0;
        int count = 0;
        for (Enrollment e : enrollments) {
            if (e.getGrade() != null) {
                sum += e.getGrade();
                count++;
            }
        }
        return count == 0 ? 0.0 : sum / count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student ID: ").append(id)
                .append(" | Name: ").append(name)
                .append(" | Avg Grade: ").append(String.format("%.2f", getAverageGrade()))
                .append("\n");

        if (enrollments.isEmpty()) {
            sb.append("   [No enrolled courses]");
        } else {
            sb.append("   Courses: ");
            boolean first = true;
            for (Enrollment e : enrollments) {
                if (!first)
                    sb.append(", ");
                sb.append(e.getCourse().getCourseCode())
                        .append(" (").append(e.getGrade() == null ? "N/A" : e.getGrade()).append(")");
                first = false;
            }
        }
        return sb.toString();
    }
}
