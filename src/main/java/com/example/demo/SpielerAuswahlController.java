package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import spielerPackage.Computer_Spieler;
import spielerPackage.Echter_Spieler;
import spielerPackage.Spieler;
import spiellogikPackage.Hauptspiel;

import java.io.IOException;
import java.util.ArrayList;

public class SpielerAuswahlController {

    ArrayList<String> checkDuplikate = new ArrayList<>();
    @FXML
    private TextField nameTextField;
    @FXML
    private CheckBox computerCheck;
    @FXML
    private Label error;
    @FXML
    private Button spielStarten;

    @FXML
    public void wechselZuStartmenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("startmenu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void wechselZuSpielfeldklick(ActionEvent event) throws IOException {
        wechselZuSpielfeld(event);}

    private void wechselZuSpielfeld(ActionEvent event) throws IOException {
        wechselZuSpielfeldHandler(event);}

    private void wechselZuSpielfeldHandler(Event event) throws IOException {
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

    @FXML
    private void spielerHinzufuegenklick(ActionEvent event) throws IOException {
            spielerHinzufuegen(event);
    }
    @FXML
    private void hinzufuegenEnter(KeyEvent event) throws IOException {
        if (event.getCode()== KeyCode.ENTER){
            spielerHinzufuegen(event);
        }
    }

    private void spielerHinzufuegen(Event event) throws IOException {

        //überprüft ob der spielername ein gültiger Wert ist und speichert ihn in einer ArrayList zur vermeidung von Duplikaten

        if (nameTextField.getText().isEmpty() || nameTextField.getText().length() > 15 || checkDuplikate.contains(nameTextField.getText().toLowerCase()) || nameTextField.getText().contains(";")){
            error.setVisible(true);
            error.setText("Der Name ist ungültig.");
            return;
        }else {
            error.setVisible(false);
            checkDuplikate.add(nameTextField.getText().toLowerCase());}

        if (computerCheck.isSelected()) {//erstellt Computer spieler wenn Box ausgewählt ist
            Spieler computerSpieler = new Computer_Spieler(nameTextField.getText());
            Hauptspiel.spielerHinzufuegen(computerSpieler);
            nameTextField.clear();
            computerCheck.setSelected(false);
        }
        else {//ansonten Erstellung Echter spieler
            Spieler spieler = new Echter_Spieler(nameTextField.getText().toLowerCase());
            Hauptspiel.spielerHinzufuegen(spieler);
            nameTextField.clear();
        }

        if (Hauptspiel.getAnzahlSpieler() >= 2) {
            spielStarten.setDisable(false);
        }

        if (Hauptspiel.getAnzahlSpieler() == 4) {
            wechselZuSpielfeldHandler(event);
        }
    }
}
