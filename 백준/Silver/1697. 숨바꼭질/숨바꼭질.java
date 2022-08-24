import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	
	static int[] count=new int[100001];
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str= new StringTokenizer(br.readLine());
		
		int n=Integer.parseInt(str.nextToken());
		int k=Integer.parseInt(str.nextToken());
		
		//Arrays.fill(count, -1);
		bfs(n,k);
		//System.out.println(Arrays.toString(count));
		System.out.println(count[k]-1);
	}

	private static void bfs(int n,int k) {
		// TODO Auto-generated method stub
		Queue<Integer> q= new LinkedList<>();
		q.offer(n);
		count[n]=1;
		while(!q.isEmpty()) {
			int tmp=q.poll();
			if(tmp==k) {
				return;
			}
			if(tmp-1>=0 && count[tmp-1]==0) {
				count[tmp-1]=count[tmp]+1;
				q.offer(tmp-1);
			}
			
			if(tmp+1<=100000 && count[tmp+1]==0) {
				count[tmp+1]=count[tmp]+1;
				q.offer(tmp+1);
				
			}
			
			
			if(tmp*2<=100000 && count[tmp*2]==0) {
				count[tmp*2]=count[tmp]+1;
				q.offer(tmp*2);
			}
		}
	}

}
