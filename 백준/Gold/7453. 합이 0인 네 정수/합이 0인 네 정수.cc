#include <iostream>
#include <vector>
#include <algorithm>
#define MAX 4001

using namespace std;
int A[MAX],B[MAX],C[MAX],D[MAX];
vector<long long> AB;
vector<long long> CD;

int N;

int main()
{
	int i,j;
	
	//freopen("input.txt","rt",stdin);
	
	cin>>N;
	for(i=0;i<N;i++)
		cin>>A[i]>>B[i]>>C[i]>>D[i]; //각 배열에 저장
	for(i=0;i<N;i++)
	{
		for(j=0;j<N;j++)
		{
			AB.push_back(A[i]+B[j]); //a,b 배열의 각 경우의 합 저장 
			CD.push_back(C[i]+D[j]); //c,d 배열의 각 경우의 합 저장 
		}
	}
	
	sort(AB.begin(),AB.end());
	sort(CD.begin(),CD.end()); //정렬
	
	long long lt=0;
	long long rt=CD.size()-1;
	long long ans=0;
	
	while(lt<AB.size() && rt>=0)
	{
		long long sum=AB[lt]+CD[rt];
		if(sum<0) lt++;
		else if(sum>0) rt--;
		else //0일 경우 
		{
			long long n_lt=upper_bound(AB.begin(),AB.end(),AB[lt])-AB.begin();
			long long n_rt=lower_bound(CD.begin(),CD.end(),CD[rt])-CD.begin()-1;
			ans+=(n_lt-lt)*(rt-n_rt);
			lt=n_lt;
			rt=n_rt;
		}
	}
	
	cout<<ans<<endl;
	
	return 0;
}
