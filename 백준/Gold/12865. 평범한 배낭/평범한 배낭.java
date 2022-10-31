import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		//Scanner sc= new Scanner(System.in);
		StringTokenizer str=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(str.nextToken());
		int k=Integer.parseInt(str.nextToken());
		
		int [][] dp=new int[n+1][k+1];
		int[] w=new int[n+1];
		int[] v=new int[n+1];
		
		for(int i=1;i<=n;i++) {
			str=new StringTokenizer(br.readLine());
			w[i]=Integer.parseInt(str.nextToken());
			v[i]=Integer.parseInt(str.nextToken());
		}
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=k;j++) {
				if(w[i]>j) {
					dp[i][j]=dp[i-1][j];
				}
				else {
					dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-w[i]]+v[i]);
				}
			}
		}
		
		System.out.println(dp[n][k]);
	}

}
