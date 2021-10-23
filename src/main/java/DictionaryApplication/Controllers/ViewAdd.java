package DictionaryApplication.Controllers;

import DictionaryApplication.Alerts.Alerts;
import DictionaryApplication.DictionaryCommandLine.Dictionary;
import DictionaryApplication.DictionaryCommandLine.DictionaryManagement;
import DictionaryApplication.DictionaryCommandLine.Word;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class ViewAdd implements Initializable {
    private Dictionary dictionary = new Dictionary();
    private DictionaryManagement dictionaryManagement = new DictionaryManagement();
    private Alerts alerts = new Alerts();
    private String path = "src/main/resources/Utils/test.txt";
    @FXML
    private Button addButton;
    @FXML
    private TextField word_target_input;
    @FXML
    private TextArea word_explain_input;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dictionaryManagement.insertFromFile(dictionary, path);

        if (word_target_input.getText().isEmpty() && word_explain_input.getText().isEmpty()) {
            addButton.setDisable(true);
        }
        word_target_input.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (!word_target_input.getText().isEmpty() && !word_explain_input.getText().isEmpty()) {
                    addButton.setDisable(false);
                } else {
                    addButton.setDisable(true);
                }
            }
        });
        word_explain_input.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (!word_target_input.getText().isEmpty() && !word_explain_input.getText().isEmpty()) {
                    addButton.setDisable(false);
                } else {
                    addButton.setDisable(true);
                }
            }

        });
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clickaddButton();
            }
        });
    }

    private void clickaddButton() {
        String word_target = word_target_input.getText().trim();
        String word_explain = word_explain_input.getText().trim();
        Alert alertsConfirmation = alerts.alertsConfirmation("Cảnh báo", "Bạn có muốn thêm từ");
        Optional<ButtonType> optional = alertsConfirmation.showAndWait();

        if (optional.get() == ButtonType.OK) {
            if (dictionaryManagement.searchWord(dictionary, word_target) != -1) {
                Alert selectionAlert = alerts.alertsConfirmation("Thông báo", "Từ này đã có trong từ điển");
                selectionAlert.getButtonTypes().clear();
                ButtonType thaythe = new ButtonType("Thay thế");
                ButtonType bosung = new ButtonType("Bổ sung");
                selectionAlert.getButtonTypes().addAll(thaythe, bosung, ButtonType.CANCEL);
                Optional<ButtonType> s1 = selectionAlert.showAndWait();
                int index = dictionaryManagement.searchWord(dictionary, word_target);
                if (s1.get() == thaythe) {
                    dictionaryManagement.updateWord(dictionary, index, path, word_explain);
                    word_target_input.setText("");
                    word_explain_input.setText("");
                }
                if (s1.get() == bosung) {
                    dictionaryManagement.addupdateWord(dictionary, index, path, word_explain);
                    word_target_input.setText("");
                    word_explain_input.setText("");
                }
            } else {
                Alert alertsInformation = alerts.alertsInformation("Thông báo", " Từ đã được thêm");
                Word word = new Word(word_target, word_explain);
                dictionaryManagement.addWord(word, path);
            }
        }
        if (optional.get() == ButtonType.CANCEL) {
            Alert alertsInformation = alerts.alertsInformation("Cảnh báo", "Yêu cầu đã bị hủy");
            word_target_input.setText("");
            word_explain_input.setText("");
            word_target_input.setText("");
            word_explain_input.setText("");
        }

    }
}
