package de.strawberry.spells.enums;

import org.bukkit.ChatColor;
import org.bukkit.Particle;

import java.util.UUID;

public enum SpellClass {
    FIRE(ChatColor.DARK_RED, Particle.FLAME, "Fire", Heads.SPELL_BOOK_RED),
    WATER(ChatColor.DARK_BLUE, Particle.DRIP_WATER, "Water", Heads.SPELL_BOOK_BLUE),
    EARTH(ChatColor.DARK_GREEN, Particle.BLOCK_DUST, "Earth", Heads.SPELL_BOOK_GREEN),
    AIR(ChatColor.AQUA, Particle.SPELL, "Air", Heads.SPELL_BOOK_LIGHT_BLUE),
    POWER(ChatColor.YELLOW, Particle.FIREWORKS_SPARK, "Power", Heads.SPELL_BOOK_YELLOW),
    MYTHIC(ChatColor.DARK_PURPLE, Particle.PORTAL, "Mythic", Heads.SPELL_BOOK_PURPLE),
    DARK(ChatColor.BLACK, Particle.SMOKE_NORMAL, "Dark", Heads.SPELL_BOOK_NETHER);

    final ChatColor chatColor;
    final Particle particle;
    final String name;
    final Heads head;
    SpellClass(ChatColor chatColor, Particle particle, String name, Heads head){
        this.chatColor = chatColor;
        this.particle = particle;
        this.name = name;
        this.head = head;
    }

    public ChatColor getChatColor() {
        return chatColor;
    }

    public Particle getParticle() {
        return particle;
    }

    public String getClassName() {
        return name;
    }

    public Heads getHead() {
        return head;
    }
}
