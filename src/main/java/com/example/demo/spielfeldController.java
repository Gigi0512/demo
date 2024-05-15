package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import spiellogikPackage.Hauptspiel;
import spielerPackage.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class spielfeldController {

    @FXML
    private TextField eingabe;
    @FXML
    private Label spielerAmZug;
    @FXML
    private Label error;
    @FXML
    private Label eins;
    @FXML
    private Label zwei;
    @FXML
    private Label drei;
    @FXML
    private Label vier;
    @FXML
    private Label fuenf;
    @FXML
    private Label sechs;

    @FXML
    public void wechselZuGewinnerScreen(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("gewinnerScreen.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        gewinnerScreenController controller = loader.getController();
        controller.gewinnerAusgabe();
        stage.setResizable(false);
        stage.show();
    }


    public void spielStartVorbereiten() throws InterruptedException {
        spielerAmZug.setText(Hauptspiel.spielerAmZug().getName());
        if (Hauptspiel.spielerAmZug() instanceof Computer_Spieler){
            //computerZugAusfuehren();
        }
    }

    @FXML
    public void spielfeldAktualisieren() throws InterruptedException {
        ArrayList<Label> felder = new ArrayList<>(Arrays.asList(eins, zwei, drei, vier, fuenf, sechs));
        for (int i = 0; i < 6; i++) {
            if (i < Hauptspiel.getStack().size()){
                felder.get(i).setText(String.valueOf(Hauptspiel.getStack().get(i)));
            }
            else {felder.get(i).setText("");}
        }
        eingabe.clear();
        spielStartVorbereiten();
        System.out.println(Hauptspiel.getSpielerListe().getFirst().getName());
    }

    public void eingabeAusfuehrenAdd(ActionEvent event) throws InterruptedException, IOException {

        if (eingabe.getText().isEmpty() || String.valueOf(Integer.parseInt(eingabe.getText())).length() != 1 || eingabe.getText().equals("0")){
            error.setVisible(true);
            error.setText("Keine gültige Eingabe.");
            return;
        }
        error.setVisible(false);
        Hauptspiel.addNumber(Integer.parseInt(eingabe.getText()));
        spielfeldAktualisieren();

        if (Hauptspiel.spielerAmZug() instanceof Computer_Spieler){
            computerZugAusfuehren(event);
        }
    }

    public void eingabeAusfuehrungSum(ActionEvent event) throws InterruptedException, IOException {

        if (Hauptspiel.getStack().size() < 2 || Hauptspiel.getRundenAnzahl() < 4){
            error.setVisible(true);
            error.setText("Aufsummieren ist nicht möglich.");
            return;
        }
        error.setVisible(false);
        Hauptspiel.summingUp();
        spielfeldAktualisieren();

        if (Hauptspiel.getPlayerWon()){
            Hauptspiel.setPlayerWon(false);
            wechselZuGewinnerScreen(event);
        }

        if (Hauptspiel.spielerAmZug() instanceof Computer_Spieler){
            computerZugAusfuehren(event);
        }
    }

    public void computerZugAusfuehren(ActionEvent event) throws InterruptedException, IOException {

        if (Hauptspiel.getAnzahlSpieler() > 2) {
            Hauptspiel.addNumber(Computer_Spieler.zugMachen_Multiplayer());
        }
        else {
            Hauptspiel.addNumber(Computer_Spieler.zugMachen_1vs1());
        }
        error.setVisible(false);
        spielfeldAktualisieren();

        if (Hauptspiel.getPlayerWon()){
            Hauptspiel.setPlayerWon(false);
            wechselZuGewinnerScreen(event);
        }
    }
}

