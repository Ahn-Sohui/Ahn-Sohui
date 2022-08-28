import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node{
		int r,c,weight;
		Node(int r,int c,int weight){
			this.r=r;
			this.c=c;
			this.weight=weight;
		}
		
	}
	
	static int[][] map;
	static int[][] dis;
	static int[] dr= {1,0,-1,0};
	static int[] dc= {0,1,0,-1};
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb= new StringBuilder();
		
		int cnt=0;
		while(true) {
			n=Integer.parseInt(br.readLine());
			if(n==0) break;
			
			map=new int[n][n];
			dis=new int[n][n];
			
			for(int i=0;i<n;i++) {
				StringTokenizer str= new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) {
					map[i][j]=Integer.parseInt(str.nextToken());
					dis[i][j]=Integer.MAX_VALUE;
				}
			}
			PriorityQueue<Node> pq=new PriorityQueue<>((v1,v2)->v1.weight-v2.weight);
			dis[0][0]=map[0][0];
			pq.offer(new Node(0,0,map[0][0]));
			
			while(!pq.isEmpty()) {
				Node minNode=pq.poll();
				
				for(int i=0;i<4;i++) {
					int rr= minNode.r+dr[i];
					int cc= minNode.c+dc[i];
					
					if(rr>=0 && rr<n && cc>=0 && cc<n) {
						if(dis[rr][cc]>dis[minNode.r][minNode.c]+map[rr][cc]) {
							dis[rr][cc]=dis[minNode.r][minNode.c]+map[rr][cc];
							pq.offer(new Node(rr,cc,dis[rr][cc]));
						}
					}
				}
			}
			cnt++;
			sb.append("Problem").append(" ").append(cnt).append(": ").append(dis[n-1][n-1]).append("\n");
		}
		
		System.out.println(sb);
	}
	

}
