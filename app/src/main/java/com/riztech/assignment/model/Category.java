package com.riztech.assignment.model;

public class Category
{
    private String name;

    private String id;

    private String shortname;

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getShortname ()
    {
        return shortname;
    }

    public void setShortname (String shortname)
    {
        this.shortname = shortname;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [name = "+name+", id = "+id+", shortname = "+shortname+"]";
    }
}