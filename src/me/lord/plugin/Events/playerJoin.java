package me.lord.plugin.Events;

import me.lord.plugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class playerJoin implements Listener {

    final Plugin plugin = Bukkit.getPluginManager().getPlugin("ZyPlugin");

    @EventHandler
    public void onJoin(PlayerJoinEvent p) {
        if (plugin.getConfig().getBoolean("join-broadcast")) {
            if (p.getPlayer().hasPermission(plugin.getConfig().getString("enter-message-permission"))) {
                String msg = plugin.getConfig().getString("enter-message-content").replace("&", "§").replace("%player%", p.getPlayer().getDisplayName());
                Bukkit.broadcastMessage(msg);
            }
        }
        if (plugin.getConfig().getBoolean("join-and-leave-message") == false) {
            p.setJoinMessage("");
        }
    }

}
