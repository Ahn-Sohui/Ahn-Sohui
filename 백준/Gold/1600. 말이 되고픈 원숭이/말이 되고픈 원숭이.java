import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int w, h, k;
	static Queue<Pair> q = new LinkedList<>();
	static boolean[][][] visited;
	static int[][] dis;

	static int[] dr_h = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] dc_h = { 1, 2, 2, 1, -1, -2, -2, -1 };
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		StringTokenizer str = new StringTokenizer(br.readLine());
		w = Integer.parseInt(str.nextToken());
		h = Integer.parseInt(str.nextToken());

		map = new int[h][w];
		//말방식으로 이동한 횟수만큼 방문 배열 체크
		visited = new boolean[h][w][k+1];
		dis = new int[h][w];

		for (int i = 0; i < h; i++) {
			str = new StringTokenizer(br.readLine());
			for (int j = 0; j < w; j++) {
				map[i][j] = Integer.parseInt(str.nextToken());
				dis[i][j]=Integer.MAX_VALUE;
			}
		}
		// print(map);
		q.offer(new Pair(0, 0, k));
		dis[0][0]=0;
		visited[0][0][k]=true;
		while (!q.isEmpty()) {
			Pair tmp=q.poll();
			if(tmp.r==h-1 && tmp.c==w-1) break;
			for(int i=0;i<4;i++) {
				int rr=tmp.r+dr[i];
				int cc=tmp.c+dc[i];
				if(rr<0|rr>=h||cc<0||cc>=w||visited[rr][cc][tmp.k]||map[rr][cc]==1) continue;
				visited[rr][cc][tmp.k]=true;
				dis[rr][cc]=dis[tmp.r][tmp.c]+1;
				q.offer(new Pair(rr,cc,tmp.k));
			}
			
			if(tmp.k>0) {
				//말방식 이동
				for(int i=0;i<8;i++) {
					int rr=tmp.r+dr_h[i];
					int cc=tmp.c+dc_h[i];
					if(rr<0|rr>=h||cc<0||cc>=w||visited[rr][cc][tmp.k-1]||map[rr][cc]==1) continue;
					
					visited[rr][cc][tmp.k-1]=true;
					dis[rr][cc]=dis[tmp.r][tmp.c]+1;
					q.offer(new Pair(rr,cc,tmp.k-1));
				}
			}
			
		}
		//print(dis);
		if(dis[h-1][w-1]==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(dis[h-1][w-1]);
	}

	private static void print(int[][] map2) {
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				System.out.print(map2[i][j] + " ");
			}
			System.out.println();
		}

	}

	static class Pair {
		int r, c, k;

		Pair(int r, int c,int k) {
			this.r = r;
			this.c = c;
			this.k=k;
		}
	}
}
