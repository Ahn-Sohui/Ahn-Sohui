import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		Pair[] degree=new Pair[n];
		
		for(int i=0;i<n;i++) {
			degree[i]=new Pair(sc.nextInt(),sc.nextInt());
		}
		
		Arrays.sort(degree,new Comparator<Pair>() {

			@Override
			public int compare(Pair o1, Pair o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1.max, o2.max);
			}
		});
		int max_deg=degree[0].max;
		int cnt=1;
		for(int i=1;i<n;i++) {
			if(max_deg<degree[i].min) {
				max_deg=degree[i].max;
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
	
	public static class Pair{
		int max,min;
		Pair(int min,int max){
			this.max=max;
			this.min=min;
		}
	}
}
