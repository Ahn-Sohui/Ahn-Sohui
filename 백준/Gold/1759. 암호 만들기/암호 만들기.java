import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static char[] arr;
	static char[] sel;
	static int l,c;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str=new StringTokenizer(br.readLine());
		l=Integer.parseInt(str.nextToken());
		c=Integer.parseInt(str.nextToken());
		
		arr=new char[c];
		str=new StringTokenizer(br.readLine());
		for(int i=0;i<c;i++) {
			arr[i]=str.nextToken().charAt(0);
		}
		Arrays.sort(arr);
		//System.out.println(Arrays.toString(arr));
		
		sel=new char[l];
		
		dfs(0,0);

	}
	private static void dfs(int idx, int k) {
		// TODO Auto-generated method stub
		if(k==sel.length) {
			int num1=0,num2=0; //자음, 모음
			for(int i=0;i<k;i++) {
				if(sel[i]=='a'||sel[i]=='e'||sel[i]=='i'||sel[i]=='o'||sel[i]=='u') num2++;
				else num1++;
			}
			//System.out.println(num1+" "+num2);
			if(num2>=1 && num1>=2) {
				for(int j=0;j<k;j++) {
					System.out.print(sel[j]);
				}
				System.out.println();
			}
			return;
		}
		
		for(int i=idx;i<arr.length;i++) {
			sel[k]=arr[i];
			dfs(i+1, k+1);
		}
	}

}
