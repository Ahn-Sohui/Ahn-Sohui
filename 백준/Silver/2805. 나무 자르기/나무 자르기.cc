#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
 
vector<int> tree; //나무의 정보를 저장할 벡터 

int main()
{
	int i,n,m,tmp,ans;
    int lt=0,mid,rt;
	//freopen("input.txt","rt",stdin);
	cin>>n>>m;
	for(i=0;i<n;i++)
	{
		cin>>tmp; //나무의 높이를 받을 변수 
		tree.push_back(tmp); // 나무의 정보를 저장 
	}

	rt=*max_element(tree.begin(),tree.end()); //가장 큰 나무
	while(lt<=rt)
	{
		long long sum=0; //잘려진 나무의 합
		mid=(lt+rt)/2;
		for(i=0;i<n;i++)
		{
			if(tree[i]>=mid)
				sum+=(tree[i]-mid); //H값 보다 크면 나무를 자르고 잘린 부분 더함 
		}
		if(sum>=m) //원하는 만큼 챙겼을 경우
		{
			ans=mid;
			lt=mid+1;
		} 
		else rt=mid-1; //더 적게 잘랐으므로 H값을 줄여야 함 
	}
	
	cout<<ans<<endl;
	return 0; 
}