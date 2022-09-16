import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{

	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		String str=br.readLine();
		
		int[] alpha =new int[26];
		
		for(int i=0;i<str.length();i++) {
			//알파벳의 갯수세기
			alpha[str.charAt(i)-'A']++;
		}
		
		int odd=0, mid_idx=0;
		
		for(int i=0;i<26;i++) {
			//홀수개인 알파벳 수
			if(alpha[i]%2!=0) {
				odd++;
				mid_idx=i;
			}
		}
		
		StringBuilder sb=new StringBuilder();
		//홀수개인 알파벳 수가 1개보다 많거나 || 1개이면서 문자열의 길이가 짝수면 불가능
		if(odd>1 || (odd==1 && str.length()%2==0)) sb.append("I'm Sorry Hansoo\n");
		else {
			for(int i=0;i<alpha.length;i++) {
				for(int j=0;j<alpha[i]/2;j++) {
					char tmp=(char)(i+'A');
					sb.append(tmp);
				}
			}

			if(str.length()%2==1) {
				char tmp=(char)(mid_idx+'A');
				sb.append(tmp);
			}
			
			
			for(int i=25;i>=0;i--) {
				for(int j=0;j<alpha[i]/2;j++) {
					char tmp=(char)(i+'A');
					sb.append(tmp);
				}
			}
		}
		
		System.out.println(sb);
	}

}
