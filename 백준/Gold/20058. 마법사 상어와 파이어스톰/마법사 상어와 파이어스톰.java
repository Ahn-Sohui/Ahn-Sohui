import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n,q,size;
	static int[][] map;
	static int[][] melt;
	static boolean[][] visited;
	static int[] dr= {-1,0,1,0};
	static int[] dc= {0,1,0,-1};
	
	static int bigSize=Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str= new StringTokenizer(br.readLine());
		n=Integer.parseInt(str.nextToken());
		q=Integer.parseInt(str.nextToken());
		size=(int)Math.pow(2, n);
		map=new int[size][size];
		
		for(int i=0;i<size;i++) {
			str=new StringTokenizer(br.readLine());
			for(int j=0;j<size;j++) {
				map[i][j]=Integer.parseInt(str.nextToken());
			}
		}
		
		str=new StringTokenizer(br.readLine());
		for(int command=0;command<q;command++) {
			int l=Integer.parseInt(str.nextToken());
			//1.정한 칸만큼 회전
			rotateMap((int)Math.pow(2, l));
			//2.얼음의 양 줄어듬
			meltDown();
		}
		//남아있는 얼음의 합
		int res=0;
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				res+=map[i][j];
			}
		}
		//남아있는 얼음 중 가장 큰 덩어리가 차지하는 칸의 갯수
		visited=new boolean[size][size];
		
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(map[i][j]>0 && !visited[i][j]) {
					countSize(i,j);
				}
			}
		}
		if(bigSize==Integer.MIN_VALUE) bigSize=0;
		System.out.println(res);
		System.out.println(bigSize);
	}
	
	
	private static void rotateMap(int l) {
		// 맵 돌리기
		int loop=size/l;
		int[][] rotate=new int[size][size];
		
		int x=0;
		
		for(int i=0;i<loop;i++) {
			int y=0;
			if(i!=0)
				x+=l;
			
			for(int j=0;j<loop;j++) {
				if(j!=0)
					y+=l;
				for(int r=0;r<l;r++) {
					for(int c=0;c<l;c++) {
						rotate[x+c][y-r+l-1]=map[x+r][y+c];
					}
				}
			}
		}
		
		map=rotate;
		
	}


	private static void meltDown() {
		Queue<Pair> queue=new LinkedList<>();
		for(int r=0;r<size;r++) {		
			for(int c=0;c<size;c++) {
				int cnt=0;
				for(int i=0;i<4;i++) {
					int rr=r+dr[i];
					int cc=c+dc[i];
					
					if(rr<0||rr>=size||cc<0||cc>=size||map[rr][cc]<=0) continue;
					cnt++;
				}
				
				if(cnt<3) queue.offer(new Pair(r,c));
			}
		}
		
		while(!queue.isEmpty()) {
			Pair tmp=queue.poll();
			map[tmp.r][tmp.c]--;
			if(map[tmp.r][tmp.c]<0) map[tmp.r][tmp.c]=0;
		}
	}
	
	private static void countSize(int startr, int startc) {
		//얼음의 덩어리 세기
		int cnt=1;
		Queue<Pair> queue= new LinkedList<>();
		queue.offer(new Pair(startr,startc));
		visited[startr][startc]=true;
		
		while(!queue.isEmpty()) {
			Pair tmp=queue.poll();
			
			for(int i=0;i<4;i++) {
				int rr=tmp.r+dr[i];
				int cc=tmp.c+dc[i];
				
				if(rr<0||rr>=size||cc<0||cc>=size||map[rr][cc]==0||visited[rr][cc]) continue;
				cnt++;
				visited[rr][cc]=true;
				queue.offer(new Pair(rr,cc));
			}
		}
		bigSize=Math.max(bigSize, cnt);
	}
	
	static class Pair{
		int r,c;
		Pair(int r,int c){
			this.r=r;
			this.c=c;
		}
	}
}
