import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static int[][] soccer;
	static int res;
	static boolean[] sel;
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int c=Integer.parseInt(br.readLine());
		soccer=new int[11][11];
		StringBuilder sb= new StringBuilder();
		
		for(int tc=0;tc<c;tc++) {
			for(int i=0;i<11;i++) {
				StringTokenizer str=new StringTokenizer(br.readLine());
				for(int j=0;j<11;j++) {
					soccer[i][j]=Integer.parseInt(str.nextToken());
				}
			}
			res=Integer.MIN_VALUE;
			
			sel=new boolean[11];
			
			dfs(0,0);
			
			sb.append(res).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static void dfs(int cnt,int sum) {
		if(cnt==11) {
			res=Math.max(res, sum);
			
			return;
		}
		
		for(int i=0;i<11;i++) {			
			if(sel[i] || soccer[cnt][i]==0) continue;
			sel[i]=true;
			dfs(cnt+1,sum+soccer[cnt][i]);
			sel[i]=false;
		}
	
	}

}
