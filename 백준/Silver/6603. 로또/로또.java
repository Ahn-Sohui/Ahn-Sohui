import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] lotto,sel;
	static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer str= new StringTokenizer(br.readLine());
			n=Integer.parseInt(str.nextToken());
			if(n==0) return;
			lotto=new int[n];
			sel=new int[6];
			
			for(int i=0;i<n;i++) {
				lotto[i]=Integer.parseInt(str.nextToken());
			}
			
			recur(0,0);
			System.out.println();
		}
	}
	
	private static void recur(int idx, int k) {
		if(k==sel.length) {
			for(int i=0;i<k;i++) {
				System.out.print(sel[i]+" ");
			}
			System.out.println();
			return;
		}
		
		for(int i=idx;i<lotto.length;i++) {
			sel[k]=lotto[i];
			recur(i+1,k+1);
		}
		
	}

}
