import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n,m,h;
	static int[] dx= {-1,0,1,0,0,0};
	static int[] dy= {0,1,0,-1,0,0};
	static int[] dz= {0,0,0,0,1,-1}; //상 하 좌 우 위 아래
	static int[][][] map;
	static Queue<Tomato> q=new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str=new StringTokenizer(br.readLine());
		m=Integer.parseInt(str.nextToken());
		n=Integer.parseInt(str.nextToken());
		h=Integer.parseInt(str.nextToken());
		
		map=new int [h][n][m];
		
		for(int i=0;i<h;i++) {
			for(int j=0;j<n;j++) {
				str=new StringTokenizer(br.readLine());
				for(int k=0;k<m;k++) {
					map[i][j][k]=Integer.parseInt(str.nextToken());
					if(map[i][j][k]==1) q.offer(new Tomato(i, j, k));
				}
			}
		}
		ripeTomato();
		int res=Integer.MIN_VALUE;
		
		for(int i=0;i<h;i++) {
			for(int j=0;j<n;j++) {
				for(int k=0;k<m;k++) {
					if(map[i][j][k]==0) {
						System.out.println(-1);
						return;
					}
					res=Math.max(res, map[i][j][k]);
				}
			}
		}
		
		if(res==1) {
			//이미 다 익어있음
			System.out.println(0);
		}else {
			System.out.println(res-1);
		}
		
	}
	
	private static void ripeTomato() {
		while(!q.isEmpty()) {
			Tomato tmp=q.poll();
			
			for(int i=0;i<6;i++) {
				int xx=tmp.x+dx[i];
				int yy=tmp.y+dy[i];
				int zz=tmp.z+dz[i];
				
				if(xx<0||xx>=n||yy<0||yy>=m|zz<0||zz>=h||map[zz][xx][yy]!=0) continue;
				
				q.offer(new Tomato(zz,xx,yy));
				map[zz][xx][yy]=map[tmp.z][tmp.x][tmp.y]+1;
			}
		}
		
	}

	static class Tomato{
		int x,y,z;
		
		Tomato(int z,int x,int y){
			this.x=x;
			this.y=y;
			this.z=z;
		}
	}
}
