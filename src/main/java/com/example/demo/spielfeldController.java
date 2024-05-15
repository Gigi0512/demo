package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import spiellogikPackage.Hauptspiel;

import java.util.Objects;

public class spielfeldController {

    @FXML
    private static TextField eingabe;
    @FXML
    private static Label spielerAmZug;
    @FXML
    private static Label error;
    @FXML
    private static Label eins;
    @FXML
    private static Label zwei;
    @FXML
    private static Label drei;
    @FXML
    private static Label vier;
    @FXML
    private static Label fuenf;
    @FXML
    private static Label sechs;

    public static void spielerAmZugAnzeigen(){
        spielerAmZug.setText(Hauptspiel.spielerAmZug().getName());
    }

    public void eingabeAusfuehren(ActionEvent actionEvent) {

        if (eingabe.getText().isEmpty() || String.valueOf(Integer.parseInt(eingabe.getText())).length() != 1 || eingabe.getText().equals("0")){
            error.setVisible(true);
            error.setText("Bitte gebe eine gültige Zahl ein.");
            return;
        }
        Hauptspiel.addNumber(Integer.parseInt(eingabe.getText()));
    }
}

