import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n,m;
	static int[] sel;
	static boolean[] v;
	static StringBuilder sb=new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str=new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(str.nextToken());
		m=Integer.parseInt(str.nextToken());
		sel=new int[m];
		v=new boolean[n];
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
			if(!v[i]) {
				v[i]=true;
				sel[depth]=i+1;
				recur(depth+1);
				v[i]=false;
			}
		}
	}

}
