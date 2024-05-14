package spielerPackage;

public class Echter_Spieler extends Spieler{

    private int wins;
    private int losses;
    private float wins_losses_ratio;
    private int winstreak;

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public float getWins_losses_ratio() {
        return wins_losses_ratio;
    }

    public int getWinstreak() {
        return winstreak;
    }

    public void setWins(int wins) {
        this.wins = wins;
        update_wins_losses_ratio();
    }

    public void setLosses(int losses) {
        this.losses = losses;
        update_wins_losses_ratio();

    }

    private void update_wins_losses_ratio() {
        this.wins_losses_ratio = this.wins/this.losses;
    }

    public void setWinstreak(int winstreak) {
        this.winstreak = winstreak;
    }

    public Echter_Spieler(String name){
        this.setName(name);
    }


    // hier noch Methode 'zugMachen' für Signal an UI zur Zugeingabe ?

}
