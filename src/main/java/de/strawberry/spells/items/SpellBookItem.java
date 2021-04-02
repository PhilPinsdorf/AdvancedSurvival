package de.strawberry.spells.items;

import de.strawberry.spells.Spells.ISpell;
import de.strawberry.spells.enums.Heads;
import de.strawberry.spells.main.Main;
import de.strawberry.spells.scoreboards.MainScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SpellBookItem implements Listener {

    Main plugin = Main.getPlugin();
    final String INVENTORY_NAME = ChatColor.DARK_PURPLE + "Basic Spell Book";
    ArrayList<ItemStack> spellsInBook = new ArrayList<ItemStack>();
    ItemStack item = Heads.SPELL_BOOK_LIBRARY.getItem();

    public void init(){
        spellsInBook.add(0, new ItemStack(Material.DIRT, 1));
        spellsInBook.add(1, new ItemStack(Material.DIRT, 1));
        spellsInBook.add(2, new ItemStack(Material.DIRT, 1));
        createBook();
        setRecipe();
    }

    private void createBook(){
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Basic Spell Book");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "This Artifact can Store 3");
        lore.add(ChatColor.GRAY + "of your most Powerful Spells!");
        meta.setLore(lore);
        item.setItemMeta(meta);
        plugin.setBasicSpellBook(item);
    }

    private void setRecipe(){
        //SpellBook
        ShapedRecipe srB = new ShapedRecipe(new NamespacedKey(plugin, "Basic_Spell_Book"),plugin.getBasicSpellBook());
        srB.shape("bab",
                  "aca",
                  "bab");
        srB.setIngredient('a', Material.OBSIDIAN);
        srB.setIngredient('b', Material.BLAZE_POWDER);
        srB.setIngredient('c', Material.BOOK);
        Bukkit.getServer().addRecipe(srB);
    }

    private ItemStack getClosedSlot(){
        ItemStack cs = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
        ItemMeta meta = cs.getItemMeta();
        meta.setDisplayName(" ");
        cs.setItemMeta(meta);
        return cs;
    }

    public void openInventory(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9, INVENTORY_NAME);;
        inv.setItem(0, getClosedSlot());
        inv.setItem(1, getClosedSlot());
        inv.setItem(2, getClosedSlot());

        if(Main.getSaveConfig().getIntFile("plugins/Spells/spellBook.yml", p.getName() + ".1") != -1) {
            inv.setItem(3, plugin.getSpellLibrary().getSpellById().get(Main.getSaveConfig().getIntFile("plugins/Spells/spellBook.yml", p.getName() + ".1")));
        }
        if(Main.getSaveConfig().getIntFile("plugins/Spells/spellBook.yml", p.getName() + ".2") != -1) {
            inv.setItem(4, plugin.getSpellLibrary().getSpellById().get(Main.getSaveConfig().getIntFile("plugins/Spells/spellBook.yml", p.getName() + ".2")));
        }
        if(Main.getSaveConfig().getIntFile("plugins/Spells/spellBook.yml", p.getName() + ".3") != -1) {
            inv.setItem(5, plugin.getSpellLibrary().getSpellById().get(Main.getSaveConfig().getIntFile("plugins/Spells/spellBook.yml", p.getName() + ".3")));
        }

        inv.setItem(6, getClosedSlot());
        inv.setItem(7, getClosedSlot());
        inv.setItem(8, getClosedSlot());
        p.openInventory(inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(e.getView().getTitle().equals(INVENTORY_NAME)) {
            if(e.getClickedInventory() != null) {
                if (e.getClickedInventory().getType() != InventoryType.PLAYER) {
                    if(e.getCurrentItem() != null) {
                        if (e.getCurrentItem().getType() == Material.BLACK_STAINED_GLASS_PANE) {
                            e.setCancelled(true);
                            return;
                        }
                    }
                }
            }
            if (e.getCurrentItem() != null) {
                if (e.getCurrentItem().getType() == Material.PLAYER_HEAD) {
                    for (ItemStack h : plugin.getSpellBooks()) {
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equals(h.getItemMeta().getDisplayName())) {
                            return;
                        }
                    }
                    e.setCancelled(true);
                    return;
                }
            }
            if (e.getCursor() != null) {
                if (e.getCursor().getType() == Material.PLAYER_HEAD) {
                    for (ItemStack h : plugin.getSpellBooks()) {
                        if (e.getCursor().getItemMeta().getDisplayName().equals(h.getItemMeta().getDisplayName())) {
                            return;
                        }
                    }
                    e.setCancelled(true);
                    return;
                }
            }
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e){
        if(e.getView().getTitle().equalsIgnoreCase(INVENTORY_NAME)) {
            ItemStack[] allItems = e.getInventory().getContents();

            if (allItems[3] != null) {
                Bukkit.broadcastMessage("Ja 1");
                for (ItemStack item : plugin.getSpellBooks()) {
                    if (allItems[3].getItemMeta() != null) {
                        if (item.getItemMeta().getDisplayName().equals(allItems[3].getItemMeta().getDisplayName())) {
                            Main.getSaveConfig().saveFile("plugins/Spells/spellBook.yml", e.getPlayer().getName() + ".1", plugin.getSpellLibrary().getSpellByItem().get(item));
                            break;
                        }
                    }
                }
            } else {
                Main.getSaveConfig().saveFile("plugins/Spells/spellBook.yml", e.getPlayer().getName() + ".1", -1);
            }

            if (allItems[4] != null) {
                Bukkit.broadcastMessage("Ja 2");
                for (ItemStack item : plugin.getSpellBooks()) {
                    if (allItems[4].getItemMeta() != null) {
                        if (item.getItemMeta().getDisplayName().equals(allItems[4].getItemMeta().getDisplayName())) {
                            Main.getSaveConfig().saveFile("plugins/Spells/spellBook.yml", e.getPlayer().getName() + ".2", plugin.getSpellLibrary().getSpellByItem().get(item));
                            break;
                        }
                    }
                }
            } else {
                Main.getSaveConfig().saveFile("plugins/Spells/spellBook.yml", e.getPlayer().getName() + ".2", -1);
            }

            if (allItems[5] != null) {
                Bukkit.broadcastMessage("Ja 3");
                for (ItemStack item : plugin.getSpellBooks()) {
                    if (allItems[5].getItemMeta() != null) {
                        if (item.getItemMeta().getDisplayName().equals(allItems[5].getItemMeta().getDisplayName())) {
                            Main.getSaveConfig().saveFile("plugins/Spells/spellBook.yml", e.getPlayer().getName() + ".3", plugin.getSpellLibrary().getSpellByItem().get(item));
                            break;
                        }
                    }
                }
            } else {
                Main.getSaveConfig().saveFile("plugins/Spells/spellBook.yml", e.getPlayer().getName() + ".3", -1);
            }
        }
    }
}
