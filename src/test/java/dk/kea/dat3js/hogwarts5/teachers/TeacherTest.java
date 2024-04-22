package dk.kea.dat3js.hogwarts5.teachers;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {


    @Test
    void getFullNameWithMiddleName() {
        // Arrance
        Teacher Teacher = new Teacher(
                "Ali",
                "Haider",
                "Mohammad",
                null,
                null,
                null
        );

        // Act
        String fullName = Teacher.getFullName();

        // Assert
        assertEquals("Ali Haider Mohammad", fullName);
    }

    @Test
    void getFullNameWithoutMiddleName() {
        // Arrange
        Teacher Teacher = new Teacher(
                "Ali",
                "Mohammad",
                null,
                null,
                null
        );

        // Act
        String fullName = Teacher.getFullName();

        // Assert
        assertEquals("Ali Mohammad", fullName);
    }


    @Test
    void setFullNameWithMiddleName() {
        // Arrange
        Teacher teacher = new Teacher(
                "first",
                "middle",
                "last",
                null,
                null,
                null
        );

        String fullName = "Harry James Potter";

        // Act
        teacher.setFullName(fullName);

        // Assert
        assertEquals("Harry", teacher.getFirstName());
        assertEquals("James", teacher.getMiddleName());
        assertEquals("Potter", teacher.getLastName());

    }

    @Test
    void setFullNameWithoutMiddleName() {
        // Arrange
        Teacher teacher = new Teacher(
                "first",
                "last",
                null,
                null,
                null
        );

        String fullName = "Harry Potter";

        // Act
        teacher.setFullName(fullName);

        // Assert
        assertEquals("Harry", teacher.getFirstName());
        assertEquals("Potter", teacher.getLastName());

        assertNull(teacher.getMiddleName());
    }


    @Test
    void setFullNameWithEmptyString() {
        // Arrange
        Teacher teacher = new Teacher(
                "first",
                "middle",
                "last",
                null,
                null,
                null
        );

        String fullName = "";

        // Act
        teacher.setFullName(fullName);

        // Assert
        assertEquals("", teacher.getFirstName());

        assertNull(teacher.getLastName());
        assertNull(teacher.getMiddleName());
    }

    @Test
    void setFullNameWithNull() {
        // Arrange
        Teacher teacher = new Teacher(
                "first",
                "middle",
                "last",
                null,
                null,
                null
        );

        // Act
        teacher.setFullName(null);

        // Assert
        assertEquals("first", teacher.getFirstName());
        assertEquals("middle", teacher.getMiddleName());
        assertEquals("last", teacher.getLastName());
    }

    @Test
    void setFullNameWithMultipleMiddleNames() {
        // Arrange
        Teacher teacher = new Teacher(
                "first",
                "middle",
                "last",
                null,
                null,
                null
        );

        // Act
        teacher.setFullName("Ali Abed Al Amier");

        // Assert
        assertEquals("Ali", teacher.getFirstName());
        assertEquals("Abed Al", teacher.getMiddleName());
        assertEquals("Amier", teacher.getLastName());
    }

    @Test
    void setFullNameWithOnlyFirstName() {
        // Arrange
        Teacher teacher = new Teacher(
                "first",
                "middle",
                "last",
                null,
                null,
                null
        );

        // Act
        teacher.setFullName("Ali");

        // Assert
        assertEquals("Ali", teacher.getFirstName());
        assertNull(teacher.getMiddleName());
        assertNull(teacher.getLastName());
    }

    @Test
    void capitalizeIndividualNames() {
        // Arrange
        Teacher teacher = new Teacher(
                "first",
                "middle",
                "last",
                null,
                null,
                null
        );

        // Act
        teacher.setFirstName("harry");
        teacher.setMiddleName("james");
        teacher.setLastName("potter");

        // Assert
        assertEquals("Harry", teacher.getFirstName());
        assertEquals("James", teacher.getMiddleName());
        assertEquals("Potter", teacher.getLastName());
    }

    @Test
    void capitalizeIndividualNamesWithCrazyCapitalization() {
        // Arrange
        Teacher teacher = new Teacher(
                "first",
                "middle",
                "last",
                null,
                null,
                null
        );

        // Act
        teacher.setFirstName("haRrY");
        teacher.setMiddleName("jAMeS");
        teacher.setLastName("PoTteR");

        // Assert
        assertEquals("Harry", teacher.getFirstName());
        assertEquals("James", teacher.getMiddleName());
        assertEquals("Potter", teacher.getLastName());
    }

    @Test
    void capitalizeIndividualNamesWithOnlyOneLetter() {
        // Arrange
        Teacher teacher = new Teacher(
                "first",
                "middle",
                "last",
                null,
                null,
                null
        );

        // Act
        teacher.setFirstName("haRrY");
        teacher.setMiddleName("j");
        teacher.setLastName("P");

        // Assert
        assertEquals("Harry", teacher.getFirstName());
        assertEquals("J", teacher.getMiddleName());
        assertEquals("P", teacher.getLastName());
    }
}