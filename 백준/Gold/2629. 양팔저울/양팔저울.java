import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[] weight;
	static boolean[][] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		//추의 갯수
		n=Integer.parseInt(br.readLine());
		weight=new int[n]; //추의 무게들
		StringTokenizer str=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			weight[i]=Integer.parseInt(str.nextToken());
		}
		
		dp=new boolean[31][15001];
		
		calc_dp(0,0);
		
		StringBuilder sb= new StringBuilder();
		int m=Integer.parseInt(br.readLine()); //구슬의 갯수
		str=new StringTokenizer(br.readLine());
		
		for(int i=0;i<m;i++) {
			int tmp=Integer.parseInt(str.nextToken()); //현재 구슬의 무게
			if(tmp>15000) sb.append("N ");
			else sb.append(dp[n][tmp]?"Y ":"N ");
		}
		
		System.out.println(sb);
	}

	private static void calc_dp(int idx, int sum) {
		if(dp[idx][sum]) return;
		dp[idx][sum]=true;
		if(idx==n) return;
		
		calc_dp(idx+1, sum+weight[idx]); //구슬의 반대편에 추 추가
		calc_dp(idx+1, sum); //현재 상태 유지
		calc_dp(idx+1, Math.abs(sum-weight[idx])); //구슬 쪽에 추 추가
	}

}
