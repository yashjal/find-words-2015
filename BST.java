/**
 * This class is a representation of a standard binary search tree.
 * It has reference to a root node. It implements insert, remove, 
 * contains and toString methods.
 * @author Yash Jalan
 * @version 12/01/15
 * @param <E> generic reference type that is comparable
 */
public class BST<E extends Comparable<E>> implements BSTInterface<E> {
	
	protected BSTNode<E> root;
	
	/**
	 * Creates an empty BST.
	 */
	public BST() {
		root = null;
	}
	
	@Override
	public void insert(E item) {
		root = insert(item, root);
	}
	
	/**
	 * Recursively searches the leaf to which item E is added.
	 * @param data item to be added to the tree
	 * @param n current node of the tree
	 * @return current node
	 */
	private BSTNode<E> insert(E data, BSTNode<E> n) {
		
		if (n == null) {
			BSTNode<E> node = new BSTNode<E>(data);
			return node;
		}
		if (data.compareTo(n.getData()) < 0) {
			n.setLeft(insert(data, n.getLeft()));
		}
		else {
			n.setRight(insert(data, n.getRight()));
		}
		return n;
	}

	@Override
	public void remove(E item) {
		root = recRemove(root, item);	
	}
	
	/**
	 * Recursively finds the item in the tree and if found removes it
	 * by replacing it with appropriate children.
	 * If the item to be removed has two children then replaces removed
	 * item with predecessor.
	 * It uses other helper function to remove and to get predecessor.
	 * @param node current node in the tree
	 * @param item item to be removed from tree
	 * @return current node
	 */
	private BSTNode<E> recRemove(BSTNode<E> node, E item) {
		// if node equal null, then do nothing, 
		// return the node in the parameter
		if (node == null) {	
		}
		
		//find the item
		else if (item.compareTo(node.getData()) < 0) {
			node.setLeft(recRemove(node.getLeft(), item));
		}
		else if (item.compareTo(node.getData()) > 0) {
			node.setRight(recRemove(node.getRight(), item));
		}
		
		//if found, remove it and replace with appropriate child
		else
			node = remove(node);
		
		return node;
	}
	
	/**
	 * Removes the node passed in the parameter from this tree.
	 * It replaces it with either the only child, or the predecessor.
	 * @param node node to be removed
	 * @return the node that replaces the removed node
	 */
	private BSTNode<E> remove(BSTNode<E> node) {
		// if node has one child replace the child with the node
		if (node.getLeft() == null)
			return node.getRight();
		if (node.getRight() == null)
			return node.getLeft();
		
		//if it has two children, then replace the node with
		//the predecessor.
		E data = getPredecessor(node);
		node.setData(data);
		// remove the predecessor node
		node.setLeft(recRemove(node.getLeft(), data));
		return node;
	}
	
	/**
	 * The method finds the predecessor of the given node.
	 * @param n the node whose predecessor we need to find
	 * @return the data of the predecessor
	 */
	private E getPredecessor(BSTNode<E> n) {
		
		BSTNode<E> current = n.getLeft();
		
		while (current.getRight() != null) {
			current = current.getRight();
		}
		
		return current.getData();
	}

	@Override
	public boolean contains(E item) {
		
		return contains(item, root);
	}
	
	/**
	 * Recursively binary searches if the item is in this tree.
	 * @param item item to be located
	 * @param currentNode current node of the tree
	 * @return true if the item is in the tree , false otherwise
	 */
	private boolean contains(E item, BSTNode<E> currentNode) {
		if (currentNode == null)
			return false;
		
		else if (item.compareTo(currentNode.getData()) < 0)
			return contains(item, currentNode.getLeft());
		
		else if (item.compareTo(currentNode.getData()) > 0)
			return contains(item, currentNode.getRight());
		else
			return true;
	}
	
	/**
	 * Produces tree like string representation of this BST.
	 * @return string containing tree-like representation of this BST. 
	 */
	public String toStringTreeFormat() {
		StringBuilder s = new StringBuilder();

		preOrderPrint(root, 0, s);
		return s.toString();
	}
	
	/**
	 * @param tree the root of the current subtree
	 * @param level level (depth) of the current recursive call in the tree
	 * to determine the indentation of each item 
	 * @param output the string that accumulated the string representation
	 * of this BST
	 * @author Joanna Klukowska
	 */
	private void preOrderPrint(BSTNode<E> tree, int level, StringBuilder output)
	{
		if (tree != null) {
			String spaces = "\n";
			if (level > 0) {
				for (int i = 0; i < level - 1; i++)
					spaces += "   ";
				spaces += "|--";
			}
			output.append(spaces);
			output.append(tree.getData());
			preOrderPrint(tree.getLeft(), level + 1, output);
			preOrderPrint(tree.getRight(), level + 1, output);
		}
		
		else {
			String spaces = "\n";
			if (level > 0) {
				for (int i = 0; i < level - 1; i++)
					spaces += "   ";
				spaces += "|--";
			}
			output.append(spaces);
			output.append("null");
		}
	}
}

/**
 * This class represents a binary search tree or just a binary tree node.
 * Contains a left child, right child and the info data field.
 * @author Yash Jalan
 * @version 12/01/15
 * @param <T> generic reference type that is comparable
 */
class BSTNode<T extends Comparable <T>> implements Comparable <BSTNode <T>> {
	
	//reference to the left subtree 
	private BSTNode <T> left;
	//reference to the right subtree
	private BSTNode <T> right;
	//data item stored in the node
	private T data;
	
	/**
	 * Constructs a BSTNode initializing the data part 
	 * according to the parameter and setting both 
	 * references to subtrees to null.
	 * @param data data to be stored in the node
	 */
	public BSTNode(T data) {
		this.data = data;
		left = null;
		right = null;
	}
	
	/**
	 * Constructs a BSTNode initializing the data part
	 * and the subtree references according to the parameters.
	 * @param data data to be stored in the node
	 * @param left reference to the left subtree
	 * @param right reference to the right subtree
	 */
	public BSTNode( T data, BSTNode<T> left, BSTNode<T> right) {
		this.left = left;
		this.right = right;
		this.data = data;
	}
	
	/**
	 * Left subtree accessor. 
	 * @return reference to the left subtree of a node
	 */
	public BSTNode<T> getLeft() {
		return left;
	}
	
	/**
	 * Changes the reference to the left subtree to the one 
	 * specified in the parameter.
	 * @param reference to the new left subtree of the node.
	 */
	public void setLeft(BSTNode<T> left) {
		this.left = left;
	}
	
	/**
	 * Right subtree accessor. 
	 * @return reference to the right subtree of a node
	 */
	public BSTNode<T> getRight() {
		return right;
	}
	
	/**
	 * Changes the reference to the right subtree to the one 
	 * specified in the parameter.
	 * @param reference to the new right subtree of the node.
	 */
	public void setRight(BSTNode<T> right) {
		this.right = right;
	}
	
	/**
	 * Returns a reference to the data stored in the node. 
	 * @return reference to the data stored in the node
	 */
	public T getData() {
		return data;
	}
	
	/**
	 * Changes the data stored in the node to the one 
	 * specified in the parameter.
	 * @param reference to the new data of the node
	 */
	public void setData(T data) {
		this.data = data;
	}


	@Override
	public int compareTo(BSTNode<T> other) {
		return this.data.compareTo(other.data);
	} 


	@Override
	public String toString() {
		return data.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		BSTNode<T> other = (BSTNode<T>) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		return true;
	}
}
