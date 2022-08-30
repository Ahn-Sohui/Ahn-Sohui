import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node{
		int vertex,weight;
		Node next;
		
		Node(int vertex,int weight,Node next){
			this.vertex=vertex;
			this.weight=weight;
			this.next=next;
		}
		
		Node(int vertex,int weight){
			this.vertex=vertex;
			this.weight=weight;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(str.nextToken())+1;
		int m=Integer.parseInt(str.nextToken());
		int x=Integer.parseInt(str.nextToken());
		
		Node[] party=new Node[n];
		
		for(int i=0;i<m;i++) {
			str=new StringTokenizer(br.readLine());
			int from=Integer.parseInt(str.nextToken());
			int to=Integer.parseInt(str.nextToken());
			int weight=Integer.parseInt(str.nextToken());
			
			party[from]=new Node(to,weight,party[from]);
		}
		
		int[] dis=new int[n];
		boolean[] v = new boolean[n];
		
		Arrays.fill(dis, Integer.MAX_VALUE);
		PriorityQueue<Node> pq=new PriorityQueue<>((v1,v2)->v1.weight-v2.weight);
		
		dis[x]=0;
		pq.offer(new Node(x,dis[x]));
		
		while(!pq.isEmpty()) {
			Node minNode= pq.poll();
			if(v[minNode.vertex]) continue;
			
			v[minNode.vertex]=true;
			
			for(Node tmp=party[minNode.vertex]; tmp!=null; tmp=tmp.next) {
				if(!v[tmp.vertex] && dis[tmp.vertex]>dis[minNode.vertex]+tmp.weight) {
					dis[tmp.vertex]=dis[minNode.vertex]+tmp.weight;
					pq.offer(new Node(tmp.vertex,dis[tmp.vertex]));
				}
			}
		}
		int max=Integer.MIN_VALUE;
		int[] dis2=new int[n];
		//각자 집에서 목적지 까지 가는 최단경로 찾기
		for(int i=1;i<n;i++) {
			if(i==x) continue;
			Arrays.fill(dis2, Integer.MAX_VALUE);
			Arrays.fill(v, false);
			PriorityQueue<Node> pq2=new PriorityQueue<>((v1,v2)->v1.weight-v2.weight);
			dis2[i]=0;
			pq2.offer(new Node(i,dis[i]));
			
			while(!pq2.isEmpty()) {
				Node minNode=pq2.poll();
				if(v[minNode.vertex]) continue;
				
				v[minNode.vertex]=true;
				
				for(Node tmp=party[minNode.vertex]; tmp!=null; tmp=tmp.next) {
					if(!v[tmp.vertex] && dis2[tmp.vertex]>dis2[minNode.vertex]+tmp.weight) {
						dis2[tmp.vertex]=dis2[minNode.vertex]+tmp.weight;
						pq2.offer(new Node(tmp.vertex,dis2[tmp.vertex]));
					}
				}
				
			}
			dis[i]+=dis2[x];
		}
		
		for(int i=1;i<n;i++) {
			max=Math.max(max, dis[i]);
		}
		
		System.out.println(max);
	}

}
