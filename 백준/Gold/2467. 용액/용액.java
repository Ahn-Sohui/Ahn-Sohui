import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		
		long[] arr =new long[n];
		
		StringTokenizer str=new StringTokenizer(br.readLine());
		
		for(int i=0;i<n;i++) {
			arr[i]=Long.parseLong(str.nextToken());
		}
		int lt=0,rt=n-1;
		long min= Long.MAX_VALUE;
		long ans1=0,ans2=0;
		while(lt<rt) {
			if(Math.abs(arr[lt]+arr[rt])<=min) {
				ans1=arr[lt];
				ans2=arr[rt];
				min=Math.abs(arr[lt]+arr[rt]);
			}
			if(Math.abs(arr[lt+1]+arr[rt])<Math.abs(arr[lt]+arr[rt-1])) {
				lt++;
			}
			else {
				rt--;
			}
		}
		
		System.out.println(ans1+" "+ans2);
	}

}
