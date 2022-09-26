import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[][] map;
	static boolean[][] sink;
	static boolean[][] visited;
	static int res=1;
	
	static int[] dr= {1,0,-1,0};
	static int[] dc= {0,1,0,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		
		map=new int[n][n];
		
		//물이 최대로 찰 수 있는 높이
		int max_h=Integer.MIN_VALUE;
		
		for(int i=0;i<n;i++) {
			StringTokenizer str=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j]=Integer.parseInt(str.nextToken());
				max_h=Math.max(map[i][j],max_h);
			}
		}
		//System.out.println(max_h);
		
		
		for(int i=1;i<max_h;i++) {
			sink=new boolean[n][n];
			sinkArea(i);
			feildCnt();
			//System.out.println(res);
		}
		
		
		System.out.println(res);
	}
	
	private static void feildCnt() {
		visited=new boolean[n][n];
		int cnt=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(!sink[i][j] && !visited[i][j]) {
					bfs(i,j);
					cnt++;
				}
			}
		}
		res=Math.max(res, cnt);
	}

	private static void bfs(int r, int c) {
		Queue<Pair> q=new LinkedList<>();
		visited[r][c]=true;
		q.offer(new Pair(r,c));
		while(!q.isEmpty()) {
			Pair tmp=q.poll();
			for(int i=0;i<4;i++) {
				int rr=tmp.r+dr[i];
				int cc=tmp.c+dc[i];
				if(rr<0||rr>=n||cc<0||cc>=n||sink[rr][cc]||visited[rr][cc]) continue;
				
				visited[rr][cc]=true;
				q.offer(new Pair(rr,cc));
			}
		}
		
	}

	private static void sinkArea(int height) {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j]<=height) {
					sink[i][j]=true;
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
