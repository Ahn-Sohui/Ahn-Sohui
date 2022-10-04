#include <iostream>
#include <queue>
#include <algorithm>

using namespace std;
int map[9][9],N,M,MAX=-2147000000;
int ch[9][9]; //맵정보 복 제
queue<pair<int, int> > Q;
int dx[4]={-1,0,1,0};
int dy[4]={0,1,0,-1};


void Virus()
{
	int x,y,xx,yy,cnt=0;
	int virus[9][9]; //바이러스가 퍼진 후의 맵 
	for(int i=1;i<=N;i++)
	{
		for(int j=1;j<=M;j++)
		{
			virus[i][j]=ch[i][j];
			if(virus[i][j]==2)
				Q.push(make_pair(i,j));
		}
	}
	while(!Q.empty())
	{
		x=Q.front().first;
		y=Q.front().second;
		Q.pop();
		for(int i=0;i<4;i++)
		{
			xx=x+dx[i];
			yy=y+dy[i];
			if(virus[xx][yy]==0)
			{
				if(xx>=1 && xx<=N && yy>=1 &&yy<=M)
				{
					virus[xx][yy]=2;
					Q.push(make_pair(xx,yy));
				}
			}
		}
	}
	
	for(int i=1;i<=N;i++)
	{
		for(int j=1;j<=M;j++)
		{
			if(virus[i][j]==0)
				cnt++;
		}
	}
	
	if(cnt>MAX) MAX=cnt;
}

void setWall(int L)
{
	
	if(L==3)
	{
		Virus();
		return;
	}
	else
	{
		for(int i=1;i<=N;i++)
		{
			for(int j=1;j<=M;j++)
			{
				if(ch[i][j]==0)
				{
					ch[i][j]=1;
					setWall(L+1);
					ch[i][j]=0;
				}
			}
		}
	}
}


int main()
{
	int i,j;
	//freopen("input.txt","rt",stdin);
	cin>>N>>M;
	for(i=1;i<=N;i++)
	{
		for(j=1;j<=M;j++)
		{
			cin>>map[i][j];
			ch[i][j]=map[i][j];
		}
	}
	
	for(i=1;i<=N;i++)
	{
		for(j=1;j<=M;j++)
		{
			if(map[i][j]==0)
			{
				ch[i][j]=1;
				setWall(1);
				ch[i][j]=0;
			}
		}
	}
	
	
	cout<<MAX<<endl;
	
	return 0;
}