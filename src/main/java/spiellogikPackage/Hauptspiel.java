package spiellogikPackage;

import spielerPackage.*;
import java.util.*;

public class Hauptspiel {

    private static boolean playerWon;
    private static LinkedList<Spieler> spielerListe = new LinkedList<Spieler>();
    private static Stack<Integer> stack = new Stack<Integer>();
    private static int rundenAnzahl = 1;

    public static void spielerHinzufuegen(Spieler spieler) {
        spielerListe.push(spieler);
    }

    public static void spielStarten(){
        Collections.shuffle(spielerListe);
    }
    public static Stack<Integer> getStack() {
        return stack;
    }

    public static int getAnzahlSpieler() {
        return spielerListe.size();
    }

    public static void addNumber(int pNumber){
        if (pNumber == 0) {
            summingUp();
            return;
        }

        if (stack.size() == 6){
            throw new IllegalArgumentException();
        }
        stack.push(pNumber);
        spielerListe.addFirst(spielerListe.removeLast());
        rundenAnzahl ++;
    }

    public static int getRundenAnzahl() {
        return rundenAnzahl;
    }

    public static void summingUp(){

        stack.push(stack.pop() + stack.pop());
        spielerListe.addFirst(spielerListe.removeLast());
        rundenAnzahl ++;

        if (stack.peek() >= 21) {
            playerWon = true;
            rundenAnzahl = 1;
        }
    }

    public static LinkedList<Spieler> getSpielerListe() {
        return spielerListe;
    }
    public static Spieler spielerAmZug(){
        return spielerListe.peek();
    }
    public static boolean getPlayerWon() {
        return playerWon;
    }
    public static void setPlayerWon(boolean playerWon) {
        Hauptspiel.playerWon = playerWon;
    }

    @Override
    public String toString(){
        return String.valueOf(stack);
    }

}
