import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		
		StringBuilder sb =new StringBuilder();
		for(int tc=1;tc<=T;tc++) {
			sb.append("#").append(tc).append(" ");
			StringTokenizer str=new StringTokenizer(br.readLine());
			
			int n=Integer.parseInt(str.nextToken());
			int m=Integer.parseInt(str.nextToken());
			
			char[][] map=new char[n][m];
			
			for(int i=0;i<n;i++) {
				map[i]=br.readLine().toCharArray();
			}
			int res=move(map,n,m);
			if(res==0)
				sb.append("GAME OVER").append("\n");
			else
				sb.append(res).append("\n");
			
		}
		System.out.println(sb);
	}
	
	private static int move(char[][] map, int n, int m) {
		Queue<Pair> suyeon=new LinkedList<>();
		Queue<Pair> devil=new LinkedList<>();
		int d_r=0,d_c=0;
		int [][] dist=new int[n][m];
		
		int[] dr= {-1,1,0,0};
		int[] dc= {0,0,-1,1};
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(map[i][j]=='S') {
					suyeon.offer(new Pair(i,j));
					map[i][j]='.';
				}
				if(map[i][j]=='*') devil.offer(new Pair(i,j));
				if(map[i][j]=='D') {
					d_r=i;
					d_c=j;
				}
			}
		}
		while(!suyeon.isEmpty()) {
			int dsize=devil.size();
			
			for(int i=0;i<dsize;i++) {
				Pair tmp=devil.poll();
				for(int d=0;d<4;d++) {
					int rr=tmp.r+dr[d];
					int cc=tmp.c+dc[d];
					
					if(rr>=0 && rr<n && cc>=0 &&cc<m) {
						if(map[rr][cc]=='.') {
							map[rr][cc]='*';
							devil.offer(new Pair(rr,cc));
						}
					}
				}
			}
			//print(map);
			int sszie=suyeon.size();
			
			for(int i=0;i<sszie;i++) {
				Pair tmp=suyeon.poll();
				if(map[tmp.r][tmp.c]=='D')
					return dist[tmp.r][tmp.c];
				for(int d=0;d<4;d++) {
					int rr=tmp.r+dr[d];
					int cc=tmp.c+dc[d];
					
					if(rr>=0 && rr<n && cc>=0 &&cc<m && dist[rr][cc]==0) {
						if(map[rr][cc]=='.'||map[rr][cc]=='D') {
							dist[rr][cc]=dist[tmp.r][tmp.c]+1;
							suyeon.offer(new Pair(rr,cc));
						}
					}			
				}
			}
		}
		return dist[d_r][d_c];
	}

	static class Pair{
		int r,c;
		Pair(int r, int c){
			this.r=r;
			this.c=c;
		}
	}
}
