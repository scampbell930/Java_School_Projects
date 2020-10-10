/*
 * Sean Campbell, CS 1450-RL2, Program 9
 */
public class CampbellSeanProject9 {
	
	public static void main(String[] args) {
		
		BSTree tree = new BSTree();
		
		TextReader read = new TextReader("C:\\Dev\\NetBeans - workspace\\NotePad++ Files\\Speech.txt");
		String nextword;
		nextword = read.GetWord(); // initial read
		
		while (nextword != null) {
			
			// Add word to tree
			tree.insert(nextword, true);
			nextword = read.GetWord();
			
		}
		
		// Display tree with word counts
		tree.display();
		
		System.out.println("");
		
		// Display tree height
		System.out.println("Tree Height: " + tree.height());
		System.out.println("");
		
		// Displaying total number of comparisons to search entire tree
		int aveSearch = tree.aveSearch();
		System.out.println("");
		System.out.println("Total Search Comparisons: " + tree.getTotal());
		System.out.println("");
		
		// Displaying number of nodes in tree
		System.out.println("Total Nodes in Tree: " + tree.nodeCount());
		System.out.println("");
		
		// Displaying the average number of comparisons to find String in tree
		System.out.println("Average Search Comparison: " + aveSearch);
		System.out.println("");
		
		// Answering questions at end of assignment
		System.out.println("");
		System.out.println("END OF ASSIGNMENT QUESTIONS:");
		System.out.println("");
		
		System.out.println("WHAT IS THE EXPECTED HEIGHT OF AN OPTIMIZED BST FOR THE WORD DATA PROVIDED IN THE TEXT?");
		System.out.println("The expected height of an optimized BST for the word data provided is 8 as the optimal height is the log2(428 nodes) = 8.74.");
		System.out.println("");
		System.out.println("WHAT IS THE EXPECTED AVERAGE NUMBER OF COMPARISONS FOR THE OPTIMIZED BST?");
		System.out.println("The expected average number of comparisons for the optimized BST is 20.01 comparisons.");
		System.out.println("");
		System.out.println("HOW DOES YOUR ACTUAL AVERAGE COMPARE TO THE THEORETICAL OPTIMUM?");
		System.out.println("My actual average is 144.9% of the theoretical average.");
		
	} //main

} //CampbellSeanProject9

class BTNode {
	
	private BTNode left;	// left child node
	private BTNode right;	// right child node
	private String word;	// word stored in node
	private int count;		// number of occurances
	
	
	// Constructor for BTNode
	public BTNode(String word) {
		
		// Setting data
		this.word = word;
		count = 1;
		left = null;
		right = null;
		
	} //BTNode constructor
	
	
	// Increment adds one to the current count
	public void increment() {
		
		// Increment count
		count++;
		
	} //increment
	
	
	// Setter for left node
	public void setLeft(BTNode left) {
		
		this.left = left;
		
	} //setLeft
	
	
	// Setter for right node
	public void setRight(BTNode right) {
		
		this.right = right;
		
	} //setRight
	
	
	// Getter for left node
	public BTNode getLeft() {
		
		return left;
		
	} //getLeft
	
	
	// Getter for right node
	public BTNode getRight() {

		return right;

	} // getRight

	
	// Getter for word
	public String getWord() {
		
		return word;
		
	} //getWord
	
	
	// Getter for count
	public int getCount() {
	
		return count;
		
	} //getCount
	
} //BTNode

class BSTree {
	
