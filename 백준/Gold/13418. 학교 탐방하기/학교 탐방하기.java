import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static int[][] college;
	static int[] parent;
	static int[] rank;
	static int[][] sel;
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str= new StringTokenizer(br.readLine());
		int n=Integer.parseInt(str.nextToken())+1;
		int m=Integer.parseInt(str.nextToken())+1;
		
		college=new int[m][3];
		
		for(int i=0;i<m;i++) {
			str=new StringTokenizer(br.readLine());
			college[i][0]=Integer.parseInt(str.nextToken()); //출발
			college[i][1]=Integer.parseInt(str.nextToken()); //도착
			college[i][2]=Integer.parseInt(str.nextToken()); //경사 0:오르막 1:내리막
		}
		
		Arrays.sort(college,new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1[2], o2[2]); //경사도에 따라 오름차순
			}
		});
		sel=new int[n-1][3];
		parent=new int[n];
		rank=new int[n];
		
		for(int i=0;i<n;i++) {
			parent[i]=i;
		}
		
		int cnt=0;
		int slope_up1=0; //오르막을 몇 번 오르는지
		for(int i=0;i<m;i++) {
			//싸이클 여부를 확인
			int pi=findSet(college[i][0]);
			int pj=findSet(college[i][1]);
			
			if(pi==pj) continue;
			
			union(pi,pj);
			//오르막길을 많이 오르는 최악의 경우
			sel[cnt]=college[i];
			if(sel[cnt][2]==0) slope_up1++;
			cnt++;
			if(cnt==n-1) break;
		}
	
		for(int i=0;i<n;i++) {
			parent[i]=i;
		}
		cnt=0;
		int slope_up2=0;
		for(int i=m-1;i>=0;i--) {
			//뒤에서 부터 확인하면 내리막길을 더 많이 포함
			//싸이클 여부를 확인
			int pi=findSet(college[i][0]);
			int pj=findSet(college[i][1]);
			
			if(pi==pj) continue;
			
			union(pi,pj);
			sel[cnt]=college[i];
			if(sel[cnt][2]==0) slope_up2++;
			cnt++;
			if(cnt==n-1) break;
		}
		int ans= (int)(Math.pow(slope_up1, 2))-(int)(Math.pow(slope_up2,2));
		System.out.println(ans);
	}
	
	private static void union(int i, int j) {
		int pi=findSet(i);
		int pj=findSet(j);
		
		if(rank[pi]>rank[pj]) {
			parent[pj]=pi;
		}
		else {
			parent[pi]=pj;
			if(rank[pi]==rank[pj]) {
				rank[pj]++;
			}
		}
	}

	private static int findSet(int i) {
		if(parent[i]==i) return i;
		return parent[i]=findSet(parent[i]);
	}

}
