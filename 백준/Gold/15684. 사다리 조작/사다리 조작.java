import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n,m,h,res=-1;
    static int[][] ladder;
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str=new StringTokenizer(br.readLine());
        n=Integer.parseInt(str.nextToken());
        m=Integer.parseInt(str.nextToken());
        h=Integer.parseInt(str.nextToken());

        ladder=new int[h+1][n+1];

        for(int i=0;i<m;i++){
            str=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(str.nextToken());
            int b=Integer.parseInt(str.nextToken());
            ladder[a][b]=1; //오른쪽으로 사다리가 연결 되어있음
            ladder[a][b+1]=-1; //왼쪽으로 사다리가 연결 되어있음
        }
        for(int i=0;i<4;i++){
            //사다리는 3개 까지만 설치
            if(recur(1,0,i)) {
                return;
            }
        }
        //3개 넘겨서 설치해야하는 경우
        System.out.print(-1);
    }

    static boolean recur(int r,int depth,int level){
        if(depth==level){
            if(check()){
                System.out.println(level);
                return true;
            }

            return false;
        }
        for(int i=r;i<=h;i++){
            for(int j=1;j<n;j++){
                if(ladder[i][j]==0 && ladder[i][j+1]==0){
                    ladder[i][j]=1;
                    ladder[i][j+1]=-1;
                    if(recur(i,depth+1,level)){
                        return true;
                    }
                    ladder[i][j]=0;
                    ladder[i][j+1]=0;
                }
            }
        }

        return false;
    }

    static boolean check(){
        for(int i=1;i<=n;i++){
            int start=i;

            for(int j=1;j<=h;j++){
                if(ladder[j][start]==1){
                    start++;
                }else if(ladder[j][start]==-1){
                    start--;
                }
            }

            //i번째 사다리가 i 위치에 도착 못했으면
            if(start!=i) return false;
        }
        return true;
    }
}
