import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int n,m,min=Integer.MAX_VALUE;
	static int[][] map;
	static int[] chk;
	static LinkedList<Pair> home=new LinkedList<>();
	static LinkedList<Pair> chicken = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str=new StringTokenizer(br.readLine());
		n=Integer.parseInt(str.nextToken());
		m=Integer.parseInt(str.nextToken());
		chk=new int[m];
		map=new int[n+1][n+1];
		
		for(int i=1;i<=n;i++) {
			str=new StringTokenizer(br.readLine());
			for(int j=1;j<=n;j++) {
				map[i][j]=Integer.parseInt(str.nextToken());
				if(map[i][j]==1) {
					//집인 경우
					home.offer(new Pair(i,j));
				}
				else if(map[i][j]==2) {
					//치킨집인 경우
					chicken.offer(new Pair(i,j));
				}
			}
		}
		
		dfs(0,0);
		
		System.out.println(min);
	}
	
	private static void dfs(int L, int idx) {
		// TODO Auto-generated method stub
		if(L==m) {
			//치킨집을 다 골랐을 때
			int sum=0;
			for(int i=0;i<home.size();i++) {
				int rr=home.get(i).r;
				int cc=home.get(i).c;
				int dis=Integer.MAX_VALUE;
				
				for (int j = 0; j < m; j++) {
					int c_r=chicken.get(chk[j]).r;
					int c_c=chicken.get(chk[j]).c;
					
					dis=Math.min(dis, Math.abs(c_r-rr)+Math.abs(c_c-cc));
					
				}
				sum+=dis;
			}
			
			//System.out.println(sum);
			min=Math.min(min, sum);
			
			return;
		}
		
		//치킨집 고르기
		for(int i=idx;i<chicken.size();i++) {
			chk[L]=i;
			dfs(L+1,i+1);
		}
		
	}

	static public class Pair{
		int r,c;
		Pair(int r, int c){
			this.r=r;
			this.c=c;
		}
	}
}
