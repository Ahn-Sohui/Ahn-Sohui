import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int r,c;
	static char [][] map;
	static int res;
	static int[] dr= {-1,0,1};
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str =new StringTokenizer(br.readLine());
		
		r=Integer.parseInt(str.nextToken());
		c=Integer.parseInt(str.nextToken());
		
		map=new char[r][c];
		
		for(int i=0;i<r;i++) {
			String tmp=br.readLine();
			for(int j=0;j<c;j++) {
				map[i][j]=tmp.charAt(j);
			}
		}
		
		//print(map);
		for(int i=0;i<r;i++) {
			dfs(i,0);
		}
		
		System.out.println(res);
	}
	
	
	private static boolean dfs(int rr, int cc) {
		// TODO Auto-generated method stub
		if(cc==c-1) {
			//빵집에 도달했을 때
			res++;
			return true;
		}
		
		for(int i=0;i<3;i++) {
			int nr=rr+dr[i];
			int nc=cc+1;
			
			if(nr<0||nr>=r||nc<0||nc>=c|| map[nr][nc]=='x') continue;
			map[nr][nc]='x';
			if(dfs(nr,nc)) return true;
			
		}
		
		return false;
	}


	private static void print(char[][] map2) {
		// TODO Auto-generated method stub
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

}
