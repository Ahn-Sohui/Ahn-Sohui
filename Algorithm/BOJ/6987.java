package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj6987 {
	static Nation[] worldCup;
	static int[] home= {0,0,0,0,0,1,1,1,1,2,2,2,3,3,4};
	static int[] away= {1,2,3,4,5,2,3,4,5,3,4,5,4,5,5};
	
	static StringBuilder sb=new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0;i<4;i++) {
			int tot_game=0;
			StringTokenizer str= new StringTokenizer(br.readLine());
			worldCup=new Nation[6];
			for(int j=0;j<6;j++) {
				worldCup[j]=new Nation(Integer.parseInt(str.nextToken()),Integer.parseInt(str.nextToken()),Integer.parseInt(str.nextToken()));
				tot_game+=worldCup[j].win+worldCup[j].draw+worldCup[j].lose;
			}
			if(tot_game>30) {
				sb.append(0+" ");
				continue;
			}
			
			if(dfs(0)) sb.append(1+" ");
			else sb.append(0+" ");

		}
		
		System.out.println(sb);
	}
	


	private static boolean dfs(int game) {
		if(game==15) {
			return true;
		}
		
		if(worldCup[home[game]].win>0 && worldCup[away[game]].lose>0) {
			worldCup[home[game]].win--;
			worldCup[away[game]].lose--;
			if(dfs(game+1)) return true;
			worldCup[home[game]].win++;
			worldCup[away[game]].lose++;
		}
		
		if(worldCup[home[game]].lose>0 && worldCup[away[game]].win>0) {
			worldCup[home[game]].lose--;
			worldCup[away[game]].win--;
			if(dfs(game+1)) return true;
			worldCup[home[game]].lose++;
			worldCup[away[game]].win++;
		}
		
		if(worldCup[home[game]].draw>0 && worldCup[away[game]].draw>0) {
			worldCup[home[game]].draw--;
			worldCup[away[game]].draw--;
			if(dfs(game+1)) return true;
			worldCup[home[game]].draw++;
			worldCup[away[game]].draw++;
		}
		
		return false;
	}



	static public class Nation{
		int win,draw,lose;
		
		Nation(int win,int draw,int lose){
			this.win=win;
			this.draw=draw;
			this.lose=lose;
		}	
	}
}
