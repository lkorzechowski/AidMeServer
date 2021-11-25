package com.orzechowski.aidme.entities.helper;

public class Login
{
    private final boolean verified;
    private final boolean helping;

    public Login(boolean verified, boolean helping)
    {
        this.verified = verified;
        this.helping = helping;
    }

    public boolean isVerified()
    {
        return verified;
    }

    public boolean isHelping()
    {
        return helping;
    }
}
