package DictionaryApplication.Controllers;


import DictionaryApplication.GoogleTranslateApi;
import com.gtranslate.Audio;
import com.gtranslate.Language;
import com.gtranslate.Translator;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javazoom.jl.decoder.JavaLayerException;

import java.util.logging.Level;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class ViewTrans implements Initializable {
    @FXML
    private TextArea transEnglish, transVn;
    @FXML
    private Button buttonTrans, speak_English, speak_Vn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (transEnglish.getText().isEmpty() && transVn.getText().isEmpty()) {
            buttonTrans.setDisable(true);
        }
        transEnglish.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (!transEnglish.getText().isEmpty()) {
                    buttonTrans.setDisable(false);
                } else {
                    buttonTrans.setDisable(true);
                }
            }
        });

        buttonTrans.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clicktransButton();
            }
        });
        speak_English.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String speakEnglish = transEnglish.getText().trim();
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
        speak_Vn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("chưa xong");

            }
        });

    }

    private void clicktransButton() {
        String word_english = transEnglish.getText().trim();
        GoogleTranslateApi googleTranslateApi = new GoogleTranslateApi();
        String word_vn = googleTranslateApi.google_Translate("vi", word_english);
        transVn.setText(word_vn);
    }


}
