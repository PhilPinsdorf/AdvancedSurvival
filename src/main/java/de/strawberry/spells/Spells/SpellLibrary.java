package de.strawberry.spells.Spells;

import de.strawberry.spells.Spells.SpellMethods.FireRaySpell;
import de.strawberry.spells.Spells.SpellMethods.PuffOfAirSpell;
import de.strawberry.spells.Spells.SpellMethods.SafeWallSpell;
import de.strawberry.spells.Spells.SpellMethods.ThorsHammerSpell;
import de.strawberry.spells.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SpellLibrary {

    Main plugin = Main.getPlugin();
    ArrayList<ItemStack> spellBooks = new ArrayList<ItemStack>();
    HashMap<Integer, ItemStack> spellById = new HashMap<Integer, ItemStack>();
    HashMap<Integer, ISpell> spellById2 = new HashMap<Integer, ISpell>();
    HashMap<ItemStack, Integer> spellByItem = new HashMap<ItemStack, Integer>();
    HashMap<ItemStack, ISpell> iSpellByItem = new HashMap<ItemStack, ISpell>();
    ArrayList<ISpell> allSpells;

    public SpellLibrary() {
        allSpells = new ArrayList<ISpell>(
                Arrays.asList(
                    new FireRaySpell(),
                    new PuffOfAirSpell(),
                    new SafeWallSpell(),
                    new ThorsHammerSpell()
                ));
    }

    public void buildSpells(){
        for(ISpell spell : getSpells()){
            //Build Item
            ItemStack item = spell.getSpellClass().getHead().getItem();

            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(spell.getSpellClass().getChatColor() + spell.getName());
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "Class: " + spell.getSpellClass().getChatColor() + spell.getSpellClass().getClassName());
            lore.add(ChatColor.GRAY + spell.getDescription());
            meta.setLore(lore);
            meta.setUnbreakable(true);
            item.setItemMeta(meta);

            spellBooks.add(item);
            spellById.put(spell.getId(), item);
            spellById2.put(spell.getId(), spell);
            spellByItem.put(item, spell.getId());
            iSpellByItem.put(item, spell);

            //Build Recipe
            ShapedRecipe srS = new ShapedRecipe(new NamespacedKey(plugin, spell.getName().replace(" ", "_").replace("'", "")),item);
            srS.shape("iii",
                    "iXi",
                    "iii");
            srS.setIngredient('X', Material.BOOK);
            srS.setIngredient('i', spell.getMaterial());
            Bukkit.getServer().addRecipe(srS);
        }
        plugin.setSpellBooks(spellBooks);
        plugin.setSpells(allSpells);
    }

    public ArrayList<ISpell> getSpells(){
        return allSpells;
    }

    public HashMap<Integer, ItemStack> getSpellById() {
        return spellById;
    }

    public HashMap<ItemStack, Integer> getSpellByItem() {
        return spellByItem;
    }

    public HashMap<Integer, ISpell> getSpellById2() {
        return spellById2;
    }

    public HashMap<ItemStack, ISpell> getISpellByItem() {
        return iSpellByItem;
    }
}
