import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
	static class Node {
		int vertex, weight;
		Node next;

		Node(int vertex, int weight,Node next) {
			this.vertex = vertex;
			this.weight = weight;
			this.next=next;
		}
		
		Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(br.readLine());
        StringBuilder sb=new StringBuilder();
		int V = Integer.parseInt(str.nextToken())+1;
		int E = Integer.parseInt(str.nextToken());

		int start = Integer.parseInt(br.readLine());

		Node[] arr=new Node[V];

		for (int i = 0; i < E; i++) {
			str=new StringTokenizer(br.readLine());
			int u = Integer.parseInt(str.nextToken());
			int v = Integer.parseInt(str.nextToken());
			int w =Integer.parseInt(str.nextToken());
			
			arr[u]=new Node(v,w,arr[u]);
		}

		int[] dis = new int[V];
		boolean[] v = new boolean[V];

		Arrays.fill(dis, Integer.MAX_VALUE);
		dis[start] = 0;
		PriorityQueue<Node> pq=new PriorityQueue<>((v1,v2)->v1.weight-v2.weight);
		pq.offer(new Node(start,dis[start]));
		
		while(!pq.isEmpty()) {
			Node minNode=pq.poll();
			if(v[minNode.vertex]) continue;
			
			v[minNode.vertex]=true;
			
			for(Node temp=arr[minNode.vertex]; temp!=null;temp=temp.next) {
				if(!v[temp.vertex] && dis[temp.vertex]>temp.weight+dis[minNode.vertex])
				{
					dis[temp.vertex]=temp.weight+dis[minNode.vertex];
					pq.offer(new Node(temp.vertex, dis[temp.vertex]));
				}
			}
		}
		
		for(int i=1;i<V;i++) {
			if(dis[i]==Integer.MAX_VALUE) sb.append("INF").append("\n");
			else sb.append(dis[i]).append("\n");
		}
        System.out.println(sb);
	}

}
