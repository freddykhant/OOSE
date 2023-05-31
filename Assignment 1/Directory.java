package edu.curtin.app;
import java.util.*;

/*
 * Represents a directory
 * 
 * @author Freddy Khant 20618166
 */

public class Directory implements Entry
{   
    // Directory name
    private String name;
    // List of children (can be FileEntry or Directory objects)
    private List<Entry> children;

    public Directory(String name)
    {
        this.name = name;
        children = new ArrayList<>();
    }

    @Override
    public String getName()
    {
        return name;
    }

    public List<Entry> getChildren()
    {
        return children;
    }

    public void addChild(Entry child)
    {
        children.add(child);
    }
}