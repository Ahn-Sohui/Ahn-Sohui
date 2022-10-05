import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R,C,M;
	static Shark[][] sea;
	static int res;
	
	static int[] dr= {-1,0,1,0};
	static int[] dc= {0,-1,0,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str= new StringTokenizer(br.readLine());
		
		R=Integer.parseInt(str.nextToken());
		C=Integer.parseInt(str.nextToken());
		M=Integer.parseInt(str.nextToken());
		
		sea=new Shark[R][C];
		
		for(int i=0;i<M;i++) {
			str=new StringTokenizer(br.readLine());
			int r=Integer.parseInt(str.nextToken());
			int c=Integer.parseInt(str.nextToken());
			int s=Integer.parseInt(str.nextToken());
			int d=Integer.parseInt(str.nextToken());
			int z=Integer.parseInt(str.nextToken());
			if(d==1) {
				d=0;
			}else if(d==4) {
				d=1;
			}
			//0:상 2:하 3:우1:좌
			sea[r-1][c-1]=new Shark(r-1, c-1, s, d, z);
		}
		
		for(int loc=0;loc<C;loc++) {
			catchShark(loc);
			moveShark();
		}
		System.out.println(res);
	}
	
	private static void moveShark() {
		Queue<Shark> q=new LinkedList<>();
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(sea[i][j]!=null) {
					q.offer(sea[i][j]);
				}
			}
		}
		sea=new Shark[R][C];
		
		while(!q.isEmpty()) {
			Shark tmp=q.poll();
			int speed=tmp.s;
			if(tmp.d==0||tmp.d==2) {
				speed%=(R-1)*2;
			}else if(tmp.d==1||tmp.d==3) {
				speed%=(C-1)*2;
			}
			
			for(int i=0;i<speed;i++) {
				int rr=tmp.r+dr[tmp.d];
				int cc=tmp.c+dc[tmp.d];
				
				if(rr<0||rr>=R||cc<0||cc>=C) {
					tmp.r-=dr[tmp.d];
					tmp.c-=dc[tmp.d];
					tmp.d=(tmp.d+2)%4;
					continue;
				}
				
				tmp.r=rr;
				tmp.c=cc;
			}
			if(sea[tmp.r][tmp.c]!=null) {
				if(sea[tmp.r][tmp.c].z<tmp.z) {
					sea[tmp.r][tmp.c]=tmp;
				}
			}else {
				sea[tmp.r][tmp.c]=tmp;
			}
		}
		
	}

	private static void catchShark(int loc) {
		// 상어 잡기
		for(int i=0;i<R;i++) {
			if(sea[i][loc]!=null) {
				res+=sea[i][loc].z;
				//System.out.println(res);
				sea[i][loc]=null;
				//한 마리만 먹기
				return;
			}
		}		
	}

	static class Shark{
		int r,c,s,d,z;

		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}		
	}
}	
