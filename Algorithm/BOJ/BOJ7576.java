package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 토마토_안소희 {
	static int[][] farm;
	static int n,m;
	static int[][] chk;
	static int[] dr= {-1,0,1,0};
	static int[] dc= {0,1,0,-1};
	static Queue<Loc> tomato=new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str= new StringTokenizer(br.readLine());
		
		m=Integer.parseInt(str.nextToken());
		n=Integer.parseInt(str.nextToken());
		farm=new int[n][m];
		chk=new int[n][m];
		for(int i=0;i<n;i++) {
			str=new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				farm[i][j]=Integer.parseInt(str.nextToken());
				if(farm[i][j]==1) tomato.offer(new Loc(i,j));
			}
		}
		//print(farm);
		bfs();
		//print(farm);
		boolean flag=true;
		int max=Integer.MIN_VALUE;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(farm[i][j]==0) flag=false;
			}
		}
		
		if(flag) {
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					max=Math.max(max, chk[i][j]);
				}
			}
			System.out.println(max);
		}
		else System.out.println(-1);
			
		
	}
	
	private static void bfs() {
		// 토마토 익히기
		while(!tomato.isEmpty()) {
			Loc tmp=tomato.poll();
			for(int i=0;i<4;i++) {
				int rr=tmp.r+dr[i];
				int cc=tmp.c+dc[i];
				if(rr>=0 && rr<n && cc>=0 && cc<m) {
					if(farm[rr][cc]==0) {
						tomato.offer(new Loc(rr,cc));
						farm[rr][cc]=1;
						chk[rr][cc]=chk[tmp.r][tmp.c]+1;
					}
				}
			}
		}
	}
	
	static class Loc{
		int r,c;
		Loc(int r,int c){
			this.r=r;
			this.c=c;
		}
	}
	
	private static void print(int[][] farm2) {
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				System.out.print(farm2[i][j]);
			}
			System.out.println();
		}
		
	}


}
