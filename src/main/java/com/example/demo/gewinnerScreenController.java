package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import spiellogikPackage.Hauptspiel;

import java.io.IOException;

public class gewinnerScreenController {

    @FXML
    private Label gewinnerLabel;

    public void gewinnerAusgabe(){
        gewinnerLabel.setText(Hauptspiel.getSpielerListe().get(Hauptspiel.getAnzahlSpieler()).getName());
    }

    @FXML
    private void wechselZuStartmenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("startmenu.fxml"));
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

    @FXML
    private void wechselZuSpielfeld(ActionEvent event) throws IOException, InterruptedException {
        Hauptspiel.spielStarten();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("spielfeld.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        spielfeldController controller = loader.getController();
        controller.spielStartVorbereiten();
        stage.setResizable(false);
        stage.show();
    }
}
