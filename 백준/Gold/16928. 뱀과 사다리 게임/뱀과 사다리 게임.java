import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] cnt;
	static int[] ladderNsnake;
	static boolean[] v;
	static Queue<Integer> q=new LinkedList<>();
	static StringBuilder sb= new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str =new StringTokenizer(br.readLine());
		cnt=new int[101];
		ladderNsnake=new int[101];
		v=new boolean[101];
		
		int n=Integer.parseInt(str.nextToken()); //사다리
		int m=Integer.parseInt(str.nextToken()); //뱀
		
		for(int i=0;i<n+m;i++) {
			str=new StringTokenizer(br.readLine());
			int from=Integer.parseInt(str.nextToken());
			int to=Integer.parseInt(str.nextToken());
			
			ladderNsnake[from]=to;
		}
		
		q.offer(1);
		v[1]=true;
		
		bfs();
		
		System.out.println(sb);
	}

	private static void bfs() {

		while(!q.isEmpty()) {
			int cur=q.poll();
			if(cur==100) {
				System.out.println(cnt[cur]);
				return;
			}
			
			for(int dice=1;dice<7;dice++) {
				int temp=cur+dice;
				if(100<temp) continue;
				if(v[temp]) continue;
				v[temp]=true;
				
				if(ladderNsnake[temp]!=0) {
					//뱀 또는 사다리
					if(!v[ladderNsnake[temp]]) {
						q.offer(ladderNsnake[temp]);
						v[ladderNsnake[temp]]=true;
						cnt[ladderNsnake[temp]]=cnt[cur]+1;
					}
				}
				else {
					q.offer(temp);
					cnt[temp]=cnt[cur]+1;
				}
			}
		}
	}

}
