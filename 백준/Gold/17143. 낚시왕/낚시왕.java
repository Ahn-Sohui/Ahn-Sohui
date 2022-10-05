import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R, C, m;
	static Shark[][] shark;
	static int res = 0; // 잡은 상어 크기의 합
	static int[] dr= {-1,0,1,0};
	static int[] dc= {0,-1,0,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(br.readLine());

		R = Integer.parseInt(str.nextToken());
		C = Integer.parseInt(str.nextToken());
		m = Integer.parseInt(str.nextToken());

		shark=new Shark[R][C];
		for (int i = 0; i < m; i++) {
			str = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(str.nextToken());
			int c = Integer.parseInt(str.nextToken());
			int s = Integer.parseInt(str.nextToken());
			int d = Integer.parseInt(str.nextToken());
			int z = Integer.parseInt(str.nextToken());
			if(d==1)
				d=0;
			else if(d==4)
				d=1;
			shark[r-1][c-1]=new Shark(r-1, c-1, s, d, z);
		}
		
		for(int i=0;i<C;i++) {
			//상어 잡기
			catchShark(i);
			moveShark();
		}
		
		
		System.out.println(res);
	}


	private static void moveShark() {
		// 상어 이동
		Queue<Shark> q=new LinkedList<>();
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(shark[i][j]!=null)
					q.offer(new Shark(i,j,shark[i][j].s,shark[i][j].d,shark[i][j].z));
			}
		}
		
		shark=new Shark[R][C];
		
		while(!q.isEmpty()) {
			Shark tmp=q.poll();
			
			int speed=tmp.s;
			
			if(tmp.d==0||tmp.d==2) {
				//상 ,하
				speed%=(R-1)*2;
			}else if(tmp.d==3||tmp.d==1) {
				//좌 우
				speed%=(C-1)*2;
			}
			
			for(int i=0;i<speed;i++) {
				int rr=tmp.r+dr[tmp.d];
				int cc=tmp.c+dc[tmp.d];
				
				if(rr<0||rr>=R||cc<0||cc>=C) {
					//벽에 부딪이면
					tmp.r-=dr[tmp.d];
					tmp.c-=dc[tmp.d];
					tmp.d=(tmp.d+2)%4;
					continue;
				}
				
				tmp.r=rr;
				tmp.c=cc;
			}
			//System.out.println(tmp);
			if(shark[tmp.r][tmp.c]!=null) {
				if(shark[tmp.r][tmp.c].z<tmp.z) {
					shark[tmp.r][tmp.c]=new Shark(tmp.r, tmp.c, tmp.s, tmp.d, tmp.z);
				}
			}else {
				shark[tmp.r][tmp.c]=new Shark(tmp.r, tmp.c, tmp.s, tmp.d, tmp.z);
			}
		}
	}


	private static void catchShark(int idx) {
		// 상어 잡기
		for(int i=0;i<R;i++) {
			if(shark[i][idx]!=null) {
				//상어가 존재한다면
				res+=shark[i][idx].z;
				//System.out.println(shark[i][idx].z);
				shark[i][idx]=null;
				return;
			}
		}
		
	}


	static class Shark {
		int r, c, s, d, z;

		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}

		@Override
		public String toString() {
			return "Shark [r=" + r + ", c=" + c + ", s=" + s + ", d=" + d + ", z=" + z + "]";
		}
	}
}
