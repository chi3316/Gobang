import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

public class Chessboard extends JPanel implements MouseListener{
    //大小一般都是15 * 15
    int rows;
    int cols;
    //指的是边框到边界的距离
    int margin;
    //默认黑色 1 -> 黑色；2->白色
    int Player;
    int cellSize;
    //落子思路：Chessboard 可以监听鼠标，鼠标点击交点位置便可以在此处创建一个ChessPiece对象
    //这个地方不能直接这么写  Vector<ChessPiece> chessPieces;  会有空指针异常！
    //要这么写必须提供构造器，你这个类竟然没有构造器，不太规范
    Vector<ChessPiece> chessPieces;
    int[][] map;
    boolean end;

    //还是给个构造器：
    public Chessboard() {
        rows = 15;
        cols = 15;
        margin = 50;
        Player = 1;
        cellSize = 30;
        chessPieces = new Vector<>();
        end = false;

        //对棋盘初始化
        /*
        我超！！！ 我这样写 ：map = new int[rows + 1][];为什么map的引用为空？？
        首先创建了一个map的一维数组，但你没有为每个元素创建二维数组。
        这就导致了map的引用是一个一维数组，但它的元素是null引用，因此在访问map[row][col]时会导致空指针异常。
         */
        map = new int[rows + 1][cols + 1];
        for(int i = 0;i< map.length;i++){
            for(int j = 0;j < map[i].length;j++) {
                map[i][j] = 0;
            }
        }
    }

    //提示框
    private void showVictoryDialog() {
        // 创建一个提示框
        if(this.Player == 1) {
            JOptionPane.showMessageDialog(this, "游戏结束，黑棋玩家胜利！", "胜利提示", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        else {
            JOptionPane.showMessageDialog(this, "游戏结束，白棋玩家胜利！", "胜利提示", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }

    }

    //判断胜负
    public void judgeVictory(int row,int col,int player) {
        //记录地图位置的分数和
        System.out.println("row = " + row + "  col = " + col);
        int sum = 0;
        //向左:
        for(int i = row;i>=0;i--){
            if(map[i][col] == player) sum += player;
            if(sum == 5 || sum == 10) {
                Sum(sum);
                this.end = true;
                break;
            }
        }
        //System.out.println(sum);
        sum = 0;
        //向右:
        for(int i = row;i<= rows;i++) {
            if(map[i][col] == player) sum += player;
            if(sum == 5 || sum == 10){
                Sum(sum);
                this.end = true;
                break;
            }
        }
       // System.out.println(sum);
        sum = 0;

        //向上
        for(int i = col;i>=0;i--){
            if(map[row][i] == player) sum += player;
            if(sum == 5 || sum == 10) {
                Sum(sum);
                this.end = true;
                break;
            }
        }
        //System.out.println(sum);
        sum = 0;

        //向下：
        for(int i = col;i<=cols;i++){
            if(map[row][i] == player) sum += player;
            if(sum == 5 || sum == 10) {
                Sum(sum);
                this.end = true;
                break;
            }
        }
        //System.out.println(sum);
        sum = 0;

        //左下
        for(int i = row,j =col;i>=0 && j>=0;i--,j--){
            if(map[i][j] == player) sum += player;
            if(sum == 5 || sum == 10) {
                Sum(sum);
                this.end = true;
                break;
            }
        }
        //System.out.println(sum);
        sum = 0;

        //右上
        for(int i = row,j =col;i<=rows && j<=cols;i++,j++){
            if(map[i][j] == player) sum += player;
            if(sum == 5 || sum == 10) {
                Sum(sum);
                this.end = true;
                break;
            }
        }
        //System.out.println(sum);
        sum = 0;

        //左上
        for(int i = row,j =col;i>=0 && j<=cols;i--,j++){
            if(map[i][j] == player) sum += player;
            if(sum == 5 || sum == 10) {
                Sum(sum);
                this.end = true;
                break;
            }
        }
        //System.out.println(sum);
        sum = 0;

        //右下
        for(int i = row,j =col;i<=rows && j>=0;i++,j--){
            if(map[i][j] == player) sum += player;
            if(sum == 5 || sum == 10) {
                Sum(sum);
                this.end = true;
                break;
            }
        }
        System.out.println(sum);
    }

    public void Sum(int sum) {
        if(sum == 5) {
            //黑棋赢
            System.out.println("黑棋获胜");
        }
        if(sum == 10) {
            System.out.println("白棋获胜");
        }
    }

    //判断落子是否有效：
    //1. 交点在地图范围内 2.该位置上没有子
    public boolean isValid(int row,int col) {
        return row >= 0 && col >= 0 && row <= rows && col <= rows && map[row][col] == 0;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //g.fillOval(10,10,20,40);要画16条线
        //画出棋盘
        for(int i = 0;i < rows + 1;i++) {
            g.drawLine(margin,(cellSize * i) + margin,(cellSize * rows) + margin,(cellSize * i) + margin);
            g.drawLine((cellSize * i) + margin,margin,(cellSize * i) + margin,(cellSize * rows) + margin);
        }

        //测试代码
//        g.fillOval(65,65,30,30);
//        g.fillOval(95,95,30,30);
        //画出棋子
        if(chessPieces != null) {
            for(var chessPiece :chessPieces) {
                //确定颜色
                if(chessPiece.getColor() == 1) g.setColor(Color.BLACK);
                else g.setColor(Color.WHITE);
                g.fillOval(chessPiece.getX(),chessPiece.getY(),cellSize,cellSize);
            }
        }


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // 只触发一次，当用户完成一次点击时
        int x = e.getX();
        int y = e.getY();
        System.out.println("x= " + x + "  y= " + y);

        //判断落子的位置：得到光标点击的 row 和 column值，得到最近的交点的公式是 ：15 + ((row - 1)*30)
        int row = (x - margin) / cellSize;
        int column = (y - margin) / cellSize;
        //System.out.println("新建了一个ChessPiece对象");

        if(isValid(row,column)) {
            ChessPiece chessPiece = new ChessPiece(15 + ((row - 1)*30) + margin, 15 + ((column - 1)*30) + margin, Player);
            map[row][column] = Player;
            this.chessPieces.add(chessPiece);
            //记得要repaint啊，不然都不会画出来
            this.repaint();
            judgeVictory(row,column,this.Player);
            if (end) {
                // 判断游戏是否结束，如果结束，显示胜利提示弹窗
                showVictoryDialog();
            }
            //更换玩家
            if(Player == 1)  Player = 2;
            else Player = 1;

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //可以在鼠标按钮保持按下状态时多次触发，例如，在拖拽操作期间会连续触发。
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

}
