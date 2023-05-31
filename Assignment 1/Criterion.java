package edu.curtin.app;

/*
 * Represents a criterion object to specify search criteria
 * 
 * @author Freddy Khant 20618166
 */

public class Criterion
{   
    // "i" or "e" to include or exclude
    private String inclusion;
    // "t" or "r" for text or regex
    private String type;
    // string of text or regex
    private String text;

    public Criterion(String inclusion, String type, String text)
    {
        this.inclusion = inclusion;
        this.type = type;
        this.text = text;
    }

    public String getInclusion()
    {
        return inclusion;
    }

    public String getType()
    {
        return type;
    }

    public String getText()
    {
        return text;
    }
}