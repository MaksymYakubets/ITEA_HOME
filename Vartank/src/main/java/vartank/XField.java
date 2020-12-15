package vartank;
import java.awt.image.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.sound.sampled.Clip;

//ігрове  поле
public class XField {
    private BufferedImage    dblbuf;
    private BufferedImage    icopy;
    private BufferedImage    ialien;
    private Graphics2D       dcbuf;
    private Graphics2D       dcboom;
    private BufferedImage    tiles;
    private BufferedImage    iboom;
    private char[]           field;
    private int              fwidth;
    private int              fheight;
    private int              dimboom;
    private Clip             sboom, scrash, sbron;

    private Vector<String>     levels;
    private LinkedList<Boom>   booms;
    private LinkedList<Moscal> aliens;



    public final static char OPEN_PATH   = '0';
    public final static char WOOD_PATH   = '1';
    public final static char BRICK_PATH  = '2';
    public final static char BLOCK_PATH  = '3';
    public final static char BASE_PATH   = '4';
    public final static char ALIEN_BEGIN = '5';
    public final static char ALIEN_END   = '7';
    public final static char USER_PATH   = '8';
    public final static int  CELL_SIZE   = 32;

    public XField() {
        dblbuf  = null;
        icopy   = null;
        ialien  = null;
        dcbuf   = null;
        dcboom  = null;
        tiles   = null;
        field   = null;
        fwidth  = fheight = 0;
        dimboom = 0;
        sboom   = null;
        scrash  = null;
        sbron   = null;
        levels  = null;
        booms   = null;
        aliens  = null;
    }

    public boolean Create(int width, int height) {
        int        ch;
        String     buf = "";
        InputStream fp = null;
        dblbuf = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        icopy  = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        dcbuf  = dblbuf.createGraphics();
        dcboom = icopy.createGraphics();

        fwidth  = width  / XField.CELL_SIZE;
        fheight = height / XField.CELL_SIZE;
        field   = new char[fwidth * fheight];
        booms   = new LinkedList<Boom>();
        aliens  = new LinkedList<Moscal>();
        levels  = new Vector<String>();

      /*  sboom   = xUtil.LoadSound(this.getClass().getResource("image/boom.wav"));
        scrash  = xUtil.LoadSound(this.getClass().getResource("image/crash.wav"));
        sbron   = xUtil.LoadSound(this.getClass().getResource("image/bron.wav"));*/

        sboom   = xUtil.LoadSound(this.getClass().getClassLoader().getResource("image/boom.wav"));         //переделал
        scrash  = xUtil.LoadSound(this.getClass().getClassLoader().getResource("image/crash.wav"));
        sbron   = xUtil.LoadSound(this.getClass().getClassLoader().getResource("image/bron.wav"));

        try {
            ialien = xUtil.ToClip32Bits(this.getClass().getClassLoader().getResource("image/test_p.png"));                      // image/aliens.jpg
            iboom  = xUtil.ToClip32Bits(this.getClass().getClassLoader().getResource("image/boom.jpg"));
            tiles  = xUtil.ToClip32Bits(this.getClass().getClassLoader().getResource("image/fons2.png"), 0xFFFF00FF);  // image/tiles.jpg
            fp     = this.getClass().getClassLoader().getResource("image/levels.au").openStream();
            buf    = "";
            do {
                ch = fp.read();
                if(ch == '+') {
                    levels.add(buf);
                    buf = "";
                } else if(Character.isDigit(ch))
                    buf += (char)ch;
            } while(ch != -1);

            fp.close();
            fp = null;
        } catch(IOException e){
           e.printStackTrace();
           return false;
        }

        dimboom = ((iboom.getWidth(null) / Boom.CADR_COLS) << 16) | (iboom.getHeight(null) / Boom.CADR_ROWS);
        return true;
    }


