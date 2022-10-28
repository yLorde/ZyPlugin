package me.lord.plugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class voar implements CommandExecutor {

    final Plugin plugin = Bukkit.getPluginManager().getPlugin("ZyPlugin");

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

        if (!(s instanceof Player)) {

            if (args.length > 2 || args.length < 1) {
                s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cComando incorreto");
                return true;
            }

            Player p = Bukkit.getPlayer(args[0]);
            if (p == null) {
                s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cPlayer offline!");
                return true;
            }

            if (args.length > 1) {

                if (args[1].equalsIgnoreCase("ativar") || args[1].equalsIgnoreCase("on")) {

                    if (p.getAllowFlight()) {
                        s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cO modo voar de " + p.getName() + " já está ativo!");
                    } else {
                        s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §aO modo voar de " + p.getName() + " foi ativado");
                        p.setAllowFlight(true);
                    }
                    return true;
                }

                if (args[1].equalsIgnoreCase("desativar") || args[1].equalsIgnoreCase("off")) {

                    if (p.getAllowFlight()) {
                        s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §aO modo voar de " + p.getName() + " foi desativado");
                        p.setFlying(false);
                        p.setAllowFlight(false);
                    } else {
                        s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cO modo voar de " + p.getName() + " já está desativado!");
                    }
                    return true;
                }
            }

            if (p.getAllowFlight()) {
                s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §aO modo voar de " + p.getName() + " foi desativado");
                p.setFlying(false);
                p.setAllowFlight(false);
            } else {
                s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §aO modo voar de " + p.getName() + " foi ativado");
                p.setAllowFlight(true);
            }
            return true;
        }

        if (args.length > 0) {

            if (args[0].equalsIgnoreCase("ativar") || args[0].equalsIgnoreCase("on")) {
                Player p = (Player) s;
                if (p.getAllowFlight()) {
                    s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cSeu modo voar já está ativado!");
                } else {
                    s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §aSeu modo voar foi ativado");
                    p.setAllowFlight(true);
                }
                return true;
            }

            if (args[0].equalsIgnoreCase("desativar") || args[0].equalsIgnoreCase("off")) {
                Player p = (Player) s;
                if (p.getAllowFlight()) {
                    s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §aSeu modo voar foi desativado");
                    p.setFlying(false);
                    p.setAllowFlight(false);
                } else {
                    s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cSeu modo voar já está desativado!");
                }
                return true;
            }

            if (s.hasPermission(plugin.getConfig().getString("name").replace("&", "§") + " zyplugin.fly.outros")) {

                if (args.length > 2) {
                    s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cComando incorreto!");
                    return true;
                }

                Player p = Bukkit.getPlayer(args[0]);
                if (p == null) {
                    s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cPlayer offline!");
                    return true;
                }

                if (args.length > 1) {

                    if (args[1].equalsIgnoreCase("on")) {

                        if (p.getAllowFlight()) {
                            s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cO modo voar de " + p.getName() + " já está ativado!");
                        } else {
                            s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §aModo voar de " + p.getName() + " ativado");
                            p.setAllowFlight(true);
                        }
                        return true;
                    }

                    if (args[1].equalsIgnoreCase("off")) {

                        if (p.getAllowFlight()) {
                            s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §aModo voar de " + p.getName() + " desativado");
                            p.setFlying(false);
                            p.setAllowFlight(false);
                        } else {
                            s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §cO modo voar de " + p.getName() + " já está desativado!");
                        }
                        return true;
                    }

                    if (p.getAllowFlight()) {
                        s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " &aModo voar de " + p.getName() + " foi desativado");
                        p.setFlying(false);
                        p.setAllowFlight(false);
                    } else {
                        s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §aModo voar de " + p.getName() + " foi ativado");
                        p.setAllowFlight(true);
                    }
                    return true;
                }

                if (p.getAllowFlight()) {
                    s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §aModo voar de " + p.getName() + " foi desativado");
                    p.setFlying(false);
                    p.setAllowFlight(false);
                } else {
                    s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §aModo voar de " + p.getName() + " foi ativado");
                    p.setAllowFlight(true);
                }
                return true;
            }

        }

        Player p = (Player) s;
        if (p.getAllowFlight()) {
            s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §aSeu modo voar foi desativado");
            p.setFlying(false);
            p.setAllowFlight(false);
        } else {
            s.sendMessage(plugin.getConfig().getString("name").replace("&", "§") + " §aSeu modo voar foi ativado");
            p.setAllowFlight(true);
        }
        return true;
    }

}
