package vartank;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class DlgFrame_keyAdapter extends KeyAdapter {
    private DlgFrame adaptee;
    DlgFrame_keyAdapter(DlgFrame adaptee) {
        this.adaptee = adaptee;
    }

    public void keyPressed(KeyEvent e) {
        adaptee.game_keyPressed(e);
    }

    public void keyReleased(KeyEvent e){
        adaptee.game_keyReleased(e);
    }
}

