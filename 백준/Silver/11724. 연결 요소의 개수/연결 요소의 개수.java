import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int v,e;
	static int[][] graph;
	static boolean[] chk;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str= new StringTokenizer(br.readLine());
		v=Integer.parseInt(str.nextToken());
		e=Integer.parseInt(str.nextToken());
		
		graph=new int[v+1][v+1];
		chk=new boolean[v+1];
		for(int i=0;i<e;i++) {
			str=new StringTokenizer(br.readLine());
			int from=Integer.parseInt(str.nextToken());
			int to=Integer.parseInt(str.nextToken());
			
			graph[from][to]=graph[to][from]=1;
		}
		
		int res=0;
		
		for(int i=1;i<=v;i++) {
			if(chk[i]==false) {
				dfs(i);
				res++;
			}
		}
		
		System.out.println(res);
	}
	
	private static void dfs(int idx) {
		if(chk[idx]==true) return;
		
		chk[idx]=true;
		for(int i=1;i<=v;i++) {
			if(graph[idx][i]==1)
				dfs(i);
		}
		
	}
	
}
