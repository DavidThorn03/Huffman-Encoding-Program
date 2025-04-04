import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/*
 * GUI class to create the GUI for the Huffman Tree
 * 
 * Input: TreeNode root - the root of the Huffman tree
 *        String[] codes - the codes for each letter
 * Output: GUI - the GUI for the Huffman tree
 * External methods used: EnDecode.encode, EnDecode.decode
 * 
 * Presents the user with a text are to input a string to encode or decode
 * if encode is selected, the string is encoded and the output is displayed
 * if decode is selected, the string is decoded and the output is displayed
 * 
 * This class also includes an action listener to listen for the encode and decode buttons
 * 
 */
public class GUI extends JFrame implements ActionListener {
    JTextArea input;
    JLabel output, ratio;
    JButton encode, decode;
    TreeNode root;
    String[] codes;
    public GUI(TreeNode root, String[] codes) {
        super("Huffman Tree");
        this.root = root; // declare the root of the tree to be used in the encode and decode methods
        this.codes = codes; // declare the codes to be used in the encode method
        // main panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 100, 10));
        // pannel to style the input text area
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(1, 50, 50));
        // panel to style/hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout (new FlowLayout(1, 50, 30));
        // panel to style the output label
        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new FlowLayout(1));
        // panel to style the ratio label
        JPanel ratioPanel = new JPanel();
        ratioPanel.setLayout(new FlowLayout(1));

        // create input text area
        input = new JTextArea(4, 30);
        inputPanel.add(input);// add to panel

        // create encode and decode buttons and add action listeners
        encode = new JButton("Encode");
        encode.addActionListener(this);
        decode = new JButton("Decode");
        decode.addActionListener(this);

        // add to panel
        buttonPanel.add(encode);
        buttonPanel.add(decode);

        output = new JLabel("Output displayed here");// create output label with default text
        ratio = new JLabel("");// create ratio label with default text

        outputPanel.add(output);// add to panel
        ratioPanel.add(ratio);// add to panel

        // add panels to main panel
        panel.add(inputPanel);
        panel.add(buttonPanel);
        panel.add(outputPanel);
        panel.add(ratioPanel);

        add(panel);
        setSize(700, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String in = input.getText(); // get the input from the text area
        ratio.setText("");
        if(in.equals("")){// if no input is entered, display an error message
            output.setText("Please enter a message to encode/decode");
        }
        else if(source == encode){// if encode button is clicked, pass the input to the encode method
            String out = EnDecode.encodeFromArray(in, codes);
            if(out.length() == 0) output.setText("Invalid message entered"); // if out is "", return error message
            else {
                output.setText("Code: " + out);// display the output
                ratio.setText("Compression ratio: " + (in.length()*7) + "/" + out.length());// display the compression ratio
            }
        }
        else{// if decode button is clicked, pass the input to the decode method
            String out = EnDecode.decode(in, root);
            if(out.length() == 0) output.setText("Invalid code entered"); // if out is "", return error message
            else{
                output.setText("Message: " + out);// display the output
                ratio.setText("Compression ratio: " + (out.length()*7) + "/" + in.length());// display the compression ratio
            }
        }
    }
}
