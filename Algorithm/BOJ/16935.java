package study.group;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ16935 {
	static int n,m,r;
	
	public static void main(String[] args) throws IOException,NumberFormatException {
		// TODO Auto-generated method stub
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//String[] str=br.readLine().split(" ");
		Scanner sc= new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		r=sc.nextInt();
		int [][] arr=new int[n][m];
		
		for(int i=0;i<n;i++) {
			//String[] strs=br.readLine().split(" ");
			for(int j=0;j<m;j++) {
				arr[i][j]=sc.nextInt();
			}
		}
		
		for(int i=0;i<r;i++) {
			//연산 번호
			int num=sc.nextInt();;
			if(num==1) {
				int[][] map=new int[n][m];
				//상하 반전
				for (int x = 0; x < n; x++) {
					for (int y = 0; y < m; y++) {
						map[n-x-1][y]=arr[x][y];
					}
				}
				arr=map;
			}
			else if(num==2) {
				int[][] map=new int[n][m];
				//좌우 반전
				for (int x = 0; x < n; x++) {
					for (int y = 0; y < m; y++) {
						map[x][m-y-1]=arr[x][y];
					}
				}
				arr=map;
			}
			else if(num==3) {
				int[][] map=new int[m][n];
				//오른쪽  90 도 회전
				int col=n-1;
				for (int x = 0; x < n; x++) {
					for (int y = 0; y < m; y++) {
						map[y][col]=arr[x][y];
					}
					col--;
				}
				
				int tmp=n;
				n=m;
				m=tmp;
				
				arr=map;
			}
			else if(num==4) {
				int[][] map=new int[m][n];
				//왼쪽 90도
				for (int x = 0; x < n; x++) {
					for (int y = 0; y < m; y++) {
						map[m-y-1][x]=arr[x][y];
					}
				}
				
				int tmp=n;
				n=m;
				m=tmp;	
				arr=map;
				
			}
			else if(num==5) {
				int[][] map=new int[n][m];
				//그룹이동 1->2 2->3 3->4 4->1
				int n_m=n/2;
				int m_m=m/2;
				//1->2
				for (int x = 0; x < n_m; x++) {
					for (int y = 0; y < m_m; y++) {
						map[x][m_m+y]=arr[x][y];
					}
				}
				
				//2->3
				for (int x = 0; x < n_m; x++) {
					for (int y = m_m; y < m; y++) {
						map[n_m+x][y]=arr[x][y];
					}
				}
				
				//3->4
				for (int x = n_m; x < n; x++) {
					int col=0;
					for (int y = m_m; y < m; y++,col++) {
						map[x][col]=arr[x][y];
					}
				}
				
				//4->1
				int row=0;
				for (int x = n_m; x < n; x++,row++) {
					for (int y = 0; y < m_m; y++) {
						map[row][y]=arr[x][y];
					}
				}
				arr=map;
				
			}else if(num==6) {
				int[][] map=new int[n][m];
				//그룹이동 1->4 2->1 3->2 4->3
				int n_m=n/2;
				int m_m=m/2;
				
				//1->4
				for (int x = 0; x < n_m; x++) {
					for (int y = 0; y < m_m; y++) {
						map[n_m+x][y]=arr[x][y];
					}
				}
				
				//2->1
				for (int x =0; x < n_m; x++) {
					int col=0;
					for (int y = m_m; y < m; y++,col++) {
						map[x][col]=arr[x][y];
					}
				}
				
				//3->2
				int row=0;
				for (int x = n_m; x < n; x++,row++) {
					for (int y = m_m; y < m; y++) {
						map[row][y]=arr[x][y];
					}
				}
				
			
				//4->3
				for (int x = n_m; x < n; x++) {
					for (int y = 0; y < m_m; y++) {
						map[x][m_m+y]=arr[x][y];
					}
				}
				arr=map;
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}

}
