import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main{

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		String str=br.readLine();
		String boom=br.readLine();
		StringBuilder sb = new StringBuilder();
		int boomSize=boom.length();
		
		Stack<Character> tmp =new Stack<>();
		
		for(int i=0;i<str.length();i++) {
			//boom 의 길이와 같아질 때 까지 집어넣기
			tmp.push(str.charAt(i));
			if(tmp.size()>=boomSize) {
				boolean flag=true;
				
				//tmp.size-boomSize 부터 tmp.size 까지 탐색
				//boom 이랑 일치하면 제거
				for(int j=0;j<boomSize;j++) {
					if(tmp.get(tmp.size()-boomSize+j) != boom.charAt(j)){
						flag=false;
						break;
					}
				}
				if(flag) {
					for(int j=0;j<boomSize;j++)
						tmp.pop();
				}
			}
		}
		
		for(Character c : tmp) {
			sb.append(c);
		}
		
		if(sb.length()==0)
			System.out.println("FRULA");
		else
			System.out.println(sb);
	}

}
