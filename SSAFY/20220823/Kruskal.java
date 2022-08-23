package day07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Kruskal {
	static int v,e;
	static int[][] edges;
	static int [] parents;
	static int[] rank;
	static int[][] sel;
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("크루스칼.txt"));
		Scanner sc= new Scanner(System.in);
		v=sc.nextInt();
		e=sc.nextInt();
		edges=new int[e][3]; //start, end, weight
		
		for(int i=0;i<e;i++) {
			edges[i][0]=sc.nextInt();
			edges[i][1]=sc.nextInt();
			edges[i][2]=sc.nextInt();	
		}
		
		//solving
		//가중치 기준으로 오름차순 정렬하고
		//v-1개까지 순환이 생기지 않는 간선을 선택한다
		Arrays.sort(edges, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1[2], o2[2]);
			}
		});
		
//		for(int i=0;i<edges.length;i++) {
//			System.out.println(Arrays.toString(edges[i]));
//		}
		parents=new int[v];
		rank=new int[v];
		sel=new int[v-1][3];
		//makeSet
		for(int  i=0;i<parents.length;i++) {
			parents[i]=i;
		}
		//v-1 까지 순환이 생기지 않는 간선을 선택한다
		int cnt=0;
		for(int i=0;i<e;i++) {
			int pi=findSet(edges[i][0]);
			int pj=findSet(edges[i][1]);
			
			//같은 그룹이면 싸이클이 형성되니 선택하지 않고 넘어간다.
			if(pi==pj) continue;
			
			union(pi,pj);
			sel[cnt]=edges[i];
			cnt++;
			if(cnt==v-1) break;
		}
		for (int i = 0; i < sel.length; i++) {
			System.out.println(Arrays.toString(sel[i]));
		}
		
	}
	
	
	private static void union(int i, int j) {
		int pi=findSet(i);
		int pj=findSet(j);
		//System.out.println(pi +", "+pj);
		if(rank[pi]>rank[pj]) {
			parents[pj]=pi;
		}else {
			parents[pi]=pj;
			if(rank[pi]==rank[pj]) {
				rank[pj]++;
			}
		}
	}


	private static int findSet(int i) {
		if(parents[i]==i) return i;
		return findSet(parents[i]);
	}

}
