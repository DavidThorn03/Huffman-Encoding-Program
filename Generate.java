import java.util.ArrayList;
/*
 * Class and method to generate the tree from the character value pairs
 * Input: ArrayList<TreeNode> valuePair - the character value pairs
 * Output: TreeNode - the root of the Huffamn tree
 * 
 * Method:
 * The list of character value pairs/nodes will be looped through until there is only one node left in the list
 * At each loop, the two lowest nodes are taken from the list
 * These two nodes will be child nodes of a new node, with a null (non letter) character and a frequency equal to the sum of the two child nodes
 * The new node is then added back to the list in the correct position (based on frequency)
 */
public class Generate {
    public static TreeNode generateTree(ArrayList<TreeNode> valuePair){
        TreeNode temp1, temp2, root;

        while(valuePair.size() > 1){ //loop till each node/ value pair has beena added to the tree
            //value pairs are in increasing order of frequency, so first two are the smallest
            temp1 = valuePair.get(0);
            temp2 = valuePair.get(1);
            //remove the two nodes / values beign worked on
            valuePair.remove(0);
            valuePair.remove(0);
            //new node created with the two lowest nodes as children
            root = new TreeNode(temp1.getFreq() + temp2.getFreq(), '*', temp1, temp2);
            boolean add = false;// to check if the new node has been added to the list
            for(int i = 0; i < valuePair.size(); i++){ // loop through list 
                if(root.getFreq() <= valuePair.get(i).getFreq()){//if the new node has a lower frequency than the current node in the list, add the new node to the list at that position
                    valuePair.add(i, root);
                    add = true;
                    break;
                }
            }
            if(!add) valuePair.add(root); //if the new node has not been added to the list, add it to the end
        }
        return valuePair.get(0); //at the end of the loop, the only node remaining is the highest node in the tree (root node) which is where the encode/decode process starts
    }
}
