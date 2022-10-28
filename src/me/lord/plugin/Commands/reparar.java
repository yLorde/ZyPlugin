package me.lord.plugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class reparar implements CommandExecutor {

    final Plugin plugin = Bukkit.getPluginManager().getPlugin("ZyPlugin");

    public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

        if (!(s instanceof Player)) {
            s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cApenas players podem usar esse comando");
            return true;
        }

        if (args.length > 1) {
            s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cComando incorreto, use: /reparar [tudo/mao]");
            return true;
        }

        Player p = (Player) s;

        if (args.length == 1) {

            if (!args[0].equalsIgnoreCase("tudo") && !args[0].equalsIgnoreCase("mao")) {
                s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cUse: /reparar [tudo/mao]");
                return true;
            }

            if (args[0].equalsIgnoreCase("tudo")) {

                if (!s.hasPermission("zyplugin.reparar.tudo")) {
                    s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cSem permissão parar reparar tudo §ezyplugin.reparar.tudo");
                    return true;
                }

                boolean reparado = false;
                for (ItemStack i : p.getInventory().getContents()) {
                    if (i != null && i.getType().getMaxDurability() != 0 && i.getDurability() != 0) {
                        i.setDurability((short)0);
                        reparado = true;
                    }
                }
                for (ItemStack i : p.getInventory().getArmorContents()) {
                    if (i != null && i.getType().getMaxDurability() != 0 && i.getDurability() != 0) {
                        i.setDurability((short)0);
                        reparado = true;
                    }
                }
                if (reparado) {
                    s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §aTudo que está no seu inventário foi reparado!");
                    p.updateInventory();
                    return true;
                } else {
                    s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cNão há nara para reparar");
                    return true;
                }
            }
        }

        ItemStack hand = p.getItemInHand();
        if (hand == null || hand.getType().getMaxDurability() == 0) {
            s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cItem inválido!");
            return true;
        }

        if (hand.getDurability() == 0) {
            s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cEsse item já está reparado!");
            return true;
        }

        hand.setDurability((short)0);
        s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §aItem reparado");
        return true;
    }

}
