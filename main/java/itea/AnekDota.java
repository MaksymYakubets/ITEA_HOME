package itea;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;


public class AnekDota {


    public AnekDota() {
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
                //      frame.add(new GuiPanel());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

            }
        });
    }

    public class GuiPanel extends JPanel {

        private JTextArea textJudah;
        private JTextArea textIsaev;


        public GuiPanel() {

            add(new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("img/Bin.gif"))));

            JButton button = new JButton("Таки анекоды");
            add(button);
            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    InputStream resourceInputStream = this.getClass().getClassLoader().getResourceAsStream("txt/anekdot1.txt");
                    StringBuilder sb = new StringBuilder();
                    BufferedReader br = null;
                    try {
                        br = new BufferedReader(new InputStreamReader(resourceInputStream, "UTF-8"));
                    } catch (UnsupportedEncodingException unsupportedEncodingException) {
                        unsupportedEncodingException.printStackTrace();
                    }
                    String textLine = null;
                    while (true) {
                        try {
                            if (!((textLine = br.readLine()) != null)) break;
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        sb.append(textLine);
                        sb.append("\n");
                    }
                    textJudah.setText(sb.toString());

                }
            });

            textJudah = new JTextArea(20, 40);
            textJudah.setFont(new Font("AnekDota", Font.PLAIN, 14));
            add(new JScrollPane(textJudah));


            JLabel label2 = new JLabel(new ImageIcon("Carry.gif")); // ITEA_HOME\MavenGui
            add(label2);

            JButton button2 = new JButton("...и немного Штирлица");
            add(button2);
            button2.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    Reader reader2 = null;
                    try {
                        reader2 = new InputStreamReader(new FileInputStream("anekdot2.txt"), "UTF-8"); // ITEA_HOME\MavenGui
                        textIsaev.read(reader2, "В цьому місці могла бути ваша реклама");
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

            textIsaev = new JTextArea(20, 40);
            textIsaev.setFont(new Font("AnekDota2", Font.PLAIN, 14));
            add(new JScrollPane(textIsaev));

        }


    }
}