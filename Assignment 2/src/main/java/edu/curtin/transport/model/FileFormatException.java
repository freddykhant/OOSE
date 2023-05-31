package edu.curtin.transport.model;

/*
 * Custom exception class that represents an exception when the file format is incorrect.
 */

public class FileFormatException extends Exception
{
    public FileFormatException(String msg)
    {
        super(msg);
    }
    
    public FileFormatException(String msg, Throwable cause)
    {
        super(msg, cause);
    }
}