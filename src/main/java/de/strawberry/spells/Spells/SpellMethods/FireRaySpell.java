package de.strawberry.spells.Spells.SpellMethods;

import de.strawberry.spells.Spells.ISpell;
import de.strawberry.spells.enums.SpellClass;
import de.strawberry.spells.rays.ParticlesOnRay;
import de.strawberry.spells.rays.StraightRay;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class FireRaySpell implements ISpell {
    @Override
    public void execute(Player p) {
        Bukkit.broadcastMessage(getName() + " executed!");

        LivingEntity entity = new StraightRay().getTargetRay(p, 20);
        if(entity != null){
            new ParticlesOnRay().spawnParticleAlongLine(p.getLocation().add(0,1,0), entity.getLocation().add(0,1,0), getSpellClass().getParticle(), 10, 5,0, 0,0, (float) 0.01, true, null);
            entity.setFireTicks(20*5);
        }
    }

    @Override
    public String getName() {
        return "Fire Ray";
    }

    @Override
    public SpellClass getSpellClass() {
        return SpellClass.FIRE;
    }

    @Override
    public String getDescription() {
        return "Burns your worst nightmares away";
    }

    @Override
    public int getId() {
        return 1;
    }

    @Override
    public Material getMaterial() {
        return Material.BLAZE_POWDER;
    }
}
