package edu.awieclawski.chlng.btree;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * CODERBYTE
 * 
 * ArrayChallenge(strArr) take the array of strings stored in strArr, which will
 * represent a binary tree, and determine if the tree is symmetric (a mirror
 * image of itself). The array will be implemented similar to how a binary heap
 * is implemented, except the tree may not be complete and NULL nodes on any
 * level of the tree will be represented with a #. For example: if strArr is
 * ["1", "2", "2", "3", "#", "#", "3"] then this tree looks like the following:
 * 
 */
class BTreeFromArray {

	protected final static Logger LOGGER = Logger.getLogger(BTreeFromArray.class.getName()); // java.util.logging.Logger
	private static String ORDERED_NODES;

	// Java program to check is
	// binary tree is symmetric or not
	public class Node {
		String key;
		Node left, right;

		Node(String item) {
			key = item;
			left = right = null;
		}
	}

	Node root;

	// Function to insert nodes in level order
	public Node insertLevelOrder(String[] arr, Node root, int i) {
		// Base case for recursion
		if (i < arr.length) {
			Node temp = new Node(arr[i]);
			root = temp;

			// insert left child
			root.left = insertLevelOrder(arr, root.left, 2 * i + 1);

			// insert right child
			root.right = insertLevelOrder(arr, root.right, 2 * i + 2);
		}
		return root;
	}

	// Function to build tree nodes in InOrder fashion
	public void inOrder(Node root) {
		if (root != null) {
			inOrder(root.left);
			ORDERED_NODES += root.key + " ";
			inOrder(root.right);
		}
	}

	// may be ArrayChallenge(strArr) in the challenge
	static String isByteTreeFromArraySymetric(String[] arr) {
		ORDERED_NODES = ""; // reset
		String result = "";
		BTreeFromArray tree = new BTreeFromArray();

		tree.root = tree.insertLevelOrder(arr, tree.root, 0);

		boolean output = tree.isSymmetric();

		if (output == true)
			result = "Symmetric"; // or true
		else
			result = "Not symmetric"; // or false

		tree.inOrder(tree.root);

		LOGGER.log(Level.INFO, "ordered nodes: [ " + ORDERED_NODES + "]");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	// returns true if trees
	// with roots as root1 and root2are mirror
	boolean isMirror(Node node1, Node node2) {
		// if both trees are empty,
		// then they are mirror image
		if (node1 == null && node2 == null)
			return true;

		// For two trees to be mirror images, the following
		// three conditions must be true 1 - Their root
		// node's key must be same 2 - left subtree of left
		// tree and right subtree
		// of right tree have to be mirror images
		// 3 - right subtree of left tree and left subtree
		// of right tree have to be mirror images
		if (node1 != null && node2 != null && node1.key == node2.key)
			return (isMirror(node1.left, node2.right) && isMirror(node1.right, node2.left));

		// if none of the above conditions is true then
		// root1 and root2 are not mirror images
		return false;
	}

	// returns true if the tree is symmetric i.e
	// mirror image of itself
	boolean isSymmetric() {
		// check if tree is mirror of itself
		return isMirror(root, root);
	}

	// Driver code
	public static void main(String args[]) {

		String[] strArr = { "1", "2", "2", "3", "4", "4", "3" }; // true

		System.out.println(isByteTreeFromArraySymetric(strArr) + "\n");

		strArr = new String[] { "1", "2", "2", "3", "#", "#", "3" }; // true

		System.out.println(isByteTreeFromArraySymetric(strArr) + "\n");

		strArr = new String[] { "4", "3", "4" }; // false

		System.out.println(isByteTreeFromArraySymetric(strArr) + "\n");

		strArr = new String[] { "10", "2", "2", "#", "1", "1", "#" }; // true

		System.out.println(isByteTreeFromArraySymetric(strArr) + "\n");

	}
}
