import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int [][] cheese;
	static boolean[][] v;
	static int n,m;
	static int dummy;
	static int[] dr= {-1,0,1,0};
	static int[] dc= {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str= new StringTokenizer(br.readLine());
		StringBuilder sb= new StringBuilder();
		n=Integer.parseInt(str.nextToken());
		m=Integer.parseInt(str.nextToken());
		
		cheese=new int[n][m];
		dummy=0;
		
		for(int i=0;i<n;i++) {
			str=new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				cheese[i][j]=Integer.parseInt(str.nextToken());
				if(cheese[i][j]==1) dummy++;
			}
		}
		//System.out.println(dummy);
		
		int cnt=0; //치즈 칸 수
		int res=0; //다 녹는데 걸리는 시간
		
		while(dummy!=0) {
			cnt=dummy;
			res++;
			v=new boolean[n][m];
			
			cheeseMelt();
			
		}
		sb.append(res).append("\n").append(cnt);
		System.out.println(sb);
	}
	
	
	private static void cheeseMelt() {
		Queue<Pair> q= new LinkedList<>();
		q.offer(new Pair(0,0));
		v[0][0]=true;
		
		while(!q.isEmpty()) {
			Pair tmp=q.poll();
			
			for(int i=0;i<4;i++) {
				int rr=tmp.r+dr[i];
				int cc=tmp.c+dc[i];
				
				if(rr<0||rr>=n||cc<0||cc>=m||v[rr][cc]) continue;
				
				v[rr][cc]=true;
				if(cheese[rr][cc]==0) q.offer(new Pair(rr,cc));
				else {
					dummy--;
					cheese[rr][cc]=0;
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
