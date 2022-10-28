package me.lord.plugin.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;

public class Utils {

    private static final BlockFace[] BLOCK_FACES;

    static {
        BLOCK_FACES = new BlockFace[]{BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST};
    }

    public static BlockFace getBlockFace(Player player) {
        return BLOCK_FACES[Math.round(player.getEyeLocation().getYaw() / 90.0F) & 3];
    }

    public static Class<?> getNMSClass(String clazz) {
        try {
            return Class.forName("net.minecraft.server." + getServerVersion() + "." + clazz);
        } catch (ClassNotFoundException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static boolean isPermissionDefault(String permissionDefault) {
        return PermissionDefault.valueOf(permissionDefault.toUpperCase()) != null;
    }

    public static String getServerVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    }

    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
