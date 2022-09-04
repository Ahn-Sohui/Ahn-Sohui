import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution {
	static int t,n;
	static int[][] desert;
	static boolean[][] visited;
	static int[] dr= {1,1,-1,-1};
	static int[] dc= {1,-1,-1,1};
	static int res,cnt;
	static HashSet<Integer> des;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		t=Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1;tc<=t;tc++) {
			sb.append("#").append(tc).append(" ");
			n=Integer.parseInt(br.readLine());
			
			desert=new int[n][n];
			
			
			for(int i=0;i<n;i++) {
				StringTokenizer str= new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) {
					desert[i][j]=Integer.parseInt(str.nextToken());
				}
			}
			res=-1;
			
			for(int i=0;i<n-2;i++) {
				for(int j=0;j<n-1;j++) {
					visited=new boolean[n][n];
					des=new HashSet<>();
					des.add(desert[i][j]);
					visited[i][j]=true;
					dfs(i,j,i,j,0);
					visited[i][j]=false;
					des.remove(desert[i][j]);
				}
			}
			//System.out.println(cnt );
			sb.append(res).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void dfs(int sr, int sc, int tr, int tc, int dir) {
		for(int d=dir;d<4;d++) {
			int rr= sr+dr[d];
			int cc=sc+dc[d];
            
			//사각형이고 출발지로 돌아왔을 때
			if(des.size()>=3 && rr==tr && cc==tc) {
				cnt=des.size();
				res=Math.max(res, cnt);
				
				return;
			}
			if(rr<0 || rr>=n||cc<0||cc>=n||visited[rr][cc]) continue;
			
			//이미 포함된 디저트카페
			if(des.contains(desert[rr][cc])) continue;
			
			visited[rr][cc]=true;
			des.add(desert[rr][cc]);
			dfs(rr,cc,tr,tc,d);
			des.remove(desert[rr][cc]);
			visited[rr][cc]=false;
		}
	}
}
