package game;

import game.character.Player;
import java.io.Serializable;

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
