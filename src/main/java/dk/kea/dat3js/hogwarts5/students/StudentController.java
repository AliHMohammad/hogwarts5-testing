package dk.kea.dat3js.hogwarts5.students;

import dk.kea.dat3js.hogwarts5.prefects.PrefectService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final PrefectService prefectService;

    public StudentController(StudentService studentService, PrefectService prefectService) {
        this.studentService = studentService;
        this.prefectService = prefectService;
    }

    // get all students
    @GetMapping
    public List<StudentResponseDTO> getAllStudents() {
        return studentService.findAll();
    }

    // get student by id
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudent(@PathVariable int id) {
        return ResponseEntity.of(studentService.findById(id));
    }

    // create post, put, patch, delete methods
    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@RequestBody StudentRequestDTO student) {
        //return new ResponseEntity<>(studentService.save(student), HttpStatus.CREATED);
        return ResponseEntity.status(201).body(studentService.save(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable int id, @RequestBody StudentRequestDTO student) {
        return ResponseEntity.of(studentService.updateIfExists(id, student));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> partialUpdateStudent(@PathVariable int id, @RequestBody StudentRequestDTO student) {
        return ResponseEntity.of(studentService.partialUpdate(id, student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> deleteStudent(@PathVariable int id) {
        return ResponseEntity.of(studentService.deleteById(id));
    }

    @DeleteMapping("{id}/prefect")
    public ResponseEntity<StudentResponseDTO> removeStudentPrefect(@PathVariable("id") Integer studentId) {
        return ResponseEntity.ok(prefectService.removeStudentPrefect(studentId));
    }

    @PostMapping("{id}/prefect")
    public ResponseEntity<StudentResponseDTO> makeStudentPrefect(@PathVariable("id") Integer studentId) throws BadRequestException {
        return ResponseEntity.ok(prefectService.makeStudentPrefect(studentId));
    }
}
