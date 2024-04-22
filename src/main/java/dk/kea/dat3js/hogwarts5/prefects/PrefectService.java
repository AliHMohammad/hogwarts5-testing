package dk.kea.dat3js.hogwarts5.prefects;

import dk.kea.dat3js.hogwarts5.house.HouseRepository;
import dk.kea.dat3js.hogwarts5.students.Student;
import dk.kea.dat3js.hogwarts5.students.StudentRepository;
import dk.kea.dat3js.hogwarts5.students.StudentResponseDTO;
import dk.kea.dat3js.hogwarts5.students.StudentService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrefectService {


    private final StudentRepository studentRepository;
    private final HouseRepository houseRepository;
    private final StudentService studentService;

    public PrefectService(StudentRepository studentRepository, HouseRepository houseRepository, StudentService studentService) {
        this.studentRepository = studentRepository;
        this.houseRepository = houseRepository;
        this.studentService = studentService;
    }


    public StudentResponseDTO makeStudentPrefect(Integer studentId) throws BadRequestException {
        Student studentInDb = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student with id " + studentId + " not found"));

        List<Student> prefectsInHouse = studentRepository.findAllByHouseNameAndPrefectIsTrue(studentInDb.getHouse().getName());

        if (prefectsInHouse.size() >= 2)
            throw new BadRequestException("There already exists two students in the house");

        studentInDb.setPrefect(true);
        studentRepository.save(studentInDb);
        return studentService.toDTO(studentInDb);
    }

    public StudentResponseDTO getSinglePrefectByStudentId(Integer id) {
        return null;
    }

    public StudentResponseDTO getPrefects() {
        return null;
    }

    public StudentResponseDTO getPrefectsByHouseName(String houseName) {
        return null;
    }

    public StudentResponseDTO removeStudentPrefect(Integer studentId) {
        return null;
    }



}
