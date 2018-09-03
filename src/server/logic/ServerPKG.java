package server.logic;

import game.NPC.Enemy;
import game.character.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class ServerPKG implements Serializable
{
    private Map<String, Player> players;
    private ArrayList<Enemy> enemies;

    public ServerPKG(Map<String, Player> players, ArrayList<Enemy> enemies)
    {
        this.players = players;
        this.enemies = enemies;
    }

    public Map<String, Player> getPlayers()
    {
        return players;
    }

    public void setPlayers(Map<String, Player> players)
    {
        this.players = players;
    }

    public ArrayList<Enemy> getEnemies()
    {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies)
    {
        this.enemies = enemies;
    }
}
