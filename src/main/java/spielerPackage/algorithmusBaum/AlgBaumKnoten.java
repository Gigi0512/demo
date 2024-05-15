package spielerPackage.algorithmusBaum;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class AlgBaumKnoten {
    private Stack<Integer> stackVonDiesemKnoten;
    private Set<AlgBaumKnoten> kinder = new HashSet<>();
    private double gewinnWahrscheinlichkeit = -1;
    public int zugWert;//gibt an, was Spieler in seinem Zug angegeben hat, also entweder eine Zahl zwischen 1 und 9 oder +

    public AlgBaumKnoten(Stack<Integer> stack, int zugWert) {
        this.stackVonDiesemKnoten = stack;
        this.zugWert = zugWert;
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

