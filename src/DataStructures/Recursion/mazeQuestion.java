package DataStructures.Recursion;

/**
 * 递归实现迷宫
 *
 * @author Jia RenHao
 * @create 2020-04-07
 */
public class mazeQuestion {
    public static void main(String[] args) {
        //创建8*7的迷宫地图
        int[][] mazeMap = new int[8][7];
        //将四周设置为1，作为墙
        //1.将第一行和最后一行设置为1
        for (int i = 0; i < 7; i++) {
            mazeMap[0][i] = 1;
            mazeMap[7][i] = 1;
        }
        //2.将第一列和最后一列设置为1
        for (int i = 0; i < 8; i++) {
            mazeMap[i][0] = 1;
            mazeMap[i][6] = 1;
        }
        //3.设置迷宫内部挡板
        mazeMap[3][1] = 1;
        mazeMap[3][2] = 1;
        //4.打印初始地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(mazeMap[i][j] + " ");
            }
            System.out.println();
        }
        //进行迷宫
        findWay(mazeMap,1,1);
        //打印新的地图
        System.out.println("---找到迷宫通路后的地图---");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(mazeMap[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 使用递归进行迷宫
     * 规定：
     * 1.map表示地图
     * 2.i和j表示从哪个位置出发
     * 3.如果找到map[6][5]则找到出口，形成一条通路
     * 4.当map[i][j]=0时，说明还没走过这条路，如果是2说明已经走过，如果是3,说明已经走过但是此路不通
     * 5.确定探路策略：下->右->上->左,如果都走不通，则回溯
     *
     * @param map 地图，说明：因为是引用类型，所以递归每次用的地图都是同一份
     * @param i   当前开始位置行
     * @param j   当前开始位置列
     * @return Boolean
     */
    public static boolean findWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
                if (findWay(map, i + 1, j)) {//先向下寻找
                    return true;
                } else if (findWay(map, i, j + 1)) {//向右寻找
                    return true;
                } else if (findWay(map, i - 1, j)) {//向上寻找
                    return true;
                } else if (findWay(map, i, j - 1)) {//向左寻找
                    return true;
                } else {
                    //如果上下左右都没找到，则回溯
                    map[i][j] = 3;
                    return false;
                }
            } else {
                //如果map[i][j] != 0，说明是1,2,3
                return false;
            }
        }
    }
}
