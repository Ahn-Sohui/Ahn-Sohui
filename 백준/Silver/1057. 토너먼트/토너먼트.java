import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer str=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(str.nextToken());
		int jimin=Integer.parseInt(str.nextToken());
		int hansu=Integer.parseInt(str.nextToken());
		
		int res=0;
		
		while(jimin!=hansu) {
			jimin=jimin/2+jimin%2;
			hansu=hansu/2+hansu%2;
			
			res++;
		}
		
		System.out.println(res);
	}

}
