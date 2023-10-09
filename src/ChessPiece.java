public class ChessPiece {
    //这里的x,y就是棋子对应的圆形的参考坐标
    private int x;
    private int y;
    //1 -> 黑色；2->白色
    private int color;

    public ChessPiece(int x, int y,int color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getColor() {
        return color;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
