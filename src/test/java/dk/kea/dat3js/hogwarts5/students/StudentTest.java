package dk.kea.dat3js.hogwarts5.students;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


class StudentTest {

    @Test
    void getFullNameWithMiddleName() {
        // Arrance
        Student student = new Student(
                "Ali",
                "Haider",
                "Mohammad",
                null,
                0
        );

        // Act
        String fullName = student.getFullName();

        // Assert
        assertEquals("Ali Haider Mohammad", fullName);
    }

    @Test
    void getFullNameWithoutMiddleName() {
        // Arrange
        Student student = new Student(
                "Ali",
                "Mohammad",
                null,
                0
        );

        // Act
        String fullName = student.getFullName();

        // Assert
        assertEquals("Ali Mohammad", fullName);
    }


    @Test
    void setFullNameWithMiddleName() {
        // Arrange
        Student student = new Student(
                "first",
                "middle",
                "last",
                null,
                0
        );

        String fullName = "Harry James Potter";

        // Act
        student.setFullName(fullName);

        // Assert
        assertEquals("Harry", student.getFirstName());
        assertEquals("James", student.getMiddleName());
        assertEquals("Potter", student.getLastName());

    }

    @Test
    void setFullNameWithoutMiddleName() {
        // Arrange
        Student student = new Student(
                "first",
                "middle",
                "last",
                null,
                0
        );

        String fullName = "Harry Potter";

        // Act
        student.setFullName(fullName);

        // Assert
        assertEquals("Harry", student.getFirstName());
        assertEquals("Potter", student.getLastName());

        assertNull(student.getMiddleName());
    }


    @Test
    void setFullNameWithEmptyString() {
        // Arrange
        Student student = new Student(
                "first",
                "middle",
                "last",
                null,
                0
        );

        String fullName = "";

        // Act
        student.setFullName(fullName);

        // Assert
        assertEquals("", student.getFirstName());

        assertNull(student.getLastName());
        assertNull(student.getMiddleName());
    }

    @Test
    void setFullNameWithNull() {
        // Arrange
        Student student = new Student(
                "first",
                "middle",
                "last",
                null,
                0
        );

        // Act
        student.setFullName(null);

        // Assert
        assertEquals("first", student.getFirstName());
        assertEquals("middle", student.getMiddleName());
        assertEquals("last", student.getLastName());
    }

    @Test
    void setFullNameWithMultipleMiddleNames() {
        // Arrange
        Student student = new Student(
                "first",
                "middle",
                "last",
                null,
                0
        );

        // Act
        student.setFullName("Ali Abed Al Amier");

        // Assert
        assertEquals("Ali", student.getFirstName());
        assertEquals("Abed Al", student.getMiddleName());
        assertEquals("Amier", student.getLastName());
    }

    @Test
    void setFullNameWithOnlyFirstName() {
        // Arrange
        Student student = new Student(
                "first",
                "middle",
                "last",
                null,
                0
        );

        // Act
        student.setFullName("Ali");

        // Assert
        assertEquals("Ali", student.getFirstName());
        assertNull(student.getMiddleName());
        assertNull(student.getLastName());
    }

    @Test
    void capitalizeIndividualNames() {
        // Arrange
        Student student = new Student(
                "first",
                "middle",
                "last",
                null,
                0
        );

        // Act
        student.setFirstName("harry");
        student.setMiddleName("james");
        student.setLastName("potter");

        // Assert
        assertEquals("Harry", student.getFirstName());
        assertEquals("James", student.getMiddleName());
        assertEquals("Potter", student.getLastName());
    }

    @Test
    void capitalizeIndividualNamesWithCrazyCapitalization() {
        // Arrange
        Student student = new Student(
                "first",
                "middle",
                "last",
                null,
                0
        );

        // Act
        student.setFirstName("haRrY");
        student.setMiddleName("jAMeS");
        student.setLastName("PoTteR");

        // Assert
        assertEquals("Harry", student.getFirstName());
        assertEquals("James", student.getMiddleName());
        assertEquals("Potter", student.getLastName());
    }

    @Test
    void capitalizeIndividualNamesWithOnlyOneLetter() {
        // Arrange
        Student student = new Student(
                "first",
                "middle",
                "last",
                null,
                0
        );

        // Act
        student.setFirstName("haRrY");
        student.setMiddleName("j");
        student.setLastName("P");

        // Assert
        assertEquals("Harry", student.getFirstName());
        assertEquals("J", student.getMiddleName());
        assertEquals("P", student.getLastName());
    }

}