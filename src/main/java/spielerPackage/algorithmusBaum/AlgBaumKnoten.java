package spielerPackage.algorithmusBaum;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class AlgBaumKnoten {
    private Stack<Integer> stackVonDiesemKnoten;
    private Set<AlgBaumKnoten> kinder = new HashSet<>();
    private double gewinnWahrscheinlichkeit = -1;
    private int zugWert; // repräsentiert den Zug, der zu dem auf diesem Knoten gespeicherten Spielstand führt (0 = Addieren)

    public AlgBaumKnoten(Stack<Integer> stack, int zugWert) {
        this.stackVonDiesemKnoten = stack;
        this.zugWert = zugWert;
    }

    public int getZugWert() {
        return zugWert;
    }

    public Set<AlgBaumKnoten> getKinder() {
        return kinder;
    }

    public double getGewinnWahrscheinlichkeit() {
        return gewinnWahrscheinlichkeit;
    }

    public void setGewinnWahrscheinlichkeit(double gewinnWahrscheinlichkeit) {
        this.gewinnWahrscheinlichkeit = gewinnWahrscheinlichkeit;
    }

    public Stack<Integer> getStackVonDiesemKnoten() {
        return stackVonDiesemKnoten;
    }

    @Override
    public String toString() {
        return "Stack: " + stackVonDiesemKnoten;

    }
}

