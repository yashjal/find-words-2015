import java.util.ArrayList;

/**
 * The class represent a dictionary of words. 
 * It provides a way of searching through the dictionary.
 * It also can produce a dictionary in which the words are limited
 * to a particular length. 
 * The dictionary is a binary search tree of strings.
 * 
 * @author Yash Jalan
 * @version 12/1/15
 */
public class Dictionary extends BST<String> implements DictionaryInterface{

	/**
	 * Creates an empty Dictionary object (no words).
	 */
	public Dictionary ( ) {
		super();
	}
	
	/**
	 * Creates a Dictionary object containing all words from the 
	 * listOfWords passed as a parameter. Stored in a BST.
	 * @param listOfWords the list of words to be stored in the newly created 
	 * Dictionary object
	 */
	public Dictionary ( ArrayList < String > listOfWords ) {
		//default super()
		int min = 0;
		int max = listOfWords.size() - 1;
		
		
		insertTree(min, max, listOfWords);
	}
	
	/**
	 * Recursive method that adds a sorted list of words
	 * in a nicely balanced binary search tree.
	 * It gets the middle element of every sub-list, adds 
	 * it to the tree. Binary-search like implementation.
	 * @param low starting index of sublists
	 * @param high ending index of sublists
	 * @param listOfWords list of words to be represented in the BST
	 */
	private void insertTree(int low, int high, ArrayList <String> listOfWords) {
		//BASE CASE 1
		if (low == high)
			insert(listOfWords.get(low));
		
		//BASE CASE 2
		else if ((low + 1) == high) {
			insert(listOfWords.get(low));
			insert(listOfWords.get(high));
		}
		
		else {
			int mid = (low + high) / 2;
			
			//insert the middle elements to the tree
			insert(listOfWords.get(mid));
			
			insertTree(low, mid - 1, listOfWords);
			insertTree(mid + 1, high, listOfWords);
		}		
	}
	 
	/**
	 * Performs (binary) search in this Dictionary object for a given word.
	 * @param word  the word to look for in this Dictionary object. 
	 * @return true if the word is in this Dictionary object, false otherwise
	 */
	@Override
	public boolean findWord ( String word ) {

		return contains(word);
	}
	
	/**
	 * Performs (binary) search in this Dictionary object for a given prefix.
	 * @param prefix  the prefix to look for in this Dictionary object. 
	 * @return true if at least one word with the specified prefix exists 
	 * in this Dictionary object, false otherwise
	 */
	@Override
	public boolean findPrefix (String prefix ) {
		
		return isPrefixInDictionaryRecursive (prefix, root);
	}

	/**
	 * Recursive method that performs a binary search on the dictionary tree.
	 * Difference between the contains and this method is that before every
	 * recursive call it checks whether the current word starts with the 
	 * given prefix.
	 * @param prefix the prefix to look for in the dictionary
	 * @param currentNode current word in the dictionary
	 * @return true if at least one word with the specified prefix exists 
	 * in this Dictionary object, false otherwise
	 */
	private boolean isPrefixInDictionaryRecursive(String prefix, BSTNode<String> currentNode) {
		
		if (currentNode == null)
			return false;
		
		//can use startsWith as this object is string BST
		if (currentNode.getData().startsWith(prefix))
			return true;
		
		else if (prefix.compareTo(currentNode.getData()) < 0 )
			return isPrefixInDictionaryRecursive(prefix, currentNode.getLeft());
		
		else
			return isPrefixInDictionaryRecursive( prefix, currentNode.getRight());

	}
}