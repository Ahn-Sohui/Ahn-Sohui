import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int[][] sdoku;
	static StringBuilder sb = new StringBuilder();
	static ArrayList<Pair> zero=new ArrayList<>();
	static boolean flag;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sdoku = new int[9][9];

		for (int i = 0; i < 9; i++) {
			String str=br.readLine();
			for(int j=0;j<9;j++) {
				sdoku[i][j]=str.charAt(j)-'0';
				if(sdoku[i][j]==0)
					zero.add(new Pair(i,j));
			}
		}
		//System.out.println(zero.size());
		playSdoku(0);
		
	}

	private static void playSdoku(int idx) {
		// TODO Auto-generated method stub
		if(idx==zero.size()) {
			printSdoku();
			System.exit(0);
		}
		//System.out.println(idx);
		Pair tmp=zero.get(idx);
		
		for(int i=1;i<=9;i++) {
			if(chk(tmp.r,tmp.c,i)) {
				sdoku[tmp.r][tmp.c]=i;
				playSdoku(idx+1);
				sdoku[tmp.r][tmp.c]=0;
			}
		}
		
	}

	private static void printSdoku() {
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				sb.append(sdoku[i][j]);
			}
			sb.append("\n");
		}
		//System.out.println(1);
		System.out.println(sb);
	}

	private static boolean chk(int r,int c,int value) {
		// 유효성 검사
		// 3*3
		int rr=(r/3)*3;
		int cc=(c/3)*3;
		
		for(int i=rr;i<rr+3;i++) {
			for(int j=cc;j<cc+3;j++) {
				if(sdoku[i][j]==value)
					return false;
			}
		}
	
		
		// 가로
		for(int i=0;i<9;i++) {
			if(sdoku[r][i]==value)
				return false;
		}

		// 세로
		for(int i=0;i<9;i++) {
			if(sdoku[i][c]==value)
				return false;
		}
		
		return true;
	}

	static class Pair {
		int r, c;

		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

}
