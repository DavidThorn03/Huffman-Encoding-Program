import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/*
 * Class to read in the character value pairs from a file
 * input: String fileName - the name of the file to read from
 * output: ArrayList<TreeNode> - the list of character value pairs
 */

public class IO {
    public static ArrayList<TreeNode> getValues(String fileName){
        String[] inputString = new String[26];
        ArrayList<TreeNode> values = new ArrayList<>();
        try {
            int line = 0;
            File file = new File(fileName); // create file object
            Scanner in = new Scanner(file); // create scanner object
            while (in.hasNextLine()){ // loop through file till end
                inputString[line] = in.nextLine(); // add each line to the array
                line++;
            }
            in.close();// close scanner
        }
        catch(FileNotFoundException ex){// catch file not found exception
            ex.printStackTrace();
        }
        for(int i = 0; i < inputString.length; i++) { // loop through the array of lines
            // add each character and value to a new tree node and add to the list
            values.add(new TreeNode(Integer.parseInt(inputString[i].substring(2, inputString[i].length())), inputString[i].charAt(0)));
        }
        return values;
    }
}
