package com.orzechowski.aidme.entities;

public class Helper
{
    private long helperId;
    private final String name;
    private final String surname;
    private final String title;
    private final String profession;

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
}
