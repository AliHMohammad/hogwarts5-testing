package dk.kea.dat3js.hogwarts5.students;

import dk.kea.dat3js.hogwarts5.house.House;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    @ManyToOne
    private House house;
    private Integer schoolYear; // 1-7

    public Student() {
    }

    public Student(String firstName, String lastName, House house, int schoolYear) {
        this(firstName, null, lastName, house, schoolYear);
    }

    public Student(String firstName, String middleName, String lastName, House house, int schoolYear) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.house = house;
        this.schoolYear = schoolYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = capitalize(firstName);
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = capitalize(middleName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = capitalize(lastName);
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Integer getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(Integer schoolYear) {
        this.schoolYear = schoolYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(getFirstName(), student.getFirstName()) && Objects.equals(getMiddleName(), student.getMiddleName()) && Objects.equals(getLastName(), student.getLastName()) && Objects.equals(getHouse().getName(), student.getHouse().getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getMiddleName(), getLastName(), getHouse().getName());
    }

    public String getFullName() {
        return firstName + " " + (middleName != null ? middleName + " " : "") + lastName;
    }

    public void setFullName(String fullName) {
        // Null
        if (fullName == null) return;

        int firstSpace = fullName.indexOf(" ");
        int lastSpace = fullName.lastIndexOf(" ");

        // First name only or Empty string
        if (firstSpace == -1) {
            setFirstName(fullName);
            setMiddleName(null);
            setLastName(null);
            return;
        }

        setFirstName(fullName.substring(0, firstSpace));
        setMiddleName((firstSpace != lastSpace ? fullName.substring(firstSpace + 1, lastSpace) : null));
        setLastName(fullName.substring(lastSpace + 1));
    }

    private String capitalize(String name) {
        if (name == null) return null;
        // Empty or one letter
        if (name.length() < 2) return name.toUpperCase();

        // Recursion, Hvis mellemnavn består af flere navne
        if (name.contains(" ")){
            int space = name.indexOf(" ");
            return capitalize(name.substring(0, space)) + " " + capitalize(name.substring(space + 1));
        }

        return name.substring(0, 1).toUpperCase()+name.substring(1).toLowerCase();
    }



}
