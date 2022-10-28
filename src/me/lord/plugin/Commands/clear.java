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

public class clear implements CommandExecutor {

    final Plugin plugin = Bukkit.getPluginManager().getPlugin("ZyPlugin");

    public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

        if (!(s instanceof Player)) {

            if (args.length != 1) {
                s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cCommando incorreto!");
                return true;
            }

            Player p = Bukkit.getPlayer(args[0]);
            if (p == null) {
                s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cPlayer offline!");
                return true;
            }

            if (inventoryIsEmpty(p)) {
                s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " &cO inventário de " + p.getName() + " já está vazio!");
                return true;
            }

            clearInventory(p);
            s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " &aO inventário de " + p.getName() + " foi limpo");
            return true;
        }

        if (args.length > 1) {
            s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cComando incorreto!");
            return true;
        }

        if (args.length == 1) {

            if (!s.hasPermission("zyplugin.clear.outros")) {
                s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cVocê não possui permissão");
                return true;
            }

            Player p = Bukkit.getPlayer(args[0]);
            if (p == null) {
                s.sendMessage("§cPlayer offline!");
                return true;
            }

            if (inventoryIsEmpty(p)) {
                s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cO inventário de " +  p.getName() + " já está vazio!");
                return true;
            }

            clearInventory(p);
            s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §aO inventário de " + p.getName() +  " foi limpo");
            return true;
        }

        Player p = (Player) s;
        if (inventoryIsEmpty(p)) {
            s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cSeu inventário já está vazio!");
            return true;
        }

        clearInventory(p);
        s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §aSeu inventário foi limpo");
        return true;
    }

    private boolean inventoryIsEmpty(Player p) {
        PlayerInventory inv = p.getInventory();

        for (ItemStack i : inv.getContents()) {
            if (i != null && i.getType() != Material.AIR)
                return false;
        }

        for (ItemStack i : inv.getArmorContents()) {
            if (i != null && i.getType() != Material.AIR)
                return false;
        }

        if (p.getItemOnCursor() != null && p.getItemOnCursor().getType() != Material.AIR)
            return false;

        return true;
    }

    private void clearInventory(Player p) {
        PlayerInventory inv = p.getInventory();
        p.setItemOnCursor(null);
        inv.clear();
        inv.setHelmet(null);
        inv.setChestplate(null);
        inv.setLeggings(null);
        inv.setBoots(null);
    }

}
