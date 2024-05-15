package com.example.demo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import spiellogikPackage.Hauptspiel;
import spielerPackage.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class SpielfeldController {

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
    private Button addID;
    @FXML
    private Button sumID;


    public void wechselZuGewinnerScreenHandler(Event event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("gewinnerScreen.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        GewinnerScreenController controller = loader.getController();
        controller.gewinnerAusgabe();
        stage.setResizable(false);
        stage.show();
    }

    public void spielStartVorbereiten()  {//legt den Text für den Spieler am Zug fest
        spielerAmZug.setText(Hauptspiel.spielerAmZug().getName());
    }

    public void spielfeldAktualisieren() {//bildet das stack graphisch dar
        ArrayList<Label> felder = new ArrayList<>(Arrays.asList(eins, zwei, drei, vier, fuenf, sechs));
        for (int i = 0; i < 6; i++) {
            if (i < Hauptspiel.getStack().size()) {
                felder.get(i).setText(String.valueOf(Hauptspiel.getStack().get(i)));
            } else {
                felder.get(i).setText("");
            }
        }
        eingabe.clear();
        spielStartVorbereiten();
    }

    @FXML
    public void eingabeAusfuehrenAdd(ActionEvent event) throws IOException {
        eingabeAusfuehrenAddHandler(event);
    }

    @FXML
    public void eingabeAusfuehrenAddEnter(KeyEvent event) throws  IOException {
        if (event.getCode() == KeyCode.ENTER) {
            eingabeAusfuehrenAddHandler(event);}
    }

    public void eingabeAusfuehrenAddHandler(Event event) throws IOException {
        //fügt eine Zahl nach gültigkeitsüberprüfung dem Stack hinzu
        try {
            if (eingabe.getText().isEmpty() || eingabe.getText().length() != 1 || eingabe.getText().equals("0") || Hauptspiel.getStack().size() == 6) {
                error.setVisible(true);
                error.setText("Keine gültige Eingabe.");
                return;
            }
            error.setVisible(false);

            Hauptspiel.addNumber(Integer.parseInt(eingabe.getText()));
            spielfeldAktualisieren();

            if (Hauptspiel.spielerAmZug() instanceof Computer_Spieler) {
                computerZugAusfuehrenHandler(event);
            }
        } catch (NumberFormatException e) {
            error.setVisible(true);
            error.setText("Keine gültige Eingabe.");
        }
    }


    @FXML
    public void eingabeAusfuehrungSum(ActionEvent event) throws IOException {
        //summiert die obersten 2 Zahlen falls möglich zusammen

        if (Hauptspiel.getStack().size() < 2 || Hauptspiel.getRundenAnzahl() < 4) {
            error.setVisible(true);
            error.setText("Aufsummieren ist nicht möglich.");
            return;
        }
        error.setVisible(false);
        Hauptspiel.summingUp();
        spielfeldAktualisieren();

        if (Hauptspiel.getPlayerWon()) {
            Hauptspiel.setPlayerWon(false);
            wechselZuGewinnerScreenHandler(event);
        }

        if (Hauptspiel.spielerAmZug() instanceof Computer_Spieler) {
            computerZugAusfuehren(event);
        }
    }

    public void computerZugAusfuehren(ActionEvent event) throws IOException {
        computerZugAusfuehrenHandler(event);
    }

    public void computerZugAusfuehrenHandler(Event event) throws IOException {
        //legt vorgehen für Zug des Computerspielers fest
        //deaktivierung der eingabebuttons
        //Thread mit sleep um Bedenkzeit des Computers zu symbolisieren
        new Thread(() -> {
            try {
                addID.setDisable(true);
                sumID.setDisable(true);
                eingabe.setDisable(true);
                Thread.sleep(2200);
                Platform.runLater(() -> {
                    if (Hauptspiel.getAnzahlSpieler() > 2) {
                        Hauptspiel.addNumber(((Computer_Spieler)Hauptspiel.spielerAmZug()).zugMachen_Multiplayer());
                    } else {
                        Hauptspiel.addNumber(((Computer_Spieler)Hauptspiel.spielerAmZug()).zugMachen_1vs1());
                    }
                    //zug jenachdem ob 1v1 oder Multiplayer
                    //erneute aktivierung der Buttons
                    error.setVisible(false);
                    spielfeldAktualisieren();
                    addID.setDisable(false);
                    sumID.setDisable(false);
                    eingabe.setDisable(false);

                    try {//Überprüfung ob Spiel zu ende
                        if (Hauptspiel.getPlayerWon()) {
                            Hauptspiel.setPlayerWon(false);
                            wechselZuGewinnerScreenHandler(event);
                        }
                        if (Hauptspiel.spielerAmZug() instanceof Computer_Spieler) {
                            //überprüft ob nach computerspieler erneut computerspieler am Zug ist
                        computerZugAusfuehrenHandler(event);
                    }
                    }catch (IOException e){}});

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

