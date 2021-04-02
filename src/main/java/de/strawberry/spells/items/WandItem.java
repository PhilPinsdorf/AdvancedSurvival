package de.strawberry.spells.items;

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

import java.util.ArrayList;
import java.util.List;

public class WandItem {

    Main plugin = Main.getPlugin();

    public void init(){
        createWand();
        setRecipe();
    }

    private void createWand(){
        ItemStack item = new ItemStack(Material.BLAZE_ROD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Basic Wand");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.AQUA + "This powerful artifact is a relic of");
        lore.add(ChatColor.AQUA + "Minecraft's ancient history!");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        plugin.setBasicWand(item);
    }

    private void setRecipe(){
        //BasicWand
        ShapedRecipe srW = new ShapedRecipe(new NamespacedKey(plugin,"Basic_Wand"),plugin.getBasicWand());
        srW.shape(" ab",
                  " ca",
                  "d  ");
        srW.setIngredient('a', Material.GHAST_TEAR);
        srW.setIngredient('b', Material.EMERALD);
        srW.setIngredient('c', Material.BLAZE_ROD);
        srW.setIngredient('d', Material.GOLD_NUGGET);
        Bukkit.getServer().addRecipe(srW);
    }
}
