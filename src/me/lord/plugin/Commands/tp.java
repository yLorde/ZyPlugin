package me.lord.plugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.Plugin;

public class tp implements CommandExecutor {
    final Plugin plugin = Bukkit.getPluginManager().getPlugin("ZyPlugin");

    public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

        if (args.length < 1 || args.length > 5) {
            s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cVerifique se o comando foi usado corretamente");
            return true;
        }

        if (args.length == 1) {

            if (!(s instanceof Player)) {
                s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cApenas players podem usar esse comando!");
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cEsse player está offline!");
                return true;
            }

            if (target.getName().equals(s.getName())) {
                s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cEsse player é você mesmo!");
                return true;
            }

            Player p = (Player) s;
            p.teleport(target, PlayerTeleportEvent.TeleportCause.COMMAND);
            p.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §aTeleportado com sucesso para " + target.getName());
            return true;
        }

        if (args.length == 2) {

            if (s.getName().equals(args[0]) && s.getName().equals(args[1])) {
                s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cEsse player é você mesmo!");
                return true;
            }

            Player player = Bukkit.getPlayer(args[0]);
            if (player == null) {
                s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cPlayer offline");
                return true;
            }

            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cPlayer 2 offline");
                return true;
            }

            if (args[0].equals(args[1])) {
                s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cVocê não pode telepotar para si mesmo!");
                return true;
            }

            player.teleport(target, PlayerTeleportEvent.TeleportCause.COMMAND);
            player.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §a" + target.getName() + " puxado com sucesso para sua localização");
            s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " &aVocê teleportou " + player.getName() + " até a localização de " + target.getName());
            return true;
        }

        if (args.length == 3) {

            if (!(s instanceof Player)) {
                s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cApenas players podem usar esse comando!");
                return true;
            }

            double x, y, z;
            try {
                x = Double.parseDouble(args[0]);
                y = Double.parseDouble(args[1]);
                z = Double.parseDouble(args[2]);
            } catch (NumberFormatException e) {
                s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cCoordernada inválida");
                return true;
            }

            Player p = (Player) s;
            Location l = new Location(p.getWorld(), x, y, z);
            p.teleport(l, PlayerTeleportEvent.TeleportCause.COMMAND);
            p.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §aTeleportado com sucesso para a coordenada");
            return true;
        }

        if (args.length == 4) {

            if (!(s instanceof Player)) {
                s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cApenas players podem usar esse comando!");
                return true;
            }

            World w = Bukkit.getWorld(args[0]);
            if (w == null) {
                s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cEsse mundo não existe!");
                return true;
            }

            double x, y, z;
            try {
                x = Double.parseDouble(args[1]);
                y = Double.parseDouble(args[2]);
                z = Double.parseDouble(args[3]);
            } catch (NumberFormatException e) {
                s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cNúmero inválido!");
                return true;
            }

            Player p = (Player) s;
            Location l = new Location(w, x, y, z);
            p.teleport(l, PlayerTeleportEvent.TeleportCause.COMMAND);
            p.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §aTeleportado com sucesso para a coordenada");
            return true;
        }

        if (args.length == 5) {
            World w = Bukkit.getWorld(args[0]);
            if (w == null) {
                s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cEsse mundo não existe!");
                return true;
            }

            double x, y, z;
            try {
                x = Double.parseDouble(args[1]);
                y = Double.parseDouble(args[2]);
                z = Double.parseDouble(args[3]);
            } catch (NumberFormatException e) {
                s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cNúmero inválido!");
                return true;
            }

            Player p = Bukkit.getPlayer(args[4]);
            if (p == null) {
                s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cPlayer offline!");
                return true;
            }

            Location l = new Location(w, x, y, z);
            p.teleport(l, PlayerTeleportEvent.TeleportCause.COMMAND);
            s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §aVocê teleportou " + p.getName() + " Até a coordenada");
            return true;
        }
        return true;
    }

}
