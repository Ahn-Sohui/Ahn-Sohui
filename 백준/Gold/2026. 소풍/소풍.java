import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int k,n,f;
	static int[][] friends;
	static boolean[] sel;
	static boolean flag=false;
	static StringBuilder sb=new StringBuilder();
	public static void main(String[] args) throws Exception{

		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str=new StringTokenizer(br.readLine());
		k=Integer.parseInt(str.nextToken());
		n=Integer.parseInt(str.nextToken());
		f=Integer.parseInt(str.nextToken());
		
		friends=new int[n+1][n+1];
		sel=new boolean[n+1];
		int[] cntFriends=new int[n+1];
		
		
		for(int i=0;i<f;i++) {
			str=new StringTokenizer(br.readLine());
			int from=Integer.parseInt(str.nextToken());
			int to=Integer.parseInt(str.nextToken());
			
			friends[from][to]=friends[to][from]=1;
			cntFriends[from]++;
			cntFriends[to]++;
		}
		
		for(int i=1;i<=n;i++) {
			//나 포함 친구수가 k명보다 작으면 고려할 필요 x
			if(cntFriends[i]<k-1) continue;
			//조합 완성
			if(flag) break;
			
			sel[i]=true;
			dfs(i,1);
			sel[i]=false;
		}
		
		//소풍 못 가면 -1
		if(!flag)
			System.out.println(-1);
		else
			System.out.println(sb);
	}
	private static void dfs(int start, int Level) {
		if(flag) return;
		
		if(Level==k) {
			for(int i=1;i<=n;i++) {
				if(sel[i])
					sb.append(i).append("\n");
			}
			flag=true;
			return;
		}
		
		for(int i=start+1;i<n+1;i++) {
			if(friends[start][i]==1 && isFriend(i)) {
				sel[i]=true;
				dfs(i,Level+1);
				sel[i]=false;				
			}
		}
	}
	
	//친구임을 확인
	private static boolean isFriend(int idx) {
		for(int i=1;i<=n;i++) {
			if(sel[i] && friends[idx][i]!=1)
				return false;
		}
		return true;
	}

}
