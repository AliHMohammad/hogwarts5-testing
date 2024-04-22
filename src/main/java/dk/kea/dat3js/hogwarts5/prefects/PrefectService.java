package dk.kea.dat3js.hogwarts5.prefects;

import dk.kea.dat3js.hogwarts5.students.StudentRepository;
import dk.kea.dat3js.hogwarts5.students.StudentResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class PrefectService {


    private final StudentRepository studentRepository;

    public PrefectService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public StudentResponseDTO makeStudentPrefect(PrefectRequestDTO prefectRequestDTO) {
        return null;
    }

    public StudentResponseDTO getSinglePrefectByStudentId(long id) {
        return null;
    }

    public StudentResponseDTO getPrefects() {
        return null;
    }

    public StudentResponseDTO getPrefectsByHouseId(long houseId) {
        return null;
    }

    public StudentResponseDTO removeStudentPrefect(long studentId) {
        return null;
    }


}
