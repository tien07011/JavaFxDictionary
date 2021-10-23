package DictionaryApplication.Controllers;

import DictionaryApplication.Alerts.Alerts;
import DictionaryApplication.DictionaryCommandLine.Dictionary;
import DictionaryApplication.DictionaryCommandLine.DictionaryManagement;
import DictionaryApplication.DictionaryCommandLine.Word;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ViewSearch implements Initializable {
    @FXML
    private Button delete, save, sound;
    @FXML
    private Label tutienganh, khongcotuhienthi;
    @FXML
    private ListView<String> listviewsearch;
    @FXML
    private TextArea nghiacuatu;
    @FXML
    private TextField search_english;

    private Dictionary dictionary = new Dictionary();
    private DictionaryManagement dictionaryManagement = new DictionaryManagement();
    public final static String path = "src/main/resources/Utils/test.txt";
    private Alerts alerts = new Alerts();
    private int indexOfWord;

    ObservableList<String> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dictionaryManagement.insertFromFile(dictionary, path);
        for (int i = 0; i < 20; ++i) {
            list.add(dictionary.get(i).getWord_target());
        }
        listviewsearch.setItems(list);
        khongcotuhienthi.setVisible(false);
        sound.setVisible(false);
        delete.setVisible(false);
        save.setVisible(false);
        search_english.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String key = search_english.getText().trim();
                if (key.isEmpty()) {
                    System.out.println("in ra list 20");
                    list.clear();
                    for (int i = 0; i < 20; ++i) {
                        list.add(dictionary.get(i).getWord_target());
                    }
                    listviewsearch.setItems(list);
                } else {
                    list.clear();
                    list = dictionaryManagement.lookupWord(dictionary, key);
                    listviewsearch.setItems(list);
                    if (list.isEmpty()) {
                        khongcotuhienthi.setVisible(true);
                    } else {
                        khongcotuhienthi.setVisible(false);
                    }
                }
                save.setVisible(false);
            }
        });
        listviewsearch.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String tam = listviewsearch.getSelectionModel().getSelectedItem();
                if (tam != null) {
                    //tra ve vi tri cua tu
                    indexOfWord = dictionaryManagement.searchWord(dictionary, tam);
                }
                tutienganh.setText(dictionary.get(indexOfWord).getWord_target());
                nghiacuatu.setText(dictionary.get(indexOfWord).getWord_explain());
                String key = tutienganh.getText().trim();
                String key1 = search_english.getText().trim();
                if (!key.isEmpty()) {
                    sound.setVisible(true);
                    delete.setVisible(true);
                }
                save.setVisible(false);
            }
        });
        sound.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String speakEnglish = tutienganh.getText().trim();
                System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
                Voice voice = VoiceManager.getInstance().getVoice("kevin16");
                if (voice != null) {
                    voice.allocate();
                    voice.speak(speakEnglish);
                } else {
                    throw new IllegalStateException("Cannot find voice: kevin16");
                }
            }
        });
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert alertsConfirmation = alerts.alertsConfirmation("Cảnh báo", "Bạn có muốn xóa từ");
                Optional<ButtonType> optional = alertsConfirmation.showAndWait();
                int key = dictionaryManagement.searchWord(dictionary, tutienganh.getText().trim());
                if (optional.get() == ButtonType.OK) {
                    dictionaryManagement.removeWord(dictionary, key, path);
                }
                String keyword = search_english.getText().trim();
                if (!keyword.isEmpty()) {
                    list.clear();
                    list = dictionaryManagement.lookupWord(dictionary, keyword);
                    listviewsearch.setItems(list);
                } else {
                    list.clear();
                    for (int i = 0; i < 20; ++i) {
                        list.add(dictionary.get(i).getWord_target());
                    }
                    listviewsearch.setItems(list);
                }
                tutienganh.setText("");
                nghiacuatu.setText("");
            }
        });
        nghiacuatu.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                save.setVisible(true);
            }
        });
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String english_word = tutienganh.getText().trim();
                String updatetumoi = nghiacuatu.getText().trim();
                Word word = new Word(english_word, updatetumoi);
                int index = dictionaryManagement.searchWord(dictionary, english_word);
                dictionaryManagement.updateWord(dictionary, index, path, updatetumoi);
                save.setVisible(false);
            }
        });
    }
}
