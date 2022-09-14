#include <iostream>
#include <algorithm>

using namespace std;

int MAP[51][51], ch[51][51], cnt = 1;
int N, M, r, c, d;
int dx[4] = { -1,0,1,0 };
int dy[4] = { 0,1,0,-1 };

void DFS() 
{
	int x, y, n_dir;
	for (int i = 0; i < 4; i++) //계속 회전시키면서 탐색
	{
		n_dir = (d + 3 - i) % 4;
		x = r + dx[n_dir];
		y = c + dy[n_dir];

		if (x < 0 || x >= N || y < 0 || y >= M || MAP[x][y] == 1) continue;
		if (MAP[x][y] == 0 && ch[x][y] == 0) //청소가능
		{
			ch[x][y] = 1;
			r = x;
			c = y;
			d = n_dir;
			cnt++;
			DFS();
		}
	}

	n_dir = (d + 2) % 4;
	x = r + dx[n_dir];
	y = c + dy[n_dir];

	if (x >= 0 && x < N && y >= 0 && y < M)
	{
		if (MAP[x][y] == 0) //후진 가능
		{
			r = x;
			c = y;
			DFS();
		}
		else //후진 불가능
		{
			cout << cnt << endl;
			exit(0);
		}
	}

}

int main()
{
	int i, j;
	//freopen("input.txt", "rt", stdin);
	cin >> N >> M;
	cin >> r >> c >> d;
	for (i = 0; i < N; i++)
	{
		for (j = 0; j < M; j++)
		{
			cin >> MAP[i][j];
		}
	}
	ch[r][c] = 1;

	DFS();

	return 0;
}