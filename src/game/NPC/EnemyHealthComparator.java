package game.NPC;

import java.util.Comparator;

public class EnemyHealthComparator implements Comparator<Enemy> {
    @Override
    public int compare(Enemy o1, Enemy o2) {
        int health1 = o1.getSkills().getHitpoints().getHealth();
        int health2 = o2.getSkills().getHitpoints().getHealth();
        return Integer.compare(health2,health1);
    }
}
