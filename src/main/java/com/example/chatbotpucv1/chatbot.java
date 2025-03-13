package com.example.chatbotpucv1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class chatbot extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("chatbotview.fxml"));
        Scene scene = new Scene(root, 700, 700);

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/design.css")).toExternalForm());

        Image icon = new Image("logoppt.png");
        stage.getIcons().add(icon);
        stage.setTitle("Chatbot PUC");
        stage.setWidth(610);
        stage.setHeight(555);
        stage.setFullScreen(false);
        stage.setFullScreenExitHint("Para sair do modo tela cheia aperte ESC");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}