import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t= Integer.parseInt(br.readLine());
		
		for(int tc=0;tc<t;tc++) {
			int n=Integer.parseInt(br.readLine());
			int[] fibo_zero=new int[41];
			int[] fibo_one =new int[41];
			
			fibo_zero[0]=1;
			fibo_zero[1]=0;
			fibo_one[0]=0;
			fibo_one[1]=1;
			
			for(int i=2;i<=n;i++) {
				fibo_zero[i]=fibo_zero[i-1]+fibo_zero[i-2];
				fibo_one[i]=fibo_one[i-1]+fibo_one[i-2];
			}
			
			sb.append(fibo_zero[n]).append(" ").append(fibo_one[n]).append("\n");
		}
		
		System.out.println(sb);
	}

}
