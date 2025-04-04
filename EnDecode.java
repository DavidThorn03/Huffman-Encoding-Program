import java.util.ArrayList;
/*
 * Class for encoding/decoding messages
 * Called from gui with button press
 * Both take in and return string
 * 
 */
public class EnDecode {

    //ENCODE SOLUTION 1 ------------------------------------------------------------------------------------------------------------------------
    // THIS IS THE SOLUTION THAT WAS USED IN THE GUI CLASS AND DISCUSSED IN THE REPORT
    /*
     * Get codes method
     * Input: TreeNode root, root of the tree
     * Output: String[] codes, array of codes for each letter
     * 
     * Method:
     * Starting at the root node, does a in order search of the tree
     * If node has child nodes, add child nodes to the stack and continue
     * Going to left child node is represented by a 0, and right with a 1. This value is added to the code string
     * If node has no child nodes (leaf node reached) leaf contains symbol
     * Add symbol node to the answer array at the position of the symbol in the alphabet (a = 0, b = 1, etc)
     * Once the whole tree has been traversed, return the array of codes
     */
    public static String[] getCodes(TreeNode root){
        String[] codes = new String[26]; // array to store codes for each letter
        ArrayList<Object[]> stack = new ArrayList<>(); 
        stack.add(new Object[]{root, ""});
        while (!stack.isEmpty()) { // while there are nodes to check
            // get next node to check from top of stack and remove from stack
            String num = (String) stack.get(stack.size() - 1)[1];
            TreeNode curr = (TreeNode) stack.get(stack.size() - 1)[0];
            stack.remove(stack.size() - 1);
            // if the current node has a right child, add it to stack
            if (curr.getRight() != null) {
                stack.add(new Object[]{curr.getRight(), num + "1"});
            }
            // if the current node has a left child, add it to the stack
            if (curr.getLeft() != null) {
                stack.add(new Object[]{curr.getLeft(), num + "0"});
            }
            // if there are no child nodes add the code for that character its position in the array (a = 0, b = 1, etc)
            if (curr.getSymbol() != '*') {
                codes[curr.getSymbol() - 'A'] = num;
            }
        }
        return codes;
    }

    // Gets the code for each letter from the array of codes and returns result
    public static String encodeFromArray(String message, String[] codes){
        String code = ""; // answer string
        for(char letter : message.toCharArray()) {
            if(letter < 'A' || letter > 'Z') return ""; // if character is not a letter, return null
            code = code + codes[letter - 'A']; 
        }
        return code;
    }

    //ENCODE SOLUTION 2-------------------------------------------------------------------------------------------------------------------------

    /*
     * Encode method
     * Input: String message, message to be encoded
     *        TreenNode root, root of the tree
     * Output: String code, bit code for input or error message if invalid input is entered (not A-Z value)
     * 
     * Method:
     * Loops through each letter of the string
     * Starting at the root node, does a in order search of the tree
     * If node has child nodes, add child nodes to the stack and continue
     * Going to left child node is represented by a 0, and right with a 1
     * If not (leaf node reached) check if value matches node
     * If not, continue searching
     * If it is the same, reset the stack and go back to the root node, add letter code to answer string
     * If whole tree is traversed and letter isnt found, character is an invalid symbol and error is returned
     * When all letters codes are found, full answer code is returned
     */
    public static String encode(String message, TreeNode root){
        String code = ""; // answer string
        for(char letter : message.toCharArray()) {// for each character in the string
            // initialise stack and reset found
            ArrayList<Object[]> stack = new ArrayList<>(); 
            stack.add(new Object[]{root, ""});
            boolean found = false;
            while (!stack.isEmpty()) { // while there are nodes to check
                // get next node to check from top of stack and remove from stack
                String num = (String) stack.get(stack.size() - 1)[1];
                TreeNode curr = (TreeNode) stack.get(stack.size() - 1)[0];
                stack.remove(stack.size() - 1);
                // if the current node has a right child, add it to stack
                if (curr.getRight() != null) {
                    stack.add(new Object[]{curr.getRight(), num + "1"});
                }
                // if the current node has a left child, add it to the stack
                if (curr.getLeft() != null) {
                    stack.add(new Object[]{curr.getLeft(), num + "0"});
                }
                // if there are no child nodes, check if leaf node symbol matches current character
                if (curr.getLeft() == null && curr.getRight() == null) {
                    // if characters match, add code for this letter to answer code, set found to true and go to next letter
                    if (curr.getSymbol() == letter) {
                        code = code + num;
                        found = true;
                        break;
                    }
                }
            }
            // if full tree is traversed and letter isnt found, return null
            if(!found) return "";
        }
        // once all letters are found, return full code
        return code;
    }

    //DECODE-----------------------------------------------------------------------------------------------------------------------------------
    
    /*
     * Decode method
     * Input: String code, binary string to be decoded
     *        TreeNode root, root of huffman tree
     * Output: String message, decoded meessage
     * 
     * Method:
     * Loop through each character in the array
     * If character is a 0, go to the left child node on the tree
     * If the character is a 1, go to the right child node on the tree
     * If the character is neither, input is invalid and should be returned
     * If the symbol of the currrent node isnt a '*' (a leaf node) add character to the answer string and go back to the root of the tree
     * If the full code string has been traversed and the last node checked didnt have a letter, the code string was incorrect or incomplete, so return error message
     * When full string is checked, return the message
     */
    public static String decode(String code, TreeNode root){
        String message = "";
        TreeNode curr = root; // start at root node
        boolean found = false;
        for(char num : code.toCharArray()){// check each character in the array
            found = false;
            // if character is 0, go to the left node
            if(num == '0'){
                curr = curr.getLeft();
            }
            // if character is 1, go to the right node
            else if(num == '1'){
                curr = curr.getRight();
            }
            // if character is neither, return ""
            else return "";
            // if the current tree node symbol isnt '*', leaf node has been reached
            if(curr.getSymbol() != '*'){
                message = message + curr.getSymbol(); // add this node symbol to answer string
                curr = root; // go back to root node
                found = true;
            }
        }
        // if last node checked wasnt a leaf node, return ""
        if(!found){
            return "";
        }
        return message; // return decoded message
    }
}
