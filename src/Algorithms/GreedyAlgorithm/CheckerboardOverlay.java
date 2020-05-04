package Algorithms.GreedyAlgorithm;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title: 棋盘覆盖问题/马踏棋盘问题
 * @Description:
 * @Author: Jia RenHao
 * @Create: 2020-05-02
 * @Version: V1.0
 */
public class CheckerboardOverlay {
    private static int X;//棋盘列数目
    private static int Y;//棋盘行数目
    private static boolean[][] visited;//当前坐标是否被访问
    private static boolean completed;//找到解，为true，否则为false

    public static void main(String[] args) {
        X = 8;//棋盘列数目
        Y = 8;//棋盘行数目
        int row = 1;//起始行位置
        int column = 1;//起始列位置
        int[][] checkerboard = new int[X][Y];//棋盘
        visited = new boolean[X][Y];//当前坐标是否被访问
        traversalCheckerboard(checkerboard, row - 1, column - 1, 1);
        for (int i = 0; i < X; i++) {
            for (int j = 0; j < Y; j++) {
                System.out.print(checkerboard[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void traversalCheckerboard(int[][] checkerboard, int row, int column, int step) {
        checkerboard[row][column] = step;//当前坐标记录第几步
        visited[row][column] = true;//标记该点已被访问
        List<Point> pointList = next(new Point(column, row));
        pointList.sort((p1, p2) -> {
            int size1 = next(p1).size();
            int size2 = next(p2).size();
            return Integer.compare(size1, size2);
        });
        while (!pointList.isEmpty()) {
            Point p = pointList.remove(0);//取出下一个要走的点
            if (!visited[p.y][p.x]) {
                traversalCheckerboard(checkerboard, p.y, p.x, step + 1);
            }
        }
        //回溯
        if (step < X * Y && !completed) {
            checkerboard[row][column] = 0;
            visited[row][column] = false;
        } else {
            completed = true;
        }
    }

    public static List<Point> next(Point curPoint) {
        List<Point> points = new ArrayList<>();
        Point point = new Point();
        if ((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y - 1) >= 0) {
            points.add(new Point(point));
        }
        if ((point.x = curPoint.x - 1) >= 0 && (point.y = curPoint.y - 2) >= 0) {
            points.add(new Point(point));
        }
        if ((point.x = curPoint.x + 1) < X && (point.y = curPoint.y - 2) >= 0) {
            points.add(new Point(point));
        }
        if ((point.x = curPoint.x + 2) < X && (point.y = curPoint.y - 1) >= 0) {
            points.add(new Point(point));
        }
        if ((point.x = curPoint.x + 2) < X && (point.y = curPoint.y + 1) < Y) {
            points.add(new Point(point));
        }
        if ((point.x = curPoint.x + 1) < X && (point.y = curPoint.y + 2) < Y) {
            points.add(new Point(point));
        }
        if ((point.x = curPoint.x - 1) >= 0 && (point.y = curPoint.y + 2) < Y) {
            points.add(new Point(point));
        }
        if ((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y + 1) < Y) {
            points.add(new Point(point));
        }
        return points;
    }
}
