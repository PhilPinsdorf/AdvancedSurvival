package de.strawberry.spells.Spells.SpellMethods;

import de.strawberry.spells.Spells.ISpell;
import de.strawberry.spells.enums.SpellClass;
import de.strawberry.spells.rays.ParticlesOnRay;
import de.strawberry.spells.rays.StraightRay;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class ThorsHammerSpell implements ISpell {
    @Override
    public void execute(Player p) {
        Bukkit.broadcastMessage(getName() + " executed!");

        LivingEntity entity = new StraightRay().getTargetRay(p, 20);
        if(entity != null){
            new ParticlesOnRay().spawnParticleAlongLine(p.getLocation().add(0,1,0), entity.getLocation().add(0,1,0), getSpellClass().getParticle(), 10, 5,0, 0,0, (float) 0.01, true, null);

            Bukkit.getServer().getWorld(entity.getLocation().getWorld().getName()).strikeLightning(entity.getLocation());
        }
    }

    @Override
    public String getName() {
        return "Thor's Hammer";
    }

    @Override
    public SpellClass getSpellClass() {
        return SpellClass.EARTH;
    }

    @Override
    public String getDescription() {
        return "Summons Bolt at Enemies Location";
    }

    @Override
    public int getId() {
        return 4;
    }

    @Override
    public Material getMaterial() {
        return Material.FIRE_CHARGE;
    }
}
