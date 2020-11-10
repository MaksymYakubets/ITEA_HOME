package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class NewFrame extends JFrame {
    private JLabel labelUa;
    private JLabel labelEn;
    private JLabel labelDe;
    private JButton buttonUa;
    private JButton buttonEn;
    private JButton buttonDe;
    private JButton buttonSave;
    private JButton buttonLoad;
    private String currentLocale = "ua"; // set default locale


    public NewFrame() {
        super("LANGUAGE");

        buttonUa = createSwitchButton("ua.png", "ua");
        buttonEn = createSwitchButton("en.png", "en");
        buttonDe = createSwitchButton("de.png", "de");
        labelUa = new JLabel("", SwingConstants.CENTER);
        labelEn = new JLabel("", SwingConstants.CENTER);
        labelDe = new JLabel("", SwingConstants.CENTER);
        buttonSave = createSaveButton();
        JLabel emptyLabel = new JLabel();
        buttonLoad = createLoadButton();
        switchLocale("ua"); // default switchLocale

        GridLayout gridLayout = new GridLayout(3, 3);
        setLayout(gridLayout); //
        add(labelUa);
        add(labelEn);
        add(labelDe);
        add(buttonUa);
        add(buttonEn);
        add(buttonDe);
        add(buttonSave);
        add(emptyLabel);
        add(buttonLoad);

        setLocationRelativeTo(null);
        setSize(350, 100);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }


    private void switchLocale(String locale) {
        switch (locale) {
            case "en":
                labelUa.setText("Ukrainian");
                labelEn.setText("English");
                labelDe.setText("German");
                break;
            case "de":
                labelUa.setText("Ukrainische");
                labelEn.setText("Englishce");
                labelDe.setText("Deutch");
                break;
            case "ua":
            default:
                labelUa.setText("Українська");
                labelEn.setText("Англійська");
                labelDe.setText("Німецька");
        }
    }

    private JButton createSwitchButton(String iconPathName, String locale) {
        Image buttonIcon = null;
        try {
            buttonIcon = ImageIO.read(new File(iconPathName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JButton button = new JButton(new ImageIcon(buttonIcon));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchLocale(locale);
                currentLocale = locale;
            }
        });

        return button;
    }

    private JButton createSaveButton() {
        JButton button = new JButton("Save");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Properties properties = new Properties();
                properties.setProperty("locale", currentLocale);
                try {
                    properties.store(new FileOutputStream("frame.properties"), "saved");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        return button;
    }

    private JButton createLoadButton() {
        JButton button = new JButton("Load");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Properties properties = new Properties();
                try {
                    properties.load(new FileInputStream("frame.properties"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                currentLocale = properties.getProperty("locale");
                switchLocale(currentLocale);
            }
        });
        return button;
    }
}
