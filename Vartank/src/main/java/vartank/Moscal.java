package vartank;

import java.util.Vector;

// кацапські зайди
final class Moscal {
    private Vector<Integer> pos = null;
    private short   posx, posy;
    private short   info;
    private int     index;
    private long    tmove;
    private float   finc, fspeed;
    private boolean irun;

    public final static short ALIEN_A = 0;
    public final static short ALIEN_B = 1;
    public final static short ALIEN_C = 2;

    public Moscal(int x, int y, float speed, short type, short life){
        pos  = new Vector<Integer>();
        initialize(x, y, speed, type, life);
    }

    public void initialize(int x, int y, float speed, short type, short life){
        posx   = (short)x;
        posy   = (short)y;
        fspeed = speed;
        info   = (short)((type << 8) | life);
        index  = 0;
        tmove  = 0L;
        finc   = 0.0f;
        irun   = true;
    }

    public void run(){
        irun  = true;
        index = 0;
    }

    public int decLife(){
        int life = getLife() - 1;
        if(life <= 0)
            info &= 0xFF00;
        else
            info  = (short)((info & 0xFF00) | (short)life);
        return life;
    }

    public Vector<Integer> getArray(){
        return pos;
    }

    public int getX() {
        return (int)posx;
    }

    public int getY() {
        return (int)posy;
    }

    public int getLife(){
        return (int)(info & 0xFF);
    }

    public int getType(){
        return (int)(info >> 8);
    }

    public boolean isRun(){
        return irun;
    }

    public void destroy(){
        if(pos != null){
            pos.clear();
            pos = null;
        }
    }

    public boolean updateFrame(long msec){
        int px1, py1, px2, py2;

        if(! irun)
            return false;

        if((msec - tmove) > 10L){
            tmove = msec;

            if((index + 1) < pos.size()){
                px1  = (int) (pos.get(index) >> 16);
                py1  = (int) (pos.get(index) & 0xFFFF);
                px2  = (int) (pos.get(index + 1) >> 16);
                py2  = (int) (pos.get(index + 1) & 0xFFFF);
                posx = (short) ((float) (px2 - px1) * finc + (float) px1);
                posy = (short) ((float) (py2 - py1) * finc + (float) py1);
            }

            finc += fspeed;
            if(finc >= 1.0f){
                finc = 0.0f;
                if (++index >= pos.size()) {
                    irun  = false;
                    index = 0;
                    return true;
                }
            }
        }
        return false;
    }
};
