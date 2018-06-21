import java.util.Scanner;

public class Main {
	int cost = 0;

	void run() {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int[] m = new int[n];
		int[] q = new int[n];
		int[] s = new int[n];
		for (int i = 0; i < n; i++)
			s[i] = m[i] = q[i] = scan.nextInt();

		selectionSort(s, n);
		System.out.println(cost);
	}
	
	void selectionSort(int[] a, int n) {
		for (int i = 0; i < n; i++) {
			int min_j = i;
			for (int j = i; j < n; j++) {
				if (a[j] < a[min_j])
					min_j = j;
			}
			if (i == min_j)
				continue;
			cost += a[i] + a[min_j];
			int tmp = a[i];
			a[i] = a[min_j];
			a[min_j] = tmp;
		}
	}

	public static void main(String[] args) {
		new Main().run();
	}
}
