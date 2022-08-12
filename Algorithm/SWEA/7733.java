package com.ssafy.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWEA7733 {
	static int t,n;
	static int[][] cheese;
	static int[][] chk;
	static int max=Integer.MIN_VALUE;
	static int cnt;
	static Queue<Pair> q= new LinkedList<>();
	
	static int[] dr= {-1,0,1,0};
	static int[] dc= {0,1,0,-1};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner(System.in);
		t=sc.nextInt();
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= t; tc++) {
			n=sc.nextInt();
			cheese=new int[n][n];
			int max_delicious=0;
			for(int i=0;i<n;i++) {
				for (int j = 0; j < n; j++) {
					cheese[i][j]=sc.nextInt();
					max_delicious = Math.max(max_delicious, cheese[i][j]);
				}
			}
			
			for(int i=0;i<=max_delicious;i++) {
				chk=new int[n][n];
				//System.out.println("i: "+i);
				Check(i);
				//print(chk);
				//print(cheese);
				//System.out.println(cnt);
				max=Math.max(max, cnt);
				cnt=0;
			}
			
			sb.append("#"+tc+" "+max+"\n");
			max=0;
		}
		
		System.out.println(sb);
	}
	private static void Check(int day) {
		// TODO Auto-generated method stub
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(cheese[i][j]==day) {
					cheese[i][j]=0; //요정이 먹음
				}
			}
		}
		//치즈 덩어리 세기
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(cheese[i][j]!=0 && chk[i][j]==0) {
					chk[i][j]=1;
					q.offer(new Pair(i,j));
					bfs();
					
					//print(chk);
				}
			}
		}
	}
	
	
	private static void print(int[][] chk) {
		// TODO Auto-generated method stub
		for(int i=0;i<chk.length;i++) {
			for (int j = 0; j < chk.length; j++) {
				System.out.print(chk[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	private static void bfs() {
		// TODO Auto-generated method stub
		while(!q.isEmpty()) {
			Pair tmp=q.poll();
			for(int i=0;i<4;i++) {
				int rr= tmp.r+dr[i];
				int cc=tmp.c+dc[i];
				if(rr<0||rr>=n||cc<0||cc>=n||cheese[rr][cc]==0||chk[rr][cc]!=0) continue;
				if(cheese[rr][cc]!=0 && chk[rr][cc]==0) {
					chk[rr][cc]=1;
					q.offer(new Pair(rr,cc));
				}
			}
		}
		
		cnt++;
	}



	static public class Pair{
		int r,c;
		Pair(int r, int c){
			this.r=r;
			this.c=c;
		}
	}

}
