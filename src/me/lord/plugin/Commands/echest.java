package me.lord.plugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

public class echest implements CommandExecutor {
    final Plugin plugin = Bukkit.getPluginManager().getPlugin("ZyPlugin");
    public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

        if (!(s instanceof Player)) {
            s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + "§cApenas players podem usar esse comando!");
            return true;
        }

        Player sender = (Player) s;

        Inventory i = sender.getEnderChest();
        sender.openInventory(i);
        return true;
    }
}
