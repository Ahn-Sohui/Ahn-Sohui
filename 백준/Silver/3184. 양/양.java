import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.LinkedList;

public class Main {
	static class Pair{
		int r,c;
        Pair(int r,int c){
            this.r=r;
            this.c=c;
        }
	}
	
	static int R,C;
	static char [][] map;
	static boolean [][] visited;
    static int[] dr={-1,0,1,0};
    static int[] dc={0,1,0,-1};
    
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str= new StringTokenizer(br.readLine());
		StringBuilder sb=new StringBuilder();
		R=Integer.parseInt(str.nextToken());
        C=Integer.parseInt(str.nextToken());
        
        map=new char[R][C];
        visited=new boolean[R][C];
        
        for(int i=0;i<R;i++){
            map[i]=br.readLine().toCharArray();
        }
		//bfs
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j]!='#'&& !visited[i][j]){
                    bfs(i,j);
                }
            }
        }
        //ㅇㅑㅇ ㄴㅡㄱㄷㅐ ㅅㅔㄱㅣ
        int sheep_cnt=0,wolf_cnt=0;
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j]=='o') sheep_cnt++;
                if(map[i][j]=='v') wolf_cnt++;
            }
        }
		sb.append(sheep_cnt).append(" ").append(wolf_cnt);
        
        System.out.println(sb);
	}
    
    static void bfs(int r,int c){
        Queue<Pair> q=new LinkedList<>();
        ArrayList<Pair> sheep=new ArrayList<>();
        ArrayList<Pair> wolf=new ArrayList<>();
        if(map[r][c]=='o') sheep.add(new Pair(r,c));
        if(map[r][c]=='v') wolf.add(new Pair(r,c));
        q.offer(new Pair(r,c));
        visited[r][c]=true;
        
        while(!q.isEmpty()){
            Pair tmp=q.poll();
            for(int i=0;i<4;i++){
                int rr=tmp.r+dr[i];
                int cc=tmp.c+dc[i];
                
                if(rr<0|| rr>=R || cc<0 || cc>= C || map[rr][cc]=='#' || visited[rr][cc]) continue;
                
                if(map[rr][cc]=='o') sheep.add(new Pair(rr,cc));
                if(map[rr][cc]=='v') wolf.add(new Pair(rr,cc));
                q.offer(new Pair(rr,cc));
                visited[rr][cc]=true;
            }
        }
        //sheep win
        if(sheep.size()>wolf.size()){
            for(int i=0;i<wolf.size();i++){
                map[wolf.get(i).r][wolf.get(i).c]='.';
            }
        }else{
            //wolf win
            for(int i=0;i<sheep.size();i++){
                map[sheep.get(i).r][sheep.get(i).c]='.';
            }
        }
    }
}