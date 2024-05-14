package spielerPackage;
import spielerPackage.algorithmusBaum.AlgBaumKnoten;
import spiellogikPackage.Hauptspiel;
import static spielerPackage.algorithmusBaum.AlgBaum.*;

import java.util.Random;

public class Computer_Spieler extends Spieler {

    public Computer_Spieler() {
        setName("Computer Spieler");
    }

    public static int zugMachen_1vs1() {
        int neueZahl = 9;
        if (Hauptspiel.getAnzahlSpieler()>2 && Hauptspiel.getStack().getLast() + Hauptspiel.getStack().get(Hauptspiel.getAnzahlSpieler()-2) >=21) {
            neueZahl = 0;
        } else {
            switch (Hauptspiel.getAnzahlSpieler()) {
                case 0, 2, 4:
                    neueZahl = 1;
                    break;
                case 1, 3, 5:
                    while (Hauptspiel.getStack().getLast() + neueZahl >=21){ // möglichst große Zahl addieren
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
        if (Hauptspiel.getAnzahlSpieler()>=2 && Hauptspiel.getStack().getLast() + Hauptspiel.getStack().get(Hauptspiel.getAnzahlSpieler()-2) >=21) {
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
