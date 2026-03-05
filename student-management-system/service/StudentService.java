package service;

import model.Student;
import repository.StudentRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentService {
    private StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public void addStudent(int id, String name) {
        repository.add(new Student(id, name));
    }

    public void removeStudent(int id) {
        repository.remove(id);
    }

    public void updateStudentName(int id, String name) {
        Optional<Student> student = repository.findById(id);
        if (student.isPresent()) {
            student.get().setName(name);
        } else {
            throw new IllegalArgumentException("Student ID " + id + " not found");
        }
    }

    public Optional<Student> getStudentById(int id) {
        return repository.findById(id);
    }

    public List<Student> getAllStudents() {
        return repository.getAll();
    }

    public List<Student> searchByName(String query) {
        String lowerQuery = query.toLowerCase();
        return repository.getAll().stream()
                .filter(s -> s.getName().toLowerCase().contains(lowerQuery))
                .collect(Collectors.toList());
    }

    public void sortByName() {
        List<Student> students = repository.getAll();
        students.sort(Comparator.comparing(Student::getName));
        // Note: This would depend on whether the repository should store the sort state
        // In this simple in-memory version, we can just return a sorted list if needed
        // but here the USER logic seems to expect in-place or updated state view.
    }

    public void sortByGrade() {
        List<Student> students = repository.getAll();
        students.sort((s1, s2) -> Double.compare(s2.getAverageGrade(), s1.getAverageGrade()));
    }
}
