package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.StringUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button auhButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField loginField;

    @FXML
    private Label resultLabel;

    @FXML
    private ImageView kozak;

    @FXML
    private ImageView divka;

    private final DBHandler dbHandler = new DBHandler();

    @FXML
    void initialize() {
        auhButton.setOnAction(actionEvent -> {

                    String loginText = loginField.getText();
                    String loginPassword = passwordField.getText();
                    if (StringUtils.isNullOrEmpty(loginText) && StringUtils.isNullOrEmpty(loginPassword)) {
                        resultLabel.setVisible(true);
                        resultLabel.setText(" Перш ніж клацать, впихни хоч щось  в ці поля !");
                        divka.setVisible(true);
                        return;
                    }
                    loginUser(loginText, loginPassword);
                }
        );
    }

    private void loginUser(String loginText, String loginPassword) {
        User user = new User(loginText, loginPassword);

        boolean isExist = dbHandler.isPlayerExists(user);
        if (isExist) {
            System.out.println("Success");
            auhButton.getScene().getWindow().hide();
            openSecondWindow();
        } else {
            resultLabel.setVisible(true);
            resultLabel.setText("Рабів до раю не пускають! Введи вірний пароль!");
            kozak.setVisible(true);
        }
    }


    private void openSecondWindow() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/app.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}




