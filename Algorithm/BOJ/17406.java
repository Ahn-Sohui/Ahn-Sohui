package study.group;

import java.util.Scanner;

public class boj17406 {
	static int n,m,k;
	static int[][] arr;
	static int[][] res;
	static int[][] rot;
	static int[] v;
	static int[] command;
	static int ans=-1;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		k=sc.nextInt();
		
		arr=new int[n][m];
		rot=new int[k][3];
		v= new int[k];
		command=new int[k];
		
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				arr[i][j]=sc.nextInt();
			}
		}
		
		for(int i=0;i<k;i++) {
			rot[i][0]=sc.nextInt()-1;
			rot[i][1]=sc.nextInt()-1;
			rot[i][2]=sc.nextInt();
		}
		dfs(0);
		
		System.out.println(ans);
	}

	private static void dfs(int L) {
		// TODO Auto-generated method stub
		if(L==k) {
			//돌리기
			doRotate();
			return;
		}
		
		for(int i=0;i<k;i++) {
			if(v[i]==0) {
				v[i]=1;
				command[L]=i;
				dfs(L+1);
				v[i]=0;
			}
		}
	}

	private static void doRotate() {
		// TODO Auto-generated method stub
		res=new int[n][m];
		for (int i = 0; i < n; i++) {
			System.arraycopy(arr[i], 0, res[i], 0, m);
		}
		
		for(int i=0; i<k; i++) {
			int move = command[i];

			// 사각형 하나씩 함수 호출해 회전 
			for (int j = 0; j < rot[move][2]; j++) {
				
				int x1 = rot[move][0] - rot[move][2] + j;
				int y1 = rot[move][1] - rot[move][2] + j;
				
				int x2 = rot[move][0] + rot[move][2] - j;
				int y2 = rot[move][1] + rot[move][2] - j;

				rotate(x1, y1, x2, y2);
			}
		}
		
		calcMatrix();
	}

	private static void calcMatrix() {
		// TODO Auto-generated method stub
		for(int i=0; i<n; i++) {
			int sum = 0;
			for(int j=0; j<m; j++) {
				sum+=res[i][j];
			}
			if(ans == -1 || ans > sum)
				ans = sum;
		}
	}

	private static void rotate(int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
		int tmp1, tmp2;

	    // 윗변 오른쪽 
	    tmp1 = res[x1][y2];
	    for (int y = y2; y > y1; y--) {
	      res[x1][y] = res[x1][y - 1];
	    }

	    // 오른쪽변 아래 
	    tmp2 = tmp1;
	    tmp1 = res[x2][y2];

	    for (int x = x2; x > x1; x--) {
	      if (x - 1 == x1) {
	        res[x][y2] = tmp2;
	        continue;
	      }
	      res[x][y2] = res[x - 1][y2];
	    }

	    // 아랫변 왼쪽 
	    tmp2 = tmp1;
	    tmp1 = res[x2][y1];

	    for (int y = y1; y < y2; y++) {
	      if (y + 1 == y2) {
	        res[x2][y] = tmp2;
	        continue;
	      }

	      res[x2][y] = res[x2][y + 1];
	    }

	    // 왼쪽변 위 
	    tmp2 = tmp1;

	    for (int x = x1; x < x2; x++) {
	      if (x + 1 == x2) {
	        res[x][y1] = tmp2;
	        continue;
	      }

	      res[x][y1] = res[x + 1][y1];
	    }
	}
		
}


