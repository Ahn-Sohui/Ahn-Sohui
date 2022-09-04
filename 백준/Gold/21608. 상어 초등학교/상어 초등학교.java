import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[][] classRoom; //자리 위치
	static int[][] llist; //좋아하는 친구 리스트
	static int[] dr= {-1,0,1,0};
	static int[] dc= {0,1,0,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
			
		classRoom=new int[n][n];
		llist=new int[n*n+1][4];
		
		for(int i=0;i<n*n;i++) {
			StringTokenizer str= new StringTokenizer(br.readLine());
			int stu_num=Integer.parseInt(str.nextToken());
			for(int j=0;j<4;j++) {
				llist[stu_num][j]=Integer.parseInt(str.nextToken());
			}
			Node tmp=calc(stu_num);
			classRoom[tmp.r][tmp.c]=stu_num;
		}
		Solve();
	}
	
	private static void Solve() {
		int ans=0;
		int tmp=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				int stu_num=classRoom[i][j];
				int[] likeFriend=llist[stu_num];
				for(int k=0;k<4;k++) {
					int rr=i+dr[k];
					int cc=j+dc[k];
					
					if(rr<0||rr>=n||cc<0||cc>=n) continue;
					if(classRoom[rr][cc]==likeFriend[0]||classRoom[rr][cc]==likeFriend[1]||classRoom[rr][cc]==likeFriend[2]||classRoom[rr][cc]==likeFriend[3]) tmp++;
				}
				if(tmp==1) ans+=1;
				else if(tmp==2) ans+=10;
				else if(tmp==3) ans+=100;
				else if(tmp==4) ans+=1000;
				tmp=0;
			}
		}
		System.out.println(ans);
	}

	private static Node calc(int stu_num) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(classRoom[i][j]==0) {
					int[] ans=like_friend(i,j,stu_num);
					int likes=ans[0];
					int empty=ans[1];
					pq.add(new Node(i,j,likes,empty));
				}
			}
		}
		return pq.poll();
	}

	private static int[] like_friend(int r, int c, int stu_num) {
		int like=0;
		int empty=0;
		int[] like_friends=llist[stu_num];
		
		for(int i=0;i<4;i++) {
			int rr=r+dr[i];
			int cc=c+dc[i];
			if(rr<0||rr>=n||cc<0||cc>=n) continue;
			
			if(classRoom[rr][cc]==like_friends[0]||classRoom[rr][cc]==like_friends[1]||classRoom[rr][cc]==like_friends[2]||classRoom[rr][cc]==like_friends[3]) like++;
			else if(classRoom[rr][cc]==0) empty++;
		}
		return new int[] {like,empty};
	}

	static class Node implements Comparable<Node>{
		int r,c,likeCnt,emptyCnt;
		
		Node(int r,int c,int likeCnt,int emptyCnt){
			this.r=r;
			this.c=c;
			this.likeCnt=likeCnt;
			this.emptyCnt=emptyCnt;
		}

		@Override
		public int compareTo(Node o) {
			if(this.likeCnt==o.likeCnt) {
				//비어있는 칸이 많은 쪽으로 자리를 정함
				if(this.emptyCnt==o.emptyCnt) {
					//같으면 왼쪽 위 배치
					if(this.r==o.r) return this.c-o.c;
					else return this.r-o.r;
				}
				else return o.emptyCnt-this.emptyCnt;
			}
			else return o.likeCnt-this.likeCnt;
		}
	}

}
