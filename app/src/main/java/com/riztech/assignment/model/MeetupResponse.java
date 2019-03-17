package com.riztech.assignment.model;

public class MeetupResponse
{
    private Meta meta;

    private Results[] results;

    public Meta getMeta ()
    {
        return meta;
    }

    public void setMeta (Meta meta)
    {
        this.meta = meta;
    }

    public Results[] getResults ()
    {
        return results;
    }

    public void setResults (Results[] results)
    {
        this.results = results;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [meta = "+meta+", results = "+results+"]";
    }
}