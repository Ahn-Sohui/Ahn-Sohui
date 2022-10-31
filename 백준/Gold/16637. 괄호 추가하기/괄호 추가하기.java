import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static int n;
    public static char[] formula;
    public static int res = Integer.MIN_VALUE;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        formula=new char[n];
        formula= br.readLine().toCharArray();


        dfs(2, formula[0] - '0');
        System.out.println(res);
    }

    public static void dfs(int idx,int sum) {

        if(idx>=n){
            res = Math.max(res, sum);
            return;
        }

        dfs(idx + 2, calc(sum, formula[idx] - '0', formula[idx- 1]));
        
        if(idx+2<n){
            int tmp = calc(formula[idx] - '0', formula[idx + 2] - '0', formula[idx + 1]);
            int sumTotal = calc(sum, tmp, formula[idx - 1]);
            dfs(idx + 4, sumTotal);

        }

    }

    public static int calc(int sum, int num, char op) {
        if(op=='+')
            return sum + num;
        if(op=='-')
            return sum - num;
        return sum * num;
    }
}