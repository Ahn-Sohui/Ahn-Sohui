import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n,m;
	static int[][] map;
	static int[][] dp;
	static int ans=0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str=new StringTokenizer(br.readLine());
		m=Integer.parseInt(str.nextToken());
		n=Integer.parseInt(str.nextToken());
		map=new int[m][n];
		dp=new int[m][n];
        
		for(int i=0;i<m;i++) {
			str=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j]=Integer.parseInt(str.nextToken());
                dp[i][j]=-1;
			}
		}
        
		
		System.out.println(dfs_dp(0,0));
		//print(dp);
	}
	
	private static void print(int[][] dp2) {
		for(int i=0;i<dp2.length;i++) {
			for(int j=0;j<dp2[i].length;j++) {
				System.out.print(dp2[i][j]);
			}
			System.out.println();
		}
		
	}

	static int[] dr= {0,1,0,-1};
	static int[] dc= {1,0,-1,0};
	
	private static int dfs_dp(int r, int c) {
		if(r==m-1 && c==n-1) {
			return 1;
		}
        if(dp[r][c]!=-1){
            return dp[r][c];
        }
		dp[r][c]=0;
		for(int i=0;i<4;i++) {
			int nr=r+dr[i];
			int nc=c+dc[i];
			
			if(nr>=0 &&nr<m && nc>=0 &&nc<n&&map[nr][nc]<map[r][c]) {
				dp[r][c]=dp[r][c]+dfs_dp(nr,nc);
			}
		}
		
		return dp[r][c];
	}

	static class Pair{
		int r,c;
		Pair(int r,int c){
			this.r=r;
			this.c=c;
		}
	}
}
