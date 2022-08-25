#include <iostream>
#include <algorithm>
#include <cstring>
using namespace std;

int N, M, MAP[501][501],ch[501][501],MAX=-2147000000; // 맵 크기,맵 정보, 방문했는지 ch , 최댓값
int dx[4] = { -1,0,1,0 };
int dy[4] = { 0,1,0,-1 };

void DFS(int x, int y, int L, int sum)
{
    if (L == 3) //더해진 블록의 수가 4개일 경우
	{
		if (sum > MAX) MAX = sum;
		return;
	}
	else
	{
		for (int i = 0; i < 4; i++)
		{
			int xx = x + dx[i];
			int yy = y + dy[i];
			if (xx >= 0 && xx < N && yy >= 0 && yy < M) //범위 안에 있는지
			{
				if (ch[xx][yy] == 0) //방문했는지
				{
					ch[xx][yy] = 1;
					DFS(xx, yy, L + 1, sum + MAP[xx][yy]);
					ch[xx][yy] = 0; //추가 비교를 위해 방문체크 해제
				}
			}
		}
	}
}

void Shape1(int x, int y) //ㅗ
{
	int sum = 0;
	sum = MAP[x][y] + MAP[x][y + 1] + MAP[x][y + 2] + MAP[x - 1][y + 1];
	if (sum > MAX) MAX = sum;
}

void Shape2(int x, int y) // ㅏ
{
	int sum = 0;
	sum = MAP[x][y] + MAP[x + 1][y] + MAP[x + 2][y] + MAP[x + 1][y + 1];
	if (sum > MAX) MAX = sum;
}

void Shape3(int x, int y) // ㅜ
{
	int sum = 0;
	sum = MAP[x][y] + MAP[x][y + 1] + MAP[x][y + 2] + MAP[x + 1][y + 1];
	if (sum > MAX) MAX = sum;
}

void Shape4(int x, int y) // ㅓ 
{
	int sum = 0;
	sum = MAP[x][y] + MAP[x][y + 1] + MAP[x + 1][y + 1] + MAP[x - 1][y + 1];
	if (sum > MAX) MAX = sum;
}

int main()
{
	int i, j;
	cin >> N >> M;
	for (i = 0; i < N; i++)
	{
		for (j = 0; j < M; j++)
		{
			cin >> MAP[i][j];
		}
	}

	for (i = 0; i < N; i++)
	{
		for (j = 0; j < M; j++)
		{
			ch[i][j]=1;
			DFS(i, j, 0, MAP[i][j]);
            ch[i][j]=0;
			if (i - 1 >= 0 && j + 2 < M ) Shape1(i,j);
			if (i + 2 < N && j + 1 < M) Shape2(i,j);
			if (i + 1 < N && j + 2 < M) Shape3(i,j);
			if (i + 1 < N && i - 1 >= 0 && j + 1 < M) Shape4(i, j);
		}
	}

	cout << MAX << endl;
	
	return  0;
}