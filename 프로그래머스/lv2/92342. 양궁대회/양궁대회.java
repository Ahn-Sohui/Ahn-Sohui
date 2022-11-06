class Solution {
    static int max=Integer.MIN_VALUE;
    static int[] lion;
    static int[] res={-1};
    
    public int[] solution(int n, int[] info) {

        lion=new int[11];
        
        arrowLion(info,n,0);

        return res;
    }
    
    public void arrowLion(int[] info,int n,int depth){
        if(depth==n){
            //화살의 갯수만큼 쐈을때
            int appeachScore=0,lionScore=0; //어피치의 점수, 라이언의 점수
            for(int i=0;i<11;i++){
                if(info[i]!=0 || lion[i]!=0){
                    if(info[i]>=lion[i]) appeachScore+=10-i;
                    else lionScore+=10-i;
                }
            }
            
            int tmp=lionScore-appeachScore;

            if(tmp<=0){
                return;
            }else{
                
                if(max<=tmp){
                    max=tmp;
                    res=lion.clone();
                }
            }
            return;
        }else{
        
        for(int i=0;i<11 && lion[i]<=info[i];i++){
            lion[i]++;
            arrowLion(info,n,depth+1);
            lion[i]--;
            }
        }
    }
}