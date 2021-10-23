package DictionaryApplication.Alerts;

import javafx.scene.control.Alert;

public class Alerts {
    // hiển thị để yêu cầu người dùng xác nhận một hành động sẽ được thực hiện hoặc không.
    // Mặc định Confirmation Alert sẽ có 2 lựa chọn cho người dùng là OK hoặc Cancel.
    public Alert alertsConfirmation(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        return alert;
    }

    public Alert alertsInformation(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
        return alert;
    }
}
