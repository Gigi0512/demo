package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import spiellogikPackage.Hauptspiel;

import java.io.IOException;

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

}

