import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static class Node{
		int vertex;
		Node next;
		
		Node(int vertex,Node next){
			this.vertex=vertex;
			this.next=next;
		}
	}
	static int n,m;
	static Node[] height;
	static int[] degree;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str=new StringTokenizer(br.readLine());
		n=Integer.parseInt(str.nextToken())+1;
		m=Integer.parseInt(str.nextToken());
		
		height=new Node[n];
		degree=new int[n]; //정점별 진입차수
		
		for(int i=0;i<m;i++) {
			str=new StringTokenizer(br.readLine());
			int from=Integer.parseInt(str.nextToken());
			int to=Integer.parseInt(str.nextToken());
			
			height[from]=new Node(to,height[from]);
			degree[to]++;
		}
		ArrayList<Integer> list=topology();
		//System.out.println(list);
        StringBuilder sb= new StringBuilder();
		for(int i=1;i<n;i++) {
			sb.append(list.get(i)).append(" ");
		}
        
        System.out.print(sb);
	}
	
	private static ArrayList<Integer> topology(){
		ArrayList<Integer> list=new ArrayList<>();
		Queue<Integer> q=new ArrayDeque<>();
		
		//진입차수가 0인 정점 큐에 넣기
		for(int i=0;i<n;i++) {
			if(degree[i]==0) q.offer(i);
		}
		
		//bfs
		while(!q.isEmpty()) {
			int cur=q.poll();
			list.add(cur);
			
			for(Node tmp=height[cur];tmp!=null; tmp=tmp.next) {
				if(--degree[tmp.vertex]==0) q.offer(tmp.vertex);
			}
		}
		return list;
	}

}
