package spielerPackage;
import spielerPackage.algorithmusBaum.AlgBaumKnoten;
import spiellogikPackage.Hauptspiel;
import spielerPackage.algorithmusBaum.AlgBaum.*;

import java.util.Random;
import java.util.Stack;

import static spielerPackage.algorithmusBaum.AlgBaum.*;

public class Computer_Spieler extends Spieler {
    private static Stack<Integer> aktuellerSpielStack = Hauptspiel.getStack();
    // static wieder rausnehmen, wenn stack nicht aktualisiert wird

    public Computer_Spieler() {
        setName("Computer Spieler");
    }

    // return 0 in case of summing up
    public static int zugMachen_1vs1() {
        int neueZahl = 9; //Zahl, die hinzugefügt werden soll
        if (aktuellerSpielStack.size()>2 && aktuellerSpielStack.getLast() + aktuellerSpielStack.get(aktuellerSpielStack.size()-2) >=21) {
            neueZahl = 0; // durch Addieren gewinnen
        } else {
            switch (aktuellerSpielStack.size()) {
                case 0, 2, 4:
                    neueZahl = 1;
                    break;
                case 1, 3, 5:
                    while (aktuellerSpielStack.getLast() + neueZahl >=21){ // möglichst große Zahl addieren
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

    public static int zugMachen_Multiplayer() {
        int aktuelleRundenanzahl = Hauptspiel.getRundenAnzahl();
        int anzahlSpieler = Hauptspiel.getAnzahlSpieler();
        int neueZahl;
        if (aktuelleRundenanzahl <4) {
            neueZahl = (new Random()).nextInt(9) +1;
        }
        if (aktuellerSpielStack.size()>=2 && aktuellerSpielStack.getLast() + aktuellerSpielStack.get(aktuellerSpielStack.size()-2) >=21) {
            neueZahl = 0;
        } else {
            int zuege = anzahlSpieler * 2;
            AlgBaumKnoten wurzel = baumErstellen(aktuelleRundenanzahl, zuege);
            setzeGewinnwahrscheinlichkeiten(wurzel, anzahlSpieler, zuege); ;
            neueZahl = bestimmeDenNaechstenZug(wurzel);
        }
        return neueZahl;
    }
}
