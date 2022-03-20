package rest_tutorial.services;

import org.springframework.data.jpa.repository.JpaRepository;
import rest_tutorial.models.Response;
import rest_tutorial.models.Student;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @author Beksultan
 */
public interface StudentService {
    Response register(Student student);

    List<Student> findAllStudents();

    Student findById(UUID studentId);

    Response deleteById(UUID studentId);

    Response updateById(UUID studentId, Student newStudent);
}
