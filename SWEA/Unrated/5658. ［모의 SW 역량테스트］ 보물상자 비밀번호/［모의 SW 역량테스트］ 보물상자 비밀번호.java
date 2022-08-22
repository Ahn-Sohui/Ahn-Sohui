import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T= Integer.parseInt(br.readLine());
		StringBuilder sb= new StringBuilder();
		for(int tc=1;tc<=T;tc++) {
			sb.append("#").append(tc).append(" ");
			StringTokenizer str= new StringTokenizer(br.readLine());
			int n=Integer.parseInt(str.nextToken());
			int k=Integer.parseInt(str.nextToken());
			
			Queue<Character> nums= new LinkedList<>();
			String arr=br.readLine();
			for(int i=0;i<n;i++) {
				nums.offer(arr.charAt(i));
			}
			
			HashSet<Long> num=new HashSet<>();
			for(int rot=0;rot<n/4;rot++) {
				//뒤에거 꺼내와서 앞에 넣기
				nums.offer(nums.poll());
				for(int i=0;i<4;i++) {
					String tmp="";
					for(int j=0;j<n/4;j++) {
						char temp=nums.poll();
						tmp+=temp;
						nums.offer(temp);
					}
					num.add(Long.parseLong(tmp,16));
				}
			}
			
			
			ArrayList<Long> res=new ArrayList<>(num);
			Collections.sort(res,Collections.reverseOrder());
			//System.out.println(res.size());
			//System.out.println(res);
			sb.append(res.get(k-1)).append("\n");
		}
		System.out.println(sb);
	}

}
