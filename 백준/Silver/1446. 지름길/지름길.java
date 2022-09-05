import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static class Node{
		int from,end,weight;
		
		Node(int from,int end,int weight){
			this.from=from;
			this.end=end;
			this.weight=weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str=new StringTokenizer(br.readLine());
		
		int N=Integer.parseInt(str.nextToken());
		int D=Integer.parseInt(str.nextToken());
		
		ArrayList<Node> shortCut=new ArrayList<>();
		
		for(int i=0;i<N;i++) {
			str=new StringTokenizer(br.readLine());
			int from=Integer.parseInt(str.nextToken());
			int end=Integer.parseInt(str.nextToken());
			int weight=Integer.parseInt(str.nextToken());
			//역주행 불가
			if(end>D) continue;
			
			//지름길 아님
			if(end-from<=weight) continue;
			
			shortCut.add(new Node(from,end,weight));
		}
		
		Collections.sort(shortCut,new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				// TODO Auto-generated method stub
				if(o1.from==o2.from) return o1.end-o2.end;
				//출발지 순으로 정렬
				return o1.from-o2.from;
			}
		});
		
		int minIdx=0,dis=0;
		int[] minDis=new int[10001];
		Arrays.fill(minDis, Integer.MAX_VALUE);
		minDis[0]=0;
		
		while(dis<D) {
			if(minIdx<shortCut.size()) {
				Node tmp=shortCut.get(minIdx);
				if(dis==tmp.from) {
					minDis[tmp.end]=Math.min(minDis[dis]+tmp.weight, minDis[tmp.end]);
					minIdx++;
				}
				else {
					minDis[dis+1]=Math.min(minDis[dis+1], minDis[dis]+1);
					dis++;
				}
			}else {
				minDis[dis+1]=Math.min(minDis[dis+1], minDis[dis]+1);
				dis++;
			}
		}
		System.out.println(minDis[D]);
	}
}
