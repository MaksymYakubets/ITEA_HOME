package ua.itea;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/*
Создать обьекты под помник где мы подключались к бд(вложение в resources), и розпарсить помник на обьекты при помощи SAXParser.
 */
public class App {
    public static void main(String[] args) {

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        POMParser pomParser = new POMParser();
        try {
            SAXParser saxParser = null;
            try {
                saxParser = saxParserFactory.newSAXParser();
            } catch (org.xml.sax.SAXException e) {
                e.printStackTrace();
            }
            try {
              saxParser.parse(App.class.getClassLoader().getResourceAsStream("pom.xml"), pomParser);
            } catch (org.xml.sax.SAXException e) {
                e.printStackTrace();
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}