import java.util.ArrayList;

public class BinarySearchTree {

	// Constructing the BST and the nodes
	protected Node root;

	protected class Node {
		protected int key;
		protected Node left, right, p;

		public Node(int key) {
			this.key = key;
			this.left = null;
			this.right = null;
			this.p = null;
		}
	}

	public BinarySearchTree() {
		root = null;
	}

	// Five main operations of the BinarySearchTree class:

	// Add element to the set if it is not present already.
	public void add(int element) {
		if (!isElement(element)) {
			Node n = new Node(element);
			Node y = null;
			Node x = root;
			while (x != null) {
				y = x;
				if (n.key < x.key) {
					x = x.left;
				} else {
					x = x.right;
				}
			}
			n.p = y;
			if (y == null) {
				root = n;
			} else if (n.key < y.key) {
				y.left = n;
			} else {
				y.right = n;
			}
		}
	}

	// Removes element from the set if it is present (Uses search, transplant and
	// minimum methods).
	public void remove(int element) {
		if (isElement(element)) {
			Node nodeToDelete = search(element);
			if (nodeToDelete.left == null) {
				transplant(nodeToDelete, nodeToDelete.right);
			} else if (nodeToDelete.right == null) {
				transplant(nodeToDelete, nodeToDelete.left);
			} else {
				Node y = minimum(nodeToDelete.right);
				if (y.p != nodeToDelete) {
					transplant(y, y.right);
					y.right = nodeToDelete.right;
					y.right.p = y;
				}
				transplant(nodeToDelete, y);
				y.left = nodeToDelete.left;
				y.left.p = y;
			}
		}
	}

	// Checks whether the element is in the set.
	public boolean isElement(int element) {
		Node controlNode = search(element);
		if (controlNode == null) {
			return false;
		}
		return (controlNode.key == element);
	}

	// Checks whether the set is empty.
	public boolean setEmpty() {
		return (setSize(root) == 0);
	}

	// Returns the number of elements of set.
	public int setSize(Node x) {
		if (x == null)
			return 0;
		return setSize(x.left) + setSize(x.right) + 1;
	}

	// Additional set-theoretical operations:

	// Returns the union of two sets.
	public BinarySearchTree union(BinarySearchTree otherSet) {
		BinarySearchTree unionSet = new BinarySearchTree();
		unionSet = unionSet.copyMaker(this);
		ArrayList<Integer> treeArray = new ArrayList<Integer>();
		treeArray = inorder(otherSet.root, treeArray);
		for (int element : treeArray) {
			if (!this.isElement(element))
				unionSet.add(element);
		}
		return unionSet;
	}

	// Returns the intersection of two sets.
	public BinarySearchTree intersection(BinarySearchTree otherSet) {
		BinarySearchTree intersectionSet = new BinarySearchTree();
		BinarySearchTree tempSet = new BinarySearchTree();
		tempSet = tempSet.copyMaker(this);
		ArrayList<Integer> treeArray = new ArrayList<Integer>();
		treeArray = inorder(otherSet.root, treeArray);
		for (int element : treeArray) {
			if (tempSet.isElement(element))
				intersectionSet.add(element);
		}
		return intersectionSet;
	}

	// Returns the difference of two sets.
	public BinarySearchTree difference(BinarySearchTree Second) {
		BinarySearchTree differenceSet = new BinarySearchTree();
		BinarySearchTree tempSet = new BinarySearchTree();
		tempSet = tempSet.copyMaker(this);
		ArrayList<Integer> treeArray = new ArrayList<Integer>();
		treeArray = inorder(tempSet.root, treeArray);
		for (int element : treeArray) {
			if (!Second.isElement(element))
				differenceSet.add(element);
		}
		return differenceSet;
	}

	// Checks whether the first set is a subset of the second set.
	public boolean subset(BinarySearchTree Second) {
		ArrayList<Integer> treeArray = new ArrayList<Integer>();
		treeArray = inorder(this.root, treeArray);
		for (int element : treeArray) {
			if (!Second.isElement(element))
				return false;
		}
		return true;
	}

	// Helper methods:

	// Transplant method used in remove.
	public void transplant(Node u, Node v) {
		if (u.p == null) {
			root = v;
		} else if (u == u.p.left) {
			u.p.left = v;
		} else {
			u.p.right = v;
		}
		if (v != null)
			v.p = u.p;
	}

	// Minimum method used in remove.
	public Node minimum(Node x) {
		while (x.left != null) {
			x = x.left;
		}
		return x;
	}

