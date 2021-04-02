package de.strawberry.spells.Spells.SpellMethods;

import de.strawberry.spells.Spells.ISpell;
import de.strawberry.spells.enums.SpellClass;
import de.strawberry.spells.rays.ParticlesOnRay;
import de.strawberry.spells.rays.StraightRay;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class PuffOfAirSpell implements ISpell {
    @Override
    public void execute(Player p) {
        Bukkit.broadcastMessage(getName() + " executed!");
        LivingEntity entity = new StraightRay().getTargetRay(p, 20);
        if(entity != null){
            new ParticlesOnRay().spawnParticleAlongLine(p.getLocation().add(0,1,0), entity.getLocation().add(0,1,0), getSpellClass().getParticle(), 10, 5,0f, 0f,0f, 1f, true, null);
            Vector v = p.getLocation().getDirection().multiply(4).setY(0.5);
            entity.setVelocity(v);
        }
    }

    @Override
    public String getName() {
        return "Puff of Air";
    }

    @Override
    public SpellClass getSpellClass() {
        return SpellClass.AIR;
    }

    @Override
    public String getDescription() {
        return "Pushes your enemy away";
    }

    @Override
    public int getId() {
        return 2;
    }

    @Override
    public Material getMaterial() {
        return Material.FEATHER;
    }
}
