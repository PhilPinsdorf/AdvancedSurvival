package de.strawberry.spells.scoreboards;

import de.strawberry.spells.Spells.ISpell;
import de.strawberry.spells.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.*;

public class MainScoreboard {

    Main plugin = Main.getPlugin();

    public void updateScoreboard(Player p, int i){
        ItemStack[] inv = p.getInventory().getContents();
        if(inv[i] != null) {
            if (inv[i].isSimilar(plugin.getBasicWand())) {
                ScoreboardManager manager = Bukkit.getScoreboardManager();
                Scoreboard board = manager.getNewScoreboard();
                Objective objective = board.registerNewObjective("dummy", "dummy", inv[i].getItemMeta().getDisplayName());

                objective.setDisplaySlot(DisplaySlot.SIDEBAR);

                Score score1 = objective.getScore(ChatColor.DARK_GRAY + "Equipped Spell:");
                score1.setScore(5);

                Score score2;
                int spellId = Main.getSaveConfig().getIntFile("plugins/Spells/spellBook.yml", p.getName() + ".1");
                if(spellId != -1 && spellId != 0) {
                    ISpell spell = plugin.getSpellLibrary().getSpellById2().get(spellId);
                    score2 = objective.getScore(spell.getSpellClass().getChatColor() + spell.getName());
                } else {
                    score2 = objective.getScore(ChatColor.GRAY + "Kein Spell ausgewählt!");
                }
                score2.setScore(4);

                Score score3 = objective.getScore(ChatColor.DARK_GRAY + "  ");
                score3.setScore(3);

                Score score4 = objective.getScore(ChatColor.DARK_GRAY + "Mana:");
                score4.setScore(2);

                Score score5 = objective.getScore(ChatColor.AQUA + "500");
                score5.setScore(1);

                p.setScoreboard(board);
                return;
            }
        }
        p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

    }
}
