package vartank;


import com.mysql.jdbc.StringUtils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class VarTank {
    boolean packFrame = false;


    public VarTank() {
        DlgFrame frame = new DlgFrame();
        // Validate frames that have preset sizes
        // Pack frames that have useful preferred size info, e.g. from their layout
        if (packFrame) {
            frame.pack();
        } else {
            frame.validate();
        }

        // Center the window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.height) / 2);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        DBHandler dbHandler = new DBHandler();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getLookAndFeel());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                final JFrame frame = new JFrame("Реєстрація добровольця");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                Panel contentPane = new Panel();
                contentPane.setLayout(new GridLayout(0, 2));

                JLabel loginLabel = new JLabel("ВВЕДИ ЛОГІН", SwingConstants.CENTER);
                final JTextField loginField = new JTextField();
                final JLabel passwordLabel = new JLabel("ВВЕДИ ПАРОЛЬ", SwingConstants.CENTER);
                final JTextField passwordField = new JTextField();
                final JLabel otvet = new JLabel("",SwingConstants.CENTER);

                JButton button = new JButton("ГРАТИ");

                contentPane.add(loginLabel);
                contentPane.add(loginField);
                contentPane.add(passwordLabel);
                contentPane.add(passwordField);
                contentPane.add(button);
                contentPane.add(otvet);

                frame.setContentPane(contentPane);
                frame.setPreferredSize(new Dimension(500, 300));
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);

                otvet.setText("");
                otvet.setForeground(Color.RED);
                otvet.setFont(new Font("Serif", Font.PLAIN, 14));

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String loginText = loginField.getText().trim();
                        String passwordText = passwordField.getText().trim();

                        if (StringUtils.isNullOrEmpty(loginText) && StringUtils.isNullOrEmpty(passwordText)) {
                            otvet.setText("Введи логін і пароль!");
                            return;
                        }
                        User user = new User(loginText, passwordText);
                        boolean isExist = dbHandler.isPlayerExists(user);
                        if (isExist) {
                            new VarTank();
                            frame.dispose();
                        } else {
                            otvet.setText("Ти не вліз в танк! .. спробуй пізніше");
                        }

                    }

                });

            }

        });

    }

}
