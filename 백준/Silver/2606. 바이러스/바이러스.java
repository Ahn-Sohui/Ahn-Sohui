import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] com;
	static int[] chk;
	static int n,k;
	static int cnt;
	static Queue<Integer> q= new LinkedList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		k= Integer.parseInt(br.readLine());
		
		com=new int[n+1][n+1];
		chk=new int[n+1];
		for(int i=0;i<k;i++) {
			StringTokenizer str = new StringTokenizer(br.readLine());
			int r= Integer.parseInt(str.nextToken());
			int c= Integer.parseInt(str.nextToken());
			
			com[r][c]=1;
			com[c][r]=1;
		}
		chk[1]=1;
		q.offer(1);
		bfs(1);
		
		System.out.println(cnt);
	}
	private static void bfs(int com_num) {
		// TODO Auto-generated method stub
		while(!q.isEmpty()) {
			int tmp=q.poll();
			
			for(int i=1;i<=n;i++) {
				if(com[tmp][i]==1 && chk[i]==0) {
					q.offer(i);
					chk[i]=1;
					cnt++;
				}
			}
		}
	}

}
