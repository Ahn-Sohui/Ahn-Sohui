import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main{
	static int n,k;
	static boolean[] alpha;
	static List<String> voca=new ArrayList<>();
	static int res=-1;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str=new StringTokenizer(br.readLine());
		n=Integer.parseInt(str.nextToken());
		k=Integer.parseInt(str.nextToken());
		alpha=new boolean[26];
		//anta,tica 는 필수
		alpha[0]=true;
		alpha['n'-'a']=true;
		alpha['t'-'a']=true;
		alpha['i'-'a']=true;
		alpha['c'-'a']=true;
		
		
		
		for(int i=0;i<n;i++) {
			String tmp=br.readLine();
			//중복되는 anta, tica 없애기
			tmp=tmp.replace("a", "");
			tmp=tmp.replace("n", "");
			tmp=tmp.replace("t", "");
			tmp=tmp.replace("i", "");
			tmp=tmp.replace("c", "");
			
			//System.out.println(tmp);
			
			voca.add(tmp);
		}
		
		
		
		if(k<5)
			System.out.println(0);
		else {
			teaching(0,1);
			System.out.println(res);
		}
		
	}

	private static void teaching(int cnt,int start) {
		if(cnt==k-5) {
			//가르칠 수 있는 단어의 수 세기
			int vocas=0;
			
			for(int i=0;i<n;i++) {
				boolean flag=true;
				for(int j=0;j<voca.get(i).length();j++) {
					if(!alpha[voca.get(i).charAt(j)-'a']) {
						flag=false;
						break;
					}
				}
				
				if(flag) vocas++;
			}
			
			res=Math.max(res, vocas);
			
		}
		
		for(int i=start;i<26;i++) {
			if(!alpha[i]) {
				alpha[i]=true;
				teaching(cnt+1, i+1);
				alpha[i]=false;
			}
		}
		
	}
}