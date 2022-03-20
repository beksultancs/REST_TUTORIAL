package rest_tutorial.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rest_tutorial.exceptions.BadRequestException;
import rest_tutorial.exceptions.NotFoundException;
import rest_tutorial.models.Response;
import rest_tutorial.models.Student;
import rest_tutorial.repositories.StudentRepository;
import rest_tutorial.services.StudentService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

/**
 * @author Beksultan
 */
@Service
@AllArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Response register(Student student) {
        String email = student.getEmail();

        checkEmail(email);

        Student savedStudent
                = studentRepository.save(student);

        log.info("student with email = {} has sucessfully saved to database", savedStudent.getEmail());

        return Response.builder()
                .httpStatus(CREATED)
                .message(String.format("student with email = %s successfully registered",
                        savedStudent.getEmail()))
                .build();
    }

    private void checkEmail(String email) {
        boolean exists = studentRepository.existsByEmail(email);

        if (exists) {
            log.warn("student with email = {} already exists", email);
            throw new BadRequestException(
                    "student with email = " + email + " already exists"
            );
        }
    }

    @Override
    public List<Student> findAllStudents() {
        List<Student> allStudents = studentRepository.findAll();
        log.info("founded {} students", allStudents.size());
        return allStudents;
    }

    @Override
    public Student findById(UUID studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> {
                    log.error("student with id = {} does not exists", studentId);
                    throw new NotFoundException(
                            String.format("student with id = %s does not exists", studentId)
                    );
                });

        log.info("founded student with id = {}", studentId);

        return student;
    }

    @Override
    public Response deleteById(UUID studentId) {
        boolean exists = studentRepository.existsById(studentId);

        if (!exists) {
            log.error("student with id = {} does not exists, you can't delete it", studentId);
            throw new BadRequestException(
                    String.format("student with id = %s does not exists, you can't delete it", studentId)
            );
        }

        studentRepository.deleteById(studentId);

        log.info("Student with id = {} has successfully deleted", studentId);

        String message = String.format("Student with id = %s has successfully deleted", studentId);

        return Response.builder()
                .httpStatus(OK)
                .message(message)
                .build();
    }

    @Override
    @Transactional
    public Response updateById(UUID studentId, Student newStudent) {
        Student student = findById(studentId);

        //fullName
        String currentFullName = student.getFullName();
        String newFullName = newStudent.getFullName();

        if (!Objects.equals(currentFullName, newFullName)) {
            student.setFullName(newFullName);
            log.info("Student with id = {} changed full name from {} to {}",
                    studentId, currentFullName, newFullName);
        }

        //email
        String currentEmail = student.getEmail();
        String newEmail = newStudent.getEmail();

        if (!currentEmail.equals(newEmail)) {
            checkEmail(newEmail);
            student.setEmail(newEmail);
            log.info("Student with id = {} changed email from {} to {}",
                    studentId, currentEmail, newEmail);
        }

        //age
        int currentAge = student.getAge();
        int newAge = newStudent.getAge();

        if (currentAge != newAge) {
            student.setAge(newAge);
            log.info("Student with id = {} changed age from {} to {}",
                    studentId, currentAge, newAge);
        }

        String message = String.format("Student with studentId = %s has successfully updated", studentId);

        return Response.builder()
                .httpStatus(RESET_CONTENT)
                .message(message)
                .build();
    }
}
