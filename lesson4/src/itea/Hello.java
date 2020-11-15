package itea;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import javax.swing.*;

public class Hello {

    public static void main(String[] args) {
        new itea.Hello();
    }


    public Hello() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("SMILE");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new GuiPanel());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

            }
        });
    }

    public class GuiPanel extends JPanel {

        private JTextArea textMoskal;

        public GuiPanel() {

            URL url = this.getClass().getClassLoader().getResource("foto.jpg");
            JLabel label = new JLabel(new ImageIcon(url));
            add(label);



            JButton button = new JButton("Тисни");
            add(button);
            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    Reader reader2 = null;
                    try {
                        reader2 = new InputStreamReader(new FileInputStream("anekdot.txt"), "UTF-8");
                        textMoskal.read(reader2, "В цьому місці могла бути ваша реклама");
                    } catch (Exception exp) {
                        exp.printStackTrace();
                    } finally {
                        try {
                            reader2.close();
                        } catch (Exception exp) {
                        }
                    }
                }
            });

            textMoskal = new JTextArea(20, 60);
            textMoskal.setFont(new Font("AnekDota", Font.PLAIN, 16));
            add(new JScrollPane(textMoskal));

        }

    }
}