import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int [] arr;
	static int n;
	static int res;
	public static void main(String[] args) throws Exception {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		arr=new int[n];
		
		Queen(0);
		System.out.println(res);
	}
	private static void Queen(int L) {
		// TODO Auto-generated method stub
		if(L==n) {
			res++;
			return;
		}
		
		for(int i=0;i<n;i++) {
			arr[L]=i;
			
			//놓을 수 있을 때
			if(chk(L)) {
				Queen(L+1);
			}
		}
	}
	private static boolean chk(int c) {
		for(int i=0;i<c;i++) {
			//같은 행
			if(arr[c]==arr[i])
				return false;
			//대각선
			else if(Math.abs(c-i)==Math.abs(arr[c]-arr[i]))
				return false;
		}
		return true;
	}
}	
