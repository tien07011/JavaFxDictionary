package DictionaryApplication.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewApp implements Initializable {
    @FXML
    private Button searchButton, addButton, transButton;
    @FXML
    private Tooltip searchTooltip, addTooltip, transTooltip;
    @FXML
    private AnchorPane container;

    private void setNode(Node node) {
        container.getChildren().clear();
        container.getChildren().add((Node) node);

    }

    @FXML
    private void showComponent(String path) {
        try {
            AnchorPane Component = FXMLLoader.load(getClass().getResource(path));
            setNode(Component);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showComponent("/Views/View_Search.fxml");
        searchTooltip.setShowDelay(Duration.seconds(1));
        transTooltip.setShowDelay(Duration.seconds(1));
        addTooltip.setShowDelay(Duration.seconds(1));

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showComponent("/Views/View_Search.fxml");
            }
        });
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showComponent("/Views/View_Add.fxml");
            }
        });
        transButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showComponent("/Views/View_Trans.fxml");
            }
        });
    }

}
