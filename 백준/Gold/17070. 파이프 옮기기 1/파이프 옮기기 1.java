import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[][] map;
	static int res=0;
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		//Scanner sc= new Scanner(System.in);
		n=Integer.parseInt(br.readLine());
		map=new int[n][n];
		for(int i=0;i<n;i++) {
			StringTokenizer str=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j]=Integer.parseInt(str.nextToken()); // 맵 초기화
			}
		}
		//dir: 0 => 가로 , dir:1 =>세로, dir:2=>대각선
		recursive(0,1,0);
		
		System.out.println(res);
	}
	private static void recursive(int r, int c, int dir) {
		// TODO Auto-generated method stub
		if(r==n-1 && c==n-1) {
			res++;
			return;
		}
		
		if(dir==0) {//가로일 때 동쪽으로
			if(c+1<n && map[r][c+1]==0) { 
				recursive(r,c+1,0);
			}
		}
		else if(dir==1) {//세로일 때 남쪽으로
			if(r+1<n && map[r+1][c]==0) {
				recursive(r+1,c,1);
			}
			
		}
		else if(dir==2) {
			//대각선일 때 동쪽
			if(c+1<n && map[r][c+1]==0) { 
				recursive(r,c+1,0);
			}
			if(r+1<n && map[r+1][c]==0) {
				recursive(r+1,c,1);
			}
		}
		
		//대각선은 모두 공통
		if(r+1<n && c+1<n && map[r][c+1]==0 && map[r+1][c]==0&&map[r+1][c+1]==0) {
			recursive(r+1,c+1,2);
		}
	}

}
