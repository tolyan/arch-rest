package pro.example.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Author: Anatoly Rybalchenko
 * Date: 11/28/13
 * Time: 5:40 PM
 */
public class TestPojo implements Serializable {
    private static final long serialVersionUID = 5009313437299427666L;

    private String name;
    private String lastname;
    private Date birthdate;
    private String notes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
