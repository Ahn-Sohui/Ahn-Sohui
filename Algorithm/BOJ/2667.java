package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class boj2667 {
	static Queue<Integer> home_cnt=new LinkedList<>(); //집 수를 저장할 큐
	static Queue<Pair> home=new LinkedList<>(); //bfs 돌릴 큐
	static int n,h_cnt;
	static int[][] map;
	static int[] dr= {-1,0,1,0};
	static int[] dc= {0,1,0,-1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		//Scanner sc=new Scanner(System.in);
		n=Integer.parseInt(br.readLine());
		map=new int[n][n];
		
		for (int i = 0; i < n; i++) {
			String str=br.readLine();
			for(int j=0;j<n;j++) {
				map[i][j]=str.charAt(j)-'0';
			}
		}

		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(map[i][j]==1) {
					home.offer(new Pair(i,j)); //첫 번째 집의 위치 저장
					map[i][j]=0; //중복체크를 피하기 위해 0으로 변경
					bfs(); //bfs 시작
				}
			}
		}
//	
//		System.out.println("=====================");
//
//		for (int i = 0; i < n; i++) {
//			
//			for (int j = 0; j < n; j++) {
//				System.out.print(map[i][j]+" ");;
//			}
//			System.out.println();
//		}
		int[] res=new int[h_cnt];
		for (int i = 0; i < h_cnt; i++) {
			res[i]=home_cnt.poll(); //배열에 저장
		}
		Arrays.sort(res); //오름차순 정렬
		
		//Arrays.sort(home_cnt);
		System.out.println(h_cnt);
		for(int i=0;i<h_cnt;i++) {
			System.out.println(res[i]);
		}
		
	}
	
	public static void bfs() {
		// TODO Auto-generated method stub
		//단지탐색
		int cnt=1;
		while(!home.isEmpty()) {
			int r=home.peek().r;
			int c=home.peek().c;
			home.remove();
			for(int i=0;i<4;i++) {
				int n_r=r+dr[i];
				int n_c=c+dc[i];
				
				if(n_r<0||n_r>=n||n_c<0||n_c>=n||map[n_r][n_c]==0) continue;
				if(map[n_r][n_c]==1) {
					cnt++;
					map[n_r][n_c]=0;
					home.offer(new Pair(n_r,n_c));
				}
			}
		}
		
		home_cnt.offer(cnt);
		cnt=0; //초기화
		h_cnt++; //단지수 증가
	}

	public static class Pair{
		int r,c;
		Pair(int r,int c){
			this.r=r;
			this.c=c;
		}
	}
	
}
