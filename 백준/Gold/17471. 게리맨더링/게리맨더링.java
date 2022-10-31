import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, res = Integer.MAX_VALUE; // 구역의 수 , 인구 차이
	static int[] section; // 구역의 인구 수 정보
	static int[][] near; // 인접한 구역의 정보

	static boolean[] sel;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer str = new StringTokenizer(br.readLine());
		section = new int[n + 1];
		near = new int[n + 1][n + 1];
		int totalSum = 0;
		for (int i = 1; i <= n; i++) {
			section[i] = Integer.parseInt(str.nextToken());
			totalSum += section[i];
		}

		for (int i = 1; i <= n; i++) {
			str = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(str.nextToken());
			for (int j = 0; j < num; j++) {
				int tmp = Integer.parseInt(str.nextToken());
				near[i][tmp] = near[tmp][i] = 1;
			}
		}

		sel = new boolean[n + 1];

		gerryMandering(1, totalSum);

		if (res == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(res);
	}

	private static void gerryMandering(int idx, int sum) {
		if (idx == n) {
			int tmp = 0;
			List<Integer> part1 = new ArrayList<>();
			List<Integer> part2 = new ArrayList<>();
			for (int i = 1; i <= n; i++) {
				if (sel[i]) {
					part1.add(i); // 구역 나누기
				} else
					part2.add(i);
			}
			if (part1.size() + part2.size() != n)
				return;
			if (part1.size() == 0 || part2.size() == 0)
				return;
			if (check(part1, '1') && check(part2, '2')) {
				// 유효성 검사 후 인구 수 비교
				for (int i = 0; i < part1.size(); i++) {
					tmp += section[part1.get(i)];
				}
				// System.out.println(part1);
				int tmp2 = sum - tmp;
				res = Math.min(res, Math.abs(tmp - tmp2));
			} else {

				return;
			}

			return;
		}
		// System.out.println(idx);
		// 구역에 포함
		sel[idx] = true;
		gerryMandering(idx + 1, sum);

		// 미포함
		sel[idx] = false;
		gerryMandering(idx + 1, sum);
	}

	private static boolean check(List<Integer> part, char team){
		//연결되었나 확인
		boolean[] connect=new boolean[n+1];
		int now=part.get(0); // 현재번호
		Queue<Integer> q=new LinkedList<>();
		q.offer(now);
		connect[now]=true;
		
		while(!q.isEmpty()) {
			int tmp=q.poll();
			for(int i=0;i<near[tmp].length;i++) {
				int next=0;
				if(near[tmp][i]==1) next=i; //tmp와 연결된 번호
				if(connect[next]) continue;
				if((team=='1'&& sel[next]) ||(team=='2' && !sel[next])) {
					//같은 구역이라면
					q.offer(next);
					connect[next]=true;
				}
			}
		}
		
		for(int i=0;i<part.size();i++) {
			if(!connect[part.get(i)]) return false; //연결 안된 구역이 있다면
		}
		
    	return true;
    }

	private static void print(int[][] map2) {
		for (int i = 1; i < map2.length; i++) {
			for (int j = 1; j < map2[i].length; j++) {
				System.out.print(map2[i][j] + " ");
			}
			System.out.println();
		}
	}

}
