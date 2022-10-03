import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, cheeseCnt, res;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static boolean[][] visited;
	static ArrayList<Pair> cheese;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		cheeseCnt = 0;
		map = new int[N][M];
		cheese = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					cheese.add(new Pair(i, j));
					cheeseCnt += 1;
				}
			}
		}
		
		res = 0;
		while (cheeseCnt != 0) {
			res++;
			visited = new boolean[N][M];
			bfs();
			solution();
		}
		
		System.out.println(res);
	}

	private static void bfs() {
		Queue<Pair> Q = new LinkedList<>();
		Q.add(new Pair(0, 0));
		visited[0][0] = true;
		map[0][0] = -1;
		
		while (!Q.isEmpty()) {
			Pair tmp = Q.poll();
			
			for (int d = 0; d < 4; d++) {
				int rr = tmp.r+dr[d];
				int cc = tmp.c+dc[d];
				
				if (rr >= 0 && rr < N && cc >= 0 && cc < M && map[rr][cc] != 1) {
					if (!visited[rr][cc]) {
						visited[rr][cc] = true;
						map[rr][cc] = 2;
						Q.add(new Pair(rr, cc));
					}
				}
			}
		}
		
	}

	private static void solution() {
		for (int i = 0; i < cheese.size(); i++) {
			int r = cheese.get(i).r;
			int c = cheese.get(i).c;
			
			int contactCnt = 0;
			for (int d = 0; d < 4; d++) {
				int rr = r+dr[d];
				int cc = c+dc[d];
				
				if (map[rr][cc] == 2) contactCnt++;
			}
			
			if (contactCnt >= 2) {
				map[r][c] = 0;
				cheeseCnt--;
				cheese.remove(i);
				i--;
			}
		}
	}

	static class Pair {
		int r,c;
		
		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}