package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import highscoreTabelle.HSTabelle;

import java.io.IOException;
import com.example.demo.*;

public class highscoreController {

    @FXML
    private TextField spielerSuchenTF;
    @FXML
    private Label ersterSpielerID;
    @FXML
    private Label zweiteSpielerID;
    @FXML
    private Label dritteSpielerID;
    @FXML
    private Label gesuchterSpielerID;
    @FXML
    private Button zurückBT;

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
    private void nachSpielerSuchen(KeyEvent event) throws IOException {
        if (event.getCode()== KeyCode.ENTER){
            gesuchterSpielerID.setText(HSTabelle.searchPlayer(spielerSuchenTF.getText()));
            spielerSuchenTF.clear();


        }

    }

}

