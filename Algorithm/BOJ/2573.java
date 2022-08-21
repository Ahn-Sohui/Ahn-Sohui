import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	static int[][] map;
	static int n,m,res; //맵의 크기 와 결과값
	static Queue<Pair> q= new LinkedList<>();
	static int[][] meltDown;
	static boolean[][] visited;
	static int[] dr= {0,1,0,-1};
	static int[] dc= {1,0,-1,0};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str= new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(str.nextToken());
		m=Integer.parseInt(str.nextToken());
		map=new int[n][m];
		meltDown=new int[n][m]; //녹는 정보 담을 배열
		visited=new boolean[n][m];
		for(int i=0;i<n;i++) {
			str=new StringTokenizer(br.readLine()); 
			for (int j = 0; j < m; j++) {
				map[i][j]=Integer.parseInt(str.nextToken());
			}	
			
		}
		//제대로 입력받는지 확인
		//print(map);
		
		//빙산이 다 녹을 떄 까지 확인
		while(!isAllMelt()) {
			//덩어리가 2덩이면 걸린 시간을 출력하고 종료
			
			if(chk()>=2) {
				System.out.println(res);
				return;
			}
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					meltDown[i][j]=0;
					visited[i][j]=false;
				}
			}
			
			//녹는 거 체크
			for(int i=1;i<n-1;i++) {
				for(int j=1;j<m-1;j++) {
					if(map[i][j]!=0) {
						q.offer(new Pair(i,j));
						bfs();
					}
				}
			}
			
			//녹아내림
			isMelting();
			
			//1년이 지났음
			res++;
			
		}
		
		System.out.println(0);
		
	}
	
	private static void isMelting() {
	//녹아내리는 함수
		for(int i=1;i<n-1;i++) {
			for(int j=1;j<m-1;j++) {
				map[i][j]-=meltDown[i][j];
				if(map[i][j]<0) map[i][j]=0;
			}
		}
		
	}

	private static void bfs() {
		Pair tmp=q.poll();
		int cnt=0;
		for(int i=0;i<4;i++) {
			int rr=tmp.r+dr[i];
			int cc=tmp.c+dc[i];
			
			if(rr<0||rr>=n||cc<0||cc>=m||map[rr][cc]!=0) continue;
			cnt++;
		}
		
		meltDown[tmp.r][tmp.c]=cnt; //해당 위치가 녹아내릴 정보 저장
		
	}
	
	private static int chk() {
		//덩어리 갯수 체크
		int ice_cnt=0;
		for(int i=1;i<n-1;i++) {
			for(int j=1;j<m-1;j++) {
				if(map[i][j]!=0 && visited[i][j]==false) {
					q.offer(new Pair(i,j));
					ice_cnt+=bfs2();
				}
			}
		}
	
		return ice_cnt;
	}

	private static int bfs2() {
		// 덩어리 체크
		while(!q.isEmpty()) {
			Pair tmp= q.poll();
			visited[tmp.r][tmp.c]=true;
			
			for(int i=0;i<4;i++) {
				int rr=tmp.r+dr[i];
				int cc=tmp.c+dc[i];
				
				if(rr<0||rr>=n||cc<0||cc>=m||map[rr][cc]==0||visited[rr][cc]==true) continue;
				
				visited[rr][cc]=true;
				q.offer(new Pair(rr,cc));
			}
		}
		return 1;
	}

	private static boolean isAllMelt() {
		//다 녹았는지 확인하는 함수
		//다 녹았다면 true, 아니면 false
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(map[i][j]!=0) return false;
			}
		}
		return true;
	}

	private static void print(int[][] arr) {
		// TODO Auto-generated method stub
		for(int i=0;i<arr.length;i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}

	static class Pair{
		//좌표값을 저장할 클래스
		int r,c;
		Pair(int r, int c){
			this.r=r;
			this.c=c;
		}
	}
}
