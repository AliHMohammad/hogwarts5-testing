package dk.kea.dat3js.hogwarts5.students;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class StudentControllerIntegrationTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    void notNull() {
        assertNotNull(webClient);
    }


    @Test
    void createStudentWithFullName() {
        webClient
                .post().uri("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new StudentRequestDTO(
                        0,
                        "Harry",
                        "James",
                        "Potter",
                        "Harry James Potter",
                        "Gryffindor",
                        7,
                        false,
                        null
                ))
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(StudentResponseDTO.class)
                .value(response -> {
                    assertNotNull(response.id());
                    assertEquals("Harry James Potter", response.fullName());
                    assertEquals(7, response.schoolYear());
                    assertEquals("Gryffindor", response.house());
                    assertEquals("Harry", response.firstName());
                    assertEquals("James", response.middleName());
                    assertEquals("Potter", response.lastName());
                });

    }


    @Test
    void createStudentWithFullNameJSON() {
        webClient
                .post().uri("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new StudentRequestDTO(
                        0,
                        "Peter",
                        "Heronimous",
                        "Lind",
                        "Peter Heronimous Lind",
                        "Gryffindor",
                        7,
                        false,
                        null
                ))
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody().json("""
                    {
                        "firstName": "Peter",
                        "middleName": "Heronimous",
                        "lastName": "Lind",
                        "fullName": "Peter Heronimous Lind",
                        "house": "Gryffindor",
                        "schoolYear": 7
                    }
                """)
                .jsonPath("$.id").exists();
    }
}

