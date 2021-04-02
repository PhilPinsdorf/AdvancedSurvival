package de.strawberry.spells.main;

import de.strawberry.spells.Spells.ISpell;
import de.strawberry.spells.Spells.SpellLibrary;
import de.strawberry.spells.config.SaveConfig;
import de.strawberry.spells.customcrafting.SpellCrafting;
import de.strawberry.spells.events.InteractEvent;
import de.strawberry.spells.events.JoinEvent;
import de.strawberry.spells.events.SlotEvent;
import de.strawberry.spells.items.SpellBookItem;
import de.strawberry.spells.items.WandItem;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.ArrayList;


public final class Main extends JavaPlugin{

    static Main plugin;
    static SaveConfig saveConfig;
    ItemStack basicWand;
    ItemStack basicSpellBook;
    WandItem wandItem;
    SpellBookItem spellBookItem;
    SpellLibrary spellLibrary;
    ArrayList<ISpell> spells = new ArrayList<ISpell>();
    ArrayList<ItemStack> spellBooks = new ArrayList<ItemStack>();

    @Override
    public void onEnable() {
        plugin = this;
        getWandItem().init();
        getSpellBookItem().init();
        getSpellLibrary().buildSpells();
        getServer().getPluginManager().registerEvents(new InteractEvent(), this);
        getServer().getPluginManager().registerEvents(new SpellBookItem(), this);
        getServer().getPluginManager().registerEvents(new SlotEvent(), this);
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new SpellCrafting(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getPlugin() {
        return plugin;
    }

    public static SaveConfig getSaveConfig() {
        if(saveConfig == null){
            saveConfig = new SaveConfig();
        }
        return saveConfig;
    }

    public ItemStack getBasicWand() {
        return basicWand;
    }

    public void setBasicWand(ItemStack basicWand) {
        this.basicWand = basicWand;
    }

    public ItemStack getBasicSpellBook() {
        return basicSpellBook;
    }

    public void setBasicSpellBook(ItemStack basicSpellBook) {
        this.basicSpellBook = basicSpellBook;
    }

    public WandItem getWandItem() {
        if(wandItem == null){
            wandItem = new WandItem();
        }
        return wandItem;
    }

    public SpellBookItem getSpellBookItem() {
        if(spellBookItem == null){
            spellBookItem = new SpellBookItem();
        }
        return spellBookItem;
    }

    public ArrayList<ISpell> getSpells() {
        return spells;
    }

    public SpellLibrary getSpellLibrary() {
        if(spellLibrary == null){
            spellLibrary = new SpellLibrary();
        }
        return spellLibrary;
    }

    public void setSpells(ArrayList<ISpell> spells) {
        this.spells = spells;
    }

    public ArrayList<ItemStack> getSpellBooks() {
        return spellBooks;
    }

    public void setSpellBooks(ArrayList<ItemStack> spellBooks) {
        this.spellBooks = spellBooks;
    }
}
