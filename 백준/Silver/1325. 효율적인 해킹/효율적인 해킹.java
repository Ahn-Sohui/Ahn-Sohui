import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] cnt; // 해킹할 수 있는 컴의 갯수
	static boolean[] visited;
	static int n, m;
	static Queue<Integer> q = new LinkedList<>();
	static ArrayList<Integer>[] coms; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(br.readLine());
		n = Integer.parseInt(str.nextToken()) + 1;
		m = Integer.parseInt(str.nextToken());
		cnt=new int[n];
		
		coms=new ArrayList[n];
		for(int i=1;i<n;i++) coms[i]=new ArrayList<>();
		
		for (int i = 0; i < m; i++) {
			str = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(str.nextToken());
			int b = Integer.parseInt(str.nextToken());
			
			coms[a].add(b);
		}

		for(int i=1;i<n;i++) {
			visited=new boolean[n];
			bfs(i);
		}
		
		//System.out.println(Arrays.toString(cnt));
		int max= Integer.MIN_VALUE;
		StringBuilder sb= new StringBuilder();
		for(int i=1;i<n;i++) {
			max=Math.max(max, cnt[i]);
		}
		
		for(int i=1;i<n;i++) {
			if(cnt[i]==max) {
				sb.append(i).append(" ");
			}
		}
		System.out.println(sb);
	}

	private static void bfs(int idx) {
		q.offer(idx);
		visited[idx]=true;
		
		while(!q.isEmpty()) {
			int tmp=q.poll();
			for(int next: coms[tmp]) {
				if(!visited[next]) {
					cnt[next]++;
					visited[next]=true;
					q.offer(next);
				}
			}
		}
	}
}
