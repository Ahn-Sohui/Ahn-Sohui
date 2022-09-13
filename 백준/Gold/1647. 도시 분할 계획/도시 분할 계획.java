import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		StringTokenizer str= new StringTokenizer(br.readLine());
		int n=Integer.parseInt(str.nextToken());
		int m=Integer.parseInt(str.nextToken());
		
		Node[] city=new Node[n+1];
		
		for(int i=0;i<m;i++) {
			str=new StringTokenizer(br.readLine());
			int from=Integer.parseInt(str.nextToken());
			int to=Integer.parseInt(str.nextToken());
			int weight=Integer.parseInt(str.nextToken());
			
			city[from]=new Node(to,weight,city[from]);
			city[to]=new Node(from,weight,city[to]);
		}
		
		boolean[] v=new boolean[n+1];
		
		PriorityQueue<Node> pq=new PriorityQueue<>((v1,v2)->v1.weight-v2.weight);
		
		pq.offer(new Node(1,0));
		
		int cnt=0,max_weight=0,res=0;
		
		while(true) {
			Node minNode=pq.poll();
			//System.out.println("minNode.verxtex: "+minNode.vertex);
			if(v[minNode.vertex]) continue;
			
			v[minNode.vertex]=true;
			res+=minNode.weight;
			max_weight=Math.max(max_weight, minNode.weight);
			if(++cnt==n) break;
			
			for(Node tmp=city[minNode.vertex];tmp!=null;tmp=tmp.next) {
				if(!v[tmp.vertex]) {
					pq.offer(new Node(tmp.vertex,tmp.weight));
				}
			}
		}
		
		System.out.println(res-max_weight);
	}
}
