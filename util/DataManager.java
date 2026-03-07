package util;

import model.Course;
import model.Enrollment;
import model.Student;
import repository.ICourseRepository;
import repository.IStudentRepository;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DataManager {

    public void saveDataToFile(String filename, IStudentRepository sRepo, ICourseRepository cRepo) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Course c : cRepo.getAll()) {
                writer.printf("COURSE;%s;%s%n", c.getCourseCode(), c.getCourseName());
            }
            for (Student s : sRepo.getAll()) {
                StringBuilder sb = new StringBuilder();
                sb.append("STUDENT;").append(s.getId()).append(";").append(s.getName());
                List<Enrollment> enrollments = s.getEnrollments();
                if (!enrollments.isEmpty()) {
                    sb.append(";");
                    String coursesStr = enrollments.stream()
                            .map(e -> e.getCourse().getCourseCode() + ":"
                                    + (e.getGrade() == null ? "null" : e.getGrade()))
                            .collect(Collectors.joining(","));
                    sb.append(coursesStr);
                }
                writer.println(sb.toString());
            }
        }
    }

    public void loadDataFromFile(String filename, IStudentRepository sRepo, ICourseRepository cRepo)
            throws IOException {
        File file = new File(filename);
        if (!file.exists())
            return;
        sRepo.clear();
        cRepo.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts[0].equals("COURSE")) {
                    cRepo.add(new Course(parts[1], parts[2]));
                } else if (parts[0].equals("STUDENT")) {
                    Student student = new Student(Integer.parseInt(parts[1]), parts[2]);
                    if (parts.length == 4) {
                        for (String cg : parts[3].split(",")) {
                            String[] cgParts = cg.split(":");
                            Optional<Course> c = cRepo.findByCode(cgParts[0]);
                            if (c.isPresent()) {
                                Enrollment enrollment = new Enrollment(student, c.get());
                                if (!cgParts[1].equals("null")) {
                                    enrollment.setGrade(Double.parseDouble(cgParts[1]));
                                }
                                student.addEnrollment(enrollment);
                            }
                        }
                    }
                    sRepo.add(student);
                }
            }
        }
    }
}
