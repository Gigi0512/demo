package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import spiellogikPackage.Hauptspiel;

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

    public void spielStartVorbereiten(){
        spielerAmZug.setText(Hauptspiel.spielerAmZug().getName());
    }

    public void felderAktualisieren(){
        ArrayList<Label> felder = new ArrayList<Label>(Arrays.asList(eins, zwei, drei, vier, fuenf, sechs));
        for (int i = 0; i < 6; i++){
            felder.get(i).setText(String.valueOf(Hauptspiel.getStack().get(i)));
        }
    }

    public void eingabeAusfuehrenAdd(ActionEvent actionEvent) {

        if (eingabe.getText().isEmpty() || String.valueOf(Integer.parseInt(eingabe.getText())).length() != 1 || eingabe.getText().equals("0")){
            error.setVisible(true);
            error.setText("Keine gültige Eingabe.");
            return;
        }
        error.setVisible(false);
        Hauptspiel.addNumber(Integer.parseInt(eingabe.getText()));
        felderAktualisieren();
        spielerAmZug.setText(Hauptspiel.spielerAmZug().getName());
    }

    public void eingabeAusfuehrungSum(ActionEvent actionEvent) {

        if (Hauptspiel.getStack().size() < 2 || Hauptspiel.getRundenAnzahl() < 4){
            error.setVisible(true);
            error.setText("Aufsummieren ist nicht möglich.");
            return;
        }
        error.setVisible(false);
        Hauptspiel.summingUp();
        felderAktualisieren();
        spielerAmZug.setText(Hauptspiel.spielerAmZug().getName());
    }
}

