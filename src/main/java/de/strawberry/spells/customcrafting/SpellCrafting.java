package de.strawberry.spells.customcrafting;

import de.strawberry.spells.main.Main;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;

public class SpellCrafting implements Listener {
    Main plugin = Main.getPlugin();
    private final ArrayList<Integer> CLOSED_SLOTS = new ArrayList<Integer>(Arrays.asList(0,1,5,6,7,8,9,11,12,13,15,16,17,18,20,22,24,26,27,29,30,31,33,34,35,36,37,41,42,43,44));
    private final ArrayList<Integer> MATERIAL_SLOTS = new ArrayList<Integer>(Arrays.asList(2,3,4,10,14,19,23,28,32,38,39,40));
    private final ArrayList<Integer> UNDEFINED_SLOTS = new ArrayList<Integer>(Arrays.asList(45,46,47,48,50,51,52,53));
    private final int BOOK_SLOT = 21;
    private final int FINAL_SLOT = 25;
    private final int CLOSE_SLOT = 49;
    private final Material CLOSED_SLOT_MATERIAL = Material.BLACK_STAINED_GLASS_PANE;
    private final Material FINAL_SLOT_MATERIAL = Material.YELLOW_STAINED_GLASS_PANE;
    private final Material CLOSE_SLOT_MATERIAL = Material.BARRIER;
    private final Material UNDEFINED_SLOT_MATERIAL = Material.RED_STAINED_GLASS_PANE;
    private final String INVENTORY_NAME = ChatColor.DARK_PURPLE + "Spell Crafting Station";

