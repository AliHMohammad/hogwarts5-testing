package dk.kea.dat3js.hogwarts5.ghosts;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/ghosts")
public class GhostController {

    private List<Ghost> ghosts = new ArrayList<>();

    public GhostController() {
        ghosts.add(new Ghost(1, "Nearly Headless Nick", "Sir Nicholas", "Gryffindor"));
        ghosts.add(new Ghost(2, "The Bloody Baron", null, "Slytherin"));
        ghosts.add(new Ghost(3, "The Grey Lady", "Helena Ravenclaw", "Ravenclaw"));
        ghosts.add(new Ghost(4, "The Fat Friar", "Friar", "Hufflepuff"));
    }

    @GetMapping
    public List<Ghost> getAllGhosts() {
        return ghosts;
    }

    @GetMapping("/{name}")
    public ResponseEntity<Ghost> getGhost(@PathVariable String name) {
        return ResponseEntity.of(ghosts.stream().filter(ghost -> ghost.getName().contains(name)).findFirst());
    }
}
