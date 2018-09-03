package game.skills;

import java.io.Serializable;

public class Skills implements Serializable{
    private Attack attack;
    private Strength strength;
    private Hitpoints hitpoints;

    public Skills()
    {
        attack = new Attack();
        strength = new Strength();
        hitpoints = new Hitpoints();

    }

    @Override
    public String toString()
    {
        return "Skills{" +
                "attack=" + attack +
                ", strength=" + strength +
                ", hitpoints=" + hitpoints +
                '}';
    }

    public Attack getAttack() {
        return attack;
    }

    public Strength getStrength() {
        return strength;
    }

    public Hitpoints getHitpoints() {
        return hitpoints;
    }
}
