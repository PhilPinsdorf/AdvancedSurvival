package de.strawberry.spells.events;

import de.strawberry.spells.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractEvent implements Listener {

    Main plugin = Main.getPlugin();

    @EventHandler
    private void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(!(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;

        if(p.getInventory().getItemInMainHand().isSimilar(plugin.getBasicWand())) {
            plugin.getSpellLibrary().getSpellById2().get(Main.getSaveConfig().getIntFile("plugins/Spells/spellBook.yml", p.getName() + ".1")).execute(p);
        }

        if(e.getPlayer().getInventory().getItemInMainHand().getType().equals(plugin.getBasicSpellBook().getType())) {
            if(e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(plugin.getBasicSpellBook().getItemMeta().getDisplayName())) {
                plugin.getSpellBookItem().openInventory(e.getPlayer());
            }
        }
    }
}
