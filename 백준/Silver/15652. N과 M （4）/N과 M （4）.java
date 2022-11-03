import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(str.nextToken());
		int m = Integer.parseInt(str.nextToken());

		int[] sel = new int[m];

		recur(n, sel, 0, 0);
		System.out.println(sb);

	}

	private static void recur(int n, int[] sel, int idx, int num) {
		if (idx == sel.length) {
			for (int i = 0; i < sel.length; i++) {
				sb.append(sel[i]).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 0; i < n; i++) {
			if (i + 1 >= num) {
				sel[idx] = i + 1;
				recur(n, sel, idx + 1, i + 1);
			}
		}
	}

}
