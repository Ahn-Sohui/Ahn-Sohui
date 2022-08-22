package study.group;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj14719 {

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str= new StringTokenizer(br.readLine());
		
		int h =Integer.parseInt(str.nextToken());
		int w= Integer.parseInt(str.nextToken());
		
		int[] arr=new int[w];
		
		str=new StringTokenizer(br.readLine());
		
		for(int i=0;i<w;i++) {
			arr[i]=Integer.parseInt(str.nextToken());
		}
		int res=0;
		
		for(int i=1;i<w;i++) {
			int lf=0;
			int rt=0;
			
			for (int j = 0; j < i; j++) {
				//왼쪽 벽 찾기
				lf=Math.max(lf, arr[j]);
			}
			
			for (int j = i+1; j < w; j++) {
				//오른쪽 벽 찾기
				rt=Math.max(rt, arr[j]);
			}
			
			if(arr[i]<lf && arr[i]<rt) res+=Math.min(lf, rt)-arr[i];
		}
		System.out.println(res);
	}

}
