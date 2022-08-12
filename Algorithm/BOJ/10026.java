import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static char[][] map1,map2; //적록색약이 아닌 사람, 적록색약인 사람
	static int n;
	static Queue<Pair> q1=new LinkedList<>();
	static Queue<Pair> q2=new LinkedList<>();
	static int cnt1,cnt2; //적록색약x , 적록색약 o
	static int[] dr= {-1,0,1,0};
	static int[] dc= {0,1,0,-1};
	static int r;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		n= Integer.parseInt(br.readLine());
		map1=new char[n][n];
		map2=new char[n][n];
		
		for(int i=0;i<n;i++) {
			map1[i]=br.readLine().toCharArray();
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(map1[i][j]=='G') {
					map2[i][j]='R';
				}
				else{
					map2[i][j]=map1[i][j];
				}
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(map1[i][j]=='R') {
					q1.offer(new Pair(i,j));									
					map1[i][j]='0';					
					bfs1('R');					
				}
				if(map2[i][j]=='R') {
					q2.offer(new Pair(i,j));
					map2[i][j]='0';
					bfs2('R');
				}
				
				if(map1[i][j]=='G') {
					q1.offer(new Pair(i,j));
					map1[i][j]='0';
					bfs1('G');
				}
				
				if(map1[i][j]=='B') {					
					q1.offer(new Pair(i,j));					
					map1[i][j]='0';
					bfs1('B');					
				}
				
				if(map2[i][j]=='B') {
					q2.offer(new Pair(i,j));
					map2[i][j]='0';
					bfs2('B');
				}
			}
		}
//		print(map1);
//		System.out.println(r);
		System.out.println(cnt1 + " "+ cnt2);
		
	}

//	private static void print(char[][] map12) {
//		// TODO Auto-generated method stub
//		for(int i=0;i<n;i++) {
//			for(int j=0;j<n;j++) {
//				System.out.print(map12[i][j]+" ");
//			}
//			System.out.println();
//		}
//	}

	private static void bfs1(char c) { //적록색약이 아닌 사람
		// TODO Auto-generated method stub
		while(!q1.isEmpty()) {
			Pair tmp=q1.poll();
			//System.out.println(tmp.r+" "+tmp.c);
			for(int i=0;i<4;i++) {
				int rr=tmp.r+dr[i];
				int cc=tmp.c+dc[i];
				if(rr<0||rr>=n||cc<0||cc>=n||map1[rr][cc]!=c) continue;
				if(map1[rr][cc]==c) {
					map1[rr][cc]='0';
					q1.offer(new Pair(rr,cc));
				}
			}
			
		}
		//if(c=='G') r++;
		cnt1++;
	}
	
	private static void bfs2(char c) { //적록색약인 사람
		// TODO Auto-generated method stub
		while(!q2.isEmpty()) {
			Pair tmp=q2.poll();
			for(int i=0;i<4;i++) {
				int rr=tmp.r+dr[i];
				int cc=tmp.c+dc[i];
				if(rr<0||rr>=n||cc<0||cc>=n||map2[rr][cc]!=c) continue;
				if(map2[rr][cc]==c) {
					map2[rr][cc]='0';
					q2.offer(new Pair(rr,cc));
				}
			}
		}
		cnt2++;
	}

	static public class Pair{
		int r,c;
		Pair(int r, int c){
			this.r=r;
			this.c=c;
		}
	}

}
