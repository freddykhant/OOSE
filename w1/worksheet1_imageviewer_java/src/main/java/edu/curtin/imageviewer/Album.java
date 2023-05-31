package edu.curtin.imageviewer;
import java.util.*;

/**
 * Represents an album of images.
 */
public class Album 
{
    private List<ImageRecord> images;

    public Album()
    {
        images = new ArrayList<ImageRecord>();
    }

    public List<ImageRecord> getImages()
    {
        return images;
    }

    public void addImageRecord(ImageRecord imageRecord)
    {
        images.add(imageRecord);
    }
}