    public boolean StartLevel(int level, xUserTank user){
        float speed = 0.0f;
        short type  = 0, life = 0;

        for(int i = 0; i < aliens.size(); ++i){
            aliens.get(i).destroy();
            aliens.set(i, null);
        }
        aliens.clear();
        booms.clear();

        if(level >= levels.size())
            return false;

        String str = levels.get(level);
        for(int i = 0; i < str.length(); ++i)
            field[i] = str.charAt(i);

        int row;
        for(int r = 0; r < fheight; ++r){
            row = r * fwidth;
            for(int c = 0; c < fwidth; ++c){
                if(field[row + c] == XField.USER_PATH) {
                    field[row + c] = XField.OPEN_PATH;
                    user.Init(c * XField.CELL_SIZE, r * XField.CELL_SIZE);
                    r = fheight;
                    break;
                }
            }
        }


        for(int r = 0; r < fheight; ++r){
            row = r * fwidth;
            for(int c = 0; c < fwidth; ++c) {
                if((field[row + c] >= XField.ALIEN_BEGIN) && (field[row + c] <= XField.ALIEN_END)) {
                    switch(field[row + c]){
                    case XField.ALIEN_BEGIN:
                        speed = 0.02f;
                        type  = 0;
                        life  = 3;
                        break;
                    case XField.ALIEN_BEGIN + 1:
                        speed = 0.03f;
                        type  = 1;
                        life  = 4;
                        break;
                    case XField.ALIEN_BEGIN + 2:
                        speed = 0.034f;
                        type  = 2;
                        life  = 2;
                        break;
                    }
                    field[row + c] = XField.OPEN_PATH;

                    aliens.add(new Moscal(c * XField.CELL_SIZE, r * XField.CELL_SIZE, speed, type, life));
                    switch(aliens.getLast().getType()){
                    case Moscal.ALIEN_A:
                        xUtil.FindPathDFS(this, aliens.getLast().getX(), aliens.getLast().getY(), user.getX(), user.getY(), aliens.getLast().getArray());
                        break;
                    case Moscal.ALIEN_B:
                        xUtil.FindPathBFS(this, aliens.getLast().getX(), aliens.getLast().getY(), user.getX(), user.getY(), aliens.getLast().getArray());
                        break;
                    case Moscal.ALIEN_C:
                        xUtil.ShortestWave(this, aliens.getLast().getX(), aliens.getLast().getY(), user.getX(), user.getY(), aliens.getLast().getArray());
                        break;
                    }
                }
            }
        }

        this.update();
        return true;
    }


    public void Stop(){
        sboom.stop();
        scrash.stop();
        sbron.stop();
    }


    public int getCountAliens(){
        return aliens.size();
    }


    public boolean IsOpen(int x, int y){
        if((x >= 0 && x < fwidth) && (y > 0 && y < fheight))
            return (field[y * fwidth + x] <= XField.WOOD_PATH);
        return false;
    }

    public boolean IsClose(int x, int y){
        if((x >= 0 && x < fwidth) && (y > 0 && y < fheight))
            return (field[y*fwidth + x] > XField.WOOD_PATH);
        return false;
    }

    public char Offset(int x, int y){
        return field[y*fwidth + x];
    }


    public void PutChange(int x, int y, char val){
        field[y*fwidth + x] = val;
        x *= XField.CELL_SIZE;
        y *= XField.CELL_SIZE;
        dcboom.drawImage(icopy,  x, y, x + XField.CELL_SIZE, y + XField.CELL_SIZE,
                         0, 0, XField.CELL_SIZE, XField.CELL_SIZE, null);
    }


    public void AddBoom(int x, int y, int begin_row, boolean i_alien) {
        int  cx = dimboom >> 16;
        int  cy = dimboom & 0xFFFF;
        if(! i_alien) {
            x = x / XField.CELL_SIZE * XField.CELL_SIZE;
            y = y / XField.CELL_SIZE * XField.CELL_SIZE;
        }
        Boom bl = new Boom(x + (XField.CELL_SIZE - cx)/2, y + (XField.CELL_SIZE - cy)/2, begin_row);
        booms.add(bl);

        if(! i_alien) {
            PutChange(x / XField.CELL_SIZE, y / XField.CELL_SIZE, XField.OPEN_PATH);
            if(! sboom.isRunning()){
                sboom.setFramePosition(0);
                sboom.start();
            }
        } else {
            if (! scrash.isRunning()) {
                scrash.setFramePosition(0);
                scrash.start();
            }
        }
    }


