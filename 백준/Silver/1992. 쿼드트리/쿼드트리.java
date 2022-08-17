import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int n;
	static int[][] map;
	static StringBuilder sb= new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		map=new int[n][n];
		
		for(int i=0;i<n;i++) {
			String str=br.readLine();
			for (int j = 0; j < n; j++) {
				map[i][j]=str.charAt(j)-'0';
			}
		}
		//print(map);
		Image(0,0,n);
		System.out.println(sb);
	}
	
	private static void Image(int r, int c, int size) {
		// TODO Auto-generated method stub
		if(chk(r,c,size)) {
			if(map[r][c]==0) {
				sb.append(0);
			}
			else {
				sb.append(1);
			}
			return;
		}
		
		int resize=size/2;
		sb.append("(");
		Image(r,c,resize); //1구역
		
		Image(r,c+resize,resize); //2구역
		Image(r+resize,c,resize); // 3구역
		Image(r+resize,c+resize,resize);//4구역	
		sb.append(")");
	}
	
	

	private static boolean chk(int r, int c, int size) {
		// TODO Auto-generated method stub
		int color=map[r][c]; // 0 || 1
		for(int i=r;i<r+size;i++) {
			for(int j=c;j<c+size;j++) {
				if(map[i][j]!=color) {
					return false; // 분할 해야함
				}
			}
		}
		
		return true;
	}

	private static void print(int[][] map2) {
		// TODO Auto-generated method stub
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(map2[i][j]+" ");
			}
			System.out.println();
		}
	}

}
