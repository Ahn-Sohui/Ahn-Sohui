import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class swea2819 {
	static int[] dr= {-1,0,1,0};
	static int[] dc= {0,1,0,-1};
	static int[][] map;
	static HashSet<String> str=new HashSet<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		
		int T= Integer.parseInt(br.readLine());
		StringBuilder sb= new StringBuilder();
		for(int tc=1;tc<=T;tc++) {
			sb.append("#").append(tc).append(" ");
			map=new int[4][4];
			StringTokenizer st= new StringTokenizer("");
			for(int i=0;i<4;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<4;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0;i<4;i++) {
				for(int j=0;j<4;j++) {
					dfs(0,i,j,""+map[i][j]);
				}
			}
			sb.append(str.size()).append("\n");
			str.removeAll(str);
		}
		
		System.out.println(sb);
	}

	private static void dfs(int L, int r, int c, String string) {
		if(L==6) {
			str.add(string);
			return;
		}
		
		for(int i=0;i<4;i++) {
			int rr=r+dr[i];
			int cc=c+dc[i];
			if(rr<0||rr>=4||cc<0||cc>=4) continue;
			dfs(L+1,rr,cc,string+map[rr][cc]);
		}
		
	}

}
