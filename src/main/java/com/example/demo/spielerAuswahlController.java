package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import spielerPackage.Echter_Spieler;
import spielerPackage.Spieler;
import spiellogikPackage.Hauptspiel;

import java.io.IOException;


public class spielerAuswahlController {

    private TextField name;

    @FXML
    private void wechselZuSpielfeld(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("spielfeld.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void wechselZuStartmenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("startmenu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void spielerHinzufuegen(ActionEvent event) throws IOException {

            Spieler spieler = new Echter_Spieler(name.getText());
            Hauptspiel.spielerHinzufuegen(spieler);

            name.setText("");
    }

}
