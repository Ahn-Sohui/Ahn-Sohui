#include <iostream>
#include <algorithm>

using namespace std;
int sdoku[9][9];
bool chk(int r, int c, int value);

void playSdoku(int r, int c) {
	if (c == 9) {
		playSdoku(r + 1, 0);
		return;
	}

	if (r == 9) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				cout << sdoku[i][j] << " ";
			}
			cout << endl;
		}

		exit(0);
	}

	if (sdoku[r][c] == 0) {
		for (int i = 1; i <= 9; i++) {
			if (chk(r, c, i)) {
				sdoku[r][c] = i;
				playSdoku(r, c + 1);
			}
		}
		sdoku[r][c] = 0;
		return;
	}
	playSdoku(r , c+1);
}


bool chk(int r, int c, int value) {
	//3*3
	int rr = (r / 3) * 3;
	int cc = (c / 3) * 3;
	for (int i = rr; i < rr + 3; i++) {
		for (int j = cc; j < cc + 3; j++) {
			if (sdoku[i][j] == value)
				return false;
		}
	}

	//가로
	for (int i = 0; i < 9; i++) {
		if (sdoku[r][i] == value)
			return false;
	}

	//세로
	for (int i = 0; i < 9; i++) {
		if (sdoku[i][c] == value)
			return false;
	}

	return true;
}

int main() {
	for (int i = 0; i < 9; i++) {
		for (int j = 0; j < 9; j++) {
			cin >> sdoku[i][j];
		}
	}

	playSdoku(0, 0);
	return 0;
}