package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import spiellogikPackage.Hauptspiel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

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

    public void spielerAmZugAnzeigen(){
        spielerAmZug.setText(Hauptspiel.spielerAmZug().getName());
    }

    public void felderAktualisieren(){
        ArrayList<Label> felder = new ArrayList<Label>(Arrays.asList(eins, zwei, drei, vier, fuenf, sechs));
        for (int i = 0; i < 6; i++){
            felder.get(i).setText(String.valueOf(Hauptspiel.getStack().get(i)));
        }
    }

    public void eingabeAusfuehren(ActionEvent actionEvent) {

        if (eingabe.getText().isEmpty() || String.valueOf(Integer.parseInt(eingabe.getText())).length() != 1 || eingabe.getText().equals("0")){
            error.setVisible(true);
            error.setText("Keine gültige Eingabe.");
            return;
        }
        error.setVisible(false);
        Hauptspiel.addNumber(Integer.parseInt(eingabe.getText()));
        felderAktualisieren();
    }
}

