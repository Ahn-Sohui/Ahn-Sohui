import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int w,h;
	static int[] dr= {-1,-1,0,1,1,1,0,-1};
	static int[] dc= {0,1,1,1,0,-1,-1,-1};
	static Queue<Pair> q=new LinkedList<>();
	static int cnt=0;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		//Scanner sc= new Scanner(System.in);
		while(true) {
			StringTokenizer str =new StringTokenizer(br.readLine());
			w=Integer.parseInt(str.nextToken());
			h=Integer.parseInt(str.nextToken());
			
			if(h==0 && w==0) break;
			
			map=new int[h][w];
			for(int i=0;i<h;i++) {
				str=new StringTokenizer(br.readLine());
				for(int j=0;j<w;j++) {
					map[i][j]=Integer.parseInt(str.nextToken());
				}
			}
			
			for(int i=0;i<h;i++) {
				for(int j=0;j<w;j++) {
					if(map[i][j]==1) {
						q.offer(new Pair(i,j));
						map[i][j]=0;
						bfs();
					}
				}
			}
			sb.append(cnt+"\n");
			cnt=0;
		}
		
		System.out.println(sb);
	}
	
	private static void bfs() {
		// TODO Auto-generated method stub
		while(!q.isEmpty()) {
			Pair n_d=q.poll();
			for(int i=0;i<8;i++) {
				int rr=n_d.r+dr[i];
				int cc=n_d.c+dc[i];
				if(rr<0 || rr>=h || cc<0||cc>=w||map[rr][cc]==0) continue;
				if(map[rr][cc]==1) {
					map[rr][cc]=0;				
					q.offer(new Pair(rr,cc));
				}
			}
		}
		cnt++;
	}

	public static class Pair{
		int r,c;
		Pair(int r, int c){
			this.r=r;
			this.c=c;
		}
	}

}
