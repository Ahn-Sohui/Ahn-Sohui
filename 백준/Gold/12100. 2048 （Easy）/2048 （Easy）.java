import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[][] board2048;
	static int res=Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		board2048=new int[n][n];
		
		for(int i=0;i<n;i++) {
			StringTokenizer str=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				board2048[i][j]=Integer.parseInt(str.nextToken());
			}
		}
		
		play2048(0);
		
		System.out.println(res);
	}

	private static void play2048(int depth) {
		if(depth==5) {
			int tmpMax=calcMax(board2048);
			res=Math.max(res, tmpMax);
//			print(board2048);
//			System.out.println("===========");
			return;
		}
		
		int[][] tmpBoard=new int[n][n];
		
        //배열 복사해놓기
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				tmpBoard[i][j]=board2048[i][j];
			}
		}
		
		for(int d=0;d<4;d++) {
            //방향값만 넣어주고 탐색
			move(d);
			play2048(depth+1);
			
            //재탐색을 위해서 다시 복사
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					board2048[i][j]=tmpBoard[i][j];
				}
			}
		}
	}
	
	private static void print(int[][] board) {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
		
	}

	private static void move(int dir) {
		// 미는 방향과 탐색 방향은 반대
		switch (dir) {
		case 0:
			//위
			for(int c=0;c<n;c++) {
				int idx=0;
				int tmp=0;
				for(int r=0;r<n;r++) {
					if(board2048[r][c]!=0) {
						if(tmp==board2048[r][c]) {
							board2048[idx-1][c]=tmp*2;
							tmp=0;
							board2048[r][c]=0;
						}else {
							tmp=board2048[r][c];
							board2048[r][c]=0;
							board2048[idx][c]=tmp;
							idx++;
						}
					}
				}
			}
			break;
		case 1:
			//아래
			for(int c=0;c<n;c++) {
				int idx=n-1;
				int tmp=0;
				for(int r=n-1;r>=0;r--) {
					if(board2048[r][c]!=0) {
						if(tmp==board2048[r][c]) {
							board2048[idx+1][c]=tmp*2;
							tmp=0;
							board2048[r][c]=0;
						}else {
							tmp=board2048[r][c];
							board2048[r][c]=0;
							board2048[idx][c]=tmp;
							idx--;
						}
					}
				}
			}
			break;
		case 2:
			//왼
			for(int r=0;r<n;r++) {
				int idx=0;
				int tmp=0;
				for(int c=0;c<n;c++) {
					if(board2048[r][c]!=0) {
						if(tmp==board2048[r][c]) {
							board2048[r][idx-1]=tmp*2;
							tmp=0;
							board2048[r][c]=0;
						}else {
							tmp=board2048[r][c];
							board2048[r][c]=0;
							board2048[r][idx]=tmp;
							idx++;
						}
					}
				}
			}
			break;
		case 3:
			//우
			for(int r=0;r<n;r++) {
				int idx=n-1;
				int tmp=0;
				for(int c=n-1;c>=0;c--) {
					if(board2048[r][c]!=0) {
						if(tmp==board2048[r][c]) {
							board2048[r][idx+1]=tmp*2;
							tmp=0;
							board2048[r][c]=0;
						}else {
							tmp=board2048[r][c];
							board2048[r][c]=0;
							board2048[r][idx]=tmp;
							idx--;
						}
					}
				}
			}
			break;
		}		
	}

	private static int calcMax(int[][] board) {
		// 가장 큰 블록 찾기
		int tmp = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				tmp = Math.max(tmp, board[i][j]);
			}
		}

		return tmp;
	}
}
