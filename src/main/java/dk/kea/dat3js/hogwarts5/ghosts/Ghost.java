package dk.kea.dat3js.hogwarts5.ghosts;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Ghost {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String realName;
    private String house;


    public Ghost() {
    }

    public Ghost(Integer id, String name, String realName, String house) {
        this.id = id;
        this.name = name;
        this.realName = realName;
        this.house = house;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }
}
