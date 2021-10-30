package com.orzechowski.aidme.entities.helper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "helper")
public class Helper
{
    @Id
    @Column(name = "helper_id", nullable = false)
    private long helperId;
    @Column(name = "helper_name", nullable = false)
    private String name;
    @Column(name = "helper_surname", nullable = false)
    private String surname;
    @Column(name = "helper_title")
    private String title;
    @Column(name = "helper_profession")
    private String profession;
    @Column(name = "helper_email")
    private String email;
    @Column(name = "helper_phone")
    private String phone;

    public Helper(long helperId, String name, String surname, String title, String profession, String email, String phone)
    {
        this.helperId = helperId;
        this.name = name;
        this.surname = surname;
        this.title = title;
        this.profession = profession;
        this.email = email;
        this.phone = phone;
    }

    public Helper(long helperId, String name, String surname, String title, String profession)
    {
        this.helperId = helperId;
        this.name = name;
        this.surname = surname;
        this.title = title;
        this.profession = profession;
    }

    public long getHelperId()
    {
        return helperId;
    }

    public String getName()
    {
        return name;
    }

    public String getSurname()
    {
        return surname;
    }

    public String getTitle()
    {
        return title;
    }

    public String getProfession()
    {
        return profession;
    }

    public String getPhone()
    {
        return phone;
    }

    public String getEmail()
    {
        return email;
    }
}
