package me.lord.plugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class comer implements CommandExecutor {
    final Plugin plugin = Bukkit.getPluginManager().getPlugin("ZyPlugin");

    public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

        if (!(s instanceof Player)) {
            s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cApenas players podem usar esse comando!");
            return true;
        }

        Player p = (Player) s;
        if (p.getFoodLevel() >= 20) {
            p.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cSeu nível de fome já está no máximo!");
            return true;
        }

        p.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §aSua fome foi regenerada");
        p.setFoodLevel(20);
        p.setSaturation(20);
        return true;

    }

}
