import java.util.Scanner;

public class Main {
	final boolean DEBUG = false;
	class Node {
		int key;
		Node p, l, r;
		Node(int key) {
			this.key = key;
		}
		void insert(Node z) {
			Node y = null;
			Node x = this;
			while (x != null) {
				y = x;
				if (z.key < x.key)
					x = x.l;
				else
					x = x.r;
			}
			z.p = y;
			
			if (z.key < y.key)
				y.l = z;
			else
				y.r = z;
		}
		void print() {
			this.inorder();
			System.out.println();
			this.preorder();
			System.out.println();
		}
		void preorder() {
			System.out.print(" " + this.key);
			if (this.l != null)
				this.l.preorder();
			if (this.r != null)
				this.r.preorder();
		}
		void inorder() {
			if (this.l != null)
				this.l.inorder();
			System.out.print(" " + this.key);
			if (this.r != null)
				this.r.inorder();
		}
	}
	void run() {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		Node t = null;
		for (int i = 0; i < n; i++) {
			String inst = scan.next();
			// set a root node
			if (i == 0) {
				int key = scan.nextInt();
				t = new Node(key);
				continue;
			}
			
			switch(inst) {
			case "insert":
				int key = scan.nextInt();
				if (DEBUG)
					System.out.println("insert: " + key);
				t.insert(new Node(key));
				break;
			case "print":
				if (DEBUG)
					System.out.println("print");
				t.print();
				break;
			}
		}
	}
	public static void main(String[] args) {
		new Main().run();
	}
}