	private static int total = 0;	// counter for total number of searches to find nodes in tree
	private static int count = 0;	// counter for printing columns
	private BTNode root;			// root node of BSTree
	
	
	// Constructor for BSTree
	public BSTree() {
		
		// Sets root to null
		root = null;
		
	} //BSTree Constructor
	
	
	// Returns tree total
	public int getTotal() {
		
		return total;
		
	} //getTotal
	
	
	// Return tree root
	public BTNode getRoot() {
		
		return root;
		
	} //getRoot
	
	
	// Insert adds a new node to the tree is the word being added is not already there, increments counter if word already is in tree
	public BTNode insert(String newword, boolean lowerCase) {
		
		// Checking if string needs to be set to lowercase
		if (lowerCase) {
			newword = newword.toLowerCase();
		}
		
		// Initializing return node
		BTNode node = new BTNode(newword);
		
		// Checking if root is set
		if (root == null) {
			root = node;
		}
		else {
			// Call recursive add function
			node = insertRecur(root, newword);
		}
		
		return node;
		
	} //insert
	
	
	// Private recursive insert for when root is not null
	private BTNode insertRecur(BTNode root, String newword) {
		
		// Initializing return node
		BTNode node = new BTNode(newword);
		
		// Checking if node needs to go left or right
		if (newword.compareTo(root.getWord()) < 0) {
			
			// Checking if left node is null
			if (root.getLeft() == null) {
				root.setLeft(node);
				node = root.getLeft();
			}
			else {
				node = insertRecur(root.getLeft(), newword);
			}
		}
		else if (newword.compareTo(root.getWord()) > 0){
			
			// Checking if right node is null
			if (root.getRight() == null) {
				root.setRight(node);
				node = root.getRight();
			}
			else {
				node = insertRecur(root.getRight(), newword);
			}
		}
		// If node word is equal to newword
		else {
			root.increment();
			node = root;
		}
		
		return node;
		
	} //insertRecur
	
	
	// wordCount returns the sum of all occurence counters in all the nodes of the BST
	public int wordCount() {
		
		return traverseCount(root);
		
	} //wordCount
	
	
	// Recursive traversal that counts all instance counters
	private int traverseCount(BTNode node) {
		
		// Checking if node has value
		if (node != null) {
			int left = traverseCount(node.getLeft());
			int right = traverseCount(node.getRight());
			
			// Totalling occurances
			return right + left + node.getCount();
		}
		else {
			return 0;
		}
		
	} //traverseCount
	
	
	// display prints BST in formatted output
	public void display() {
		
		inOrderTraversal(root);
		
		System.out.println("");
		System.out.println("Total Word Count: " + wordCount());
		
	} //display
	
	
	// Traverses the tree in order
	private void inOrderTraversal(BTNode node) {
		
		// Checking if node has value
		if (node != null) {
			inOrderTraversal(node.getLeft());
			visit(node);
			inOrderTraversal(node.getRight());
		}
		
	} //inOrderTraversal
	
	
	// Traverses the tree in order for searchAverage()
	private void inOrderTraversalAverage(BTNode node) {

		// Checking if node has value
		if (node != null) {
			inOrderTraversalAverage(node.getLeft());
			visitAverage(node);
			inOrderTraversalAverage(node.getRight());
		}

	} // inOrderTraversal
	
	
	// visit prints current node data
	private void visit(BTNode node) {
		
		// Checking if its the start of a new row
		if (count % 4 == 0) {
			System.out.print("|");
		}
		
		// Incrementing count
		count++;
		
		// Checking if its reached 4 columns
		if (count % 4 == 0) {
			System.out.printf("%s %3s |\n", (node.getWord() + "          ").substring(0, 10), Integer.toString(node.getCount()));
		}
		else {
			System.out.printf("%s %3s |", (node.getWord() + "          ").substring(0, 10), Integer.toString(node.getCount()));
		}
		
	} //visit
	
	
	// visitAverage method for aveSearch() that prints the String and the number of searches it took to find the string, then adds count to total variable
	private void visitAverage(BTNode node) {
		
		// Counting number of searches
		int count =  searchCount(node.getWord());
		
		// Printing word and result
		System.out.println("Word: " + (node.getWord() + "          ").substring(0, 10) + "  Searches: " + count);
		
		total = total + count;
		
	} //visitAverage
	
	
	// Height calculates the height of the tree
	public int height() {
		
		// Calling recursive private height function
		return heightRecur(root, -1);
		
	} //height
	
	
	// Private recursive function for finding tree height
	private int heightRecur(BTNode node, int level) {
		
		// Initializing return int
		int result = level;
		
		// Checking if node is null
		if (node != null) {
			
			result = Math.max(heightRecur(node.getLeft(), level + 1), heightRecur(node.getRight(), level + 1));
			
		}
		
		return result;
		
	} //heightRecur
	
	
	// nodeCount returns number of nodes in tree
	public int nodeCount() {
		
		return nodeCountRecur(root);
		
	} //nodeCount
	
	
	// nodeCountRecur recursively counts the nodes in the tree
	private int nodeCountRecur(BTNode node) {
		
		// Initializing default return
		int result = 0;
		
		// Checking if node is null
		if (node != null) {
			
			result = 1 + nodeCountRecur(node.getLeft()) + nodeCountRecur(node.getRight());
			
		}
		
		return result;
		
	} //nodeCountRecur
	
	
	// searchCount calculates the number of comparisons to find a String value in the tree
	public int searchCount(String string) {
		
		return searchCountRecur(root, string);
		
	} //searchCount
	
	
	//searchCountRecur is a recursive private function that returns the number of searches needed to find a String in tree
	private int searchCountRecur(BTNode node, String string) {
		
		// Initiailizing default return
		int result = 1;
		
		// Checking if current node is null
		if (node != null) {
			
			// Checking if node is storing value
			if (string.compareToIgnoreCase(node.getWord()) == 0) {
				
				result = 2;
				
			}
			else {
				
				// Checking if node data is larger than search string
				if (string.compareToIgnoreCase(node.getWord()) < 0) {
					
					return 3 + searchCountRecur(node.getLeft(), string);
					
				}
				else {
					
					return 3 + searchCountRecur(node.getRight(), string);
					
				}
			}
			
		}
		
		return result;
		
	} //stringCountRecur
	
	
	// aveSearch returns the average number of comparisons to find a String in tree
	public int aveSearch() {
		
		// Initializing return
		int averageSearches = 0;
		
		// Traversing tree and calculating total number of searches
		inOrderTraversalAverage(root);
		
		// Calculating number of nodes in tree
		int nodeCount = nodeCount();
		
		// Calculating average number of searches
		// Checking if there are nodes in tree
		if (nodeCount != 0) {
			
			averageSearches = total / nodeCount;
			
		}
		
		return averageSearches;
		
	} //aveSearch
	
} //BSTree












































