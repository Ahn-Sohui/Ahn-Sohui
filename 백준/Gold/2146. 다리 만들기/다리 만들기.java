import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map;
    static int[] dr = {0, 0, 1, -1}; //동서남북
    static int[] dc = {1, -1, 0, 0};
    static boolean[][] visited;
    static int[][] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer str = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(str.nextToken());
            }
        }


        int res=Integer.MAX_VALUE;
        changeIsland();
        //print(map);
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(map[i][j]>0){
                    visited=new boolean[n][n];
                    dist=new int[n][n];

                    int tmp=makeBridge(i,j);
                    //print(dist);
                    if(tmp==-1) continue;
                    res=Math.min(res,tmp);
                }
            }
        }
        System.out.println(res-1);
    }

    private static int makeBridge(int r,int c){
        //같은 섬에 포함된 육지 쪽으로는 확장시키지 않음
        Queue<Pair> q=new LinkedList<>();
        visited[r][c]=true;
        q.offer(new Pair(r,c,map[r][c]));
        int num=map[r][c];
        while(!q.isEmpty()){
            Pair tmp=q.poll();
            if(tmp.num!=0 && tmp.num!=num)
                return dist[tmp.r][tmp.c];
            for(int i=0;i<4;i++){
                int rr=tmp.r+dr[i];
                int cc=tmp.c+dc[i];

                if(rr<0||rr>=n||cc<0||cc>=n||map[rr][cc]==num||visited[rr][cc]) continue;
                visited[rr][cc]=true;
                dist[rr][cc]=dist[tmp.r][tmp.c]+1;
                q.offer(new Pair(rr,cc,map[rr][cc]));

            }
        }

        return -1;
    }

    private static void print(int[][] map) {
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
    private static void changeIsland() {
        int num = 1;
        Queue<Pair> q = new LinkedList<>();
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && map[i][j] != 0) {
                    map[i][j] = num;
                    visited[i][j] = true;
                    q.offer(new Pair(i, j));

                    while (!q.isEmpty()) {
                        Pair tmp = q.poll();

                        for (int d = 0; d < 4; d++) {
                            int rr = tmp.r + dr[d];
                            int cc = tmp.c + dc[d];

                            if (rr < 0 || rr >= n || cc < 0 || cc >= n || visited[rr][cc] || map[rr][cc] == 0) continue;
                            visited[rr][cc]=true;
                            map[rr][cc]=num;
                            q.offer(new Pair(rr,cc));
                        }
                    }
                    num++;
                }
            }
        }
    }

    static class Pair {
        int r, c, num;

        Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }

        Pair(int r, int c, int num) {
            this.r = r;
            this.c = c;
            this.num = num;
        }
    }
}
