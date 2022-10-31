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
	static Pair start;
	static int[] dr= {0,1,0,-1};
	static int[] dc= {1,0,-1,0};	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str= new StringTokenizer(br.readLine());
		n=Integer.parseInt(str.nextToken());
		m=Integer.parseInt(str.nextToken());
		map=new char[n][m];
		visited=new boolean[n][m][64];
		for(int i=0;i<n;i++) {
			String tmp=br.readLine();
			for(int j=0;j<m;j++) {
				map[i][j]=tmp.charAt(j);
				if(map[i][j]=='0') {
					start=new Pair(i,j,0,0);
				}
			}
		}
		 bfs(start.r,start.c);
		
	}
	
	private static void bfs(int r, int c) {
		Queue<Pair> q=new LinkedList<>();
		q.offer(new Pair(r,c,0,0));
		visited[r][c][0]=true;
		
		while(!q.isEmpty()) {
			Pair tmp=q.poll();
			int count=tmp.cnt;
			int key=tmp.key;
			
			if(map[tmp.r][tmp.c]=='1') {
				System.out.println(tmp.cnt);
				return;
			}
			
			for(int i=0;i<4;i++) {
				int rr=tmp.r+dr[i];
				int cc=tmp.c+dc[i];
				
				if(rr<0||rr>=n||cc<0||cc>=m||visited[rr][cc][key]||map[rr][cc]=='#') continue;
				
				if(map[rr][cc]-'a'>=0 && map[rr][cc]-'a'<6) {
					//열쇠일 때
					int tmpkey=(1<<(map[rr][cc]-'a'))|key;
					
					if(!visited[rr][cc][tmpkey]) {
						visited[rr][cc][tmpkey]=true;
						visited[rr][cc][key]=true;
						q.offer(new Pair(rr,cc,tmpkey,count+1));

					}
				}else if(map[rr][cc]-'A'>=0 && map[rr][cc]-'F'<6) {
					//문일때
					int tmpDoor=(1<<(map[rr][cc]-'A')) & key;
					
					if(tmpDoor>0) {
						visited[rr][cc][key]=true;
						q.offer(new Pair(rr,cc,key,count+1));
					}
				}else {
					//평지
					visited[rr][cc][key]=true;
					q.offer(new Pair(rr,cc,key,count+1));
				}
			}
		}
		System.out.println(-1);
	}

	static class Pair{
		int r,c,key,cnt;
		
		Pair(int r,int c,int key,int cnt){
			this.r=r;
			this.c=c;
			this.key=key;
			this.cnt=cnt;
		}
	}
}