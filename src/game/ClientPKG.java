package game;

import game.NPC.Enemy;
import game.character.Player;

import java.io.Serializable;
import java.util.ArrayList;

public class ClientPKG implements Serializable
{
    private final Player player;

    public ClientPKG(Player player)
    {
        this.player = player;
    }

    public Player getPlayer()
    {
        return player;
    }
}
