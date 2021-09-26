package com.orzechowski.aidme.entities;

import javax.persistence.Column;
import javax.persistence.Id;

public class ServerSideHelper
{
    @Id
    private long helperId;
    @Column(name = "helper_name", nullable = false)
    private final String name;
    @Column(name = "helper_surname", nullable = false)
    private final String surname;
    @Column(name = "helper_title")
    private final String title;
    @Column(name = "helper_profession")
    private final String profession;
    @Column(name = "email")
    private final String email;
    @Column(name = "phone")
    private final int phone;

    public ServerSideHelper(String name, String surname, String title, String profession, String email, int phone)
    {
        this.name = name;
        this.surname = surname;
        this.title = title;
        this.profession = profession;
        this.email = email;
        this.phone = phone;
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

    public String getEmail()
    {
        return email;
    }

    public int getPhone()
    {
        return phone;
    }
}
