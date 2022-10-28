package me.lord.plugin.Events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class playerQuit implements Listener {

    final Plugin plugin = Bukkit.getPluginManager().getPlugin("ZyPlugin");

    @EventHandler
    public void onLeave(PlayerQuitEvent p) {
        if (plugin.getConfig().getBoolean("join-and-leave-message") == false) {
            p.setQuitMessage("");
        }
    }
}
