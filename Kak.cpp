#include <iostream>
#include <string>

using namespace std;

const int FIELD_WIDTH = 10;
const int FIELD_HEIGHT = 5;
const int GOAL_WIDTH = 3;

struct Position {
    int x;
    int y;
};

void printField(const Position& ball, const Position& goal) {
    for (int y = 0; y < FIELD_HEIGHT; ++y) {
        for (int x = 0; x < FIELD_WIDTH; ++x) {
            if (x == ball.x && y == ball.y) {
                cout << "O"; // 球
            } else if (x >= goal.x && x < goal.x + GOAL_WIDTH && y == goal.y) {
                cout << "|"; // 球门
            } else {
                cout << ".";
            }
        }
        cout << endl;
    }
}

bool isGoal(const Position& ball, const Position& goal) {
    return ball.y == goal.y && ball.x >= goal.x && ball.x < goal.x + GOAL_WIDTH;
}

int main() {
    Position ball = {FIELD_WIDTH / 2, FIELD_HEIGHT / 2};
    Position goal = {FIELD_WIDTH / 2 - GOAL_WIDTH / 2, FIELD_HEIGHT - 1};

    int score = 0;

    while (true) {
        printField(ball, goal);

        cout << "当前得分: " << score << endl;
        cout << "选择踢球方向 (左: a, 右: d, 上: w, 下: s, 退出: q): ";
        char input;
        cin >> input;

        if (input == 'q') {
            break;
        }

        Position newBall = ball;

        switch (input) {
            case 'a': // 左
                newBall.x--;
                break;
            case 'd': // 右
                newBall.x++;
                break;
            case 'w': // 上
                newBall.y--;
                break;
            case 's': // 下
                newBall.y++;
                break;
            default:
                cout << "无效输入，请重新选择。" << endl;
                continue;
        }

        if (newBall.x >= 0 && newBall.x < FIELD_WIDTH && newBall.y >= 0 && newBall.y < FIELD_HEIGHT) {
            ball = newBall;
        } else {
            cout << "球出界了！" << endl;
            ball = {FIELD_WIDTH / 2, FIELD_HEIGHT / 2}; // 重置球的位置
        }

        if (isGoal(ball, goal)) {
            score++;
            cout << "进球了！得分: " << score << endl;
            ball = {FIELD_WIDTH / 2, FIELD_HEIGHT / 2}; // 重置球的位置
        }
    }

    cout << "游戏结束，最终得分: " << score << endl;

    return 0;
}
