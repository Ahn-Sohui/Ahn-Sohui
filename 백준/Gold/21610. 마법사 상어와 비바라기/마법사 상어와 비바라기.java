import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[][] map;
	static boolean[][][] cloud;
	static Queue<Pair> water = new LinkedList<>(); // 물이 증가한 바구니의 좌표

	static int[] dr = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dc = { -1, -1, 0, 1, 1, 1, 0, -1 }; // 구름의 이동방향

	static int[] spreadR = { -1, -1, 1, 1 };
	static int[] spreadC = { -1, 1, 1, -1 }; // 물복사버그

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(br.readLine());
		n = Integer.parseInt(str.nextToken());
		m = Integer.parseInt(str.nextToken());

		map = new int[n][n];
		cloud = new boolean[2][n][n]; // 구름의 위치체크

		for (int i = 0; i < n; i++) {
			str = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(str.nextToken());
			}
		}

		// 구름의 초기위치
		for (int i = 1; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				cloud[1][n - i][j] = true;
			}
		}
//		System.out.println("start");
//		print(cloud[1]);
//		System.out.println("---------");
		// print(map);
		for (int command = 0; command < m; command++) {
			str = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(str.nextToken());
			int s = Integer.parseInt(str.nextToken());

			// 구름 이동
			cloudMoving(dir, s);
//			System.out.println("move");
//			print(cloud[1]);
//			System.out.println("===========");
			// 비 내리기
			Rain();
//			System.out.println("pre");
//			print(cloud[0]);
//			System.out.println("===========");

//			System.out.println("rain");
//			print(map);
//			System.out.println("=========");
			// 물복사 버그
			copyWater();

			// 구름 생성
			makeCloud();
//			System.out.println("make:" + command);
//
//			print(cloud[1]);
//			System.out.println("============");
		}

		int res = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				res += map[i][j];
			}
		}

		System.out.println(res);
	}

	private static void print(boolean[][] bs) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(bs[i][j] + " ");
			}
			System.out.println();
		}

	}

	private static void Rain() {
		// 비내리기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (cloud[1][i][j]) {
					map[i][j]++;
					water.offer(new Pair(i, j));
					cloud[1][i][j] = false;
					cloud[0][i][j] = true; // 구름이 사라짐
				}
			}
		}

	}

	private static void makeCloud() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] >= 2) {
					if (!cloud[0][i][j]) {
						map[i][j] -= 2;
						cloud[1][i][j] = true;
					} else {
						cloud[0][i][j] = false;
					}
				}
			}
		}

	}

	private static void copyWater() {
		// 구름이 있는 칸의 물 증가
		while (!water.isEmpty()) {
			int cnt = 0;
			Pair tmp = water.poll();
			for (int i = 0; i < 4; i++) {
				int rr = tmp.r + spreadR[i];
				int cc = tmp.c + spreadC[i];

				if (rr < 0 || rr >= n || cc < 0 || cc >= n)
					continue;
				if (map[rr][cc] >= 1)
					cnt++;
			}
			map[tmp.r][tmp.c] += cnt;
		}
	}

	private static void cloudMoving(int dir, int s) {
		Queue<Pair> q = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (cloud[1][i][j]) {
					q.offer(new Pair(i, j)); // 현재 구름의 위치
					cloud[1][i][j]=false;
					//System.out.println(i+" "+j);
				}
			}
		}
		
		
		
		int size = q.size();
		for (int i = 0; i < size; i++) {

			Pair tmp = q.poll();
			
			int rr = tmp.r + dr[dir - 1] * s;
			int cc = tmp.c + dc[dir - 1] * s; // 정해진 방향으로 정해진 거리만큼 이동

			while (!isValid(rr))
				rr = change(rr);
			while (!isValid(cc))
				cc = change(cc);
			//System.out.println("rr,cc" + rr + " " + cc);
			//
			cloud[1][rr][cc] = true;
		}

	}

	private static int change(int x) {
		return x<0?x+n:x-n;
	}

	private static boolean isValid(int x) {
		return x>=0&&x<n;
	}

	private static void print(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	static class Pair {
		int r, c;

		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
