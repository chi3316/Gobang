import javax.swing.*;
public class Start extends JFrame {
    //有一个继承了JPanel的棋盘对象
    Chessboard chessboard = null;
    //构造器
    public Start() {
        chessboard = new Chessboard();
        setTitle("五子棋");
        this.add(chessboard);
        this.addMouseListener(chessboard);
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        var start = new Start();
        System.out.println("游戏开始!");
    }
}