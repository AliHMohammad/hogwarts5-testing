package dk.kea.dat3js.hogwarts5.students;

import dk.kea.dat3js.hogwarts5.common.PersonWithNames;
import dk.kea.dat3js.hogwarts5.house.House;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Student implements PersonWithNames {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    @ManyToOne
    private House house;
    private Integer schoolYear; // 1-7

    private Boolean prefect;

    private Gender gender;

    public Student() {
    }

    public Student(String firstName, String lastName, House house, int schoolYear) {
        this(firstName, null, lastName, house, schoolYear, null, null);
    }

    public Student(String firstName, String middleName, String lastName, House house, int schoolYear, Boolean prefect, String gender) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.house = house;
        this.schoolYear = schoolYear;
        this.prefect = prefect;
        setGender(gender);
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

    public Boolean getPrefect() {
        return prefect;
    }

    public void setPrefect(Boolean prefect) {
        if (schoolYear < 5) return;

        this.prefect = prefect;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        if (gender == null){
            return;
        }
        this.gender = gender;
    }

    public void setGender(String gender) {
        if (gender == null || gender.isEmpty()) {
            return;
        }

        try {
            setGender(Gender.valueOf(gender.toUpperCase()));
        } catch (IllegalArgumentException e) {
            System.out.println("gender must either be 'MALE' or 'FEMALE'");
        }

    }

}