	// Inorder function that returns all the elements of the set in an array.
	public ArrayList<Integer> inorder(Node x, ArrayList<Integer> returnArray) {
		if (x != null) {
			inorder(x.left, returnArray);
			returnArray.add(x.key);
			inorder(x.right, returnArray);
		}
		return returnArray;
	}

	// Print all the elements of the arraylist.
	public void arrayPrint(ArrayList<Integer> arrayToPrint) {
		System.out.print("{");
		for (int element : arrayToPrint) {
			System.out.print(element + ",");
		}
		System.out.println("}\n");
	}

	// Searches the set to find and return the chosen element's node.
	public Node search(int k) {
		Node x = root;
		while (x != null && k != x.key) {
			if (k < x.key) {
				x = x.left;
			} else {
				x = x.right;
			}
		}
		return x;
	}

	// Creates a copy of the set.
	public BinarySearchTree copyMaker(BinarySearchTree original) {
		BinarySearchTree copySet = new BinarySearchTree();
		ArrayList<Integer> treeArray = new ArrayList<Integer>();
		treeArray = inorder(original.root, treeArray);
		for (int i = 0; i < treeArray.size(); i++) {
			copySet.add(treeArray.get(i));
		}
		return copySet;
	}

	// Returns the bigger integer.
	public int max(int x, int y) {
		if (x > y) {
			return x;
		}
		return y;
	}

	// Calculates the height of a BST.
	public int height(Node x) {
		if (x == null) {
			return 0;
		}
		if (x.left == null && x.right == null) {
			return 0;
		}
		return max(height(x.left), height(x.right)) + 1;
	}

	// Testing in main:
	public static void main(String args[]) {

		BinarySearchTree first = new BinarySearchTree();
		first.add(72);
		first.add(7);
		first.add(43);
		first.add(31);
		first.add(16);
		first.add(65);
		first.add(65);
		System.out.println("First set after adding 7 elements (2 same):");
		ArrayList<Integer> array = new ArrayList<Integer>();
		first.arrayPrint(first.inorder(first.root, array));

		BinarySearchTree second = new BinarySearchTree();
		second.add(31);
		second.add(58);
		second.add(92);
		second.add(43);
		second.add(11);
		System.out.println("Second set after adding 5 elements:");
		array = new ArrayList<Integer>();
		second.arrayPrint(second.inorder(second.root, array));

		System.out.println("Deleting '58' and a non-element from the second set:");
		second.remove(58);
		second.remove(18);
		array = new ArrayList<Integer>();
		second.arrayPrint(second.inorder(second.root, array));

		System.out.println("Is '31' an element of the second set?");
		System.out.println(second.isElement(31) + "\n");

		System.out.println("Is '22' an element of the second set?");
		System.out.println(second.isElement(22) + "\n");

		System.out.println("Is the second set empty?");
		System.out.println(second.setEmpty() + "\n");

		System.out.println("Size of the second set:");
		System.out.println(second.setSize(second.root) + "\n");

		System.out.print("First set:");
		array = new ArrayList<Integer>();
		first.arrayPrint(first.inorder(first.root, array));
		System.out.print("Second set:");
		array = new ArrayList<Integer>();
		second.arrayPrint(second.inorder(second.root, array));

		System.out.println("Union of the first and the second set: ");
		BinarySearchTree unionSet = new BinarySearchTree();
		unionSet = first.union(second);
		array = new ArrayList<Integer>();
		unionSet.arrayPrint(unionSet.inorder(unionSet.root, array));

		System.out.println("Intersection of the first and the second set: ");
		BinarySearchTree intersectionSet = new BinarySearchTree();
		intersectionSet = first.intersection(second);
		array = new ArrayList<Integer>();
		intersectionSet.arrayPrint(intersectionSet.inorder(intersectionSet.root, array));

		System.out.println("Difference of the first and the second set: ");
		BinarySearchTree differenceSet = new BinarySearchTree();
		differenceSet = first.difference(second);
		array = new ArrayList<Integer>();
		differenceSet.arrayPrint(differenceSet.inorder(differenceSet.root, array));

		System.out.println("Is the first set a subset of the second set? ");
		System.out.println(first.subset(second) + "\n");

		BinarySearchTree sub = new BinarySearchTree();
		sub.add(31);
		sub.add(92);
		sub.add(43);
		System.out.println("Is the set {31,92,43} a subset of the second set? ");
		System.out.println(sub.subset(second));
	}
}
