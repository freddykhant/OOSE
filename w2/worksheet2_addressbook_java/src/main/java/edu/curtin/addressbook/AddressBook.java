package edu.curtin.addressbook;

import java.util.*;

/**
 * Contains all the address book entries.
 * 
 * @author ...
 */
public class AddressBook
{
    private Map<String,Entry> entries;

    public AddressBook()
    {
        entries = new HashMap<String,Entry>();
    }

    public Entry getEntry(String strKey)
    {
        return entries.get(strKey);
    }

    public void setEntry(String strKey, Entry pEntry)
    {
        entries.put(strKey, pEntry);
    }
}
