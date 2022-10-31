import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str= new StringTokenizer(br.readLine());
        int n=Integer.parseInt(str.nextToken());
        int d=Integer.parseInt(str.nextToken());
        int k=Integer.parseInt(str.nextToken());
        int c=Integer.parseInt(str.nextToken());

        int[] sushi=new int[n];
        int res=0;
        for(int i=0;i<n;i++){
            sushi[i]=Integer.parseInt(br.readLine());
        }

        int[] sel=new int[d+1];
        int selDish=0;
        //k개 담기
        for(int i=0;i<k;i++){
            if(sel[sushi[i]]==0){
                selDish++;
            }
            sel[sushi[i]]++;
        }
        res=selDish;

        for(int i=1;i<n;i++){
            if(res<=selDish){
                if(sel[c]==0){
                    //쿠폰 초밥
                    res=selDish+1;
                }else
                    res=selDish;
            }

            sel[sushi[i-1]]--;
            if(sel[sushi[i-1]]==0) selDish--;

            if(sel[sushi[(i+k-1)%n]]==0) selDish++;
            sel[sushi[(i+k-1)%n]]++;
        }

        System.out.println(res);
    }
}
