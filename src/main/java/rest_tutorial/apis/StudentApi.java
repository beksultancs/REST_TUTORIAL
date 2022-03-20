package rest_tutorial.apis;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rest_tutorial.exceptions.BadRequestException;
import rest_tutorial.exceptions.NotFoundException;
import rest_tutorial.models.Response;
import rest_tutorial.models.Student;
import rest_tutorial.services.StudentService;
import java.util.List;
import java.util.UUID;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author Beksultan
 */
@RestController
@RequestMapping("api/students")
@AllArgsConstructor
public class StudentApi {

    //dependency injection
    private final StudentService studentService;

    //methods {POST, DELETE, GET, PUT, PATCH} without logic

    @PostMapping("/register")
    public Response registerNewStudent(@RequestBody Student student) {
        return studentService.register(student);
    }

    @GetMapping
    public List<Student> findAllStudents() {
        return studentService.findAllStudents();
    }

    //localhost:8080/api/students/get/23
    @GetMapping("/get/{studentId}")
    public Student findById(@PathVariable UUID studentId) {
        return studentService.findById(studentId);
    }

    //localhost:8080/api/students/delete
    @DeleteMapping("/delete/{studentId}")
    public Response deleteById(@PathVariable UUID studentId) {
         return studentService.deleteById(studentId);
    }

    @PutMapping("/update/{studentId}")
    public Response updateById(@PathVariable UUID studentId,
                               @RequestBody Student student) {
        return studentService.updateById(studentId, student);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public Response handleNotFoundException(NotFoundException notFoundException) {
        return Response.builder()
                .httpStatus(NOT_FOUND)
                .message(notFoundException.getMessage())
                .build();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(BAD_REQUEST)
    public Response handleBadRequestException(BadRequestException badRequestException) {
        return Response.builder()
                .httpStatus(BAD_REQUEST)
                .message(badRequestException.getMessage())
                .build();
    }
}
