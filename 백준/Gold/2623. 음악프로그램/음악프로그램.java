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
		
		public Node(int vertex,Node next) {
			this.vertex=vertex;
			this.next=next;
		}
	}
	
	static int n,m; //가수의 수, pd의 명 수
	static int[] inDegree;
	static Node[] singerList;
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str= new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(str.nextToken())+1;
		m=Integer.parseInt(str.nextToken());
		
		singerList=new Node[n];
		inDegree=new int[n];
		
		for(int i=0;i<m;i++) {
			str=new StringTokenizer(br.readLine());
			int num=Integer.parseInt(str.nextToken()); //담당한 가수의 수
			int[] tmp=new int[num]; //임시 저장
			for(int j=0;j<num;j++) {
				tmp[j]=Integer.parseInt(str.nextToken());
			}
			
			for(int j=0;j<num-1;j++) {
				int from=tmp[j];
				int to=tmp[j+1];
				
				singerList[from]=new Node(to,singerList[from]);
				inDegree[to]++;
			}
		}
		
		ArrayList<Integer> setList=topologySort();
		
		if(setList.size()==n-1) {
			for(int i=0;i<setList.size();i++) {
				System.out.println(setList.get(i));
			}
		}else
			System.out.println(0);
	}
	
	private static ArrayList<Integer> topologySort() {
		ArrayList<Integer> res=new ArrayList<>();
		Queue<Integer> q=new ArrayDeque<>();
		
		for(int i=1;i<n;i++) {
			if(inDegree[i]==0) q.offer(i);
		}
		
		while(!q.isEmpty()) {
			int cur=q.poll();
			res.add(cur);
			
			for(Node tmp=singerList[cur]; tmp!=null; tmp=tmp.next) {
				if(--inDegree[tmp.vertex]==0) q.offer(tmp.vertex);
			}
		}
		return res;
	}

}
