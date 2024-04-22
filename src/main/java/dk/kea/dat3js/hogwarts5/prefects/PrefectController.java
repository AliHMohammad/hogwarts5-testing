package dk.kea.dat3js.hogwarts5.prefects;

import dk.kea.dat3js.hogwarts5.students.StudentResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(path = "/prefects")
public class PrefectController {

    private final PrefectService prefectService;

    public PrefectController(PrefectService prefectService) {
        this.prefectService = prefectService;
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> makeStudentPrefect(@RequestBody PrefectRequestDTO prefectRequestDTO) {
        return ResponseEntity.ok(prefectService.makeStudentPrefect(prefectRequestDTO));
    }

    @GetMapping("{id}")
    public ResponseEntity<StudentResponseDTO> getSinglePrefectByStudentId(@PathVariable("id") long id) {
        return ResponseEntity.ok(prefectService.getSinglePrefectByStudentId(id));
    }

    @GetMapping
    public ResponseEntity<StudentResponseDTO> getPrefects() {
        return ResponseEntity.ok(prefectService.getPrefects());
    }

    @GetMapping("house/{houseId}")
    public ResponseEntity<StudentResponseDTO> getPrefectsByHouseId(@PathVariable("houseId") long houseId) {
        return ResponseEntity.ok(prefectService.getPrefectsByHouseId(houseId));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<StudentResponseDTO> removeStudentPrefect(@PathVariable("id") long studentId) {
        return ResponseEntity.ok(prefectService.removeStudentPrefect(studentId));
    }


}
