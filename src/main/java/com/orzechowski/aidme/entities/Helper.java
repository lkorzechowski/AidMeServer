package com.orzechowski.aidme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "helpers")
public class Helper
{
    @Id
    private long helperId;
    @Column(name = "helper_name", nullable = false)
    private final String name;
    @Column(name = "helper_surname", nullable = false)
    private final String surname;
    @Column(name = "helper_title")
    private String title;
    @Column(name = "helper_profession")
    private String profession;

    public Helper(String name, String surname, String title, String profession)
    {
        this.name = name;
        this.surname = surname;
        this.title = title;
        this.profession = profession;
    }

    public Helper(long helperId, String name, String surname, String title, String profession)
    {
        this.helperId = helperId;
        this.name = name;
        this.surname = surname;
        this.title = title;
        this.profession = profession;
    }

    public Helper(String name, String surname)
    {
        this.name = name;
        this.surname = surname;
    }

    public Helper(long helperId, String name, String surname)
    {
        this.helperId = helperId;
        this.name = name;
        this.surname = surname;
    }

    public Helper(String name, String surname, String odd)
    {
        this.name = name;
        this.surname = surname;
        if(odd.length()<4) this.title = odd;
        else this.profession = odd;
    }

    public Helper(long helperId, String name, String surname, String odd)
    {
        this.helperId = helperId;
        this.name = name;
        this.surname = surname;
        if(odd.length()<4) this.title = odd;
        else this.profession = odd;
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
}
