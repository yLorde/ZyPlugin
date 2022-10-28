package me.lord.plugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;

public class itemHead implements CommandExecutor {

    final Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("ZyPlugin");

    public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

        if (!(s instanceof Player)) {
            s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cApenas players podem usar esse comando!");
            return true;
        }

        Player p = (Player) s;
        PlayerInventory i = p.getInventory();
        ItemStack hand = p.getItemInHand();
        ItemStack helmet = i.getHelmet();

        if (hand != null && hand.getType().name().contains("HELMET") || hand.getType() != Material.AIR && hand.getType().getMaxDurability() == 0) {
            i.setHelmet(hand);
            i.setItemInHand(helmet);
            p.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + "§6" + i.getName() + " §aItem colocam como capacete");
        } else {
            p.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cItem inválido!");
        }
        return true;
    }

}
