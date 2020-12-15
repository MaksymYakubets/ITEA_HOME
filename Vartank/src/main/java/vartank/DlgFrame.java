package vartank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.awt.Color;


public class DlgFrame extends JFrame {
    JPanel pane;
    Image ilogo = null;
    Font font = null;
    xUserTank user_game = null;
    XField field_game = null;
    Timer timer_update = null;
    int level = 0;
    int state = 0;
    boolean ilevel = false;
    long lvltime = 0L;

    private final static int GAME_START = 1;
    private final static int GAME_PLAY = 2;
    private final static int GAME_OVER = 3;
    private final static int GAME_WINNER = 4;

    public DlgFrame() {
        try {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            jbInit();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Component initialization.
     *
     * @throws java.lang.Exception
     */
    private void jbInit() throws Exception {
        pane = (JPanel) getContentPane();
        pane.setLayout(null);

        setSize(new Dimension(640, 512 + 20));
        setResizable(false);
        setTitle("VarTank");
        setBackground(Color.WHITE);

        font = new Font("Arial", Font.PLAIN, 24);
        ilogo = javax.imageio.ImageIO.read(this.getClass().getClassLoader().getResource("image/moscal.jpg"));
        state = GAME_START;

        field_game = new XField();
        if (!field_game.Create(640, 480)) {
            JOptionPane.showMessageDialog(this, "Помилка при створенні полотна !", "Помилка", JOptionPane.ERROR_MESSAGE);
            return;
        }

        user_game = new xUserTank();

        ActionListener calltimer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DlgFrame.this.timer();
            }
        };
        timer_update = new Timer(10, calltimer);
        timer_update.start();
        pane.setBorder(null);
        pane.setDoubleBuffered(false);
        addKeyListener(new DlgFrame_keyAdapter(this));
    }

    public void timer() {
        this.repaint();
    }

    @Override
    public void paint(Graphics dc) {
        Font ftmp;
        Graphics2D dc2;
        long time = System.currentTimeMillis();

        if (state == GAME_PLAY) {
            field_game.Erase();

            user_game.Draw(field_game.GetDC(), field_game, time);
            field_game.Draw(time, user_game);
            if (ilevel) {
                dc2 = field_game.GetDC();
                ftmp = dc2.getFont();
                dc2.setFont(font);
                dc2.setColor(Color.YELLOW);
                dc2.drawString("РІВЕНЬ - " + (level + 1), getWidth() / 2 - 60, getHeight() / 2 - 60);
                dc2.setFont(ftmp);
                if ((time - lvltime) > 1500L) {
                    lvltime = time;
                    ilevel = false;
                }
            }

            dc.drawImage(field_game.Handle(), 0, 29, null);

            dc.setColor(Color.WHITE);
            dc.fillRect(0, 512, getWidth(), 14);
            dc.setColor(Color.BLUE);
            dc.drawString("ЖИТТЯ: " + user_game.getLife(), 10, getHeight() - 8);
            dc.drawString("РІВЕНЬ: " + (level + 1), getWidth() - 80, getHeight() - 8);

            field_game.IsBulletsCollision(user_game);
            field_game.IsCollision(user_game);

            if (user_game.getLife() < 0) {
                field_game.Stop();
                user_game.Stop();
                dc.setColor(Color.WHITE);
                dc.fillRect(0, 0, getWidth(), getHeight());
                state = GAME_OVER;
            }
            if (field_game.getCountAliens() == 0) {
                if (!field_game.StartLevel(++level, user_game)) {
                    field_game.Stop();
                    user_game.Stop();
                    state = GAME_WINNER;
                }
                ilevel = true;
                lvltime = System.currentTimeMillis();
            }

        } else if (state == GAME_START) {
            dc2 = field_game.GetDC();
            dc2.setColor(Color.WHITE);
            dc2.fillRect(0, 0, getWidth(), getHeight());
            dc2.drawImage(ilogo, (getWidth() - 300) / 2, 100, 300, 200, null);
            dc2.setColor(Color.RED);
            dc2.setFont(new Font("Arial", Font.PLAIN, 20));
            dc2.drawString("Ваша ціль - знищити окупантів", 180, 50);
            ftmp = dc.getFont();
            dc2.setFont(font);
            dc2.setColor(Color.BLUE);
            dc2.drawString("Для гри натисніть ENTER", 170, 400);
            dc2.setColor(Color.GREEN);
            dc2.setFont(new Font("Arial", Font.PLAIN, 18));
            dc2.drawString("Управління в грі: клавіші вліво, вправо, вниз, вгору, стріляти-пробіл", 40, 450);

            dc.drawImage(field_game.Handle(), 0, 29, null);
            dc.setColor(Color.WHITE);
            dc.fillRect(0, 512, getWidth(), 14);

        } else if (state == GAME_OVER) {
            dc2 = (Graphics2D) field_game.GetDC();
            dc2.setColor(Color.WHITE);
            dc2.fillRect(0, 0, getWidth(), getHeight());
            dc2.drawImage(ilogo, (getWidth() - 300) / 2, 100, 300, 200, null);
            ftmp = dc2.getFont();
            dc2.setFont(font);
            dc2.setColor(Color.RED);
            dc2.drawString("ВАС ВБИЛИ МОСКАЛІ !!!", 180, 370);
            dc2.setColor(Color.GREEN);
            dc2.setFont(new Font("Arial", Font.PLAIN, 18));
            dc2.drawString("Почати спочатку - натисніть ENTER", 180, 400);
            dc2.setFont(ftmp);
            dc.drawImage(field_game.Handle(), 0, 29, null);
            dc.setColor(Color.WHITE);
            dc.fillRect(0, 512, getWidth(), 14);

        } else if (state == GAME_WINNER) {
            dc2 = (Graphics2D) field_game.GetDC();
            dc2.setColor(Color.WHITE);
            dc2.fillRect(0, 0, getWidth(), getHeight());
            dc2.drawImage(ilogo, (getWidth() - 300) / 2, 100, 300, 200, null);
            ftmp = dc2.getFont();
            dc2.setFont(font);
            dc2.setColor(Color.GREEN);
            dc2.drawString("* * * СЛАВА УКРАЇНІ, ВИ ПЕРЕМОГЛИ !!! * * *", 95, 370);
            dc2.setColor(Color.GREEN);
            dc2.setFont(new Font("Arial", Font.PLAIN, 18));
            dc2.drawString("Почати спочатку - натисніть ENTER", 180, 400);
            dc2.setFont(ftmp);
            dc.drawImage(field_game.Handle(), 0, 29, null);
            dc.setColor(Color.WHITE);
            dc.fillRect(0, 512, getWidth(), 14);
        }
        dc.dispose();
    }

    public void game_keyPressed(KeyEvent e) {
        if (state == GAME_PLAY)
            user_game.KeyPress(e.getKeyCode(), field_game);
        else if ((state == GAME_START) || (state == GAME_OVER) || (state == GAME_WINNER)) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                level = 0;
                ilevel = true;
                lvltime = System.currentTimeMillis();
                field_game.StartLevel(level, user_game);
                state = GAME_PLAY;
            }
        }
    }

    public void game_keyReleased(KeyEvent e) {
        if (state == GAME_PLAY)
            user_game.KeyUp(e.getKeyCode());
    }
}



