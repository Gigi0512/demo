package spiellogikPackage;

import spielerPackage.*;
import java.util.*;

public class Hauptspiel {

    private static LinkedList<Spieler> spielerListe = new LinkedList<Spieler>();
    private static Stack<Integer> stack = new Stack<Integer>();
    private static int rundenAnzahl = 1;

    public static void spielerHinzufuegen(Spieler spieler) {
        spielerListe.push(spieler);
    }

    public static void spielStarten(){
        Random rand = new Random();
        int start = rand.nextInt(getAnzahlSpieler());
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

        if (String.valueOf(pNumber).length() != 1){
            throw new IllegalArgumentException("Gebe eine Zahl von 1-9 an.");
        }
        if (stack.size() >= 6){
            throw new IllegalArgumentException("Du kannst keine Zahl hinzufügen.");
        }

        stack.push(pNumber);
        spielerListe.addFirst(spielerListe.removeLast());
        rundenAnzahl ++;
    }

    public static int getRundenAnzahl() {
        return rundenAnzahl;
    }

    public static void summingUp(){

        if (rundenAnzahl <= 3){
            throw new IllegalArgumentException("Addieren ist erst ab der 3. Spielrunde möglich.");
        }

        if (stack.size() < 2){
            throw new IllegalArgumentException("Es müssen mindestens 2 Zahlen da sein.");
        }

        stack.push(stack.pop() + stack.pop());
        spielerListe.addFirst(spielerListe.removeLast());
        rundenAnzahl ++;

        if (stack.peek() >= 21) {
            System.out.println("Gewinner: " + spielerListe.getFirst().toString());
            stack.removeAllElements();
        }
    }

    public static LinkedList<Spieler> getSpielerListe() {
        return spielerListe;
    }
    public static Spieler spielerAmZug(){
        return spielerListe.peek();
    }

    @Override
    public String toString(){
        return String.valueOf(stack);
    }

}
