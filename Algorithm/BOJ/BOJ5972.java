package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj5972 {
	static class Node{
		int vertex,weight;
		Node next;
		
		Node(int vertex,int weight,Node next){
			this.vertex=vertex;
			this.weight=weight;
			this.next=next;
		}
		
		Node (int vertex,int weight){
			this.vertex=vertex;
			this.weight=weight;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str= new StringTokenizer(br.readLine());
		
		int n=Integer.parseInt(str.nextToken())+1;
		int m=Integer.parseInt(str.nextToken());
		
		Node[] cow= new Node[n];
		
		for(int i=0;i<m;i++) {
			str=new StringTokenizer(br.readLine());
			int from=Integer.parseInt(str.nextToken());
			int to=Integer.parseInt(str.nextToken());
			int weight=Integer.parseInt(str.nextToken());
			
			cow[from]=new Node(to,weight,cow[from]);
			cow[to]=new Node(from,weight,cow[to]);
		}
		
		int[] dis = new int[n];
		boolean[] v=new boolean[n];
		
		PriorityQueue<Node> pq=new PriorityQueue<>((v1,v2)->v1.weight-v2.weight);
		
		Arrays.fill(dis, Integer.MAX_VALUE);
		dis[1]=0;
		pq.offer(new Node(1,dis[1]));
		
		while(!pq.isEmpty()) {
			
			Node minNode= pq.poll();
			
			if(v[minNode.vertex]) continue;
			
			v[minNode.vertex]=true;
			
			//System.out.println(Arrays.toString(v));
			
			for(Node tmp=cow[minNode.vertex];tmp!=null; tmp=tmp.next) {
				if(!v[tmp.vertex] && dis[tmp.vertex]>dis[minNode.vertex]+tmp.weight) {
					dis[tmp.vertex]=dis[minNode.vertex]+tmp.weight;
					pq.offer(new Node(tmp.vertex,dis[tmp.vertex]));
				}
			}
		}
		StringBuilder sb=new StringBuilder();
		sb.append(dis[n-1]);
		
		System.out.println(sb);
	}

}
