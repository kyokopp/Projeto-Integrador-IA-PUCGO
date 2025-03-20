package com.example.chatbotpucv1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class chatbot extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            // Carrega o arquivo FXMl, e informa caso não seja possivel
            URL fxmlUrl = getClass().getResource("chatbotview.fxml");
            if (fxmlUrl == null) {
                throw new IOException("Não foi possível encontrar o arquivo: chatbotview.fxml");
            }

            Parent root = FXMLLoader.load(fxmlUrl);
            Scene scene = new Scene(root);

            // Tenta carregar o arquivo css, e informa caso não seja possivel
            try {
                URL cssUrl = getClass().getResource("/styles/design.css");
                if (cssUrl != null) {
                    String css = cssUrl.toExternalForm();
                    scene.getStylesheets().add(css);
                } else {
                    System.out.println("Arquivo CSS não encontrado !. Design padrão será ultilizado no lugar.");
                }
            } catch (Exception e) {
                System.err.println("Erro ao carregar o arquivo CSS: " + e.getMessage());
            }
            // Tenta carregar o ícone
            try {
                URL iconUrl = getClass().getResource("/styles/logoficial.png");
                if (iconUrl != null) {
                    Image icon = new Image(iconUrl.toExternalForm());
                    stage.getIcons().add(icon);
                } else {
                    System.out.println("Erro, falha ao encontrar o icone. Icone padrão será ultilizado no lugar.");
                }
            } catch (Exception e) {
                System.err.println("Erro ao carregar o Icone: " + e.getMessage());
            }

            // Configura o Stage
            stage.setTitle("Chatbot PUC");
            stage.setWidth(610);
            stage.setHeight(555);
            stage.setResizable(true);
            stage.setMinWidth(400);
            stage.setMinHeight(300);
            stage.setFullScreen(false);
            stage.setFullScreenExitHint("Para sair do modo tela cheia aperte ESC");

            // Seta a Scene e mostra o Stage
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            showErrorAlert("Erro no app", "Falha ao iniciar o aplicativo",
                    "Detalhes do erro: " + e.getMessage());
            throw e;
        }
    }
        // error handling
    private void showErrorAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}