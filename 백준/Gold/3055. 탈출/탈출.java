import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static char[][] map;
	static int[][] chk;
	static int r,c;
	static Queue<Loc> q= new LinkedList<>();
	static Queue<Loc> water= new LinkedList<>();
	static int[] dr= {-1,0,1,0};
	static int[] dc= {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str=new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		r=Integer.parseInt(str.nextToken());
		c=Integer.parseInt(str.nextToken());
		map=new char[r][c];
		chk=new int[r][c];
		
		for(int i=0;i<r;i++) {
			map[i]=br.readLine().toCharArray();
		}
		
		int f_r=0,f_c=0;
		
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				if(map[i][j]=='*')
					water.offer(new Loc(i,j)); // 물의 위치 저장
				else if(map[i][j]=='S')
					{
						map[i][j]='.';
						q.offer(new Loc(i,j));
					}
				else if(map[i][j]=='D') {
					f_r=i;
					f_c=j;
				}
			}
		}
		
	
		bfs();
		
		if(chk[f_r][f_c]==0) sb.append("KAKTUS");
		else sb.append(chk[f_r][f_c]);
		
		System.out.println(sb);
	}
	
	private static void bfs() {
		while(!q.isEmpty()) {
			//물 부터 퍼트림
			int w_size= water.size();
			for(int i=0;i<w_size;i++) {
				Loc tmp=water.poll();
				for(int d=0;d<4;d++) {
					int rr=tmp.r+dr[d];
					int cc=tmp.c+dc[d];
					if(rr<0||rr>=r||cc<0||cc>=c||map[rr][cc]=='*'||map[rr][cc]=='X'||map[rr][cc]=='D') continue;
					map[rr][cc]='*';
					water.offer(new Loc(rr,cc));
				}
			}
			//print(chk);
			//System.out.println("================");
			//도치 이동
			int d_size=q.size();
			for(int i=0;i<d_size;i++) {
				Loc tmp=q.poll();
				for(int d=0;d<4;d++) {
					int rr=tmp.r+dr[d];
					int cc=tmp.c+dc[d];
					
					//범위를 벗어나거나 이미 방문한 곳이라면 탐색 할 필요 없음
					if(rr<0||rr>=r||cc<0||cc>=c||chk[rr][cc]!=0||map[rr][cc]=='*'||map[rr][cc]=='X') continue;
					
					
					if(map[rr][cc]=='.') {
						q.offer(new Loc(rr,cc));
						chk[rr][cc]=chk[tmp.r][tmp.c]+1;
					}
					if(map[rr][cc]=='D') {
						chk[rr][cc]=chk[tmp.r][tmp.c]+1;
					}
				}
			}
			//print(map);
			//print(chk);
			//System.out.println("-----------------");
		}
	}


	private static void print(int[][] chk2) {
		for(int i=0;i<chk2.length;i++) {
			for(int j=0;j<chk2[i].length;j++) {
				System.out.print(chk2[i][j]);
			}
			System.out.println();
		}
		
	}


	static public class Loc{
		int r,c;
		Loc(int r,int c){
			this.r=r;
			this.c=c;
		}
	}
	
	private static void print(char[][] map2) {
		
		for(int i=0;i<map2.length;i++) {
			for(int j=0;j<map2[i].length;j++) {
				System.out.print(map2[i][j]);
			}
			System.out.println();
		}
	}

}
