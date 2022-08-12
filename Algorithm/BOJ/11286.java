package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class boj11286_1 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
			int abs1 = Math.abs(o1);
            int abs2 = Math.abs(o2);

            if(abs1 == abs2) return o1 > o2 ? 1 : -1;
            return abs1 - abs2;
        });
	
		//Scanner sc=new Scanner(System.in);
		StringBuilder sb= new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		for(int i=0;i<n;i++) {
			int command=Integer.parseInt(br.readLine());
			if(command!=0) {
				pq.add(command);
				
			}
			if(command==0) {
				if(pq.size()==0) {
					sb.append("0\n");
				}
				else {
					sb.append(pq.poll()+"\n");
				}
			}
		}
		System.out.println(sb);
		//System.out.println(pq);
	}

}
