import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int n;
	static char[][] map;
	static int[][] dist; //지나 온 검은 방의 수 누적
	
	static int[] dr= {0,1,0,-1};
	static int[] dc= {1,0,-1,0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		map=new char[n][n];
		dist=new int[n][n];
		for(int i=0;i<n;i++) {
			map[i]=br.readLine().toCharArray();
		}
		Init();
		//출발점
		dist[0][0]=0;
		makeMaze(0,0);
		
		System.out.println(dist[n-1][n-1]);
	}
	private static void Init() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				dist[i][j]=Integer.MAX_VALUE;
			}
		}
	}
	private static void makeMaze(int r, int c) {
		Queue<Pair> q=new LinkedList<>();
		q.offer(new Pair(r,c));
		while(!q.isEmpty()) {
			Pair tmp=q.poll();
			for(int i=0;i<4;i++) {
				int rr=tmp.r+dr[i];
				int cc=tmp.c+dc[i];
				
				if(rr<0||rr>=n||cc<0||cc>=n) continue;
				
				if(map[rr][cc]=='1') {
					if(dist[rr][cc]>dist[tmp.r][tmp.c]) {
						dist[rr][cc]=dist[tmp.r][tmp.c];
						q.offer(new Pair(rr,cc));
					}
				}else {
					if(dist[rr][cc]>dist[tmp.r][tmp.c]+1) {
						dist[rr][cc]=dist[tmp.r][tmp.c]+1;
						q.offer(new Pair(rr,cc));
					}
				}
			}
		}
	}
	
	static class Pair{
		int r,c;
		Pair(int r,int c){
			this.r=r;
			this.c=c;
		}
	}
}
