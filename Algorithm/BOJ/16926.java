package study.group;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16926 {
	static int n,m,r;
	static int[][] arr;
	static int[] dr= {0,1,0,-1};
	static int[] dc= {1,0,-1,0};
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str= new StringTokenizer(br.readLine());
		n=Integer.parseInt(str.nextToken());
		m=Integer.parseInt(str.nextToken());
		r=Integer.parseInt(str.nextToken());
		
		int[][] arr=new int[n][m];
		
		for (int i = 0; i < n; i++) {
			str=new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j]=Integer.parseInt(str.nextToken());
			}
		}
		
		for(int i=0;i<r;i++) {
			int num=Math.min(n, m)/2;
			
			for (int j = 0; j < num; j++) {
				int s_r=j;
				int s_c=j;
				int dir=0;
				int tmp=arr[s_r][s_c];
				while(dir<4) {
					int n_r=s_r+dr[dir];
					int n_c=s_c+dc[dir];
					if(n_r>=j && n_r<n-j && n_c>=j && n_c<m-j) {
						arr[s_r][s_c]= arr[n_r][n_c];
						s_r=n_r;
						s_c=n_c;
					}else {
						dir++;
					}
				
				}
				arr[j+1][j]=tmp;
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		
	}

}
