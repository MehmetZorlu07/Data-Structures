
public class DoublyLinkedList {

	// Constructing the DLL and the nodes
	protected Node head;
	protected int size;

	protected class Node {
		protected int key;
		protected Node next;
		protected Node prev;

		public Node(int key, Node next, Node prev) {
			this.key = key;
			this.next = next;
			this.prev = prev;
		}
	}

	public DoublyLinkedList() {
		head = null;
		size = 0;
	}

	// Five main operations of the DoublyLinkedList class:

	// Add element to the set if it is not present already.
	public void add(int element) {
		if (!isElement(element)) {
			Node n = new Node(element, head, null);
			if (head != null)
				head.prev = n;
			head = n;
			size++;
		}
	}

	// Remove element from the set if it is present (Uses search method).
	public void remove(int element) {
		if (isElement(element)) {
			Node nodeToDelete = search(element);
			if (nodeToDelete.prev != null) {
				nodeToDelete.prev.next = nodeToDelete.next;
			} else {
				head = nodeToDelete.next;
			}
			if (nodeToDelete.next != null) {
				nodeToDelete.next.prev = nodeToDelete.prev;
			}
			size--;
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
		return (size == 0);
	}

	// Returns the number of elements of set.
	public int setSize() {
		return this.size;
	}

	// Additional set-theoretical operations:

	// Returns the union of two sets.
	public DoublyLinkedList union(DoublyLinkedList Second) {
		DoublyLinkedList unionSet = new DoublyLinkedList();
		unionSet = unionSet.copyMaker(this);
		Node n = Second.head;
		while (n != null) {
			if (!unionSet.isElement(n.key)) {
				unionSet.add(n.key);
			}
			n = n.next;
		}
		return unionSet;
	}

	// Returns the intersection of two sets.
	public DoublyLinkedList intersection(DoublyLinkedList Second) {
		DoublyLinkedList intersectionSet = new DoublyLinkedList();
		DoublyLinkedList tempSet = new DoublyLinkedList();
		tempSet = tempSet.copyMaker(this);
		Node n = Second.head;
		while (n != null) {
			if (tempSet.isElement(n.key)) {
				intersectionSet.add(n.key);
			}
			n = n.next;
		}
		return intersectionSet;
	}

	// Returns the difference of two sets.
	public DoublyLinkedList difference(DoublyLinkedList Second) {
		DoublyLinkedList differenceSet = new DoublyLinkedList();
		DoublyLinkedList tempSet = new DoublyLinkedList();
		tempSet = tempSet.copyMaker(this);
		Node n = tempSet.head;
		while (n != null) {
			if (!Second.isElement(n.key)) {
				differenceSet.add(n.key);
			}
			n = n.next;
		}
		return differenceSet;
	}

	// Checks whether the first set is a subset of the second set.
	public boolean subset(DoublyLinkedList Second) {
		Node n = this.head;
		while (n != null) {
			if (!Second.isElement(n.key)) {
				return false;
			}
			n = n.next;
		}
		return true;
	}

	// Helper methods:

	// Searches the set to find and return the chosen element's node.
	public Node search(int element) {
		Node x = head;
		while (x != null && x.key != element) {
			x = x.next;
		}
		return x;
	}

	// Iterates through every node and prints them.
	public void iterateForward() {
		Node tmp = head;
		System.out.print("{");
		while (tmp != null) {
			System.out.print(tmp.key + ",");
			tmp = tmp.next;
		}
		System.out.println("}\n");
	}

	// Creates a copy of the set.
	public DoublyLinkedList copyMaker(DoublyLinkedList original) {
		DoublyLinkedList copySet = new DoublyLinkedList();
		Node n = original.head;
		while (n != null) {
			copySet.add(n.key);
			n = n.next;
		}
		return copySet;
	}

	// Testing in main:
	public static void main(String args[]) {

		DoublyLinkedList first = new DoublyLinkedList();
		first.add(72);
		first.add(7);
		first.add(43);
		first.add(31);
		first.add(16);
		first.add(65);
		first.add(65);
		System.out.println("First set after adding 7 elements (2 same):");
		first.iterateForward();

		DoublyLinkedList second = new DoublyLinkedList();
		second.add(31);
		second.add(58);
		second.add(92);
		second.add(43);
		second.add(11);
		System.out.println("Second set after adding 5 elements:");
		second.iterateForward();

		System.out.println("Deleting '58' and a non-element from the second set:");
		second.remove(58);
		second.remove(18);
		second.iterateForward();

		System.out.println("Is '31' an element of the second set?");
		System.out.println(second.isElement(31) + "\n");

		System.out.println("Is '22' an element of the second set?");
		System.out.println(second.isElement(22) + "\n");

		System.out.println("Is the second set empty?");
		System.out.println(second.setEmpty() + "\n");

		System.out.println("Size of the second set:");
		System.out.println(second.setSize() + "\n");

		System.out.print("First set:");
		first.iterateForward();
		System.out.print("Second set:");
		second.iterateForward();

		System.out.println("Union of the first and the second set: ");
		DoublyLinkedList unionSet = new DoublyLinkedList();
		unionSet = first.union(second);
		unionSet.iterateForward();

		System.out.println("Intersection of the first and the second set: ");
		DoublyLinkedList intersectionSet = new DoublyLinkedList();
		intersectionSet = first.intersection(second);
		intersectionSet.iterateForward();

		System.out.println("Difference of the first and the second set: ");
		DoublyLinkedList differenceSet = new DoublyLinkedList();
		differenceSet = first.difference(second);
		differenceSet.iterateForward();

		System.out.println("Is the first set a subset of the second set? ");
		System.out.println(first.subset(second) + "\n");

		DoublyLinkedList sub = new DoublyLinkedList();
		sub.add(31);
		sub.add(92);
		sub.add(43);
		System.out.println("Is the set {31,92,43} a subset of the second set? ");
		System.out.println(sub.subset(second));
	}
}
