package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class startmenuController {

    // start knopf:
    @FXML
    private void wechselZuSpielerauswahl(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("spielerauswahl.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    @FXML
    private void wechselZuHighscoretabelle(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("highscore.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        highscoreController controller = loader.getController();
        controller.startHST();
        stage.setResizable(false);
        stage.show();
    }
}
