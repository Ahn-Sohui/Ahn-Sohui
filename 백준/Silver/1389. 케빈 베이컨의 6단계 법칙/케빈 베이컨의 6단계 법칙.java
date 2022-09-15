import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int v,e;
	static int[][] Bacon;
	static Queue<Pair> q;
	static boolean[] visited;
	static int min_bacon=Integer.MAX_VALUE,min_num;
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str=new StringTokenizer(br.readLine());
		v=Integer.parseInt(str.nextToken());
		e=Integer.parseInt(str.nextToken());
		
		Bacon=new int[v+1][v+1];
		
		for(int i=0;i<e;i++) {
			str=new StringTokenizer(br.readLine());
			int from =Integer.parseInt(str.nextToken());
			int to =Integer.parseInt(str.nextToken());
			
			Bacon[from][to]=1;
			Bacon[to][from]=1;
		}
		
		for(int i=1;i<=v;i++) {
			visited=new boolean[v+1];
			bfs(i);
		}
		
		System.out.println(min_num);
	}
	
	private static void bfs(int start) {
		int cnt=0;
		q=new LinkedList<>();
		
		visited[start]=true;
		
		q.offer(new Pair(start,0));
		
		while(!q.isEmpty()) {
			Pair tmp=q.poll();
			cnt+=tmp.val;
			
			for(int i=1;i<=v;i++) {
				if(Bacon[tmp.num][i]==1 && !visited[i]) {
					visited[i]=true;
					q.offer(new Pair(i,tmp.val+1));
				}
			}
		}
		
		if(min_bacon>cnt) {
			min_bacon=cnt;
			min_num=start;
		}
		
	}

	static class Pair{
		int num,val;
		Pair(int num,int val){
			this.num=num;
			this.val=val;
		}
	}
}
