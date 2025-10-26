import java.io.*;
import java.util.Scanner;

public class FileIO {
    public static int numOfLines(File file){
        int i = 0;
        try {
            Scanner fileReader = new Scanner(file);
            fileReader.nextLine();// For the first unnecesarry part.
            while (fileReader.hasNext()){
                fileReader.nextLine();
                i++;
            }
            fileReader.close();
        }

        catch (FileNotFoundException e){
            System.out.println("File Not Found!");
        }

        return i;
    }
    
    public static int numOfMostColumns(File file){
        int highestColumn = 0;
        int i = 0;
        try {
            Scanner fileReader = new Scanner(file);
            fileReader.nextLine();// For the first unnecesarry part.
            while (fileReader.hasNext()){
                i = fileReader.nextLine().split(",").length;
                if (i > highestColumn){
                    highestColumn = i;
                }
            }
            fileReader.close();
        }

        catch (FileNotFoundException e){
            System.out.println("File Not Found!");
        }

        return highestColumn;
    }
    
    public static String[][] readLines(File file){
        int capacity = numOfLines(file);
        int attributes = numOfMostColumns(file);

        String[][] fileArray = new String[capacity][];
        try {
            Scanner fileReader = new Scanner(file);
            fileReader.nextLine();//For the first unnecesarry part.
            int i = 0;
            while (fileReader.hasNext()){
                String[] gamerArray = new String[attributes];
                gamerArray = fileReader.nextLine().split(",");
                fileArray[i++] = gamerArray;
            }
            fileReader.close();
        }

        catch (FileNotFoundException e){
            System.out.println("File Not Found!");
        }

        return fileArray;
    }
}