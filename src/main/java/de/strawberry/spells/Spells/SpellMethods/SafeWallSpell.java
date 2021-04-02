package de.strawberry.spells.Spells.SpellMethods;

import de.strawberry.spells.Spells.ISpell;
import de.strawberry.spells.enums.SpellClass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class SafeWallSpell implements ISpell {
    @Override
    public void execute(Player p) {
        Bukkit.broadcastMessage(getName() + " executed!");
    }

    @Override
    public String getName() {
        return "Safe Wall";
    }

    @Override
    public SpellClass getSpellClass() {
        return SpellClass.EARTH;
    }

    @Override
    public String getDescription() {
        return "Builds secure wall";
    }

    @Override
    public int getId() {
        return 3;
    }

    @Override
    public Material getMaterial() {
        return Material.STONE;
    }
}
