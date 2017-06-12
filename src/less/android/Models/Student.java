package less.android.Models;

import less.android.Utils.Generator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student implements Serializable {
    private final Long id = Generator.generateId();
    private String firstName;
    private String surename;
    private String secondName;
    private Date dateOfBirth;
    private Long groupId;
    transient private List<Contact> contacts;

    public Student(String firstName, String surename, String secondName, Date dateOfBirth, Long groupId) {
        this.firstName = firstName;
        this.surename = surename;
        this.secondName = secondName;
        this.dateOfBirth = dateOfBirth;
        this.groupId = groupId;
        this.contacts = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    @Override
    public int hashCode() {
        return (int)(21+id*42);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Student)) {
            return false;
        }

        return ((Student) obj).getId().equals(this.id);
    }
}
