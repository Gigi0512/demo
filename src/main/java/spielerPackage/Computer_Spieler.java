package spielerPackage;

import spielerPackage.algorithmusBaum.AlgBaumKnoten;
import spiellogikPackage.Hauptspiel;

import static spielerPackage.algorithmusBaum.AlgBaum.*;

import java.util.Random;

public class Computer_Spieler extends Spieler {

    public Computer_Spieler(String name) {
        this.setName(name + " (C)");
    }

    public int zugMachen_1vs1() {
        int neueZahl = 9;
        if (Hauptspiel.getStack().size() >= 2 && (Hauptspiel.getStack().getLast() + Hauptspiel.getStack().get(Hauptspiel.getStack().size() - 2) >= 21)) {
            neueZahl = 0; // Addieren, falls dadurch gewonnen wird // 0 symbolisiert Addieren
        } else {
            switch (Hauptspiel.getStack().size()) {
                case 0, 2, 4:
                    neueZahl = 1;
                    break;
                case 1, 3, 5:
                    while (Hauptspiel.getStack().getLast() + neueZahl >= 21) { // möglichst große Zahl addieren
                        neueZahl--;
                    }
                    break;
                case 6:
                    neueZahl = 0;
                    break;
            }
        }
        return neueZahl;
    }

    public int zugMachen_Multiplayer() {
        int aktuelleRundenanzahl = Hauptspiel.getRundenAnzahl();
        int anzahlSpieler = Hauptspiel.getAnzahlSpieler();
        int neueZahl;
        if (aktuelleRundenanzahl < 4) {
            neueZahl = (new Random()).nextInt(9) + 1;
            return neueZahl;
        }
        if (Hauptspiel.getStack().size() >= 2 && (Hauptspiel.getStack().getLast() + Hauptspiel.getStack().get(Hauptspiel.getStack().size() - 2) >= 21)) {
            neueZahl = 0;
        } else if (Hauptspiel.getStack().size() == 6) { // zur Übersichtlichkeit hier: bei vollem Stack immer addieren
            neueZahl = 0;
        } else {
            int zuege = anzahlSpieler * 2; // hier kann manuell die Tiefe des Baumes festgelegt werden: Achtung Speicherplatz!
            AlgBaumKnoten wurzel = baumErstellen(aktuelleRundenanzahl, zuege);
            setzeGewinnwahrscheinlichkeiten(wurzel, anzahlSpieler, zuege);
            neueZahl = bestimmeDenNaechstenZug(wurzel);
        }
        return neueZahl;
    }
}
