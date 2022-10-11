import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 한 칸씩 우로 이동
 * 직전 높이와 현재 높이 비교
 * => 같다 : 연속길이 카운트
 * => 다르다 -> 높이 2 이상 : 활주로 건설x
 * 		  -> 높이 1 : 오르막경사 , 내리막 경사 체크
 * 
 * */
public class Solution {
	static int n,x,map[][],map2[][];
	//map => 행우선 탐색   //map2 => 열우선 탐색
	public static void main(String[] args) throws Exception{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for(int tc=1;tc<=T;tc++) {
			sb.append("#").append(tc).append(" ");
			StringTokenizer str= new StringTokenizer(br.readLine());
			n=Integer.parseInt(str.nextToken());
			x=Integer.parseInt(str.nextToken());
			
			map=new int[n][n];
			map2=new int[n][n];
			
			for(int i=0;i<n;i++) {
				str=new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) {
					map2[j][i]=map[i][j]=Integer.parseInt(str.nextToken());
				}
			}
			sb.append(process()).append("\n");
		}
		System.out.println(sb);

	}

	private static int process() {
		int cnt=0;
		for(int i=0;i<n;i++) {
			if(makeRoad(map[i])) cnt++;
			if(makeRoad(map2[i])) cnt++;
		}
		return cnt;
	}

	//해당 지형 정보로 활주로 건설이 가능하면 true, 불가능하면 false
	private static boolean makeRoad(int[] road) {
		int beforeHeight=road[0], size=0;
		int j=0;
		
		while(j<n) {
			if(beforeHeight==road[j]) {
				// 동일 높이 
				size++;
				j++;
			}else if(beforeHeight+1==road[j]) {
				//동일 높이가 아님 : 오르막 경사로 설치체크
				if(size<x) return false; //x미만이면 활주로 건설 불가
				beforeHeight++;
				size=1;	
				j++;
			}else if(beforeHeight-1 == road[j]) {
				//이전 높이보다 1 작음
				int cnt=0;
				for(int k=j;k<n;k++) {
					if(road[k]!=beforeHeight-1) return false;
					if(++cnt==x) break;
				}
				
				if(cnt<x) return false;
				
				//내리막 경사로를 세웠기 때문
				beforeHeight--;
				j+=x;
				size=0;
				
			}else {
				//높이가 2 이상 차이
				return false;
			}
		}
		return true;
	}

}
