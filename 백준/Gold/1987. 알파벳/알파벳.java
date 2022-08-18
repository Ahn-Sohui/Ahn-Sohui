import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int r,c,res;
	static int[][] map;
	static int[] dr= {0,1,0,-1};
	static int[] dc= {1,0,-1,0};
	//static ArrayList<Character> charac=new ArrayList<>(); // 밟은 알파벳 체크
	static int[] alpha;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str= new StringTokenizer(br.readLine());
		
		r=Integer.parseInt(str.nextToken());
		c=Integer.parseInt(str.nextToken());
		map=new int[r][c];
		alpha=new int[26];
		for(int i=0;i<r;i++) {
			String input=br.readLine();
			for(int j=0;j<c;j++) {
				map[i][j]=input.charAt(j)-'A';
			}
		}
		//print(map);
		//System.out.println(alpha[67]);
		
		dfs(0,0,0);
		
		System.out.println(res);
	}
	
	
	private static void dfs(int pr, int pc,int cnt) {
		// TODO Auto-generated method stub
		if(alpha[map[pr][pc]]==1) {
			res=Math.max(res, cnt);
			return;
		}
		
		alpha[map[pr][pc]]=1;
		for(int i=0;i<4;i++) {
			int rr=pr+dr[i];
			int cc=pc+dc[i];
			if(rr<0||rr>=r||cc<0||cc>=c) continue;
			dfs(rr,cc,cnt+1);
		}
		alpha[map[pr][pc]]=0;
	}


	private static void print(char[][] map2) {
		// TODO Auto-generated method stub
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

}
