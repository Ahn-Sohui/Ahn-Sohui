import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int[] dr= {-2,-2,0,0,2,2};
		int[] dc= {-1,1,-2,2,-1,1};
		
		int n=Integer.parseInt(br.readLine());
		
		StringTokenizer str=new StringTokenizer(br.readLine());
		
		int sr=Integer.parseInt(str.nextToken());
		int sc=Integer.parseInt(str.nextToken());
		int destiR=Integer.parseInt(str.nextToken());
		int destiC=Integer.parseInt(str.nextToken());
		
		int[][] dist=new int[n][n];
		Init(dist);
		
		Queue<Pair> q= new LinkedList<>();
		
		dist[sr][sc]=0;
		q.offer(new Pair(sr, sc));
		
		while(!q.isEmpty()) {
			Pair tmp=q.poll();
			if(tmp.r==destiR && tmp.c==destiC) {
				break;
			}
			
			for(int i=0;i<6;i++) {
				int rr=tmp.r+dr[i];
				int cc=tmp.c+dc[i];
				
				if(rr<0||rr>=n||cc<0||cc>=n||dist[rr][cc]!=-1) continue;
				
				q.offer(new Pair(rr, cc));
				dist[rr][cc]=dist[tmp.r][tmp.c]+1;
			}
		}
		
		System.out.println(dist[destiR][destiC]);
	}
	
	private static void Init(int[][] dist) {
		for(int i=0;i<dist.length;i++) {
			for(int j=0;j<dist[i].length;j++) {
				dist[i][j]=-1;
			}
		}
		
	}

	static class Pair{
		int r,c;

		public Pair(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

}
