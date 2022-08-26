import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int r,c,t;
	static int[][] map;
	static int up_air,down_air;
	static int[] dr= {0,1,0,-1};
	static int[] dc= {1,0,-1,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str= new StringTokenizer(br.readLine());
		
		r=Integer.parseInt(str.nextToken());
		c=Integer.parseInt(str.nextToken());
		t=Integer.parseInt(str.nextToken());
		
		map=new int[r][c];
		up_air=r;
		down_air=0; //공기청정기의 위치
		
		for(int i=0;i<r;i++) {
			str=new StringTokenizer(br.readLine());
			for(int j=0;j<c;j++) {
				map[i][j]=Integer.parseInt(str.nextToken());
				if(map[i][j]==-1) {
					if(up_air<i) down_air=i;
					else up_air=i;
				}
			}
		}
		
		//System.out.println(up_air+" "+down_air);
		for(int sec=0;sec<t;sec++) {
			//공기청정기 가동 시작
			//step1. 먼지 흩뿌리기
			isBlowing();
			
			//print(map);
			//step2. 공기 청정기 가동
			airCleaner();
			//print(map);
		}
		int res=2; //공기 청정기 칸이 -2 차지
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				res+=map[i][j];
			}
		}
		
		System.out.println(res);
	}
	
	
	private static void print(int[][] map2) {
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				System.out.print(map2[i][j]+" ");
			}
			System.out.println();
		}
		
	}


	private static void airCleaner() {
		// 공기 청정기 돌리기
		//윗 칸은 반시계
		//공기청정기로 들어가는 값은 그냥 사라지는 값이라서 저장할 필요없이 그냥 덮어 씌우면 됨
		
		for(int i=up_air-1;i>=1;i--) {
			map[i][0]=map[i-1][0];
		}
		for(int i=0;i<c-1;i++) {
			map[0][i]=map[0][i+1];
		}
		for(int i=0;i<up_air;i++) {
			map[i][c-1]=map[i+1][c-1];
		}
		for(int i=c-1;i>=1;i--) {
			map[up_air][i]=map[up_air][i-1];
		}
		map[up_air][1]=0;
		//아랫칸은 시계
		
		for(int i=down_air+1;i<r-1;i++) {
			map[i][0]=map[i+1][0];
		}
		for(int i=0;i<c-1;i++) {
			map[r-1][i]=map[r-1][i+1];
		}
		for(int i=r-1;i>down_air;i--) {
			map[i][c-1]=map[i-1][c-1];
		}
		for(int i=c-1;i>=1;i--) {
			map[down_air][i]=map[down_air][i-1];
		}
		map[down_air][1]=0;
	}


	private static void isBlowing() {
		//먼지 흩뿌리기
		Queue<Loc> dust=new LinkedList<>();
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				if(map[i][j]>0) {
					//먼지 존재
					dust.offer(new Loc(i,j));
				}
			}
		}
		int[][] spread=new int[r][c];
		//먼지를 퍼트리자
		while(!dust.isEmpty()) {
			Loc tmp=dust.poll();
			int cnt=0; // 퍼진 횟수 체크
			int spread_dust=map[tmp.r][tmp.c]/5;
			for(int i=0;i<4;i++) {
				int rr=tmp.r+dr[i];
				int cc=tmp.c+dc[i];
				
				if(rr<0||rr>=r||cc<0||cc>=c||map[rr][cc]==-1) continue;
				
				spread[rr][cc]+=spread_dust;
				cnt++;
			}
			map[tmp.r][tmp.c]-=spread_dust*cnt; //확산된 수 만큼 빼줌
		}
		
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				map[i][j]+=spread[i][j]; //확산된 양 저장
			}
		}
	}
	
	static class Loc{
		int r,c;
		
		Loc(int r, int c){
			this.r=r;
			this.c=c;
		}
	}
}
