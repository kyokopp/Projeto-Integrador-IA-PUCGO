module com.example.chatbotpucv1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.chatbotpucv1 to javafx.fxml;
    exports com.example.chatbotpucv1;
}