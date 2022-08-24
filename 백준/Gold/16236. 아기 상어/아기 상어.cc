#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <cstring>


using namespace std;

int SEA[21][21], ch[21][21], e_fish = 0, b_shark = 2,N ,sec=0; //맵정보, 걸리는 시간체크, 먹은 물고기의 수, 상어의 크기, 맵 크기, 걸리는 시간
int dx[4] = { -1,0,1,0 };
int dy[4] = { 0,1,0,-1 };
int s_x, s_y; // 아기상어의 위치
queue<pair<int, int>> Q; //아기상어의 위치 저장



int main()
{
	int i, j;
	cin >> N;
	for (i = 0; i < N; i++)
	{
		for (j = 0; j < N; j++)
		{
			cin >> SEA[i][j];
			if (SEA[i][j] == 9)
			{
				s_x = i;
				s_y = j; //아기상어의 처음위치 저장
			}
		}
	}

	SEA[s_x][s_y] = 0; // 나중에 지나갈 수 있도록 바꿔줌

	while (1)
	{
		int x, y, xx, yy, min_dist = 2147000000; 
		memset(ch, -1, sizeof(ch)); //ch 배열 초기화
		ch[s_x][s_y] = 0;
        vector<pair<int, int>> fish; // 물고기의 위치 저장
		if (e_fish >= b_shark) //먹은 물고기 수가 상어의 크기와 크거나 같은지 확인
		{
			e_fish -= b_shark;
			b_shark++;
		}
		Q.push({ s_x,s_y });
		while (!Q.empty())
		{
			x = Q.front().first;
			y = Q.front().second;
			Q.pop();
			for (i = 0; i < 4; i++)
			{
				xx = x + dx[i];
				yy = y + dy[i]; //방향탐색
				if (xx >= 0 && xx < N && yy >= 0 && yy < N)
				{
					if (ch[xx][yy] == -1)
					{
						if (SEA[xx][yy] == b_shark || SEA[xx][yy] == 0)
						{
							ch[xx][yy] = ch[x][y] + 1;
							Q.push({ xx,yy });
						}
						else if (SEA[xx][yy] < b_shark && SEA[xx][yy] >= 1)
						{
							ch[xx][yy] = ch[x][y] + 1;
							fish.push_back({ xx,yy });
							Q.push({ xx,yy });
						}
					}
				}
			}
		}

		if (fish.size() == 0) //먹을 수 있는 물고기 없음
		{
			cout << sec << endl;
			return 0;
		}
		else if (fish.size() == 1) // 먹을 수 있는 물고기가 한 마리
		{
			s_x = fish[0].first;
			s_y = fish[0].second; //상어 이동
			SEA[s_x][s_y] = 0; //물고기 냠
			e_fish++;
			sec += ch[s_x][s_y];
		}
		else // 2마리 이상
		{
			vector<pair<int, int>> min_fish; //최단거리에 있는 물고기들 저장
			for (i = 0; i < fish.size(); i++)
			{
				if (ch[fish[i].first][fish[i].second] < min_dist) min_dist = ch[fish[i].first][fish[i].second]; // 최단거리 저장
			}

			for (i = 0; i < fish.size(); i++)
			{
				if (min_dist == ch[fish[i].first][fish[i].second])
					min_fish.push_back({ fish[i].first,fish[i].second });
			}

			if (min_fish.size() == 1)//최단 거리에 있는 물고기가 1마리
			{
				s_x = min_fish[0].first;
				s_y = min_fish[0].second; //상어 이동
				SEA[s_x][s_y] = 0; 
				e_fish++;
				sec += ch[s_x][s_y];
			}
			else //2마리 이상
			{
				sort(min_fish.begin(), min_fish.end()); //정렬
				s_x = min_fish[0].first;
				s_y = min_fish[0].second; //상어 이동
				SEA[s_x][s_y] = 0;
				e_fish++;
				sec += ch[s_x][s_y];
			}
		}
	}
}