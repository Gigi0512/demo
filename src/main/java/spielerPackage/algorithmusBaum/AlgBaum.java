package spielerPackage.algorithmusBaum;

import spiellogikPackage.Hauptspiel;

import java.util.Stack;

// Algorithmus ab dem 4. Spielzug bei 3 oder mehr Spielern

public class AlgBaum {

    public static AlgBaumKnoten baumErstellen(int aktuelleRundenanzahl, int zuege) {
        AlgBaumKnoten wurzel = new AlgBaumKnoten(Hauptspiel.getStack(), 0);
        kinderHinzufuegen(wurzel, zuege, aktuelleRundenanzahl);
        return wurzel;
    }

    private static void kinderHinzufuegen(AlgBaumKnoten aktuellerKnoten, int zuege, int aktuelleRundenanzahl) {

        if (zuege <= 0 || aktuellerKnoten.getStackVonDiesemKnoten().peek() > 20) {
            return; // Methode beenden, falls keine weitere Ebene hinzugefügt werden soll
        }

        if (aktuellerKnoten.getStackVonDiesemKnoten().size() <= 5) {
            for (int i = 1; i <= 9; i++) {
                Stack<Integer> kopieStack = new Stack<>();
                kopieStack.addAll(aktuellerKnoten.getStackVonDiesemKnoten());
                kopieStack.push(i);
                aktuellerKnoten.getKinder().add(new AlgBaumKnoten(kopieStack, i));
            }
        }

        if (aktuellerKnoten.getStackVonDiesemKnoten().size() >= 2 && aktuelleRundenanzahl > 3) {
            Stack<Integer> kopieStack = new Stack<>();
            kopieStack.addAll(aktuellerKnoten.getStackVonDiesemKnoten());
            kopieStack.push(kopieStack.pop() + kopieStack.pop());
            aktuellerKnoten.getKinder().add(new AlgBaumKnoten(kopieStack, 0));
        }

        for (AlgBaumKnoten kind : aktuellerKnoten.getKinder()) {
            kinderHinzufuegen(kind, zuege - 1, aktuelleRundenanzahl + 1); // Rekursiver Aufruf für die nächste Baum-Ebene
        }
    }

    public static void setzeGewinnwahrscheinlichkeiten(AlgBaumKnoten aktuellerWurzelKnoten, int spielerAnzahl, int zuege) {
        if (aktuellerWurzelKnoten.getKinder().isEmpty()) {
            if (istMehrAls21(aktuellerWurzelKnoten)) {
                if ((spielerAnzahl == 3 && zuege == 6) || (spielerAnzahl == 4 && zuege == 8)) {
                    aktuellerWurzelKnoten.setGewinnWahrscheinlichkeit(1);
                } else {
                    aktuellerWurzelKnoten.setGewinnWahrscheinlichkeit(0);
                }
            } else {
                aktuellerWurzelKnoten.setGewinnWahrscheinlichkeit(0.5);
            }
        } else { // hier Berechnung der restlichen Wahrscheinlichkeiten für jeden Knoten
            double zusammenaddierteGewinnWahrscheinlichkeit = 0.0;
            for (AlgBaumKnoten kind : aktuellerWurzelKnoten.getKinder()) {
                setzeGewinnwahrscheinlichkeiten(kind, spielerAnzahl, zuege - 1); // rekursiver Aufruf
                zusammenaddierteGewinnWahrscheinlichkeit += kind.getGewinnWahrscheinlichkeit();
            }
            aktuellerWurzelKnoten.setGewinnWahrscheinlichkeit(zusammenaddierteGewinnWahrscheinlichkeit / aktuellerWurzelKnoten.getKinder().size());
        }
    }

    private static boolean istMehrAls21(AlgBaumKnoten aktuellerKnoten) {
        Stack<Integer> stack = aktuellerKnoten.getStackVonDiesemKnoten();
        if (stack.size() >= 2) {
            int sum = stack.getLast() + stack.get(stack.size() - 2);
            return sum >= 21;
        }
        return false;
    }

    public static int bestimmeDenNaechstenZug(AlgBaumKnoten wurzel) {
        int besterZug = 99; // Wert wird sicher überschrieben
        double hoechsteGewinnWahrscheinlichkeit = -1;
        for (AlgBaumKnoten kind : wurzel.getKinder()) {
            if (kind.getGewinnWahrscheinlichkeit() > hoechsteGewinnWahrscheinlichkeit) {
                hoechsteGewinnWahrscheinlichkeit = kind.getGewinnWahrscheinlichkeit();
                besterZug = kind.zugWert;
            }
        }
        return besterZug;
    }
}
