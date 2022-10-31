import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n,k;
	static int[] degree;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str=new StringTokenizer(br.readLine());
		n=Integer.parseInt(str.nextToken());
		k=Integer.parseInt(str.nextToken());
		
		degree=new int[n];
		str=new StringTokenizer(br.readLine());
		int sum=0; //초기값
		for(int i=0;i<n;i++) {
			degree[i]=Integer.parseInt(str.nextToken());
			if(i<k)
				sum+=degree[i];
		}
		
		if(n==k) {
			System.out.println(sum);
		}else {

			int max=Integer.MIN_VALUE;
			for(int i=0;i<=n-k;i++) {
				max=Math.max(max, sum);
				if(i==n-k) {
					max=Math.max(max, sum);
					break;
				}
				sum=sum-degree[i]+degree[i+k];
			}
			System.out.println(max);
		}
		
		
	}

}
