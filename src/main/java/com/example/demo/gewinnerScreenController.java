package com.example.demo;

import highscoreTabelle.HSTabelle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import spielerPackage.Computer_Spieler;
import spielerPackage.Echter_Spieler;
import spiellogikPackage.Hauptspiel;

import java.io.IOException;

public class gewinnerScreenController {

    @FXML
    private Label gewinnerLabel;

    public void gewinnerAusgabe(){
        gewinnerLabel.setText(Hauptspiel.getSpielerListe().get(1).getName());
    }

    @FXML
    private void wechselZuStartmenu(ActionEvent event) throws IOException {
        highscoreTabelleBefuellen();
        Hauptspiel.getSpielerListe().clear();
        Hauptspiel.getStack().clear();

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
        highscoreTabelleBefuellen();
        Hauptspiel.getSpielerListe().clear();

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
        highscoreTabelleBefuellen();
        Hauptspiel.getStack().clear();
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

    private void highscoreTabelleBefuellen(){

        if (Hauptspiel.getSpielerListe().get(1) instanceof Echter_Spieler) {
            HSTabelle.addWin(Hauptspiel.getSpielerListe().get(1).getName());
            Hauptspiel.getSpielerListe().remove(1);
        }

        for (int i = 0; i < Hauptspiel.getSpielerListe().size(); i++) {
            if (Hauptspiel.getSpielerListe().get(i) instanceof Computer_Spieler){
                Hauptspiel.getSpielerListe().remove(i);
            }
        }

        for (int i = 0; i < Hauptspiel.getAnzahlSpieler(); i++){
            HSTabelle.addLose(Hauptspiel.getSpielerListe().get(i).getName());
        }
    }


}
