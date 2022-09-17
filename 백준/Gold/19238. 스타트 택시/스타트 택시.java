import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int[][] dist;
	static int n,m,fuel;
	static Pair taxi; //택시의 위치정보
	static Pair[] destination;
	static int clientCnt; //태운 승객 수
	static int[] dr= {-1,0,1,0};
	static int[] dc= {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str=new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(str.nextToken());
		m=Integer.parseInt(str.nextToken());
		fuel=Integer.parseInt(str.nextToken()); //초기연료
		
		map=new int[n+1][n+1];
		destination=new Pair[m+1];
		dist=new int[n+1][n+1];
		
		for(int i=1;i<=n;i++) {
			str=new StringTokenizer(br.readLine());
			for(int j=1;j<=n;j++) {
				map[i][j]=Integer.parseInt(str.nextToken());
				if(map[i][j]==1) {
					//벽일 경우
					map[i][j]=-1;
				}
			}
		}
		
		str=new StringTokenizer(br.readLine());
		taxi=new Pair(Integer.parseInt(str.nextToken()),Integer.parseInt(str.nextToken()));
		
		for(int i=1;i<=m;i++) {
			str=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(str.nextToken());
			int b=Integer.parseInt(str.nextToken()); //승객의 위치
			int c=Integer.parseInt(str.nextToken());
			int d=Integer.parseInt(str.nextToken()); //목적지의 위치
			
			map[a][b]=i; //승객의 위치
			destination[i]=new Pair(c,d); //도착지의 위치정보 저장
		}
		
		for(int i=0;i<m;i++) {
			//승객 수 만큼 반복
			//태울 승객 찾기
			int clientNum=findClient();
			//System.out.println(dist[taxi.r][taxi.c]);
			fuel-=dist[taxi.r][taxi.c];
			if(clientNum==-1 || fuel<=0) {
				//승객이없거나 연료소진 시
				System.out.println(-1);
				return;
			}
			
			int useFuel=Drive(clientNum);
			//System.out.println(useFuel);
			if(fuel<useFuel || useFuel==-1) {
				System.out.println(-1);
				return;
			}
			fuel+=useFuel;
			map[taxi.r][taxi.c]=0; //승객 탑승처리
			taxi=destination[clientNum];
			
			//System.out.println("taxi : "+taxiR+" "+taxiC);
			//System.out.println(clientCnt+" "+fuel);
		}
		
		System.out.println(fuel);
		//print(map);
	}
	
	private static int findClient() {
		//승객 찾기
		//택시의 현 위치에서  각 정점까지의 최단 거리 구하기
		Queue<Pair> q=new LinkedList<>();
		PriorityQueue<Pair> client=new PriorityQueue<>(); //승객정보담기
		
		Init(dist);
		
		dist[taxi.r][taxi.c]=0;
		q.offer(taxi);
		
		if(map[taxi.r][taxi.c]>0) { 
			//현재 택시 위치가 승객 위치
			return map[taxi.r][taxi.c];
		}
		
		while(!q.isEmpty()) {
			
			int size=q.size();
			for(int step=0;step<size;step++) {
				
				Pair tmp=q.poll();
				for(int i=0;i<4;i++) {
					int rr=tmp.r+dr[i];
					int cc=tmp.c+dc[i];
					
					if(rr<=0||rr>n||cc<=0||cc>n||map[rr][cc]==-1||dist[rr][cc]!=-1) continue;
					if(map[rr][cc]>0) {
						//손님일 경우
						client.offer(new Pair(rr,cc));
					}
					q.offer(new Pair(rr,cc));
					dist[rr][cc]=dist[tmp.r][tmp.c]+1;
				}
			}
			
			if(!client.isEmpty()) {
				taxi=client.poll();			
				//고객의 숫자 정보 넘겨주기
				return map[taxi.r][taxi.c];
			}
		}		
		
		return -1;
	}

	private static int Drive(int client) {
		// 택시 운행 메서드
		Queue<Pair> q=new LinkedList<>();
		
		Init(dist);
		
		dist[taxi.r][taxi.c]=0;
				
		q.offer(taxi);
		while(!q.isEmpty()) {
			int size=q.size();
			
			for(int cnt=0;cnt<size;cnt++) {
				Pair tmp=q.poll();
				
				if(tmp.r==destination[client].r && tmp.c==destination[client].c) {
					return dist[tmp.r][tmp.c];
				}
				
				for(int i=0;i<4;i++) {
					int rr=tmp.r+dr[i];
					int cc=tmp.c+dc[i];
					
					if(rr<=0||rr>n||cc<=0||cc>n||dist[rr][cc]!=-1||map[rr][cc]<0) continue;
					
					dist[rr][cc]=dist[tmp.r][tmp.c]+1;
					q.offer(new Pair(rr,cc));
				}
			}
		}

		return -1;
	}

	private static void Init(int[][] arr) {
		//배열 초기화 함수
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				arr[i][j]=-1;
			}
		}
	}

	private static void print(int[][] map2) {
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				System.out.print(map2[i][j]+" ");
			}
			System.out.println();
		}
	}

	static class Pair implements Comparable<Pair>{
		int r,c;
		Pair(int r,int c){
			this.r=r;
			this.c=c;
		}
		@Override
		public int compareTo(Pair o) {
			if(this.r==o.r) {
				return this.c-o.c;
			}
			return this.r-o.r;
		}
	}
}
