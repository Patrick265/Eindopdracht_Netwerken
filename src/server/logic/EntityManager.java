package server.logic;

import game.NPC.Enemy;

import java.util.ArrayList;
import java.util.Iterator;

public class EntityManager
{
    public static void removeMonster(Enemy enemy, ArrayList<Enemy> enemies)
    {
        Iterator<Enemy> iterator = enemies.iterator();

        while(iterator.hasNext())
        {
            Enemy deletableEnemy = iterator.next();
            if (deletableEnemy.equals(enemy))
            {
                iterator.remove();
                break;
            }
        }
    }
}
