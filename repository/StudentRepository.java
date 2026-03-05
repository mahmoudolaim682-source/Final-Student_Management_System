package repository;

import model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepository {
    private List<Student> students;

    public StudentRepository() {
        this.students = new ArrayList<>();
    }

    public void add(Student student) {
        if (student == null)
            throw new IllegalArgumentException("Cannot add a null student");
        if (findById(student.getId()).isPresent())
            throw new IllegalArgumentException("Student ID already exists: " + student.getId());
        students.add(student);
    }

    public void remove(int id) {
        boolean removed = students.removeIf(s -> s.getId() == id);
        if (!removed)
            throw new IllegalArgumentException("Student ID " + id + " not found");
    }

    public Optional<Student> findById(int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
    }

    public List<Student> getAll() {
        return new ArrayList<>(students);
    }

    public void clear() {
        students.clear();
    }
}
