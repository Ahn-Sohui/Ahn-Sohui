import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n,m;
	static char[][] map;
	static boolean[][][] visited;
	static int[][] dist;
	static int[] dr= {-1,0,1,0};
	static int[] dc= {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str=new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(str.nextToken());
		m=Integer.parseInt(str.nextToken());
		map=new char[n][m];
		dist=new int[n][m];
		for(int i=0;i<n;i++) {
			String st=br.readLine();
			for(int j=0;j<m;j++) {
				map[i][j]=st.charAt(j);
				dist[i][j]=-1;
			}
		}
		visited=new boolean[2][n][m];
		//print(map);
		
		visited[0][0][0]=visited[1][0][0]=true;
		dist[0][0]=1;
		bfs();
		//print(dist);
		System.out.println(dist[n-1][m-1]);
	}

	private static void bfs() {
		Queue<Pair> q= new LinkedList<>();
		q.offer(new Pair(0,0,false));
		while(!q.isEmpty()) {
			Pair tmp=q.poll();
			if(tmp.r==n-1 && tmp.c==m-1){
                return;
            }
			for(int i=0;i<4;i++) {
				int rr=tmp.r+dr[i];
				int cc=tmp.c+dc[i];
				
				if(rr<0||rr>=n||cc<0||cc>=m) continue;
				
				if(map[rr][cc]=='0') {
					if(!tmp.breakW && !visited[0][rr][cc]) {
						q.offer(new Pair(rr,cc, false));
						dist[rr][cc]=dist[tmp.r][tmp.c]+1;
						visited[0][rr][cc]=true;
					}else if(tmp.breakW  && !visited[1][rr][cc]) {
						q.offer(new Pair(rr,cc,true));
						dist[rr][cc]=dist[tmp.r][tmp.c]+1;
						visited[1][rr][cc]=true;
					}
				}else if(map[rr][cc]=='1') {
					if(!tmp.breakW) {
						q.offer(new Pair(rr,cc,true));
						dist[rr][cc]=dist[tmp.r][tmp.c]+1;
						visited[1][rr][cc]=true;
					}
				}										
			}
		}		
	}

	private static void print(int[][] map2) {
		for(int i=0;i<map2.length;i++) {
			for(int j=0;j<map2[i].length;j++) {
				System.out.print(map2[i][j]);
			}
			System.out.println();
		}
	}
	
	static class Pair{
		int r,c;
		boolean breakW;
		
		Pair(int r,int c, boolean breakW){
			this.r=r;
			this.c=c;
			this.breakW=breakW;
		}
	}
}
