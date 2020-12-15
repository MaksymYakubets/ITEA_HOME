package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class SecondPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView rightImg;

    @FXML
    private ImageView leftImg;

    @FXML
    private ImageView heavenImg;

    @FXML
    void initialize() {
        assert rightImg != null : "fx:id=\"rightImg\" was not injected: check your FXML file 'app.fxml'.";
        assert leftImg != null : "fx:id=\"leftImg\" was not injected: check your FXML file 'app.fxml'.";
        assert heavenImg != null : "fx:id=\"heavenImg\" was not injected: check your FXML file 'app.fxml'.";

    }
}
