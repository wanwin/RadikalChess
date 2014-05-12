package Model;

public class Player {

    private String player;

    public Player(String player) {
        this.player = player;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object obj) {
        Player name = (Player) obj;
        return player.equals(name.player);
    }
}