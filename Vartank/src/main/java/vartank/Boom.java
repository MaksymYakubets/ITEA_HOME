package vartank;

final class Boom {
    private int  x, y, start_row, start_col;
    private long time;

    public final static int CADR_COLS = 5;
    public final static int CADR_ROWS = 2;

    public Boom(int px, int py, int row){
        x         = px;
        y         = py;
        start_row = row;
        start_col = 0;
        time      = 0L;
    }

    public boolean updateFrame(long msec){
        if((msec - time) > 92L){
            time = msec;
            if(++start_col >= Boom.CADR_COLS){
                start_col = 0;
                if(++start_row >= Boom.CADR_ROWS)
                    return true;
            }
        }
        return false;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getRow(){
        return start_row;
    }

    public int getCol(){
        return start_col;
    }
}
