package de.strawberry.spells.events;

import de.strawberry.spells.scoreboards.MainScoreboard;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        new MainScoreboard().updateScoreboard(p, p.getInventory().getHeldItemSlot());
    }
}
