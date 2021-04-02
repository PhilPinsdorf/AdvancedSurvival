package de.strawberry.spells.Spells;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public class Spell {

    String name;
    ChatColor chatColor;
    String description;
    int id;
    Material material;
    ISpell spell;

    public Spell(String name, ChatColor chatColor, String description, int id, Material material, ISpell spell){
        this.name = name;
        this.chatColor = chatColor;
        this.description = description;
        this.id = id;
        this.material = material;
        this.spell = spell;
    }

    public String getName() {
        return name;
    }

    public ChatColor getChatColor() {
        return chatColor;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public Material getMaterial() {
        return material;
    }

    public ISpell getSpell() {
        return spell;
    }
}
