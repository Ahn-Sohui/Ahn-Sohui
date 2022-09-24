import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = {0,1,0,-1};
	static int[] dc= {1,0,-1,0};
	static int h,w;
	static char[][] map;
	static int[][] dist;
	static Queue<Pair> q=new LinkedList<>();
	static int res=Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str= new StringTokenizer(br.readLine());
		h=Integer.parseInt(str.nextToken());
		w=Integer.parseInt(str.nextToken());
		
		map=new char[h][w];
		
		for(int i=0;i<h;i++) {
			map[i]=br.readLine().toCharArray();
		}
		
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				if(map[i][j]=='L') {
					bfs(i,j);
				}
			}
		}
		
		System.out.println(res);
		//print(map);
	}
	
	private static void bfs(int startr, int startc) {
		q.offer(new Pair(startr,startc));
		dist=new int[h][w];
		Init();
		dist[startr][startc]=0;
		
		while(!q.isEmpty()) {
			Pair tmp=q.poll();
			for(int i=0;i<4;i++) {
				int rr=tmp.r+dr[i];
				int cc=tmp.c+dc[i];
				
				if(rr<0||rr>=h||cc<0||cc>=w||dist[rr][cc]!=Integer.MIN_VALUE||map[rr][cc]=='W') continue;
				
				dist[rr][cc]=dist[tmp.r][tmp.c]+1;
				res=Math.max(res, dist[rr][cc]);
				q.offer(new Pair(rr,cc));
			}
		}	
	}

	private static void Init() {

		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				dist[i][j]=Integer.MIN_VALUE;
			}
		}
		
	}

	private static void print(char[][] map2) {
		for(int i=0;i<map2.length;i++) {
			for(int j=0;j<map2[i].length;j++) {
				System.out.print(map2[i][j]+" ");
			}
			System.out.println();
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
