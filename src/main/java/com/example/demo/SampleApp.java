package com.example.demo;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

import spiellogikPackage.*;
import spielerPackage.*;


public class SampleApp extends Application {
    private static final TextField playerTextField = new TextField();
    private static final Button nextPlayerButton = new Button("Next Player");
    private static final Button startButton = new Button("Start");
    private static final CheckBox aiPlayerCheckbox = new CheckBox("Use AI Player");
    private static final TextField numberTextField = new TextField();
    private static final Button plusButton = new Button("+");
    private static final Button addButton = new Button("Add");
    private static final TableView<TableEntry> tableView = new TableView<>();
    private static final Label errorLabel = new Label("");

    public static void main(String[] args) {
        launch(args);
    }

    public static void error(String errorNachricht){
        errorLabel.setText(errorNachricht);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene1 = createFirstScene();
        Scene scene2 = createSecondScene();
        primaryStage.setTitle("21+ Game");
        primaryStage.setScene(scene1);
        primaryStage.show();

        nextPlayerButton.setOnAction(e -> {

            if (aiPlayerCheckbox.isSelected()) {
                Spieler spieler = new Computer_Spieler();
                Hauptspiel.spielerHinzufuegen(spieler);
                aiPlayerCheckbox.setSelected(false);
            } else {
                Spieler spieler = new Echter_Spieler(playerTextField.getText());
                Hauptspiel.spielerHinzufuegen(spieler);
                playerTextField.clear();
            }

            if (Hauptspiel.getAnzahlSpieler() >= 2) {
                startButton.setDisable(false);
            }

            if (Hauptspiel.getAnzahlSpieler() == 4) {
                primaryStage.setScene(scene2);
            }
        });

        startButton.setOnAction(e -> {
            primaryStage.setScene(scene2);
            primaryStage.setMinHeight(500); // Minimle höhe
            primaryStage.setMaxHeight(500); // Maximale höhe
            primaryStage.setMinWidth(150);  // Min breite
            primaryStage.setMaximized(false);
        });

        plusButton.setOnAction(e -> {
            if (Hauptspiel.spielerAmZug() instanceof Computer_Spieler) {
                int zahl;
                if (Hauptspiel.getAnzahlSpieler() >= 3) {
                    zahl = Computer_Spieler.zugMachen_Multiplayer();
                }
                else {
                    zahl = Computer_Spieler.zugMachen_1vs1();
                }
                Hauptspiel.addNumber(zahl);
                tableView.getItems().add(new TableEntry(zahl));
            } else {
                int zahl = Integer.parseInt(numberTextField.getText());
                Hauptspiel.addNumber(zahl);
                tableView.getItems().add(new TableEntry(zahl));
                numberTextField.clear();
            }

            tableView.refresh(); // TableView aktualisieren
        });


        addButton.setOnAction(e -> {

            // Falls der nächste spieler ein computer ist wird automatisch sein zug gemacht
            if (Hauptspiel.spielerAmZug() instanceof Computer_Spieler) {
                plusButton.fire();
            }

            Hauptspiel.addNumber(0);
        });
    }

    public static void gewinnerAusgeben(){
        Scene congratulationsScene = createCongratulationScene(Hauptspiel.spielerAmZug());
        Stage secondStage = new Stage();
        secondStage.setScene(congratulationsScene);
        secondStage.show();
    }

    private Scene createFirstScene() {
        nextPlayerButton.setMaxWidth(Double.MAX_VALUE);
        startButton.setMaxWidth(Double.MAX_VALUE);
        startButton.setDisable(true);

        VBox vBox = new VBox(5, playerTextField, aiPlayerCheckbox, nextPlayerButton, startButton);
        vBox.setPadding(new Insets(10));

        return new Scene(vBox, 200, 150);
    }

    private Scene createSecondScene() {
        plusButton.setMaxWidth(Double.MAX_VALUE);
        addButton.setMaxWidth(Double.MAX_VALUE);
        errorLabel.setTextFill(Color.RED);


        TableColumn<TableEntry, Number> numberColumn = new TableColumn<>("Number");

        VBox.setVgrow(tableView, Priority.ALWAYS); // tabelle mitgeben das sie sich horizontal ausbreitet

        VBox vBox = new VBox(5, numberTextField, errorLabel, plusButton, addButton, tableView);
        vBox.setPadding(new Insets(10));
        vBox.setPrefHeight(500); // Höhe tabelle verstellen

        return new Scene(vBox);
    }


    public static class TableEntry {

        private final SimpleIntegerProperty number;

        public TableEntry(int number) {

            this.number = new SimpleIntegerProperty(number);
        }

        public SimpleIntegerProperty numberProperty() {
            return number;
        }
    }

    private static Scene createCongratulationScene(Spieler spieler) {
        VBox congratulationsPane = new VBox(20);
        congratulationsPane.setPadding(new Insets(25));

        Text congratsText = new Text("Congratulations " + spieler.getName() + "!");
        congratsText.setFont(Font.font(20));
        Button closeButton = new Button("Close");

        congratulationsPane.getChildren().addAll(congratsText, closeButton);
        Scene congratulationsScene = new Scene(congratulationsPane, 300, 200);

        closeButton.setOnAction(e -> {
            // schließen zweite scene
            ((Stage) closeButton.getScene().getWindow()).close();
        });
        return congratulationsScene;
    }
}