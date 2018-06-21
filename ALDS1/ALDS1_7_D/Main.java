import java.util.Scanner;

public class Main {
	final boolean DEBUG = false;
	static class Node {
		private static boolean isFirstPrint = false;

		int id;
		Node l, r;
		Node(int id) {
			this.id = id;
		}

		void printPostOrder() {
			isFirstPrint = true;
			this.postorder();
		}
		void postorder() {
			if (this.l != null)
				this.l.postorder();
			if (this.r != null)
				this.r.postorder();
			System.out.print((isFirstPrint ? "" : " ") + this.id);
			isFirstPrint = false;
		}
	}
	Node[] nodes;
	int[] preIds, inIds;
	int preIndex, inIndex;

	void run() {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		nodes = new Node[n + 1];
		preIds = new int[n];
		inIds = new int[n];
		preIndex = inIndex = 0;

		for (int i = 1; i <= n; i++)
			nodes[i] = new Node(i);
		for (int i = 0; i < n; i++)
			preIds[i] = scan.nextInt();
		for (int i = 0; i < n; i++)
			inIds[i] = scan.nextInt();

		int rootId = preIds[0];
		nodes[rootId] = makeTree(inIds);

		nodes[rootId].printPostOrder();
		System.out.println();
	}

	Node makeTree(int[] ids) {
		if (ids.length == 0)
			return null;
		if (ids.length == 1) {
			preIndex++;
			return nodes[ids[0]];
		}
		int rootId = preIds[preIndex++];
		int[] lIds = null, rIds = null;
		int lidx = 0, ridx = 0;
		for (int i = 0; i < ids.length; i++) {
			if (rootId != ids[i])
				continue;
			lidx = i;
			ridx = ids.length - i - 1;
			if (DEBUG)
				System.out.println("\nlidx, ridx:" + lidx + ", " + ridx);
			lIds = new int[lidx];
			rIds = new int[ridx];
			for (int j = 0; j < lidx; j++)
				lIds[j] = ids[j];
			for (int j = 0; j < ridx; j++)
				rIds[j] = ids[i + j + 1];
		}
		if (DEBUG) {
			System.out.print("ids: ");
			for (int i = 0; i < ids.length; i++)
				System.out.print(ids[i] + " ");
			System.out.println("\nrootId: " + rootId);
			System.out.print("lIds: ");
			for (int i = 0; i < lIds.length; i++)
				System.out.print(lIds[i] + " ");
			System.out.print("\nrIds: ");
			for (int i = 0; i < rIds.length; i++)
				System.out.print(rIds[i] + " ");
			System.out.println();
		}
		nodes[rootId].l = makeTree(lIds);
		nodes[rootId].r = makeTree(rIds);
		return nodes[rootId];
	}

	public static void main(String[] args) {
		new Main().run();
	}
}
