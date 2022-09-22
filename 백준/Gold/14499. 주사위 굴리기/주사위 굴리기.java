import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, m, x, y, k;
	static int[][] map;
	static int[] dice;

	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { 1, -1, 0, 0 }; // 동,서,북,남

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(br.readLine());

		n = Integer.parseInt(str.nextToken());
		m = Integer.parseInt(str.nextToken());
		x = Integer.parseInt(str.nextToken());
		y = Integer.parseInt(str.nextToken());
		k = Integer.parseInt(str.nextToken());

		map = new int[n][m];
		dice = new int[7];
		for (int i = 0; i < n; i++) {
			str = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(str.nextToken());
			}
		}
		//print(map);
		str = new StringTokenizer(br.readLine());

		for (int i = 0; i < k; i++) {
			int dir = Integer.parseInt(str.nextToken());
			// 방향에 맞게 주사위 움직이기
			moveDice(dir);
		}

	}

	private static void print(int[][] map2) {
		for(int i=0;i<map2.length;i++) {
			for(int j=0;j<map[i].length;j++) {
				System.out.print(map2[i][j]+" ");
			}
			System.out.println();
		}
		
	}

	private static void moveDice(int dir) {
		// 주사위 움직이기
		int rr = x + dr[dir - 1];
		int cc = y + dc[dir - 1];

		// 범위를 벗어나면 종료
		if (rr < 0 || rr >= n || cc < 0 || cc >= m)
			return;
		
		x=rr;
		y=cc;
		
		rollDice(dir);
		
		if(map[rr][cc]==0) {
			map[rr][cc]=dice[6];
		}else {
			dice[6]=map[rr][cc];
			map[rr][cc]=0;
		}
		System.out.println(dice[1]);
	}

	private static void rollDice(int dir) {
		// 위에 수 저장
		int tmp = dice[1];
		switch (dir) {
		case 1:
			dice[1] = dice[4];
			dice[4] = dice[6];
			dice[6] = dice[3];
			dice[3] = tmp;
			break;
		case 2:
			dice[1] = dice[3];
			dice[3] = dice[6];
			dice[6] = dice[4];
			dice[4] = tmp;
			break;
		case 3:
			dice[1] = dice[5];
			dice[5] = dice[6];
			dice[6] = dice[2];
			dice[2] = tmp;
			break;
		case 4:
			dice[1] = dice[2];
			dice[2] = dice[6];
			dice[6] = dice[5];
			dice[5] = tmp;
			break;
		}
	}

}
