package dk.kea.dat3js.hogwarts5.ghosts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GhostController.class)
class GhostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getGhostByName() throws Exception {
        mockMvc.perform(get("/ghosts/Nearly Headless Nick"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Nearly Headless Nick"))
                .andExpect(jsonPath("$.house").value("Gryffindor"))
                .andExpect(jsonPath("$.realName").value("Sir Nicholas"));
    }

    @Test
    void getAllGhosts() throws Exception {
        mockMvc.perform(get("/ghosts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].name").value("Nearly Headless Nick"))
                .andExpect(jsonPath("$[1].name").value("The Bloody Baron"))
                .andExpect(jsonPath("$[2].name").value("The Grey Lady"))
                .andExpect(jsonPath("$[3].name").value("The Fat Friar"));
    }
}