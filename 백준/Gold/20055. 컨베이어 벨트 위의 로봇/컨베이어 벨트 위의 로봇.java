import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n,k;
	static int[][] belt;
	static boolean[] robot;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str= new StringTokenizer(br.readLine());
		n=Integer.parseInt(str.nextToken());
		k=Integer.parseInt(str.nextToken());
		
		belt = new int[2][n]; //컨베이어 벨트
		robot=new boolean[n]; //로봇의 유무
		
		str=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			belt[0][i]=Integer.parseInt(str.nextToken());
		}
		
		for(int i=n-1;i>=0;i--) {
			
			belt[1][i]=Integer.parseInt(str.nextToken());
		}
		int blankCnt=0,level=0; //내구도가 0인 칸의 갯수, 단계
		while(blankCnt<k) {
			//내구도가 0인 칸이 k개 이상이면 종료
			level++;
			//벨트 & 로봇 한 칸 회전
			moving();
			//로봇 한 칸 이동
			robotMove();
			//로봇 올리기
			if(belt[0][0]>0) {
				//로봇 올리기
				belt[0][0]--;
				robot[0]=true;
			}
			blankCnt=chkBlank(belt);
			
		}
		
		System.out.println(level);
	}

	private static void robotMove() {
		//로봇 움직이기
		//먼저 올린 로봇부터 체크
		for(int i=n-2;i>=0;i--) {
			if(robot[i]) {
				if(!robot[i+1] && belt[0][i+1]>=1) {
					robot[i]=false;
					robot[i+1]=true;
					belt[0][i+1]--;
				}
			}
		}
		if(robot[n-1]) {
			//내리는 칸에 로봇이 있을 경우
			robot[n-1]=false; //내림
		}
	}

	private static void moving() {
		// 컨베이어 벨트 & 로봇 한 칸 이동
		// 컨베이어 벨트도 이동
		int num = belt[1][0];
		for (int i = 1; i < n; i++) {
			belt[1][i - 1] = belt[1][i];
		}

		belt[1][n - 1] = belt[0][n - 1];

		for (int i = n - 2; i >= 0; i--) {
			belt[0][i + 1] = belt[0][i];
			if (robot[i]) {
				if (!robot[i + 1]) {
					robot[i] = false;
					robot[i + 1] = true;
				}
			}
		}
		belt[0][0] = num;

		if (robot[n - 1])
			robot[n - 1] = false;
	}

	private static int chkBlank(int[][] belt) {
		//빈칸 수 리턴
		int cnt=0;
		
		for(int i=0;i<belt.length;i++) {
			for(int j=0;j<belt[i].length;j++) {
				if(belt[i][j]==0) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	private static void print(int[][] belt) {
		for(int i=0;i<belt.length;i++) {
			for(int j=0;j<belt[i].length;j++) {
				System.out.print(belt[i][j]+" ");
			}
			System.out.println();
		}
		
	}

}