    @EventHandler
    private void onInteract(PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(e.getClickedBlock().getType() == Material.ENCHANTING_TABLE){
                if(structureExists(e.getClickedBlock().getLocation())) {
                    openCraftingInventory(e.getPlayer());
                }
            }
        }
    }

    @EventHandler
    private void onClick(InventoryClickEvent e){
        if(e.getView().getTitle().equals(INVENTORY_NAME)){
            Player p = (Player) e.getWhoClicked();
            if(e.getClickedInventory() == null) return;
            if(e.getClickedInventory().getType() != InventoryType.PLAYER) {
                if(e.getCurrentItem() != null) {
                    if (e.getCurrentItem().getType() == Material.BLACK_STAINED_GLASS_PANE || e.getCurrentItem().getType() == Material.YELLOW_STAINED_GLASS_PANE || e.getCurrentItem().getType() == Material.RED_STAINED_GLASS_PANE) {
                        e.setCancelled(true);
                        return;
                    }
                    if(e.getCurrentItem().getType() == Material.BARRIER){
                        e.setCancelled(true);
                        p.closeInventory();
                    }
                }
                if(e.getSlot() == FINAL_SLOT){
                    if(e.getInventory().getContents()[e.getSlot()].getType() != Material.YELLOW_STAINED_GLASS_PANE) {
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                e.getClickedInventory().getItem(BOOK_SLOT).setAmount(e.getClickedInventory().getItem(BOOK_SLOT).getAmount() - 1);
                                for (int i : MATERIAL_SLOTS) {
                                    e.getClickedInventory().getItem(i).setAmount(e.getClickedInventory().getItem(i).getAmount() - 1);
                                }
                                resetFinalSlot(e.getClickedInventory());
                                p.updateInventory();
                            }
                        }.runTaskLater(plugin, 1);
                    } else {
                        e.setCancelled(true);
                        return;
                    }
                }
                new BukkitRunnable() {
                    @Override
                    public void run () {
                        ItemStack[] allItems = e.getInventory().getContents();
                        if(allItems[BOOK_SLOT] == null) {resetFinalSlot(e.getClickedInventory()); return;}
                        if(allItems[BOOK_SLOT].getType() != Material.BOOK) {resetFinalSlot(e.getClickedInventory()); return;}
                        if(allItems[MATERIAL_SLOTS.get(0)] == null) {resetFinalSlot(e.getClickedInventory()); return;}
                        Material mustHave = allItems[MATERIAL_SLOTS.get(0)].getType();
                        for (int i : MATERIAL_SLOTS){
                            allItems = e.getInventory().getContents();
                            if(allItems[i] == null) {resetFinalSlot(e.getClickedInventory()); return;}
                            if(allItems[i].getType() != mustHave) {resetFinalSlot(e.getClickedInventory()); return;}
                        }
                        e.getClickedInventory().setItem(FINAL_SLOT, new ItemStack(Material.DIRT, 1));
                    }
                }.runTaskLater(plugin, 1);
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e){
        if(e.getView().getTitle().equals(INVENTORY_NAME)) {
            Player p = (Player) e.getPlayer();
            for(int i : MATERIAL_SLOTS){
                if(e.getInventory().getItem(i) != null) {
                    p.getWorld().dropItem(p.getLocation().add(0,1,0), e.getInventory().getItem(i));
                }
            }
            if(e.getInventory().getItem(BOOK_SLOT) != null) {
                p.getWorld().dropItem(p.getLocation().add(0,1,0), e.getInventory().getItem(BOOK_SLOT));
            }
        }
    }

    private void resetFinalSlot(Inventory inv){
        new BukkitRunnable(){
            @Override
            public void run(){
                ItemStack i3 = new ItemStack(FINAL_SLOT_MATERIAL, 1);
                ItemMeta m3 = i3.getItemMeta();
                m3.setDisplayName(ChatColor.YELLOW + "Final Book");
                i3.setItemMeta(m3);
                inv.setItem(FINAL_SLOT, i3);
            }
        }.runTaskLater(plugin,1);
    }

    private void openCraftingInventory(Player p){
        new BukkitRunnable() {
            @Override
            public void run () {
                p.getOpenInventory().close();
            }
        }.runTaskLater(plugin, 1);
        new BukkitRunnable() {
            @Override
            public void run () {
                Inventory inv = Bukkit.createInventory(null, 9*6, INVENTORY_NAME);

                ItemStack i1 = new ItemStack(CLOSED_SLOT_MATERIAL, 1);
                ItemMeta m1 = i1.getItemMeta();
                m1.setDisplayName(" ");
                i1.setItemMeta(m1);
                for(int i : CLOSED_SLOTS) {
                    inv.setItem(i, i1);
                }

                ItemStack i2 = new ItemStack(UNDEFINED_SLOT_MATERIAL, 1);
                ItemMeta m2 = i2.getItemMeta();
                m2.setDisplayName(" ");
                i2.setItemMeta(m2);
                for(int i : UNDEFINED_SLOTS) {
                    inv.setItem(i, i2);
                }

                ItemStack i3 = new ItemStack(FINAL_SLOT_MATERIAL, 1);
                ItemMeta m3 = i3.getItemMeta();
                m3.setDisplayName(ChatColor.YELLOW + "Final Book");
                i3.setItemMeta(m3);
                inv.setItem(FINAL_SLOT, i3);

                ItemStack i4 = new ItemStack(CLOSE_SLOT_MATERIAL, 1);
                ItemMeta m4 = i4.getItemMeta();
                m4.setDisplayName(ChatColor.RED + "Close");
                i4.setItemMeta(m4);
                inv.setItem(CLOSE_SLOT, i4);

                p.openInventory(inv);
            }
        }.runTaskLater(plugin, 1);
    }

    private boolean structureExists(Location loc){
        loc.subtract(0, 1,0);
        if(checkBlock(loc, Material.REDSTONE_BLOCK)) return false;
        for(int x = -1; x < 2; x++){
            if(x != 0) {
                if(checkBlock(loc.add(x, 0, 0), Material.NETHER_BRICK_STAIRS)) return false;
                loc.subtract(x, 0, 0);
            }
        }
        for(int z = -1; z < 2; z ++){
            if(z != 0) {
                if(checkBlock(loc.add(0, 0, z), Material.NETHER_BRICK_STAIRS)) return false;
                loc.subtract(0, 0, z);
            }
        }
        for(int x = -3; x < 4; x++){
            for (int z = -3; z < 4; z++){
                if(x == 3 || x ==-3 || z ==3 || z ==-3){
                    if(checkBlock(loc.add(x, -1, z), Material.QUARTZ_STAIRS)) return false;
                }else {
                    if(checkBlock(loc.add(x, -1, z), Material.DARK_OAK_PLANKS)) return false;
                }
                loc.subtract(x, -1, z);
            }
        }
        loc.add(2,0,2);
        for(int y = 0; y < 3; y++){
            for(int x = -4; x < 4; x +=4){
                for(int z = -4; z < 4; z +=4){
                    if(y < 2){
                        if(checkBlock(loc.add(x,y,z), Material.QUARTZ_PILLAR)) return false;
                    } else {
                        if(checkBlock(loc.add(x,y,z), Material.SEA_LANTERN)) return false;
                    }
                    loc.subtract(x, y, z);
                }
            }
        }
        return true;
    }

    private boolean checkBlock(Location loc, Material mat){
        return loc.getBlock().getType() != mat;
    }
}
