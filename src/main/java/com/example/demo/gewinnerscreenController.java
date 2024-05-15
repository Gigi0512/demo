package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import spiellogikPackage.Hauptspiel;

import java.awt.*;
import java.io.IOException;

public class gewinnerscreenController {

    @FXML
    private Label gewinnerName;

    public void gewinnerAusgeben(){
        gewinnerName.setText(Hauptspiel.getSpielerListe().get(Hauptspiel.getAnzahlSpieler()).getName());
    }

    // start knopf:
    @FXML
    private void wechselZuspielfeld(ActionEvent event) throws IOException {
        Hauptspiel.spielStarten();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("spielfeld.fxml"));
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
    private void wechselstartmenu(ActionEvent event) throws IOException {
        Hauptspiel.resetSpielerListe();


        FXMLLoader loader = new FXMLLoader(getClass().getResource("startmenu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
