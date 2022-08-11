package study.group;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SWEA4050 {
	static int t,n;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc=new Scanner(System.in);
		t=sc.nextInt();
		StringBuilder sb= new StringBuilder();
		for(int tc=1;tc<=t;tc++) {
			n=sc.nextInt();
			Integer [] cost= new Integer[n];
			//StringTokenizer str = new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) {
				cost[i]=sc.nextInt();
			}
			//할인 받을 수 있는 최댓값을 구해야함
			//내림차순 정렬하고 3번째 수는 안더함
			Arrays.sort(cost, Collections.reverseOrder());
			int money=0;
			for(int i=0;i<n;i++) {
				if(i%3==2) continue;
				money+=cost[i];
			}
			
			sb.append("#"+tc+" "+money+"\n");
		}
		
		System.out.println(sb);
	}

}
