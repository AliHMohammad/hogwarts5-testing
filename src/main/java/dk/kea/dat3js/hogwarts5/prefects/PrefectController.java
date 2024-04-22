package dk.kea.dat3js.hogwarts5.prefects;

import dk.kea.dat3js.hogwarts5.students.StudentResponseDTO;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(path = "/prefects")
public class PrefectController {

    private final PrefectService prefectService;

    public PrefectController(PrefectService prefectService) {
        this.prefectService = prefectService;
    }

    @PostMapping("{id}")
    public ResponseEntity<StudentResponseDTO> makeStudentPrefect(@PathVariable("id") Integer studentId) throws BadRequestException {
        return ResponseEntity.ok(prefectService.makeStudentPrefect(studentId));
    }

    @GetMapping("{id}")
    public ResponseEntity<StudentResponseDTO> getSinglePrefectByStudentId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(prefectService.getSinglePrefectByStudentId(id));
    }

    @GetMapping
    public ResponseEntity<StudentResponseDTO> getPrefects() {
        return ResponseEntity.ok(prefectService.getPrefects());
    }

    @GetMapping("house/{houseId}")
    public ResponseEntity<StudentResponseDTO> getPrefectsByHouseName(@PathVariable("houseId") String houseName) {
        return ResponseEntity.ok(prefectService.getPrefectsByHouseName(houseName));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<StudentResponseDTO> removeStudentPrefect(@PathVariable("id") Integer studentId) {
        return ResponseEntity.ok(prefectService.removeStudentPrefect(studentId));
    }


}
