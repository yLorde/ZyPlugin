package me.lord.plugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

public class lixeira implements CommandExecutor {

    final Plugin plugin = Bukkit.getPluginManager().getPlugin("ZyPlugin");

    public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

        if (!(s instanceof Player)) {
            s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cApenas players podem usar esse comando!");
            return true;
        }

        Player p = (Player) s;
        Inventory inv = Bukkit.createInventory(p, 36, "§0Lixeira");
        s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §aLixeira virtual aberta.");
        p.openInventory(inv);
        return true;

    }

}
