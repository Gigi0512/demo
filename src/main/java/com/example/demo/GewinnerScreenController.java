package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import spielerPackage.Echter_Spieler;
import spiellogikPackage.Hauptspiel;
import java.io.IOException;

import spielerPackage.*;
import highscoreTabelle.HSTabelle;

public class GewinnerScreenController {

    @FXML
    private Label gewinnerLabel;
    @FXML
    private Label gewinnerLabel1;

    public void gewinnerAusgabe(){
        gewinnerLabel.setText(Hauptspiel.getSpielerListe().get(1).getName());
        gewinnerLabel1.setText(Hauptspiel.getSpielerListe().get(1).getName());
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
        Hauptspiel.getStack().clear();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("highscore.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        HighscoreController controller = loader.getController();
        controller.startHST();
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void wechselZuSpielfeld(ActionEvent event) throws IOException {
        highscoreTabelleBefuellen();
        Hauptspiel.getStack().clear();
        Hauptspiel.spielStarten();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("spielfeld.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        SpielfeldController controller = loader.getController();
        controller.spielStartVorbereiten();
        stage.setResizable(false);
        stage.show();

        if (Hauptspiel.spielerAmZug() instanceof Computer_Spieler) {
            controller.computerZugAusfuehrenHandler(event);
        }
    }

    private void highscoreTabelleBefuellen(){
        //ruft für den gewinner (immer an der 2. Stelle derListe) die addWin Methode auf falls er Instanceof Echter Spieler ist.
        //erstelle Stream für die Liste und ruft für alle EchtenSpieler die nicht Gewinner sind die Methode addLose auf
        Spieler zweiterSpieler = Hauptspiel.getSpielerListe().get(1);
        if (zweiterSpieler instanceof Echter_Spieler) {
            HSTabelle.addWin(zweiterSpieler.getName());
        }
        Hauptspiel.getSpielerListe().stream()
                .filter(spieler -> spieler instanceof Echter_Spieler)
                .filter(spieler -> spieler != zweiterSpieler)
                .map(Spieler::getName)
                .toList()
                .forEach(HSTabelle::addLose);
    }
}
