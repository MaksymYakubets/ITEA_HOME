package vartank;

final class Bullet {
    private final static int BUFSIZE = 32;
    private short[] x = null;
    private short[] y = null;
    private char[] dx = null;
    private char[] dy = null;
    private int   cnt = 0;

    public Bullet(){
        x   = new short[Bullet.BUFSIZE];
        y   = new short[Bullet.BUFSIZE];
        dx  = new char[Bullet.BUFSIZE];
        dy  = new char[Bullet.BUFSIZE];
        cnt = 0;
    }

    public void add(int px, int py, int pdx, int pdy){
        if(cnt < Bullet.BUFSIZE){
            x[cnt]  = (short)px;
            y[cnt]  = (short)py;
            dx[cnt] = (char)pdx;
            dy[cnt] = (char)pdy;
            ++cnt;
        }
    }

    public void remove(int index){
        if(index < cnt){
            --cnt;
            for(int i = index; i < cnt; ++i){
                x[i]  = x[i + 1];
                y[i]  = y[i + 1];
                dx[i] = dx[i + 1];
                dy[i] = dy[i + 1];
            }
        }
    }

    public void reset(){
        cnt = 0;
    }

    public int at_x(int i){
        return (int)x[i];
    }

    public int at_y(int i){
        return (int)y[i];
    }

    public void move_all(){
        for(int i = 0; i < cnt; ++i){
            x[i] += (short)dx[i];
            y[i] += (short)dy[i];
        }
    }

    public int count() {
        return cnt;
    }
}
