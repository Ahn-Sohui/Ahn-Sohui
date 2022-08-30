import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static int[] parent;
	static ArrayList<Node> coms;
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		int n= Integer.parseInt(br.readLine());
		int m=Integer.parseInt(br.readLine());
		
		coms=new ArrayList<>();
		for(int i=0;i<m;i++) {
			StringTokenizer str= new StringTokenizer(br.readLine());
			int from=Integer.parseInt(str.nextToken());
			int end=Integer.parseInt(str.nextToken());
			int weight=Integer.parseInt(str.nextToken());
			
			coms.add(new Node(from,end,weight));
		}
		parent=new int[n+1];
		for(int i=1;i<=n;i++) {
			parent[i]=i;
		}
		
		Collections.sort(coms,new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				// TODO Auto-generated method stub
				return o1.weight-o2.weight;
			}
		});
		
		int res=0;
		
		for(int i=0;i<coms.size();i++) {
			Node tmp=coms.get(i);
			
			if(findSet(tmp.from)!=findSet(tmp.end)) {
				res+=tmp.weight;
				union(tmp.from,tmp.end);
			}
		}
		
		System.out.println(res);
	}
	
	
	private static void union(int i, int j) {
		i=findSet(i);
		j=findSet(j);
		
		if(i!=j) {
			parent[j]=i;
		}
		
	}


	private static int findSet(int i) {
		if(i==parent[i]) return i;
		
		return parent[i]=findSet(parent[i]);
	}
	
	
	static class Node{
		int from,end,weight;
		
		Node(int from,int end,int weight){
			this.from=from;
			this.end=end;
			this.weight=weight;
		}
		
	}
}