    public void Draw(long time, xUserTank user){
        Boom bl;
        int row, px, py;

        if(! aliens.isEmpty()){

            Moscal alien;
            for(Iterator<Moscal> it = aliens.listIterator(); it.hasNext(); ){
                alien = it.next();
                dcbuf.drawImage(ialien, alien.getX(), alien.getY(), alien.getX() + XField.CELL_SIZE,
                                alien.getY() + XField.CELL_SIZE, alien.getType() * XField.CELL_SIZE, 0,
                                alien.getType() * XField.CELL_SIZE + XField.CELL_SIZE, XField.CELL_SIZE, null);

                if (alien.updateFrame(time)) {
                    switch (alien.getType()) {
                    case Moscal.ALIEN_A:
                        xUtil.FindPathDFS(this, alien.getX(), alien.getY(), user.getX(), user.getY(), alien.getArray());
                        break;
                    case Moscal.ALIEN_B:
                        xUtil.FindPathBFS(this, alien.getX(), alien.getY(), user.getX(), user.getY(), alien.getArray());
                        break;
                    case Moscal.ALIEN_C:
                        xUtil.ShortestWave(this, alien.getX(), alien.getY(), user.getX(), user.getY(), alien.getArray());
                        break;
                    }
                    alien.run();
                }
            }

        } else {
            // перемога і перехід на новий рівень
        }


        // виведення вибуху
        if(! booms.isEmpty()) {
            px = dimboom >> 16;
            py = dimboom & 0xFFFF;
            Iterator<Boom> prv, it = booms.iterator();
            while(it.hasNext()){
                prv = it;
                bl  = it.next();
                if(bl.updateFrame(time)){
                    prv.remove();
                    break;
                } else {
                    dcbuf.drawImage(iboom, bl.getX(), bl.getY(), bl.getX() + px, bl.getY() + py,
                                    bl.getCol()*px, bl.getRow()*py, bl.getCol()*px+px, bl.getRow()*py+py, null);
                }
            }
        }


        for(int y = 0; y < fheight; ++y){
            row = y*fwidth;
            for(int x = 0; x < fwidth; ++x){
                if(field[row + x] == XField.WOOD_PATH){
                    px = x * XField.CELL_SIZE;
                    py = y * XField.CELL_SIZE;
                    dcbuf.drawImage(tiles,  x * XField.CELL_SIZE, py, px + XField.CELL_SIZE, py + XField.CELL_SIZE,
                                    XField.CELL_SIZE, 0, XField.CELL_SIZE << 1, XField.CELL_SIZE, null);
                }
            }
        }
    }


    //перевірка зіткнення вареникомета з москалями
    public boolean IsCollision(xUserTank user){
        int res, rad;
        Moscal alien;
        Iterator<Moscal> prv, it = aliens.iterator();
        while(it.hasNext()){
            prv   = it;
            alien = it.next();
            res = (user.getX() - alien.getX())*(user.getX() - alien.getX()) + (user.getY() - alien.getY())*(user.getY() - alien.getY());
            rad = (user.getSize() + XField.CELL_SIZE-31) * (user.getSize() + XField.CELL_SIZE-31);
            if(res < rad){
                user.decLife();
                if(alien.decLife() == 0){
                    AddBoom(alien.getX(), alien.getY(), 0, true);
                    alien.destroy();
                    alien = null;
                    prv.remove();
                }
                return true;
            }
        }
        return false;
    }


    //перевірка зіткнення куль
    public boolean IsBulletsCollision(xUserTank user) {
        int ix, iy;
        Moscal alien;
        Iterator<Moscal> prv, it;
        Bullet bls = user.getBullets();

        for (int i = 0; i < bls.count(); ++i) {
            ix = bls.at_x(i);
            iy = bls.at_y(i);

            for (it = aliens.iterator(); it.hasNext(); ) {
                prv   = it;
                alien = it.next();
                if ((ix > alien.getX() && ix < alien.getX() + XField.CELL_SIZE) &&
                    (iy > alien.getY() && iy < alien.getY() + XField.CELL_SIZE)) {
                    bls.remove(i);
                    if (alien.decLife() == 0){
                        AddBoom(alien.getX(), alien.getY(), 0, true);
                        alien.destroy();
                        alien = null;
                        prv.remove();
                    } else {
                        if(! sbron.isRunning()){
                            sbron.setFramePosition(0);
                            sbron.start();
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }


    public Graphics2D GetDC() {
        return dcbuf;
    }

    public BufferedImage Handle() {
        return dblbuf;
    }

    public void Erase() {
        dcbuf.drawImage(icopy, 0, 0, null);
    }

    public int  GetWidth() {
        return fwidth;
    }

    public int GetHeight(){
        return fheight;
    }


    private void update(){
        int px, py, pos, index;
        for(int y = 0; y < fheight; ++y) {
            py = y * XField.CELL_SIZE;
            for(int x = 0; x < fwidth; ++x){
                px    = x * XField.CELL_SIZE;
                index = (int)(field[y*fwidth + x] - '0');
                if(index == 1)
                    --index;
                pos   = index * XField.CELL_SIZE;
                dcboom.drawImage(tiles,  x * XField.CELL_SIZE, py, px + XField.CELL_SIZE, py + XField.CELL_SIZE,
                             pos, 0, pos + XField.CELL_SIZE, XField.CELL_SIZE, null);
            }
        }
    }

}
