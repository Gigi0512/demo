package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import spielerPackage.Computer_Spieler;
import spielerPackage.Echter_Spieler;
import spielerPackage.Spieler;
import spiellogikPackage.Hauptspiel;

import java.io.IOException;
import java.util.ArrayList;

public class spielerAuswahlController {

    ArrayList<String> checkDuplikate = new ArrayList<String>();
    @FXML
    private TextField nameTextField;
    @FXML
    private CheckBox computerCheck;
    @FXML
    private Label error;
    @FXML
    private Button spielStarten;

    @FXML
    private void wechselZuSpielfeld(ActionEvent event) throws IOException {
        Hauptspiel.spielStarten();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("spielfeld.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        spielfeldController controller = loader.getController();
        controller.spielerAmZugAnzeigen();
        stage.show();
    }

    @FXML
    public void wechselZuStartmenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("startmenu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void spielerHinzufuegen(ActionEvent event) throws IOException {

            if (nameTextField.getText().isEmpty() || nameTextField.getText().length() > 15  || checkDuplikate.contains(nameTextField.getText())){
                error.setVisible(true);
                error.setText("Der Name ist ungültig.");
                return;
            }
            error.setVisible(false);
            checkDuplikate.add(nameTextField.getText());

            if (computerCheck.isSelected()) {
                Spieler computerSpieler = new Computer_Spieler();
                computerSpieler.setName(nameTextField.getText());
                Hauptspiel.spielerHinzufuegen(computerSpieler);
                nameTextField.clear();
            }
            else {
                Spieler spieler = new Echter_Spieler(nameTextField.getText());
                Hauptspiel.spielerHinzufuegen(spieler);
                nameTextField.clear();
            }

            if (Hauptspiel.getAnzahlSpieler() >= 2) {
                spielStarten.setDisable(false);
            }

            if (Hauptspiel.getAnzahlSpieler() == 4) {
                wechselZuSpielfeld(event);
            }
    }
}
