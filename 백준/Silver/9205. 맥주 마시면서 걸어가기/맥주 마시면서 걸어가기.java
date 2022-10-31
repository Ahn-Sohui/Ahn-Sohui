import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n,sr,sc,dr,dc;
	static ArrayList<Pair> store=new ArrayList<>();
	static StringBuilder sb=new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		int t= Integer.parseInt(br.readLine());
		
		for(int tc=0;tc<t;tc++) {
			n=Integer.parseInt(br.readLine());
			for(int i=0;i<n+2;i++) {
				StringTokenizer str= new StringTokenizer(br.readLine());
				if(i==0) {
					sr=Integer.parseInt(str.nextToken());
					sc=Integer.parseInt(str.nextToken());
				}else if(i==n+1) {
					dr=Integer.parseInt(str.nextToken());
					dc=Integer.parseInt(str.nextToken());
				}else {
					store.add(new Pair(Integer.parseInt(str.nextToken()),Integer.parseInt(str.nextToken())));
				}
			}
			walk();
            store.clear();
		}
		System.out.println(sb);
	}
	private static void walk() {
		// 걸어가면서 맥주 마시기
		Queue<Pair> q =new LinkedList<>();
		boolean[] visited=new boolean[n];
		q.offer(new Pair(sr,sc));
		
		while(!q.isEmpty()) {
			Pair cur=q.poll();
			
			if((Math.abs(cur.r-dr)+Math.abs(cur.c-dc))<=1000) {
				//페스티벌 위치
				sb.append("happy").append("\n");
				return;
			}
			//System.out.println(cur);
			for(int i=0;i<n;i++) {
				if(!visited[i]) {
					int rr=store.get(i).r;
					int cc=store.get(i).c;
					
					int dist=Math.abs(cur.r-rr)+Math.abs(cur.c-cc);
					
					if(dist<=1000) {
						visited[i]=true;
						q.offer(new Pair(rr,cc));
					}
				}
			}
		}
		
		sb.append("sad").append("\n");
		
	}
	
	static class Pair{
		int r,c;
		Pair(int r,int c){
			this.r=r;
			this.c=c;
		}
		@Override
		public String toString() {
			return "Pair [r=" + r + ", c=" + c + "]";
		}
		
		
	}
}
