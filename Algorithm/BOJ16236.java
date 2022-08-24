package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 아기상어_안소희 {
	static int[][] sea,chk;
	static int n;
	static int[] dr= {-1,0,1,0};
	static int[] dc= {0,1,0,-1};
	static int babyshark_r,babyshark_c,shark_age=2,e_fish,res;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		n= Integer.parseInt(br.readLine());
		StringTokenizer str=new StringTokenizer("");
		sea=new int[n][n];
		
		
		for(int i=0;i<n;i++) {
			str=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				sea[i][j]=Integer.parseInt(str.nextToken());
				if(sea[i][j]==9) {
					babyshark_r=i;
					babyshark_c=j;
				}
			}
		}
		
		sea[babyshark_r][babyshark_c]=0;
		
		while(true) {
			chk=new int[n][n];
			
			if(e_fish>=shark_age) {
				//먹은 물고기 수가 상어의 나이보다 크다면
				e_fish-=shark_age;
				shark_age++;
			}
			//물고기를 먼저 찾을것
			ArrayList<Loc> fish= new ArrayList<>();
			Queue<Loc> shark=new LinkedList<>();
			shark.offer(new Loc(babyshark_r,babyshark_c));
			
			while(!shark.isEmpty()) {
				Loc tmp=shark.poll();
				for(int i=0;i<4;i++) {
					int rr=tmp.r+dr[i];
					int cc=tmp.c+dc[i];
					if(rr>=0 && rr<n && cc>=0 && cc<n) {
						if(chk[rr][cc]==0) {
							//방문하지 않았던 곳
							if(sea[rr][cc]==0 || sea[rr][cc]==shark_age) {
								//빈칸 지나가기 가능
								shark.offer(new Loc(rr,cc));
								chk[rr][cc]=chk[tmp.r][tmp.c]+1;
							}
							if(sea[rr][cc]>=1 && sea[rr][cc]<shark_age) {
								//먹을 수 있는 물고기를 큐에 집어넣음
								shark.offer(new Loc(rr,cc));
								fish.add(new Loc(rr,cc));
								chk[rr][cc]=chk[tmp.r][tmp.c]+1;
							}
						}
					}
				}
			}
			
			//물고기 먹으러 가자
			if(fish.size()==0) {
				//먹을 수 있는 물고기가 없을 때
				//print(chk);
				
				System.out.println(res);
				return;

			}
			else if(fish.size()==1) {
				//먹을 수 있는 물고기가 한마리
				//이동
				babyshark_r= fish.get(0).r;
				babyshark_c=fish.get(0).c;
				//냠
				sea[babyshark_r][babyshark_c]=0;
				e_fish++;
				res+=chk[babyshark_r][babyshark_c];
			}
			else {
				//두 마리 이상
				int min_dist=Integer.MAX_VALUE;
				for(int i=0;i<fish.size();i++) {
					min_dist=Math.min(min_dist, chk[fish.get(i).r][fish.get(i).c]);
				}
				ArrayList<Loc> min_fish=new ArrayList<>();
				for(int i=0;i<fish.size();i++) {
					if(min_dist==chk[fish.get(i).r][fish.get(i).c]) {
						//거리가 짧은 물고기들 집어 넣기
						min_fish.add(new Loc(fish.get(i).r,fish.get(i).c));
					}
				}
				
				if(min_fish.size()==1) {
					//한마리라면
					//이동
					babyshark_r= min_fish.get(0).r;
					babyshark_c=min_fish.get(0).c;
					//냠
					sea[babyshark_r][babyshark_c]=0;
					e_fish++;
					res+=chk[babyshark_r][babyshark_c];
				}
				else {
					//두마리 이상 이라면
					//정렬이 필요함
					Collections.sort(min_fish,new Comparator<Loc>() {

						@Override
						public int compare(Loc o1, Loc o2) {
							if(o1.r==o2.r)
								return o1.c-o2.c;
							return o1.r-o2.r;
						}
					});
					//이동
					babyshark_r= min_fish.get(0).r;
					babyshark_c=min_fish.get(0).c;
					//냠
					sea[babyshark_r][babyshark_c]=0;
					e_fish++;
					res+=chk[babyshark_r][babyshark_c];
				}
				
			}
		}
		
		
	}
	
	private static void print(int[][] chk2) {
		// TODO Auto-generated method stub
		for(int i=0;i<chk2.length;i++) {
			for(int j=0;j<chk2[i].length;j++) {
				System.out.print(chk2[i][j]);
			}
			System.out.println();
		}
	}

	static class Loc{
		int r,c;
		Loc(int r,int c){
			this.r=r;
			this.c=c;
		}
	}

}
