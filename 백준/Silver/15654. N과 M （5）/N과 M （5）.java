import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n,m;
	static int[] sel;
	static int[] arr;
	static boolean[] visited;
	static StringBuilder sb=new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str=new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(str.nextToken());
		m=Integer.parseInt(str.nextToken());
		sel=new int[m];
		arr=new int[n];
		visited=new boolean[n];
		
		str=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(str.nextToken());
		}
		
		Arrays.sort(arr);
		
		recur(0);
		
		System.out.println(sb);
	}
	
	private static void recur(int depth) {
		if(depth==m) {
			for(int i=0;i<m;i++) {
				sb.append(sel[i]).append(" ");
			}
			sb.append("\n");
			
			return;
		}
		
		for(int i=0;i<n;i++) {
			if(!visited[i]) {
				visited[i]=true;
				sel[depth]=arr[i];
				recur(depth+1);
				visited[i]=false;
			}
		}
	}

}
