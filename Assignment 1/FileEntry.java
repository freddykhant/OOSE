package edu.curtin.app;
import java.util.*;

/*
 * Represents a file entry
 * 
 * @author Freddy Khant 20618166
 */

public class FileEntry implements Entry
{
    // File name
    private String name;
    // List of string contents, each element represents a line of text
    private List<String> content;

    public FileEntry(String name, List<String> content)
    {
        this.name = name;
        this.content = content;
    }

    // Accessor methods
    @Override
    public String getName()
    {
        return name;
    }

    public List<String> getContent()
    {
        return content;
    }
}