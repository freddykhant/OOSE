package edu.curtin.app;

import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/*
 * The application for the text file searching tool
 * 
 * @author Freddy Khant 20618166
 */

public class TextFileSearcher
{
    private static String directoryPath; // root directory path
    private static Directory rootDirectory; // root directory to start from
    private static List<Criterion> criteria = new ArrayList<>(); // list of search criteria
    private static OutputFormat outputFormat = OutputFormat.COUNT; // output format originally set to count
    private static final Logger LOGGER = Logger.getLogger(TextFileSearcher.class.getName());
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        // Code from 4.6.1 Command-Line Parameters of Assignment Specification
        if(args.length < 1)
        {
            directoryPath = ".";
        }
        else 
        {
            directoryPath = args[0];
        }

        File directory = new File(directoryPath); // From 4.6.2 Reading Directory Trees
        rootDirectory = new Directory(directory.getName()); // Create directory object to represent root directory
        readDirectory(directory, rootDirectory); // Read tree and store contents in memory
        displayMenu(rootDirectory); // Display the menu system
    }
    
    // Method to recursively read directory tree and store contents in memory
    private static void readDirectory(File directory, Directory rootDirectory)
    {
        File[] files = directory.listFiles(); // From 4.6.2 Reading Directory Trees
        if(files != null)
        {
            for(File file : files) // Loop through files
            {
                if(file.isDirectory()) // If directory
                {
                    Directory newDir = new Directory(file.getName()); // Create new sub directory object
                    rootDirectory.addChild(newDir); // Add sub directory into parent's children list
                    readDirectory(file, newDir); // Recursively read through sub directory
                }
                else if(file.isFile()) // If file
                {
                    try
                    {
                        String text = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath()))); // Read file's text contents into a String variable
                        List<String> contents = Arrays.asList(text.split("\n")); // List containing file's contents, each element is a line in the file
                        FileEntry newFile = new FileEntry(file.getName(), contents); // Creates new file object
                        rootDirectory.addChild(newFile); // Adds file into parent directory's children list
                    }
                    catch(IOException e)
                    {
                        LOGGER.log(Level.WARNING, "Error reading file: " + file.getName()); // Log warning for IOException
                    }
                }
            }
        }
    }

    // Menu system to select functionalities
    private static void displayMenu(Directory directory)
    {
        boolean done = false;
        while(!done)
        {
            System.out.println("\n======================================");
            System.out.println("MENU FOR TEXT SEARCH APPLICATION");
            System.out.println("======================================\n");
            System.out.println("1. Set Criteria");
            System.out.println("2. Set Output Format");
            System.out.println("3. Report");
            System.out.println("4. Quit");
            System.out.println("\nChoose an Option:");
        
            try
            {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch(choice)
                {
                    case 1:
                        setCriteria();
                        break;
                    case 2:
                        setOutputFormat();
                        break;
                    case 3:
                        searchReport(directory, 0);
                        break;
                    case 4:
                        done = true;
                        break;
                    default:
                        System.out.println("Enter a valid number");
                        break;
                }
            }
            catch(NumberFormatException e)
            {
                LOGGER.log(Level.WARNING, "Enter a number"); // Log warning for invalid input
            }
        }
    }

    // Method to set search criteria
    private static void setCriteria()
    {
        System.out.println("Enter search criteria:");
        String input = scanner.nextLine();
        while(!input.isEmpty()) // Accept several lines until blank line is encountered
        {
            String[] parts = input.split(" ");
            Criterion criterion = new Criterion(parts[0], parts[1], parts[2]); // Create new Criterion object with unique fields
            criteria.add(criterion); // Add to list of criteria
            input = scanner.nextLine();
        }
    }
    
    /* Method to check for pattern matching of regex and text expressions
    From 4.6.3 Regular Expressions code example
    */
    private static boolean matchCriterion(String criterion, String input)
    {
        Pattern pattern = Pattern.compile(criterion);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    // Method to select output format, count matching lines, show matching lines
    private static void setOutputFormat()
    {
        System.out.println("\nChoose output format:");
        System.out.println("1. Count of matching lines per file");
        System.out.println("2. Show actual matching lines\n");
        int choice = scanner.nextInt();
        scanner.nextLine();

        // Selects output format based on user input
        try
        {
            switch(choice)
            {
                case 1:
                    outputFormat = OutputFormat.COUNT; // Switched to count
                    System.out.println("Output Format: Count");
                    break;
                case 2:
                    outputFormat = OutputFormat.SHOW; // Switched to show
                    System.out.println("Output Format: Show");
                    break;
                default:
                    System.out.println("Enter a valid number");
                    break;
            }
        }
        catch(NumberFormatException e)
        {
            LOGGER.log(Level.WARNING, "Enter a number"); // Log warning for invalid input
        }
    }

    // Decorator method, performs a method based on the state of OutputFormat
    private static void searchReport(Directory directory, int indentation)
    {
        if(outputFormat == OutputFormat.COUNT)
        {
            countReport(directory, indentation);
        }
        else if(outputFormat == OutputFormat.SHOW)
        {
            showReport(directory, indentation);
        }
    }

    // Method to count all matching lines specified by search criteria
    private static int countReport(Directory parent, int indentation)
    {
        int lineCount = 0;
        for(Entry entry : parent.getChildren()) // Search through parent directory's children
        {
            if(entry instanceof Directory) // If directory
            {
                Directory directory = (Directory)entry; // downcast
                int childLineCount = countReport(directory, indentation+1); // recurse to count inside the sub directory
                lineCount += childLineCount; // increment total line count with child's line count
                System.out.println(getIndentation(indentation) + directory.getName() + ": " + childLineCount + " lines"); // print name and line count
            }
            else if(entry instanceof FileEntry) // if File
            {
                FileEntry file = (FileEntry)entry; 
                int fileLineCount = 0;
                for(String line : file.getContent()) // Search file content line by line
                {
                    for(Criterion criterion : criteria) // Search criteria
                    {
                        if(criterion.getInclusion().equals("i")) // If criterion is inclusive
                        {
                            if(criterion.getType().equals("t")) // If criterion is text based
                            {
                                if(matchCriterion(criterion.getText(), line)) // If line matches criterion
                                {
                                    fileLineCount += 1; // Increment file's line count
                                }
                            }
                            if(criterion.getType().equals("r")) // etc
                            {
                                if(matchCriterion(criterion.getText(), line))
                                {
                                    fileLineCount += 1;
                                }
                            }
                        }
                    }
                }
                lineCount += fileLineCount;
                System.out.println(getIndentation(indentation+1) + entry.getName() + ": " + fileLineCount + " lines"); // Print file line count with name
            }
        }
        System.out.println(getIndentation(indentation) + parent.getName() + ": " + lineCount + " lines"); // Print parent directory's total line count
        return lineCount;
    }

    // Method for showing all lines with matching search criteria
    private static void showReport(Directory parent, int indentation)
    {
        System.out.println(getIndentation(indentation) + parent.getName() + ":"); // Display directory name
        for(Entry entry : parent.getChildren())
        {
            if(entry instanceof Directory)
            {
                Directory directory = (Directory)entry;
                showReport(directory, indentation+1); // Recursively show matching lines in child directory
            }
            else if(entry instanceof FileEntry)
            {
                FileEntry file = (FileEntry)entry;
                System.out.println(getIndentation(indentation+1) + entry.getName() + ":"); // Display file name
                int lineCount = 1; // Initialize line number
                for(String line : file.getContent())
                {
                    for(Criterion criterion : criteria)
                    {
                        if(criterion.getInclusion().equals("i"))
                        {
                            if(matchCriterion(criterion.getText(), line))
                            {
                                System.out.println(getIndentation(indentation+2) + lineCount + " " + line); // Display line number and text
                            }
                        }
                    }
                    lineCount++; // Increment line number
                }
            }
        }
    }
    
    // Method to determine how much indentation, used for the display of directory tree
    private static String getIndentation(int indentation)
    {
        String indent = "";
        for(int i = 0; i < indentation; i++)
        {
            indent += "\t";
        }
        return indent;
    }
}