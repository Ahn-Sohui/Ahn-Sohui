import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
	static int n,k;
	static int[] cards;
	static int[] sel;
	static HashSet<String> res;
	public static void main(String[] args) throws Exception{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		k=Integer.parseInt(br.readLine());
		
		cards=new int[n];
		sel=new int[k];
		res=new HashSet<>();
		for(int i=0;i<n;i++) {
			cards[i]=Integer.parseInt(br.readLine());
		}
		
		recur(0,0);
		
		//System.out.println(res.toString());
		System.out.println(res.size());
	}
	
	private static void recur(int start, int idx) {
		if(idx==k) {
			//숫자 만들기
			boolean[] visited=new boolean[sel.length];
			int[] tmp=new int[sel.length];
			//System.out.println(Arrays.toString(sel));
			makeNum(0,visited,tmp);			
			return;
		}
		
		for(int i=start;i<n;i++) {
			sel[idx]=cards[i];
			recur(i+1,idx+1);
		}
	}

	private static void makeNum(int idx, boolean[] visited, int[] tmp) {
		//순열 구해서 hashset 에 집어넣기
		if(idx==tmp.length) {
			//System.out.println(Arrays.toString(tmp));
			String str="";
			for(int i=0;i<tmp.length;i++) {
				str+=tmp[i]+"";
			}
			//System.out.println(str);
			res.add(str);
		}
		
		for(int i=0;i<sel.length;i++) {
			if(!visited[i]) {
				visited[i]=true;
				tmp[idx]=sel[i];
				makeNum(idx+1, visited, tmp);
				visited[i]=false;
			}
		}
	}

}
