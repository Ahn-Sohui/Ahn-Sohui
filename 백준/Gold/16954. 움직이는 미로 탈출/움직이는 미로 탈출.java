import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static char[][] map;
	static int[] dr= {-1,-1,0,1,1,1,0,-1,0}; //팔방 + 제자리
	static int[] dc= {0,1,1,1,0,-1,-1,-1,0};
	static Queue<Pair> charac=new LinkedList<>();
	static boolean flag=false;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map=new char[8][8];
		
		for(int i=0;i<8;i++) {
			String str=br.readLine();
			for(int j=0;j<8;j++) {
				map[i][j]=str.charAt(j);
			}
		}
		charac.offer(new Pair(7,0));
		//print(map);
		//종료 조건 : 욱제의 캐릭터가 목적지에 도착했는가 || 벽과 캐릭터가 만났는가
		//욱제의 캐릭터 가장 왼쪽 아래에 있음
		while(!charac.isEmpty() && !flag) {
			//캐릭터 이동
			moveCharac();
			//벽이동
			moveWall();
		}
		
		if(flag) {
			//도착하지 못했음
			System.out.println(1);
		}else
			System.out.println(0);
	}

	private static void moveCharac() {
		int size=charac.size();
		for(int i=0;i<size;i++) {
			if(flag) return;
			
			Pair tmp=charac.poll();
			
			if(map[tmp.r][tmp.c]=='#') continue;
			
			if(tmp.r==0 && tmp.c==7) {
				flag=true;
				return;
			}
			
			for(int j=0;j<9;j++) {
				int rr= tmp.r+dr[j];
				int cc=tmp.c+dc[j];
				if(rr<0||rr>=8||cc<0||cc>=8||map[rr][cc]=='#') continue;
				charac.offer(new Pair(rr,cc));
			}
			
		}
		
	}

	private static void moveWall() {
		for(int i=0;i<7;i++) {
			map[7][i]='.';
		}
		
		for(int i=6;i>=0;i--) {
			for(int j=0;j<8;j++) {
				if(map[i][j]=='#') {
					map[i+1][j]='#';
					map[i][j]='.';
				}
			}
		}
		
	}


	private static void print(int[][] dist2) {
		for(int i=0;i<dist2.length;i++) {
			for(int j=0;j<dist2[i].length;j++) {
				System.out.print(dist2[i][j]);
			}
			System.out.println();
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
