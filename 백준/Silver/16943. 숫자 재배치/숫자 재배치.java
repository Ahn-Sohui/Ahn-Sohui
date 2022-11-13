import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static String a,b;
	static int bnum;
	static int[] arr;
	static boolean[] visited;
	static int res;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str=new StringTokenizer(br.readLine());
		
		a=str.nextToken();
		bnum=Integer.parseInt(str.nextToken());
		
		visited=new boolean[a.length()];
		arr=new int[a.length()];
		
		for(int i=0;i<arr.length;i++) {
			arr[i]=a.charAt(i)-'0';
		}
		
		res=-1;
		recur(0,0);
		
		System.out.println(res);
	}

	private static void recur(int depth, int num) {
		if(depth==a.length()) {
			res=Math.max(res, num);
			return;
		}
		
		for(int i=0;i<a.length();i++) {
			if(visited[i]) continue;
			if(depth==0 && arr[i]==0) continue; //맨 앞자리가 0일 땐 넘어감
			if(num *10 +arr[i]>bnum) continue; // 다음 순열에 해당하는 값이 b 보다 크다면 넘어감
			
			visited[i]=true;
			recur(depth+1, num*10+arr[i]);
			visited[i]=false;
		}
		
	}

}
