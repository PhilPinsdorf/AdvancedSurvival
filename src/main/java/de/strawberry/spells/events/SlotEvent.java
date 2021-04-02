package de.strawberry.spells.events;

import de.strawberry.spells.scoreboards.MainScoreboard;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

public class SlotEvent implements Listener {

    @EventHandler
    public void onSlot(PlayerItemHeldEvent e){
        new MainScoreboard().updateScoreboard(e.getPlayer(), e.getNewSlot());
    }
}
