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
    private final StudentService studentService;

    public PrefectService(StudentRepository studentRepository, HouseRepository houseRepository, StudentService studentService) {
        this.studentRepository = studentRepository;
        this.studentService = studentService;
    }


    public StudentResponseDTO makeStudentPrefect(Integer studentId) throws BadRequestException {
        Student studentInDb = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student with id " + studentId + " not found"));

        List<Student> prefectsInHouse = studentRepository.findAllByHouseNameAndPrefectIsTrue(studentInDb.getHouse().getName());

        if (prefectsInHouse.size() >= 2)
            throw new BadRequestException("There already exists two students in the house");

        for (Student prefectStudent : prefectsInHouse) {
            if (prefectStudent.getGender() == studentInDb.getGender())
                throw new BadRequestException("There already exists a" + studentInDb.getGender().name().toLowerCase() + " prefect student in this house");
        }

        studentInDb.setPrefect(true);
        studentRepository.save(studentInDb);
        return studentService.toDTO(studentInDb);
    }

    public StudentResponseDTO getSinglePrefectByStudentId(Integer id) {
        Student studentInDb = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student with id " + id + " not found"));

        if (!studentInDb.getPrefect())
            throw new EntityNotFoundException("Student with id " + id + " is not prefect");

        return studentService.toDTO(studentInDb);
    }

    public List<StudentResponseDTO> getPrefects() {
        return studentRepository.findAllByPrefectIsTrue().stream().map(studentService::toDTO).toList();
    }

    public List<StudentResponseDTO> getPrefectsByHouseName(String houseName) {
        return studentRepository.findAllByHouseNameAndPrefectIsTrue(houseName).stream().map(studentService::toDTO).toList();
    }

    public StudentResponseDTO removeStudentPrefect(Integer studentId) {
        Student studentInDb = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student with id " + studentId + " not found"));

        studentInDb.setPrefect(false);

        studentRepository.save(studentInDb);
        return studentService.toDTO(studentInDb);
    }



}
