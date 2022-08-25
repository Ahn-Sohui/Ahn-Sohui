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
		StringTokenizer str= new StringTokenizer(br.readLine());
		int V=Integer.parseInt(str.nextToken())+1;
		int E=Integer.parseInt(str.nextToken())+1;
		
		Node[] college=new Node[V];
		
		for(int i=0;i<E;i++) {
			str= new StringTokenizer(br.readLine());
			int from=Integer.parseInt(str.nextToken());
			int to =Integer.parseInt(str.nextToken());
			int weight=Integer.parseInt(str.nextToken());
			
			//무향 그래프이기 때문에 양방향으로 넣어줘야함
			college[from]=new Node(to,weight,college[from]);
			college[to]=new Node(from,weight,college[to]);
		}
		
		int[] minEdge=new int[V];
		boolean[] v= new boolean[V];
		
		Arrays.fill(minEdge,Integer.MAX_VALUE);
		//0번 부터 시작
		minEdge[0]=-1;
		
		PriorityQueue<Node> pq=new PriorityQueue<>((v1,v2)->v1.weight-v2.weight);
		
		pq.offer(new Node(0,minEdge[0]));
		int cnt=0,ans1=0;
		while(true) {
			Node minNode=pq.poll();
			//이미 추가된 정점이라면
			if(v[minNode.vertex]) continue;
			
			//추가
			v[minNode.vertex]=true;
			if(++cnt==V) break;
			
			for(Node temp=college[minNode.vertex]; temp!=null;temp=temp.next) {
				if(!v[temp.vertex] && minEdge[temp.vertex]>temp.weight)
				{
					minEdge[temp.vertex]=temp.weight;
					pq.offer(new Node(temp.vertex, temp.weight));
				}
			}
		}
		
		for(int i=0;i<V;i++) {
			if(minEdge[i]==0) ans1++;
		}
		
		//초기화
		
		cnt=0;
		
		Arrays.fill(minEdge,Integer.MIN_VALUE);
		Arrays.fill(v, false);
		
		
		minEdge[0]=-1;
		PriorityQueue<Node> pq2=new PriorityQueue<>((v1,v2)->v2.weight-v1.weight);
		pq2.offer(new Node(0,minEdge[0]));
		
		while(true) {
			Node minNode=pq2.poll();
			//이미 추가된 정점이라면
			if(v[minNode.vertex]) continue;
			
			//추가
			v[minNode.vertex]=true;
			if(++cnt==V) break;
			
			for(Node temp=college[minNode.vertex]; temp!=null;temp=temp.next) {
				if(!v[temp.vertex] && minEdge[temp.vertex]<temp.weight)
				{
					minEdge[temp.vertex]=temp.weight;
					pq2.offer(new Node(temp.vertex, temp.weight));
				}
			}
		}
		//System.out.println(Arrays.toString(minEdge));
		int ans2=0;
		for(int i=0;i<V;i++) {
			if(minEdge[i]==0) ans2++;
		}
		//System.out.println(Arrays.toString(minEdge));
		//System.out.println(ans1+" "+ans2);
		System.out.println(ans1*ans1-ans2*ans2);
	}
}
