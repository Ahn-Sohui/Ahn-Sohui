import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n,m;
	static int[] arr;
	static int[] sel;
	static StringBuilder sb=new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str=new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(str.nextToken());
		m=Integer.parseInt(str.nextToken());
		
		arr=new int[n];
		sel=new int[m];
		
		str=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(str.nextToken());
		}
		
		Arrays.sort(arr);
		
		recur(0);
		
		System.out.println(sb);
	}

	private static void recur(int idx) {
		if(idx==m) {
			for(int i=0;i<m;i++) {
				sb.append(sel[i]).append(" ");
			}
			sb.append("\n");
			
			return;
		}
		
		int pre=-1;
		for(int i=0;i<n;i++) {
			if(pre!=arr[i]) {
				pre=arr[i];
				sel[idx]=pre;
				recur(idx+1);
			}
		}
	}

}
