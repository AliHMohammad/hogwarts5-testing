package dk.kea.dat3js.hogwarts5.prefects;

import dk.kea.dat3js.hogwarts5.house.House;
import dk.kea.dat3js.hogwarts5.house.HouseRepository;
import dk.kea.dat3js.hogwarts5.students.*;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class PrefectServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private PrefectService prefectService;


    @Test
    void setStudentPrefectWhenHouseHasTwoPrefects() throws BadRequestException {
        // Arrange
        House house = new House(
                "Gryffindor",
                null,
                new String[]{"Red", "Blue"}
        );
        Student student = new Student(
                "first",
                "middle",
                "last",
                house,
                6,
                false,
                Gender.MALE.name()
        );
        student.setId(1);

        Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        Mockito.when(studentRepository.findAllByHouseNameAndPrefectIsTrue(student.getHouse().getName())).thenReturn(List.of(new Student(), new Student()));

        // Act
        // Der er ingen Act. I Assert der kalder vi og asserter på samme tid et throw.

        // Assert
        assertThrows(BadRequestException.class, () -> prefectService.makeStudentPrefect(student.getId()));
    }

    @Test
    void setStudentPrefectWhenHouseHasLessThanTwoPrefects() throws BadRequestException {
        // Arrange
        House house = new House(
                "Gryffindor",
                null,
                new String[]{"Red", "Blue"}
        );
        Student student = new Student(
                "first",
                "middle",
                "last",
                house,
                6,
                false,
                Gender.MALE.name()
        );
        student.setId(1);

        Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        Mockito.when(studentRepository.findAllByHouseNameAndPrefectIsTrue(student.getHouse().getName())).thenReturn(List.of(new Student()));
        Mockito.when(studentService.toDTO(any())).thenReturn(new StudentResponseDTO(
                1,
                null,
                null,
                null,
                null,
                null,
                null,
                true,
                null
        ));

        // Act
        StudentResponseDTO studentResponseDTO = prefectService.makeStudentPrefect(student.getId());

        // Assert
        assertTrue(studentResponseDTO.prefect());
    }



    @Test
    void setMaleStudentPrefectWhenHouseAlreadyHasMalePrefect() {
        // Arrange
        House house = new House(
                "Gryffindor",
                null,
                new String[]{"Red", "Blue"}
        );
        Student student = new Student(
                "first",
                "middle",
                "last",
                house,
                0,
                false,
                Gender.MALE.name()
        );
        student.setId(1);

        Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        Mockito.when(studentRepository.findAllByHouseNameAndPrefectIsTrue(student.getHouse().getName())).thenReturn(List.of(
                new Student(
                        "f",
                        "m",
                        "l",
                        house,
                        0,
                        false,
                        Gender.MALE.name()
                )
        ));

        // Act
        // Der er ingen Act. I Assert der kalder vi og asserter på samme tid et throw.

        // Assert
        assertThrows(BadRequestException.class, () -> prefectService.makeStudentPrefect(student.getId()));
    }


    @Test
    void setFemaleStudentPrefectWhenHouseAlreadyHasMalePrefect() throws BadRequestException {
        // Arrange
        House house = new House(
                "Gryffindor",
                null,
                new String[]{"Red", "Blue"}
        );
        Student student = new Student(
                "first",
                "middle",
                "last",
                house,
                0,
                false,
                Gender.FEMALE.name()
        );
        student.setId(1);

        Mockito.when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        Mockito.when(studentRepository.findAllByHouseNameAndPrefectIsTrue(student.getHouse().getName())).thenReturn(List.of(
                new Student(
                        "f",
                        "m",
                        "l",
                        house,
                        0,
                        false,
                        Gender.MALE.name()
                )
        ));
        Mockito.when(studentService.toDTO(any())).thenReturn(new StudentResponseDTO(
                1,
                null,
                null,
                null,
                null,
                null,
                null,
                true,
                Gender.FEMALE.name()
        ));

        // Act
        StudentResponseDTO studentResponseDTO = prefectService.makeStudentPrefect(student.getId());

        // Assert
        assertTrue(studentResponseDTO.prefect());
    }

}