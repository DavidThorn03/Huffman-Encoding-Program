import java.util.ArrayList;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        ArrayList<TreeNode> valuePair = IO.getValues("LetterCountAscending.txt");// get character value pairs from file
        try {
            // Set the Look and Feel to Windows
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Windows Look and Feel is not available.");
        }

        TreeNode root = Generate.generateTree(valuePair); // create tree from character value pairs
        String[] codes = EnDecode.getCodes(root); // generate codes for each letter

        GUI frame = new GUI(root, codes); //create GUI
    }
}