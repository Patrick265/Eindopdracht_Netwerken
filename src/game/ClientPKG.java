package game;

import game.NPC.Enemy;
import game.character.Player;

import java.io.Serializable;
import java.util.ArrayList;

public class ClientPKG implements Serializable
{
    private final Player player;
    private final ArrayList<Enemy> enemies;

    public ClientPKG(Player player, ArrayList<Enemy> enemies)
    {
        this.player = player;
        this.enemies = enemies;
    }

    public Player getPlayer()
    {
        return player;
    }

    public ArrayList<Enemy> getEnemies()
    {
        return enemies;
    }

    public String toStringPlayers()
    {
        return player.getLocation().toString();
    }

}
