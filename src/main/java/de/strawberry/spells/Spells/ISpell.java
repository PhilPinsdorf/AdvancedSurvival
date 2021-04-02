package de.strawberry.spells.Spells;

import de.strawberry.spells.enums.SpellClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public interface ISpell
{
    public void execute(Player p);

    public String getName();

    public SpellClass getSpellClass();

    public String getDescription();

    public int getId();

    public Material getMaterial();
}
