# Huffman-Encoding-Program

This project was compelted for the Data Structures and Algorithms project

This involved creating a Java program to encode and decode messages encoded with a Huffman tree.

The program featured a JFrame GUI with sophisticated user input control to prevent errors from stopping execution.

The program works by creating a Huffman tree from symbol-frequency pairs and forming a binary tree from them.

The tree is formed by taking the two symbol-frequency pairs with the lowest frequency and using them as child tree nodes, with a parent node with no (null) symbol and a frequency of the sum of the child nodes. This parent node is then added to the list of symbol-frequency pairs and this process is repeated until there is only one node left, the root node.

The symbols are encoded using an array to store each symbols encoded binary string, which can be found by doing a search of the tree (depth first searched is used), adding a 0 to the code string when going to a left node and a 1 when going to a right node.

The symbols are decoded by traversing the tree in accordance to their encoded string, going down the left node whenever a 0 is the next string value and the right whenever a 1 is the next value, until a node leaf is reached, in which case a symbol has been found and the search continues form back at the root node.
