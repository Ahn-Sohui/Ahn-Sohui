import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static boolean flag=false;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String start=br.readLine();
		String target=br.readLine();
		StringBuilder pr=new StringBuilder();
		
		while(!flag) {
			//맨뒤가 A라면 A를 뺀다
			//A가 아니라면 뒤집고 B를 뺀다
			if(target.length()<start.length()) break;
			
			int idx=target.length()-1;
			
			if(target.charAt(idx)=='A') {
				String tmp="";
				for(int i=0;i<idx;i++) {
					tmp=tmp+target.charAt(i);
				}
				target=tmp;
			}else {
				//B를 빼고 뒤집기
				String tmp="";
				for(int i=0;i<idx;i++) {
					tmp=tmp+target.charAt(i);
				}

				StringBuffer sb=new StringBuffer(tmp);
				target=sb.reverse().toString();
			}
			
			//System.out.println(target);
			
			if(target.equals(start)) {
				flag=true;
			}
			
		}
		
		if(flag)
			pr.append("1");
		else
			pr.append("0");
		
		System.out.println(pr);
	}
	
	

}
