import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n,k,l;
	static int[][] dummy;
	static char[] command; //방향 변환 정보
	static List<Pos> snake;
	static boolean[][] snakeLen;
	
	static int[] dr= {0,1,0,-1};
	static int[] dc= {1,0,-1,0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		k=Integer.parseInt(br.readLine());
		
		dummy=new int[n][n];
		command=new char[10001];
		snakeLen=new boolean[n][n];
		
		for(int i=0;i<k;i++) {
			StringTokenizer str=new StringTokenizer(br.readLine());
			int r=Integer.parseInt(str.nextToken())-1;
			int c=Integer.parseInt(str.nextToken())-1;
			
			dummy[r][c]=1; // 사과의 위치 표시
		}
		
		//print(dummy);
		
		l=Integer.parseInt(br.readLine());
		
		for(int i=0;i<l;i++) {
			StringTokenizer str=new StringTokenizer(br.readLine());
			int t=Integer.parseInt(str.nextToken());
			String com=str.nextToken();
			
			command[t]=com.charAt(0);
		}
		
		snake= new LinkedList<>();
		
		//시작점
		snake.add(new Pos(0, 0));
		snakeLen[0][0]=true;
		int time=0;
		int curR=0,curC=0;
		int dir=0;
		
		
		while(true) {
			time++;
			//몸 길이를 늘려 머리를 다음 칸에 위치
			int nextR=curR+dr[dir];
			int nextC=curC+dc[dir];

			if(nextC<0||nextR<0||nextC>=n||nextR>=n||snakeLen[nextR][nextC]) break;
			
			snakeLen[nextR][nextC]=true;
			//사과 존재 => 사과를 먹고 꼬리 고정
			//없으면 꼬리를 땡겨서 몸길이 유지
			if(dummy[nextR][nextC]==1) {
				dummy[nextR][nextC]=0;
				snake.add(new Pos(nextR, nextC)); //머리칸 전진
			}else {
				snake.add(new Pos(nextR, nextC));
				snakeLen[snake.get(0).r][snake.get(0).c]=false;
				snake.remove(0); // 꼬리칸 땡기기
			}
			
			//회전

			if(command[time]=='D' || command[time]=='L') {
				//방향 전환 정보가 존재하면 
				if(command[time]=='D') {
					//오른쪽으로 90도 회전
					dir+=1;
					if(dir==4)
						dir=0;
					
				}else {
					//왼쪽으로 90도 회전
					dir-=1;
					if(dir==-1)
						dir=3;
				}
			}
			
			curR=nextR;
			curC=nextC;
			
		}
		
		System.out.println(time);
	}

	static class Pos{
		int r,c;
		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		@Override
		public String toString() {
			return "Pos [r=" + r + ", c=" + c +  "]";
		}
		
		
	}
}
