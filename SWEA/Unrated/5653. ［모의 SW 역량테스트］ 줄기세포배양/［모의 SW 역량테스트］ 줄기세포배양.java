import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int[][] map;
	static int[] dr= {-1,0,1,0};
	static int[] dc= {0,1,0,-1};
	static ArrayList<Cell> cellList; 
	static int n,m,k;
	
	static StringBuilder sb=new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t= Integer.parseInt(br.readLine());

		for(int tc=1;tc<=t;tc++) {
			sb.append("#").append(tc).append(" ");			
			StringTokenizer str= new StringTokenizer(br.readLine());
			n=Integer.parseInt(str.nextToken());
			m=Integer.parseInt(str.nextToken());
			k=Integer.parseInt(str.nextToken());
			
			map=new int[n+(2*k)+1][m+(2*k)+1];
			cellList=new ArrayList<>();
			for(int i=0;i<n;i++) {
				str=new StringTokenizer(br.readLine());
				for(int j=0;j<m;j++) {
					map[i+k][j+k]=Integer.parseInt(str.nextToken());
					cellList.add(new Cell(i+k,j+k,map[i+k][j+k],0,0));
				}
			}
			//배양
			Cultivation();
		}
		System.out.println(sb);
	}
	private static void Cultivation() {
		int time=0;
		while(true) {
			time++;
			//배양
			bfsCult();
			//죽이기
			bfsDead();
			
			if(time==k) {
				sb.append(cellList.size()).append("\n");
				return;
			}
		}	
	}
	
	private static void bfsDead() {
		//세포 죽이기
		int size=cellList.size();
		for(int i=size-1;i>=0;i--) {
			Cell tmp=cellList.get(i);
			if(tmp.dead==0 && tmp.active==tmp.lifeTime) cellList.remove(i);
		}
	}
	
	private static void bfsCult() {
		//배양시키기
		ArrayList<Cell> newCell=new ArrayList<>();
		PriorityQueue<Cell> pq=new PriorityQueue<>();
		
		for(Cell cell: cellList) {
			if(cell.active==cell.lifeTime && cell.dead!=0) {
				cell.dead--;
				pq.offer(new Cell(cell.r,cell.c,cell.lifeTime,cell.active,cell.dead));
			}
		}
		
		while(!pq.isEmpty()) {
			Cell tmp= pq.poll();
			
			for(int i=0;i<4;i++) {
				int rr= tmp.r+dr[i];
				int cc= tmp.c+dc[i];
				
				if(rr<0||rr>=n+(2*k)+1||cc<0||cc>=m+(2*k)+1||map[rr][cc]!=0) continue;
				
				map[rr][cc]=tmp.lifeTime;
				newCell.add(new Cell(rr,cc,tmp.lifeTime,0,0));
			}
		}
		
		//원래있던 세포들 활성시간 up
		upActive();
		
		for(Cell cell:newCell) {
			cellList.add(new Cell(cell.r,cell.c,cell.lifeTime,cell.active,cell.dead));
		}
	}
	
	private static void upActive() {
		for(Cell cell:cellList) {
			//이미 활성화된 경우 증가시킬 필요 없음
			if(cell.active==cell.lifeTime) continue;
			//세포 활성화
			if(++cell.active==cell.lifeTime) cell.dead=cell.lifeTime;
		}
	}
	
	static class Cell implements Comparable<Cell>{
		int r,c,lifeTime,active,dead;
		
		Cell(int r,int c,int lifeTime,int active, int isDead){
			this.r=r;
			this.c=c;
			this.lifeTime=lifeTime;
			this.active=active;
			this.dead=isDead;
		}

		@Override
		public int compareTo(Cell o) {
			//내림차순
			return o.lifeTime-this.lifeTime;
		}
	}
}
