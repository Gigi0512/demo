package spielerPackage;

public abstract class Spieler {

    private String name;

    protected void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}